/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Administrador;
import com.ufpr.tads.tcc.beans.Compra;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Ingresso;
import com.ufpr.tads.tcc.beans.ItemCompra;
import com.ufpr.tads.tcc.beans.Lote;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.AdministradorFacade;
import com.ufpr.tads.tcc.facade.CompraFacade;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.IngressoFacade;
import com.ufpr.tads.tcc.facade.LoteFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateus
 */
@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("action");
        if (acao == null || acao.equals("buy") ) {
            String USER_AGENT = "Mozilla/5.0";
            String POST_URL = "https://ws.sandbox.pagseguro.uol.com.br/v2/checkout?email=mateus.picoloto97@gmail.com&token=33554465089A4C17B034CEA18A4A3F30";

            String POST_PARAMS = "currency=BRL&item";


            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Compra carrinho = CompraFacade.buscarCarrinho(id);
                Usuario usuario = UsuarioFacade.buscar(carrinho.getIdUsuario());

                switch (usuario.getTipo()) {
                    case "a" :
                        Administrador admin = AdministradorFacade.buscar(usuario.getIdReferencia());
                        POST_PARAMS += "&senderName=" + admin.getNome() + " " + admin.getSobrenome()
                                //+ "&senderEmail=" + usuario.getEmail()
                                + "&senderCPF=" + admin.getCpf();
                        break;
                    case "c" :
                        Comprador comp = CompradorFacade.buscar(usuario.getIdReferencia());
                        POST_PARAMS += "&senderName=" + comp.getNome() + " " + comp.getSobrenome()
                                //+ "&senderEmail=" + usuario.getEmail()
                                + "&senderCPF=" + comp.getCpf();
                        break;
                }

                int i = 1;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                for (ItemCompra item : carrinho.getItems() ) {
                    Lote lote = LoteFacade.buscar(item.getIdLote());
                    for ( int j = 0; j < item.getQuantidade(); ++j ) {
                        Ingresso ingresso = new Ingresso();
                        String serial = generateSerial();
                        boolean existente = IngressoFacade.buscarPorSerialIdEvento(serial, lote.getEvento().getId());
                        while ( existente ) {
                            serial = generateSerial();
                            existente = IngressoFacade.buscarPorSerialIdEvento(serial, lote.getEvento().getId());
                        }
                        ingresso.setLote(lote);
                        ingresso.setSerial(serial);
                        ingresso.setUsuario(usuario);
                        ingresso.setAcesso(false);
                        IngressoFacade.inserir(ingresso);
                    }
                    POST_PARAMS += "&itemId" + i + "=" + i
                            + "&itemDescription" + i + "=" + lote.getNome()
                            + "&itemAmount" + i + "=" + String.format("%.2f", lote.getPreço()).replace(",", ".")
                            + "&itemQuantity" + i + "=" + item.getQuantidade()
                            + "&itemWeight" + i + "=0";
                }

                POST_PARAMS += "&shippingAddressRequired=false";

                URL obj = new URL(POST_URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);

                // For POST only - START
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(POST_PARAMS.getBytes());
                os.flush();
                os.close();
                // For POST only - END

                int responseCode = con.getResponseCode();
                System.out.println("POST Response Code :: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                        BufferedReader in = new BufferedReader(new InputStreamReader(
                                        con.getInputStream()));
                        String inputLine;
                        StringBuffer resp = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                                resp.append(inputLine);
                        }
                        in.close();

                        int start = resp.indexOf("<code>");
                        int end = resp.indexOf("</code>");

                        String code = resp.substring(start + 6, end);
                        response.sendRedirect("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=" + code);
                } else {
                        System.out.println("POST request not worked");
                }
            } catch(SQLException | IOException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
        } else if ( acao.equals("response") ) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/EventoServlet?action=list");
            rd.forward(request, response);
        }
        
    }
    
    private String generateSerial() {
        // GERA O SERIAL
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 

        StringBuilder sb = new StringBuilder(10); 

        for (int l = 0; l < 10; l++) { 

            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 

            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 

        return sb.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
