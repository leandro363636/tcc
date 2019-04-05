/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.google.gson.Gson;
import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Lote;
import com.ufpr.tads.tcc.facade.LoteFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "LoteServlet", urlPatterns = {"/LoteServlet"})
public class LoteServlet extends HttpServlet {

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
        
            String ação = request.getParameter("acao");
            if (ação.equals("adicionar")) {
                String nome = request.getParameter("nomeLote");
                try {
                    int qtd = Integer.parseInt(request.getParameter("qtdLote"));
                    double preço = Double.parseDouble(request.getParameter("precoLote"));
                    int idEvento = Integer.parseInt(request.getParameter("idEvento"));
                    Lote lote = new Lote();
                    lote.setNome(nome);
                    lote.setPreço(preço);
                    lote.setQuantidade(qtd);
                    Evento evento = new Evento();
                    evento.setId(idEvento);
                    lote.setEvento(evento);
                    LoteFacade.inserir(lote);
                    /*List<Lote> lotes = new ArrayList();
                    lotes.add(lote);*/
                    String json = new Gson().toJson(lote);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    String json = new Gson().toJson(false);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            } else if (ação.equals("listar")) {
                try {
                    int idEvento = Integer.parseInt(request.getParameter("idEvento"));
                    List<Lote> lotes = LoteFacade.buscarTodosLotesPorIdEvento(idEvento);
                    String json = new Gson().toJson(lotes);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    String json = new Gson().toJson(false);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                    /*request.setAttribute("exception", ex);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);*/
                }
            } else if (ação.equals("deletar")) {
                try {
                    int idLote = Integer.parseInt(request.getParameter("idLote"));
                    LoteFacade.remover(idLote);
                    String json = new Gson().toJson(true);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    String json = new Gson().toJson(false);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                }
            } else if (ação.equals("editar")) {
                String nome = request.getParameter("nomeLote");
                try {
                    int qtd = Integer.parseInt(request.getParameter("qtdLote"));
                    double preço = Double.parseDouble(request.getParameter("precoLote"));
                    int idEvento = Integer.parseInt(request.getParameter("idEvento"));
                    int idLote = Integer.parseInt(request.getParameter("idLote"));
                    Lote lote = new Lote();
                    lote.setId(idLote);
                    lote.setNome(nome);
                    lote.setPreço(preço);
                    lote.setQuantidade(qtd);
                    Evento evento = new Evento();
                    evento.setId(idEvento);
                    lote.setEvento(evento);
                    LoteFacade.alterar(lote);
                    String json = new Gson().toJson(lote);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    String json = new Gson().toJson(false);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);
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
