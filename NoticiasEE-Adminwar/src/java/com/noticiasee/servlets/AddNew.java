package com.noticiasee.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.noticiasee.entities.Noticia;
import com.noticiasee.sessions.NoticiaFacade;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jdk.nashorn.internal.objects.NativeError;
import org.jsoup.Jsoup;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddNew", urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNew extends HttpServlet {

    @EJB
    private NoticiaFacade noticiaFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            File fileSaveDir = new File(System.getProperty("imagenes.noticias"));
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            Part filePart = request.getPart("inputImg");

            InputStream fileContent = filePart.getInputStream();

            BufferedImage image = ImageIO.read(fileContent);
            Date d = new Date();

            if (image != null) {

                String title = request.getParameter("title");
                String body = request.getParameter("description");

                Noticia noticia = new Noticia();

                noticia.setTitle(title);

                noticia.setBody(body);
                noticia.setShortDesc(parseHtml(body).substring(0, 200) + "..."); //generamos el short desc 
                noticia.setPosted(d);
                noticia.setImgLink("");
                request.authenticate(response);
                noticia.setAuthor(request.getRemoteUser());
                noticiaFacade.create(noticia);//crear la noticia para el id-----

                File ofB = new File(System.getProperty("imagenes.noticias"), noticia.getId() + ".jpg"); //jpg para no guardar transparencias
                OutputStream osB = new FileOutputStream(ofB);
                ImageIO.write(image, "jpg", ofB);

                noticiaFacade.edit(noticia);//update a la noticia

                Map<String, String> mess = new HashMap<>();
                mess.put("id", noticia.getId().toString());
                Gson gson = new GsonBuilder().create();
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(mess));

            } else {

                Map<String, String> emess = new HashMap<>();
                emess.put("error", "El archivo no es una imagen");
                Gson gson = new GsonBuilder().create();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(emess));
            }

        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Error interno del servidor");
            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));
        }

    }

    public String parseHtml(String html) {
        return Jsoup.parse(html).text();
    }
}
