/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.EventoFacade;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mateus
 */
@WebServlet(name = "EventoServlet", urlPatterns = {"/EventoServlet"})
public class EventoServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String acao = request.getParameter("action");
        
        Usuario lb = (Usuario) session.getAttribute("usuario");
        if (lb == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }
        
        if (acao == null || acao.equals("list")) {
            List<Evento> eventos;
            try {
                eventos = EventoFacade.buscarTodosEventosPorIdUsuario(lb.getId());
                request.setAttribute("eventos", eventos);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/eventosListar.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("show")) {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Evento evento = EventoFacade.buscar(id);
                    request.setAttribute("visualizarevento", evento);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("exception", ex);
                    request.setAttribute("javax.servlet.error.status_code", 500);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/eventosVisualizar.jsp");
                rd.forward(request, response);
            } else { 
                if (acao.equals("formUpdate")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        Evento evento = EventoFacade.buscar(id);
                        request.setAttribute("alterarevento", evento);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("exception", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/eventosForm.jsp?form=alterar");
                    rd.forward(request, response);
                } else {
                    if (acao.equals("remove")) {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            EventoFacade.remover(id);
                        } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                            request.setAttribute("exception", ex);
                            request.setAttribute("javax.servlet.error.status_code", 500);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                            rd.forward(request, response);
                        }
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/EventoServlet?action=list");
                        rd.forward(request, response);
                    } else {
                        if (acao.equals("update")) {
                            Evento evento = new Evento();
                            try {
                                int id = Integer.parseInt(request.getParameter("id"));
                                evento.setId(id);
                            } catch (NumberFormatException ex) {
                                request.setAttribute("exception", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            
                            /*Identifica se o formulario é do tipo multipart/form-data*/
                            if (ServletFileUpload.isMultipartContent(request)) {
                                try {
                                    /*Faz o parse do request*/
                                    List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                                    /*Escreve a o arquivo na pasta img*/
                                    for (FileItem item : multiparts) {
                                        if (!item.isFormField()) {
                                            byte[] array = new byte[7]; // length is bounded by 7
                                            new Random().nextBytes(array);
                                            String generatedString = new String(array, Charset.forName("UTF-8"));
                                            String[] fileName = item.getContentType().split("/");
                                            //System.out.println(fileName[fileName.length - 1]);
                                            item.write(new File(request.getServletContext().getRealPath("img")+ File.separator + item.getName()));
                                        }
                                    }

                                    System.out.println("Arquivo carregado com sucesso");
                                } catch (Exception ex) {
                                    System.out.println("Upload de arquivo falhou devido a "+ ex);
                                }

                            } else {
                                System.out.println("Desculpe este Servlet lida apenas com pedido de upload de arquivos");
                            }
                            
                            evento.setNome(request.getParameter("nome"));
                            evento.setDesc(request.getParameter("desc"));
                            evento.setEndereco(request.getParameter("endereco"));
                            
                            String dataInicioString = request.getParameter("dataInicio");
                            String dataFimString = request.getParameter("dataFim");
                            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            try {
                                Date data = new Date(fmt.parse(dataInicioString).getTime());
                                evento.setDataInicio(data);
                                data = new Date(fmt.parse(dataFimString).getTime());
                                evento.setDataFim(data);
                            } catch (ParseException ex) {
                                request.setAttribute("exception", ex);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                                               
                            try {
                                EventoFacade.alterar(evento);
                            } catch (SQLException | ClassNotFoundException  ex) {
                                request.setAttribute("exception", ex);
                                request.setAttribute("javax.servlet.error.status_code", 500);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                rd.forward(request, response);
                            }
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/EventoServlet?action=list");
                            rd.forward(request, response);
                        } else {
                            if (acao.equals("formNew")) {
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/eventosForm.jsp");
                                rd.forward(request, response);
                            } else {
                                if (acao.equals("new")) {
                                    Evento evento = new Evento();
                                    evento.setNome(request.getParameter("nome"));
                                    evento.setDesc(request.getParameter("desc"));
                                    evento.setEndereco(request.getParameter("endereco"));
                                    evento.setAprovado(false);
                                    evento.setUsuario(lb);

                                    String dataInicioString = request.getParameter("dataInicio");
                                    String dataFimString = request.getParameter("dataFim");
                                    DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                    try {
                                        Date data = new Date(fmt.parse(dataInicioString).getTime());
                                        evento.setDataInicio(data);
                                        data = new Date(fmt.parse(dataFimString).getTime());
                                        evento.setDataFim(data);
                                    } catch (ParseException ex) {
                                        request.setAttribute("exception", ex);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }

                                    try {
                                        EventoFacade.inserir(evento);
                                    } catch (SQLException | ClassNotFoundException  ex) {
                                        request.setAttribute("exception", ex);
                                        request.setAttribute("javax.servlet.error.status_code", 500);
                                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                                        rd.forward(request, response);
                                    }
                                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/EventoServlet?action=list");
                                    rd.forward(request, response);
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
