/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Admin;
import com.ufpr.tads.tcc.beans.Organizador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.AdminFacade;
import com.ufpr.tads.tcc.facade.OrganizadorFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "OrganizadorServlet", urlPatterns = {"/OrganizadorServlet"})
public class OrganizadorServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
         HttpSession session = request.getSession();
        String acao = request.getParameter("action");
        
        String email = request.getParameter("email");
        System.out.print(email);
        
        Usuario lb = (Usuario) session.getAttribute("usuario");
        if (lb == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }  
            if (acao == null || acao.equals("list")) {
            List<Organizador> org;
            try {             
                org = OrganizadorFacade.getOrganizador();
                request.setAttribute("organizador", org);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorList.jsp");
            rd.forward(request, response);
        }else{
            if (acao.equals("show")) {
                         
                try{
                  int idOrganizador = Integer.parseInt(request.getParameter("idOrganizador"));
                    Organizador org;
                    
                    org = OrganizadorFacade.buscar(idOrganizador);
                    request.setAttribute("visualizarorganizador", org);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/organizadorVisualizar.jsp");
                rd.forward(request, response);
                
            } else { 
              if (acao.equals("formUpdate")) {
                    try {
                        int idOrganizador = Integer.parseInt(request.getParameter("idOrganizador"));
                        Organizador org = OrganizadorFacade.buscar(idOrganizador);
                        request.setAttribute("alterarOrg", org);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("exception", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorForm.jsp?form=alterar");
                    rd.forward(request, response);
              
                      } else {
                        if (acao.equals("update")) {
                                  Organizador org = new Organizador();
                            try {
                                int idOrganizador = Integer.parseInt(request.getParameter("idOrganizador"));
                                org.setIdOrganizador(idOrganizador);
                                
                             /*   String email = request.getParameter("email");*/
                                     
                                Organizador us = OrganizadorFacade.getOrganizadorEmail(request.getParameter("email"));
                                try {
                                    if (us != null && us.getIdOrganizador() != org.getIdOrganizador()) {
                                        throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                    }
                                } catch (EmailDuplicadoException ex) {
                                    request.setAttribute("msg", ex.getMessage());

                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=list");
                                    rd.forward(request, response);
                                }
                                
                                org.setEmail(email);
                            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                request.setAttribute("javax.servlet.jsp.jspException", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            
                                    org.setCnpj(request.getParameter("cnpj"));
                                    org.setNomeDaOrganizacao(request.getParameter("NomedaOrganizacao"));
                                    org.setNomeDoResponsavel(request.getParameter("NomeDoResponsavel"));
                                    org.setRgDoResponsavel(request.getParameter("rgDoResponsavel"));
                                    org.setEndereco(request.getParameter("endereco"));
                                    int tipo = Integer.parseInt(request.getParameter("tipo"));
                                    org.setTipo(tipo);

                        
                            try {
                                if (org.getSenha() != null || org.getSenha().equals("")) {
                                    OrganizadorFacade.alterarSemSenha(org);
                                } else {
                                    OrganizadorFacade.alterar(org);
                                }
                            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException  ex) {
                                request.setAttribute("javax.servlet.jsp.jspException", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorServlet?action=list");
                            rd.forward(request, response);
                       
                    } else { if (acao.equals("formNew")) {
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorForm.jsp");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("new")) {
                                  Organizador org = new Organizador();
                                    org.setCnpj(request.getParameter("cnpj"));
                                    org.setNomeDaOrganizacao(request.getParameter("nomeDaOrganizacao"));
                                    org.setNomeDoResponsavel(request.getParameter("nomeDoResponsavel"));
                                    org.setRgDoResponsavel(request.getParameter("rgDoResponsavel"));
                                    org.setEmail(request.getParameter("email"));
                                    org.setSenha(request.getParameter("senha"));
                                    org.setEndereco(request.getParameter("endereco"));
                                    int tipo = Integer.parseInt(request.getParameter("tipo"));
                                    org.setTipo(tipo);
                                    org.setEndereco(request.getParameter("endereco"));
                                    try{
                                        
                                    OrganizadorFacade.insertOrganizador(org);
                                  
                                    } catch (SQLException | ClassNotFoundException  ex) {
                                        request.setAttribute("exception", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/admList.jsp");
                                rd.forward(request, response);  
                                }
        
                         
                            }
                        }
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
