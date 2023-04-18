/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1832.framework.servlet;

import etu1832.framework.Mapping;
import etu1832.framework.ModelView;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilitaires.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ONEF
 */
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls = new HashMap<>();

    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> MappingUrls) {
        this.MappingUrls = MappingUrls;
    }

    @Override
    public void init() {
        try {

            String path = this.getClass().getClassLoader().getResource("").getPath();
            path = path.replaceAll("%20", " ");
            Util.setMappingUrls(this.getMappingUrls(), path,
                    Util.getAllPackages(null, path.substring(1, path.length()), null));
            System.out.println("HashMap");
            for (Map.Entry<String, Mapping> entry : this.getMappingUrls().entrySet()) {
                System.out.println(entry.getKey() + "|" + entry.getValue().getMethod());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAttributeRequest(HttpServletRequest request, ModelView mv) {
        if (mv.getData() == null || mv.getData().size() == 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : mv.getData().entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    public Mapping getMapping(String url) throws Exception {
        if (this.MappingUrls.containsKey("/" + url)) {
            return this.MappingUrls.get("/" + url);
        }
        throw new Exception("Cette URL n' est associee a aucune classe");
    }

    public ModelView callModelAndFunction(Mapping map) throws Exception {
        Class c = Class.forName(map.getClassName());
        Method fonction = c.getDeclaredMethod(map.getMethod(), (Class[]) null);
        System.out.println("Fonction " + fonction.getName());
        Object o = c.newInstance();
        ModelView mv = (ModelView) fonction.invoke(o, (Object[]) null);
        System.out.println("Vue " + mv.getVue());
        return mv;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected ModelView processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] data = Util.retrieveDataFromURL(request.getRequestURI());
        System.out.println("URL du Client");
        for (int i = 0; i < data.length; i++) {
            String string = data[i];
            System.out.println(string);
        }
        try {
            if (data[data.length - 1].equalsIgnoreCase("")) {
                return new ModelView("index.html");
            }
            ModelView mv = callModelAndFunction(getMapping(data[data.length - 1]));
            this.setAttributeRequest(request, mv);
            System.out.println(mv.getVue());
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelView("error.html");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ModelView vue = processRequest(request, response);
        RequestDispatcher dispa = request.getRequestDispatcher("/WEB-INF/vues/" + vue.getVue());
        dispa.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
