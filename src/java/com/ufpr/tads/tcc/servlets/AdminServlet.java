/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Admin;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.AdminFacade;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

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
            List<Admin> adm;
            try {             
              adm = AdminFacade.buscarListaAdmin(lb.getId());
                request.setAttribute("admin", adm);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admForm.jsp");
            rd.forward(request, response);
        } else { if (acao.equals("formUpdate")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Admin adm = AdminFacade.selectAdminById(id);
                        request.setAttribute("alterarAdm", adm);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("exception", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admForm.jsp?form=alterar");
                    rd.forward(request, response);
                } else { if (acao.equals("formNew")) {
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/admForm.jsp");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("new")) {
                                    Admin adm = new Admin();
                                    adm.setEmail(request.getParameter("email"));
                                    adm.setSenha(request.getParameter("senha"));
                                    adm.setNome(request.getParameter("nome"));
                                    adm.setSobrenome(request.getParameter("sobrenome"));
                                    adm.setRg(request.getParameter("rg"));
                                    adm.setCpf(request.getParameter("cpf"));
                                    String dataString = request.getParameter("data");
                                    DateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
                                    try {
                                        Date data = new Date(frmt.parse(dataString).getTime());
                                        adm.setDataNascimento(data);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                adm.setTipo(request.getParameter("tipo"));
                                    adm.setEndereco(request.getParameter("endereco"));
                                    try{
                                        
                                    AdminFacade.inserir(adm);
                                  
                                    } catch (SQLException | ClassNotFoundException  ex) {
                                        request.setAttribute("exception", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuariosListar.jsp");
                                rd.forward(request, response);  
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
