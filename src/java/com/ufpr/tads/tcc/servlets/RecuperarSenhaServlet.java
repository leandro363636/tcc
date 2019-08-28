/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.exceptions.EmailNaoExisteException;
import com.ufpr.tads.tcc.facade.AdministradorFacade;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.OrganizadorFacade;
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
import java.util.Properties;
import java.util.Random;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@WebServlet(name = "RecuperarSenhaServlet", urlPatterns = {"/RecuperarSenhaServlet"})
public class RecuperarSenhaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        if (acao.equals("formNew")) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/esqueciMinhaSenha.jsp?form=recuperar");
            rd.forward(request, response);
        } else {
            if (acao.equals("recuperar")) {

                Comprador comprador = new Comprador();
                comprador.setEmail(request.getParameter("email"));
                String cpf;
                cpf = request.getParameter("cpf");
                comprador.setCpf(cpf.replaceAll("[.-]", ""));
                String email = request.getParameter("email");

                try {

                    Usuario us = (Usuario) UsuarioFacade.buscarUsuarioByEmail(email);

                    try {

                        if (us == null || (us.getEmail() == null ? comprador.getEmail() != null : !us.getEmail().equals(comprador.getEmail()))) {

                            throw new EmailNaoExisteException("E-mail não esta cadastrado no sistema.");

                        } else {
                            String nome = "";
                            try {
                                switch (us.getTipo()) {
                                    case "a" :
                                        nome = AdministradorFacade.buscarNomePorId(us.getIdReferencia());
                                        break;
                                    case "o" : 
                                        nome = OrganizadorFacade.buscarNomePorId(us.getIdReferencia());
                                        break;
                                    case "c" :
                                        nome = CompradorFacade.buscarNomePorId(us.getIdReferencia());
                                        break;

                                }
                            } catch (SQLException | ClassNotFoundException ex) {
                                request.setAttribute("javax.servlet.jsp.jspException", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            comprador.setNome(nome);
                            comprador.setId(us.getIdReferencia());
                        }
                    } catch (EmailNaoExisteException ex) {
                        request.setAttribute("msg", ex.getMessage());

                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/esqueciMinhaSenha.jsp?form=erro");
                        rd.forward(request, response);
                    }

                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/esqueciMinhaSenha.jsp?form=erro");
                    rd.forward(request, response);
                }
                HttpSession session = request.getSession();
                Properties props = new Properties();
                /**
                 * Parâmetros de conexão com servidor Gmail
                 */
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.socketFactory.port", "587");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "587");

                Session sessionEmail = Session.getInstance(props,
                        new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("fasticketads@gmail.com", "26842486");
                    }
                });
                String senha;
                Random rand = new Random();
                char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    int ch = rand.nextInt(letras.length);
                    sb.append(letras[ch]);
                }
                comprador.setSenha(sb.toString());

                try {

                    Message message = new MimeMessage(sessionEmail);
                    message.setFrom(new InternetAddress("fasticketads@gmail.com")); //Remetente

                    Address[] toUser = InternetAddress //Destinatário(s)
                            .parse(comprador.getEmail());
                    message.setRecipients(Message.RecipientType.TO, toUser);

//message.setContent("Enviei este email utilizando JavaMail com minha conta GMail!     Essa é sua senha:" + senha, "text/html; charset=utf-8"); 
                    message.setRecipients(Message.RecipientType.TO, toUser);
                    message.setSubject("Recuperação de senha");//Assunto
                    message.setText("Olá " + comprador.getNome() + "! foi solicitado uma nova senha para acesso ao FasTicket com a sua conta. Essa é a sua nova senha: '" + comprador.getSenha() + "'. Caso não tenha solicitado a redefinição de senha, por favor entre em contato com a nossa equipe por este email");
                    /**
                     * Método para enviar a mensagem criada
                     */
                    Transport.send(message);

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

                try {
                    CompradorFacade.alterarSenha(comprador);
                } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/esqueciMinhaSenha.jsp?form=erro");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/esqueciMinhaSenha.jsp?form=enviado");
                rd.forward(request, response);

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
