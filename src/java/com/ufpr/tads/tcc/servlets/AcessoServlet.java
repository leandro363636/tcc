/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.servlets;

import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author matri
 */
@WebServlet(name = "AcessoServlet", urlPatterns = {"/AcessoServlet"})
public class AcessoServlet extends HttpServlet {

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
        response.setContentType("application/json");
        if (request.getMethod().equals("POST")) {
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
              BufferedReader reader = request.getReader();
              while ((line = reader.readLine()) != null)
                jb.append(line);
            } catch (Exception e) { 

            }

            try {
                JSONObject jsonObject =  JSONObject.fromObject(jb.toString());

                System.out.println(jsonObject.get("email"));
                String email = (String) jsonObject.get("email");
                String senha = (String) jsonObject.get("senha");
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
                    System.out.println(ex.getMessage());
                }
                Usuario us = new Usuario();

                try {
                    us = UsuarioFacade.buscarUsuarioByEmail(email);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                HashMap<String, String> hm = new HashMap<String, String>();

                if (us != null && us.getEmail().equals(email) && us.getSenha().equals(hexString.toString())) {
                    hm.put("response", Integer.toString(us.getId()));
                } else {
                    String message = "Login ou senha inválidos";
                    hm.put("response", message);
                }
                JSONObject json = JSONObject.fromObject(hm);
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
            } catch (JSONException e) {
              // crash and burn
              throw new IOException("Error parsing JSON request string");
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
