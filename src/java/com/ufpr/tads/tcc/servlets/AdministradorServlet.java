/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Administrador;
import com.ufpr.tads.tcc.beans.Cidade;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Endereço;
import com.ufpr.tads.tcc.beans.Estado;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.AdministradorFacade;
import com.ufpr.tads.tcc.facade.CidadeFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import static javax.xml.bind.DatatypeConverter.parseString;
import java.util.*;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "AdministradorServlet", urlPatterns = {"/AdministradorServlet"})
public class AdministradorServlet extends HttpServlet {

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

        Usuario us = (Usuario) session.getAttribute("usuario");
        if (us == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }
        if (acao == null || acao.equals("list")) {
            List<Administrador> adm;
            try {
                adm = AdministradorFacade.buscarTodosAdministradores();
                request.setAttribute("admin", adm);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admList.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("show")) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Administrador adm;
                    adm = AdministradorFacade.buscar(id);
                    Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "administrador");
                    if (endereço.getId() != 0 ) {
                        endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                        adm.setEndereco(endereço);
                    } else {
                        endereço = new Endereço();
                        adm.setEndereco(endereço);
                    }
                    request.setAttribute("visualizaradm", adm);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/admVisualizar.jsp");
                rd.forward(request, response);

            } else {
                if (acao.equals("formUpdate")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Administrador adm = AdministradorFacade.buscar(id);
                        Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "administrador");
                        if (endereço.getId() != 0 ) {
                            endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                            adm.setEndereco(endereço);
                        } else {
                            endereço = new Endereço();
                            adm.setEndereco(endereço);
                        }
                        List<Estado> estados = new ArrayList<>();
                        estados = EstadoFacade.buscarTodosEstados();
                        request.setAttribute("estados", estados);
                        request.setAttribute("alterarAdm", adm);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("exception", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }

                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admForm.jsp?form=alterar");
                    rd.forward(request, response);

                } else {
                    if (acao.equals("update")) {
                        Administrador adm = new Administrador();
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            adm.setIdAdministrador(id);

                            us = (Usuario) UsuarioFacade.buscarUsuarioByEmail(request.getParameter("email"));
                            try {
                                if (us != null && us.getIdReferencia() != adm.getIdAdministrador()) {
                                    throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                }
                            } catch (EmailDuplicadoException ex) {
                                request.setAttribute("msg", ex.getMessage());

                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=list");
                                rd.forward(request, response);
                            }

                            adm.setEmail(email);
                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                            request.setAttribute("javax.servlet.jsp.jspException", ex);
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }

                        adm.setNome(request.getParameter("nome"));
                        adm.setSobrenome(request.getParameter("sobrenome"));
                        adm.setRg(request.getParameter("rg"));
                        adm.setCpf(request.getParameter("cpf"));
                        adm.setSenha(request.getParameter("senha"));
                        String dataString = request.getParameter("dataNascimento");
                        DateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date data = new Date(frmt.parse(dataString).getTime());
                            adm.setDataNascimento(data);
                        } catch (ParseException ex) {
                            Logger.getLogger(AdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Endereço endereço = new Endereço();
                        endereço.setRua(request.getParameter("rua"));
                        endereço.setCep(request.getParameter("cep"));
                        endereço.setReferencia("administrador");
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
                            if (adm.getSenha() != null || adm.getSenha().equals("")) {
                                AdministradorFacade.alterarSemSenha(adm);
                            } else {
                                AdministradorFacade.alterar(adm);
                            }
                            endereço.setIdReferencia(adm.getIdAdministrador());
                            EndereçoFacade.alterarPorIdReferencia(endereço);
                        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                            request.setAttribute("javax.servlet.jsp.jspException", ex);
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=list");
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
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admForm.jsp");
                            rd.forward(request, response);
                        } else {
                            if (acao.equals("new")) {
                                Administrador adm = new Administrador();
                                adm.setEmail(request.getParameter("email"));
                                adm.setSenha(request.getParameter("senha"));
                                adm.setNome(request.getParameter("nome"));
                                adm.setSobrenome(request.getParameter("sobrenome"));
                                adm.setRg(request.getParameter("rg"));
                                adm.setCpf(request.getParameter("cpf"));
                                String dataString = request.getParameter("dataNascimento");
                                DateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    Date data = new Date(frmt.parse(dataString).getTime());
                                    adm.setDataNascimento(data);
                                } catch (ParseException ex) {
                                    Logger.getLogger(AdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Endereço endereço = new Endereço();
                                endereço.setRua(request.getParameter("rua"));
                                endereço.setCep(request.getParameter("cep"));
                                endereço.setReferencia("administrador");
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
                                    AdministradorFacade.inserir(adm);
                                    adm.setIdAdministrador(AdministradorFacade.buscarIdPorDadosAdministrador(adm));
                                    endereço.setIdReferencia(adm.getIdAdministrador());
                                    EndereçoFacade.inserir(endereço);
                                } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                                    request.setAttribute("exception", ex);
                                    request.setAttribute("javax.servlet.error.status_code", 500);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                    rd.forward(request, response);
                                }

                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=list");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("suspend")) {
                                    try {
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        AdministradorFacade.suspender(id);
                                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=list");
                                    rd.forward(request, response);
                                } else {
                                    if (acao.equals("active")) {
                                        try {
                                            int id = Integer.parseInt(request.getParameter("id"));
                                            AdministradorFacade.ativar(id);
                                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                            request.setAttribute("javax.servlet.jsp.jspException", ex);
                                            request.setAttribute("javax.servlet.error.status_code", 500);
                                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                            rd.forward(request, response);
                                        }
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=list");
                                        rd.forward(request, response);
                                    }
                                }
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
