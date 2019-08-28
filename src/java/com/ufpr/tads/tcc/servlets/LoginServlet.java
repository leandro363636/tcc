package com.ufpr.tads.tcc.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.dao.UsuarioDAO;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.exceptions.UsuarioSenhaInvalidosException;
import com.ufpr.tads.tcc.facade.AdministradorFacade;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.OrganizadorFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
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
 * @author mateus
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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

        String acao = request.getParameter("action");
        if (acao == null || acao.equals("login")) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            StringBuffer hexString = new StringBuffer();
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                byte[] hash = md.digest(senha.getBytes("UTF-8"));
                for (int i = 0; i < hash.length; i++) {
                    if ((0xff & hash[i]) < 0x10) {
                        hexString.append("0"
                                + Integer.toHexString((0xFF & hash[i])));
                    } else {
                        hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }

            Usuario usuario = new Usuario();

            try {
                usuario = UsuarioFacade.buscarUsuarioByEmail(email);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            try {
                if (usuario != null && usuario.getEmail().equals(email) && usuario.getSenha().equals(hexString.toString())) {
                    Usuario us = new Usuario();
                    us.setId(usuario.getId());
                    us.setEmail(usuario.getEmail());
                    us.setTipo(usuario.getTipo());
                    us.setIdReferencia(usuario.getIdReferencia());
                    String nome             = "";
                    String redirecionamento   = "";
                    try {
                        switch (us.getTipo()) {

                            case "a" :
                                nome                = AdministradorFacade.buscarNomePorId(us.getIdReferencia());
                                redirecionamento    = "/EventoServlet?action=list";
                                break;
                            case "o" : 
                                nome                = OrganizadorFacade.buscarNomePorId(us.getIdReferencia());
                                redirecionamento    = "/EventoServlet?action=list";
                                break;
                            case "c" :
                                nome = CompradorFacade.buscarNomePorId(us.getIdReferencia());
                                redirecionamento    = "/EventoServlet?action=list";
                                break;

                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", us);
                    session.setAttribute("nome", nome);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(redirecionamento);
                    rd.forward(request, response);

                } else {
                    throw new UsuarioSenhaInvalidosException("Usuário/Senha inválidos.");
                }
            } catch (UsuarioSenhaInvalidosException ex) {
                request.setAttribute("msg", ex.getMessage());

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } else if (acao.equals("social")) {
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String sobrenome = request.getParameter("sobrenome");
            Comprador usuario = new Comprador();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSobrenome(sobrenome);
            /*usuario.setSenha("");
            usuario.setRg("");
            usuario.setCpf("");
            usuario.setDataNascimento(new Date());*/
            
            try {
                Usuario us = UsuarioFacade.buscarUsuarioByEmail(email);                      
                if (us != null && (us.getEmail() == null ? usuario.getEmail() == null : us.getEmail().equals(usuario.getEmail()))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    session.setAttribute("nome", usuario.getNome());
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/EventoServlet?action=list");
                    rd.forward(request, response);
                    
                } else {
                    try {
                        CompradorFacade.inserirSocial(usuario);
                    } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                     HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    session.setAttribute("nome", usuario.getNome());
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
                                    rd.forward(request, response);
                    
                }
            }
                  catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }

        } else if (acao.equals("logout")) {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.invalidate();
            }

            request.setAttribute("msg", "Usuário desconectado com sucesso");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
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
