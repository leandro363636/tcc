/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

//import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import com.ufpr.tads.tcc.beans.Cidade;
import com.ufpr.tads.tcc.beans.Endereço;
import com.ufpr.tads.tcc.beans.Estado;
import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Lote;
import com.ufpr.tads.tcc.beans.Comprador;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.CidadeFacade;
import com.ufpr.tads.tcc.facade.EndereçoFacade;
import com.ufpr.tads.tcc.facade.EstadoFacade;
import com.ufpr.tads.tcc.facade.EventoFacade;
import com.ufpr.tads.tcc.facade.LoteFacade;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author mateus
 */
@WebServlet(name = "EventosListarUsuarioServlet", urlPatterns = {"/EventosListarUsuarioServlet"})
@MultipartConfig
public class EventosListarUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String acao = request.getParameter("action");

        if (acao == null || acao.equals("list")) {

            List<Evento> eventos;
            try {
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
                Usuario us = new Usuario();
                int id;
                us = (Usuario) session.getAttribute("usuario");
                id = us.getId();
                int total = 0;

                total = EventoFacade.buscarTotalPorIdUsuarioAprovado(id);
                eventos = EventoFacade.buscarTodosEventosPorIdUsuarioPagAprovado(pg, id);

                int numeroPaginas = (int) Math.ceil(total * 1.0 / 9);

                ListIterator<Evento> eventoIterator = eventos.listIterator();

                while (eventoIterator.hasNext()) {
                    // Need to call next, before set.
                    Evento evento = eventoIterator.next();
                    Endereço endereço = EndereçoFacade.buscarPorReferencia(evento.getId(), "evento");
                    endereço.setCidade(CidadeFacade.buscarCidade(endereço.getCidade().getId()));
                    evento.setEndereco(endereço);
                    // Replace item returned from next()
                    eventoIterator.set(evento);
                }

                request.setAttribute("total", total);
                request.setAttribute("numeroPaginas", numeroPaginas);
                request.setAttribute("pagina", pg);
                request.setAttribute("eventos", eventos);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/listarEventosUsuario.jsp");
            rd.forward(request, response);
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        //LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
