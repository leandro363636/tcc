/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.tcc.ws;

import com.google.gson.Gson;
import com.ufpr.tads.tcc.beans.Evento;
import com.ufpr.tads.tcc.beans.Ingresso;
import com.ufpr.tads.tcc.beans.Usuario;
import com.ufpr.tads.tcc.facade.EventoFacade;
import com.ufpr.tads.tcc.facade.IngressoFacade;
import com.ufpr.tads.tcc.facade.UsuarioFacade;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author matri
 */
@Path("acesso")
public class AcessoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AcessoWS
     */
    public AcessoWS() {
    }

    /**
     * Retrieves representation of an instance of com.ufpr.tads.tcc.ws.AcessoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Produces("application/json")
    @Path("Evento/list/{id}")
    public String listEventosByOrganizator(@PathParam("id")int id) {
        Gson g = new Gson();
        try {
            List<Evento> eventos = EventoFacade.buscarTodosEventosPorIdUsuario(id);
            return g.toJson(eventos);
        } catch (SQLException | ClassNotFoundException ex) {
            return g.toJson(ex.getMessage());
        }
        
    }
    
    @GET
    @Produces("application/json")
    @Path("Ingresso/find/{idEvento}/{serial}")
    public String findByOrganizator(@PathParam("idEvento")int idEvento, @PathParam("serial")String serial) {
        Gson g = new Gson();
        try {
            int idIngresso = IngressoFacade.buscarIdPorSerialIdEvento(serial, idEvento);
            return g.toJson(idIngresso);
        } catch (SQLException | ClassNotFoundException ex) {
            return g.toJson(ex.getMessage());
        }
        
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("Usuario/login/")
    public String login(String content) {
        Gson g = new Gson();
        Usuario usuario = (Usuario) g.fromJson(content, Usuario.class);
        StringBuffer hexString = new StringBuffer();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(usuario.getSenha().getBytes("UTF-8"));
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return g.toJson(ex.getMessage());
        }
        Usuario us = new Usuario();

        try {
            us = UsuarioFacade.buscarUsuarioByEmail(usuario.getEmail());
        } catch (SQLException | ClassNotFoundException ex) {
            return g.toJson(ex.getMessage());
        }
        HashMap<String, String> hm = new HashMap<String, String>();
        
        if (us != null && us.getEmail().equals(usuario.getEmail()) && us.getSenha().equals(hexString.toString())) {
            hm.put("id", Integer.toString(us.getId()));
            return g.toJson(hm);
        } else {
            String message = "Erro no login";
            hm.put("erro", message);
            return g.toJson(hm);
        }
    }

    /**
     * PUT method for updating or creating an instance of AcessoWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("Ingresso/acesso/")
    public String putJson(String content) {
        Gson g = new Gson();
        int id = (int) g.fromJson(content, int.class);
        HashMap<String, String> hm = new HashMap<String, String>();
        try {
            IngressoFacade.acessar(id);
            String message = "Acesso realizado";
            hm.put("mensagem", message);
            return g.toJson(hm);
        } catch (SQLException | ClassNotFoundException ex) {
            return g.toJson(ex.getMessage());
        }
    }
}
