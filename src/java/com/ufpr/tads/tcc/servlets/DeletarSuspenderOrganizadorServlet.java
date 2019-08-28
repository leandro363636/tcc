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
import com.ufpr.tads.tcc.beans.Organizador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.tcc.facade.CidadeFacade;
import com.ufpr.tads.tcc.facade.CompradorFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.OrganizadorFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.console;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tiago
 */
@WebServlet(name = "DeletarSuspenderOrganizadorServlet", urlPatterns = {"/DeletarSuspenderOrganizadorServlet"})
public class DeletarSuspenderOrganizadorServlet extends HttpServlet {

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
            try {
                List<Organizador> organizadores;

                String pagina = request.getParameter("pagina");
                int pg = 1;
                if (pagina != null && !pagina.equals("")) {
                    try {
                        pg = Integer.parseInt(pagina);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                }

                organizadores = OrganizadorFacade.selectOrganizadores(pg);

                int total = OrganizadorFacade.buscarTotal();

                int numeroPaginas = (int) Math.ceil(total * 1.0 / 9);

                ListIterator<Organizador> organizadorIterator = organizadores.listIterator();

                while (organizadorIterator.hasNext()) {
                    // Need to call next, before set.
                    Organizador organizador = organizadorIterator.next();
                    // Replace item returned from next()
                    organizadorIterator.set(organizador);
                }

                request.setAttribute("total", total);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pg);

                request.setAttribute("organizadores", organizadores);

            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/deletarSuspenderOrganizador.jsp");
            rd.forward(request, response);

        } else if (acao.equals("desactive")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                OrganizadorFacade.suspender(id);
            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
             try {
                List<Organizador> organizadores;

                String pagina = request.getParameter("pagina");
                int pg = 1;
                if (pagina != null && !pagina.equals("")) {
                    try {
                        pg = Integer.parseInt(pagina);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                }

                organizadores = OrganizadorFacade.selectOrganizadores(pg);

                int total = OrganizadorFacade.buscarTotal();

                int numeroPaginas = (int) Math.ceil(total * 1.0 / 9);

                ListIterator<Organizador> organizadorIterator = organizadores.listIterator();

                while (organizadorIterator.hasNext()) {
                    // Need to call next, before set.
                    Organizador organizador = organizadorIterator.next();
                    // Replace item returned from next()
                    organizadorIterator.set(organizador);
                }

                request.setAttribute("total", total);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pg);

                request.setAttribute("organizadores", organizadores);

            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/deletarSuspenderOrganizador.jsp");
            rd.forward(request, response);

        } else if (acao.equals("active")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                OrganizadorFacade.ativar(id);
            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            try {
                List<Organizador> organizadores;

                String pagina = request.getParameter("pagina");
                int pg = 1;
                if (pagina != null && !pagina.equals("")) {
                    try {
                        pg = Integer.parseInt(pagina);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                }

                organizadores = OrganizadorFacade.selectOrganizadores(pg);

                int total = OrganizadorFacade.buscarTotal();

                int numeroPaginas = (int) Math.ceil(total * 1.0 / 9);

                ListIterator<Organizador> organizadorIterator = organizadores.listIterator();

                while (organizadorIterator.hasNext()) {
                    // Need to call next, before set.
                    Organizador organizador = organizadorIterator.next();
                    // Replace item returned from next()
                    organizadorIterator.set(organizador);
                }

                request.setAttribute("total", total);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pg);

                request.setAttribute("organizadores", organizadores);

            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/deletarSuspenderOrganizador.jsp");
            rd.forward(request, response);

        } else if (acao.equals("delete")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                OrganizadorFacade.deletar(id);
            } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            try {
                List<Organizador> organizadores;

                String pagina = request.getParameter("pagina");
                int pg = 1;
                if (pagina != null && !pagina.equals("")) {
                    try {
                        pg = Integer.parseInt(pagina);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                }

                organizadores = OrganizadorFacade.selectOrganizadores(pg);

                int total = OrganizadorFacade.buscarTotal();

                int numeroPaginas = (int) Math.ceil(total * 1.0 / 9);

                ListIterator<Organizador> organizadorIterator = organizadores.listIterator();

                while (organizadorIterator.hasNext()) {
                    // Need to call next, before set.
                    Organizador organizador = organizadorIterator.next();
                    // Replace item returned from next()
                    organizadorIterator.set(organizador);
                }

                request.setAttribute("total", total);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pg);

                request.setAttribute("organizadores", organizadores);

            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/deletarSuspenderOrganizador.jsp");
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
