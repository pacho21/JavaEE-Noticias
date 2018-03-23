/*
Se encargara de devolver las ultimás 3 noticias ( o las que queremos)
 */
package com.noticiasee.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.noticiasee.sessions.NoticiaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class LastNews extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            request.setAttribute("Noticias", noticiaFacade.getLastById());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
            
        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "No se han podido cargar las noticias....");
            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));
        }

    }

}
