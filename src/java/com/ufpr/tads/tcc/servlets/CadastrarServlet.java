/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Cidade;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Endereço;
import com.ufpr.tads.tcc.beans.Estado;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CadastrarServlet", urlPatterns = {"/CadastrarServlet"})
public class CadastrarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String acao = request.getParameter("action");

        //Comprador us = (Comprador) session.getAttribute("usuario");
        if (acao == null || acao.equals("list")) {
            List<Comprador> compradores;
            try {
                compradores = CompradorFacade.buscarTodosCompradores();
                request.setAttribute("compradores", compradores);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("formNew")) {
                try {
                    List<Estado> estados = new ArrayList<>();
                    estados = EstadoFacade.buscarTodosEstados();
                    request.setAttribute("estados", estados);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("exception", ex);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                rd.forward(request, response);
            } else {
                if (acao.equals("new")) {
                    Comprador comprador = new Comprador();
                    try {
                        String email = request.getParameter("email");

                        Comprador us = (Comprador) UsuarioFacade.buscarUsuarioByEmail(email);
                        try {
                            if (us != null && us.getId() != comprador.getId()) {
                                throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                            }
                        } catch (EmailDuplicadoException ex) {
                            request.setAttribute("msg", ex.getMessage());

                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CadastrarServlet?action=list");
                            rd.forward(request, response);
                        }

                        comprador.setEmail(email);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    String cpf;
                    String rg;
                    comprador.setNome(request.getParameter("nome"));
                    comprador.setSobrenome(request.getParameter("sobrenome"));
                    comprador.setSenha(request.getParameter("senha"));

                    String dataNascimentoString = request.getParameter("dataNascimento");
                    DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date data = new Date(fmt.parse(dataNascimentoString).getTime());
                        comprador.setDataNascimento(data);
                    } catch (ParseException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    cpf = request.getParameter("cpf");
                    rg = request.getParameter("rg");

                    comprador.setCpf(cpf.replaceAll("[.-]", ""));
                    comprador.setRg(rg.replaceAll("[.-]", ""));

                    Endereço endereço = new Endereço();
                    endereço.setRua(request.getParameter("rua"));
                    endereço.setCep(request.getParameter("cep"));
                    endereço.setReferencia("comprador");
                    try {
                        endereço.setNumero(Integer.parseInt(request.getParameter("numero")));
                        Cidade cidade = new Cidade();
                        cidade.setId(Integer.parseInt(request.getParameter("cidade")));
                        endereço.setCidade(cidade);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }

                    try {
                        CompradorFacade.inserir(comprador);
                        comprador.setIdComprador(CompradorFacade.buscarIdPorDadosComprador(comprador));
                        endereço.setIdReferencia(comprador.getIdComprador());
                        EndereçoFacade.inserir(endereço);
                    } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    Comprador us = new Comprador();
                    us.setId(comprador.getId());
                    us.setNome(comprador.getNome());
                    session = request.getSession();
                    session.setAttribute("usuario", us);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
                    rd.forward(request, response);
                }
            }
        }
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
