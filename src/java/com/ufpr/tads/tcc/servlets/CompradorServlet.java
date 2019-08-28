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
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.CidadeFacade;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * @author mateus
 */
@WebServlet(name = "CompradorServlet", urlPatterns = {"/CompradorServlet"})
public class CompradorServlet extends HttpServlet {

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
        Usuario us = (Usuario) session.getAttribute("usuario");
        if (us == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (acao == null || acao.equals("list")) {
            List<Comprador> compradores;
            try {
                compradores = CompradorFacade.buscarTodosCompradores();
                request.setAttribute("compradores", compradores);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/compradoresListar.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("show")) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Comprador comprador = CompradorFacade.buscar(id);
                    Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "comprador");
                    if (endereço.getId() != 0 ) {
                        endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                        comprador.setEndereco(endereço);
                    } else {
                        endereço = new Endereço();
                        comprador.setEndereco(endereço);
                    }
                    request.setAttribute("visualizarcomprador", comprador);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("javax.servlet.jsp.jspException", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/compradoresVisualizar.jsp");
                rd.forward(request, response);
            } else { 
                if (acao.equals("formUpdate")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Comprador comprador = CompradorFacade.buscar(id);
                        Endereço endereço = EndereçoFacade.buscarPorReferencia(id, "comprador");
                        if (endereço.getId() != 0 ) {
                            endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                            comprador.setEndereco(endereço);
                        } else {
                            endereço = new Endereço();
                            comprador.setEndereco(endereço);
                        }
                        
                        List<Estado> estados = new ArrayList<>();
                        estados = EstadoFacade.buscarTodosEstados();
                        request.setAttribute("estados", estados);
                        request.setAttribute("alterarcomprador", comprador);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/compradoresForm.jsp?form=alterar");
                    rd.forward(request, response);
                } else {
                    if (acao.equals("suspend")) {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            CompradorFacade.suspender(id);
                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                            request.setAttribute("javax.servlet.jsp.jspException", ex);
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
                        rd.forward(request, response);
                    } else {
                        if (acao.equals("update")) {
                            Comprador comprador = new Comprador();
                            try {
                                int id = Integer.parseInt(request.getParameter("id"));
                                comprador.setIdComprador(id);
                                String email = request.getParameter("email");
                                
                                us = (Usuario) UsuarioFacade.buscarUsuarioByEmail(email);
                                try {
                                    if (us != null && us.getIdReferencia() != comprador.getIdComprador()) {
                                        throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                    }
                                } catch (EmailDuplicadoException ex) {
                                    request.setAttribute("msg", ex.getMessage());

                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=list");
                                    rd.forward(request, response);
                                }
                                
                                comprador.setEmail(email);
                            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                request.setAttribute("exception", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            
                            comprador.setNome(request.getParameter("nome"));
                            comprador.setSobrenome(request.getParameter("sobrenome"));
                            comprador.setSenha(request.getParameter("senha"));
                            comprador.setCpf(request.getParameter("cpf"));
                            comprador.setRg(request.getParameter("rg"));
                            
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
                                if (comprador.getSenha() != null || comprador.getSenha().equals("")) {
                                    CompradorFacade.alterarSemSenha(comprador);
                                } else {
                                    CompradorFacade.alterar(comprador);
                                }
                                endereço.setIdReferencia(comprador.getIdComprador());
                                EndereçoFacade.alterarPorIdReferencia(endereço);
                            } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException  ex) {
                                request.setAttribute("exception", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
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
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/compradoresForm.jsp");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("new")) {
                                    Comprador comprador = new Comprador();
                                    try {
                                        String email = request.getParameter("email");

                                        Comprador comp = (Comprador) UsuarioFacade.buscarUsuarioByEmail(email);
                                        try {
                                            if (comp != null && comp.getId() != comprador.getId()) {
                                                throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                                            }
                                        } catch (EmailDuplicadoException ex) {
                                            request.setAttribute("msg", ex.getMessage());

                                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=list");
                                            rd.forward(request, response);
                                        }

                                        comprador.setEmail(email);
                                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }

                                    comprador.setNome(request.getParameter("nome"));
                                    comprador.setSobrenome(request.getParameter("sobrenome"));
                                    comprador.setSenha(request.getParameter("senha"));
                                    comprador.setRg(request.getParameter("rg"));
                                    comprador.setCpf(request.getParameter("cpf"));
                                    
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
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
                                    rd.forward(request, response);
                                } else {
                                    if (acao.equals("active")) {
                                        try {
                                            int id = Integer.parseInt(request.getParameter("id"));
                                            CompradorFacade.ativar(id);
                                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                                            request.setAttribute("javax.servlet.jsp.jspException", ex);
                                            request.setAttribute("javax.servlet.error.status_code", 500);
                                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                            rd.forward(request, response);
                                        }
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/CompradorServlet?action=list");
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
