/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noticiasee.servlets;

import com.noticiasee.sessions.NoticiaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
public class GetNew extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(URLDecoder.decode(req.getPathInfo(), "UTF-8").substring(1));
        try {
            int i = Integer.parseInt(URLDecoder.decode(req.getPathInfo(), "UTF-8").substring(1));            
            req.setAttribute("Noticia", noticiaFacade.getById(i));
            RequestDispatcher rd = req.getRequestDispatcher("/noticia.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("Noticia", noticiaFacade.getById(Integer.parseInt(request.getParameter("noticiaID"))));
            RequestDispatcher rd = request.getRequestDispatcher("/noticia.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
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
