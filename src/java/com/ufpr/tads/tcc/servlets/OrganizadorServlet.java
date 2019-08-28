/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Administrador;
import com.ufpr.tads.tcc.beans.Cidade;
import com.ufpr.tads.tcc.beans.Endereço;
import com.ufpr.tads.tcc.beans.Estado;
import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Organizador;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.AdministradorFacade;
import com.ufpr.tads.tcc.facade.CidadeFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.EventoFacade;
import com.ufpr.tads.tcc.facade.OrganizadorFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        Usuario us = (Usuario) session.getAttribute("usuario");
        if (us == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }
        if (acao == null || acao.equals("list")) {
            List<Organizador> org;
            try {
                org = OrganizadorFacade.buscarTodosOrganizadores();
                request.setAttribute("organizador", org);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorList.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("show")) {

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Organizador org;
                    org = OrganizadorFacade.buscar(id);
                    Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "organizador");
                    if (endereço.getId() != 0 ) {
                        endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                        org.setEndereco(endereço);
                    } else {
                        endereço = new Endereço();
                        org.setEndereco(endereço);
                    }
                    request.setAttribute("visualizarorganizador", org);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorVisualizar.jsp");
                rd.forward(request, response);

            } else {
                if (acao.equals("formUpdate")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Organizador org = OrganizadorFacade.buscar(id);
                        Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "organizador");
                        if (endereço.getId() != 0 ) {
                            endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                            org.setEndereco(endereço);
                        } else {
                            endereço = new Endereço();
                            org.setEndereco(endereço);
                        }
                        List<Estado> estados = new ArrayList<>();
                        estados = EstadoFacade.buscarTodosEstados();
                        request.setAttribute("alterarOrg", org);
                        request.setAttribute("estados", estados);
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
                            int id = Integer.parseInt(request.getParameter("id"));
                            org.setIdOrganizador(id);

                            /*   String email = request.getParameter("email");*/
                            us = (Usuario) UsuarioFacade.buscarUsuarioByEmail(request.getParameter("email"));
                            try {
                                if (us != null && us.getIdReferencia() != org.getIdOrganizador()) {
                                    throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                }
                            } catch (EmailDuplicadoException ex) {
                                request.setAttribute("msg", ex.getMessage());

                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=list");
                                rd.forward(request, response);
                            }

                            org.setEmail(email);
                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                            request.setAttribute("exception", ex);
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }

                        org.setCnpj(request.getParameter("cnpj"));
                        org.setRg(request.getParameter("rg"));
                        org.setNomeOrganizador(request.getParameter("nomeDaOrganizacao"));
                        org.setNomeResponsavel(request.getParameter("nomeDoResponsavel"));
                        org.setSobrenome(request.getParameter("sobrenome"));
                        
                        Endereço endereço = new Endereço();
                        endereço.setRua(request.getParameter("rua"));
                        endereço.setCep(request.getParameter("cep"));
                        endereço.setReferencia("organizador");
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
                        org.setSenha(request.getParameter("senha"));
                        try {
                            if (org.getSenha() != null || org.getSenha().equals("")) {
                                OrganizadorFacade.alterarSemSenha(org);
                            } else {
                                OrganizadorFacade.alterar(org);
                            }
                            endereço.setIdReferencia(org.getIdOrganizador());
                            EndereçoFacade.alterarPorIdReferencia(endereço);
                        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                            request.setAttribute("exception", ex);
                            System.out.println(ex.getMessage());
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorServlet?action=list");
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
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorForm.jsp");
                            rd.forward(request, response);
                        } else {
                            if (acao.equals("new")) {
                                Organizador org = new Organizador();
                                org.setCnpj(request.getParameter("cnpj"));
                                org.setRg(request.getParameter("rg"));
                                org.setNomeOrganizador(request.getParameter("nomeDaOrganizacao"));
                                org.setNomeResponsavel(request.getParameter("nomeDoResponsavel"));
                                org.setSobrenome(request.getParameter("sobrenome"));
                                org.setEmail(request.getParameter("email"));
                                org.setSenha(request.getParameter("senha"));

                                Endereço endereço = new Endereço();
                                endereço.setRua(request.getParameter("rua"));
                                endereço.setCep(request.getParameter("cep"));
                                endereço.setReferencia("organizador");
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
                                    OrganizadorFacade.inserir(org);
                                    org.setIdOrganizador(OrganizadorFacade.buscarIdPorDadosOrganizador(org));
                                    endereço.setIdReferencia(org.getIdOrganizador());
                                    EndereçoFacade.inserir(endereço);
                                } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                                    request.setAttribute("exception", ex);
                                    request.setAttribute("javax.servlet.error.status_code", 500);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                    rd.forward(request, response);
                                }

                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorServlet?action=list");
                                rd.forward(request, response);
                            } else if (acao.equals("suspend")) {
                                try {
                                    int id = Integer.parseInt(request.getParameter("id"));
                                    OrganizadorFacade.suspender(id);
                                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                    request.setAttribute("exception", ex);
                                    request.setAttribute("javax.servlet.error.status_code", 500);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                    rd.forward(request, response);
                                }
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorServlet?action=list");
                                rd.forward(request, response);
                            }  else if (acao.equals("active")) {
                                try {
                                    int id = Integer.parseInt(request.getParameter("id"));
                                    OrganizadorFacade.ativar(id);
                                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                    request.setAttribute("exception", ex);
                                    request.setAttribute("javax.servlet.error.status_code", 500);
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                    rd.forward(request, response);
                                }
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/OrganizadorServlet?action=list");
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
