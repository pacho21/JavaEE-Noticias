package com.noticiasee.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.noticiasee.entities.Noticia;
import com.noticiasee.sessions.NoticiaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class GetMoreNews extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        try {
            List<Noticia> masNoticias = noticiaFacade.getMoreById(Integer.parseInt(req.getParameter("cargadas")));
 
           if (masNoticias.size() > 0) {
                //do stuff
                resp.setContentType("application/json");
                PrintWriter pw = resp.getWriter();
                pw.println(gson.toJson(masNoticias));

            } else {

                Map<String, String> mess = new HashMap<>();
                mess.put("mess", "No hay más noticias");
                resp.setContentType("application/json");
                PrintWriter pw = resp.getWriter();
                pw.println(gson.toJson(mess));
            }

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Server error");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            PrintWriter pw = resp.getWriter();
            pw.println(gson.toJson(emess));

        }

    }

}
