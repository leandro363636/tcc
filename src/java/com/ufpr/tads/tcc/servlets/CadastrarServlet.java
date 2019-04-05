/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
        
        Usuario lb = (Usuario) session.getAttribute("usuario");
        if (acao == null || acao.equals("list")) {
            List<Usuario> usuarios;
            try {
                usuarios = UsuarioFacade.buscarTodosUsuarios();
                request.setAttribute("usuarios", usuarios);
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
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("new")) {
                                    Usuario usuario = new Usuario();
                                    try {
                                        String email = request.getParameter("email");

                                        Usuario us = UsuarioFacade.buscarUsuarioByEmail(email);
                                        try {
                                            if (us != null && us.getId() != usuario.getId()) {
                                                throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                            }
                                        } catch (EmailDuplicadoException ex) {
                                            request.setAttribute("msg", ex.getMessage());

                                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CadastrarServlet?action=list");
                                            rd.forward(request, response);
                                        }

                                        usuario.setEmail(email);
                                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    String cpf;
                                     String rg;
                                    usuario.setNome(request.getParameter("nome"));
                                    usuario.setSobrenome(request.getParameter("sobrenome"));
                                    usuario.setSenha(request.getParameter("senha"));
                                    cpf = request.getParameter("cpf");
                                    rg = request.getParameter("rg");

                                    usuario.setCpf(cpf.replaceAll("[.-]", ""));
                                    usuario.setRg(rg.replaceAll("[.-]", ""));
                                   
                                    try {
                                        UsuarioFacade.inserir(usuario);
                                    } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    Usuario us = new Usuario();
                        us.setId(usuario.getId());
                        us.setNome(usuario.getNome());
                                        session = request.getSession();
                        session.setAttribute("usuario", us);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=list");
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
