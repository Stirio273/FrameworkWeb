/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu1832.framework.servlet;

import etu1832.framework.FileUpload;
import etu1832.framework.Mapping;
import etu1832.framework.ModelView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import utilitaires.Util;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ONEF
 */
@MultipartConfig
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls = new HashMap<>();
    HashMap<String, Object> singleton = new HashMap<>();

    public HashMap<String, Object> getSingleton() {
        return singleton;
    }

    public void setSingleton(HashMap<String, Object> singleton) {
        this.singleton = singleton;
    }

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
            Util.setFieldsFrontServlet(this.getMappingUrls(), this.getSingleton(), path,
                    Util.getAllPackages(null, path.substring(1, path.length()), null));
            System.out.println("HashMap URL");
            for (Map.Entry<String, Mapping> entry : this.getMappingUrls().entrySet()) {
                System.out.println(entry.getKey() + "|" + entry.getValue().getMethod());
            }
            System.out.println("HashMap Singleton");
            for (Map.Entry<String, Object> entry : this.getSingleton().entrySet()) {
                System.out.println(entry.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] partToByte(Part part) throws Exception {
        InputStream is = part.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int byteReader;
        while ((byteReader = is.read(buffer)) != -1) {
            baos.write(buffer, 0, byteReader);
        }
        byte[] byteArray = baos.toByteArray();
        baos.close();
        is.close();
        return byteArray;
    }

    public void manageFileUpload(Object o, Field attribut, Part part) throws Exception {
        attribut.setAccessible(true);
        System.out.println(part.getSubmittedFileName());
        if (part.getSize() > 0) {
            byte[] b = this.partToByte(part);
            attribut.set(o, new FileUpload(part.getSubmittedFileName(), b));
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

    public void fillAttributeOfObject(Object o, HttpServletRequest request) throws Exception {
        Field[] attributs = o.getClass().getDeclaredFields();
        for (Field field : attributs) {
            field.setAccessible(true);
            if (field.getType() == FileUpload.class) {
                try {
                    this.manageFileUpload(o, field, request.getPart(field.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String value = request.getParameter(field.getName());
                if (value != null) {
                    field.set(o, Util.castString(value, field.getType()));
                }
            }
        }
    }

    public Object instanceObject(Mapping map) throws Exception {
        if (this.singleton.containsKey(map.getClassName())) {
            if (this.singleton.get(map.getClassName()) != null) {
                Util.resetObject(this.singleton.get(map.getClassName()));
                return this.singleton.get(map.getClassName());
            } else {
                this.singleton.replace(map.getClassName(), Class.forName(map.getClassName()).newInstance());
                return this.singleton.get(map.getClassName());
            }
        }
        Class<?> c = Class.forName(map.getClassName());
        Object o = c.newInstance();
        return o;
    }

    public ModelView callModelAndFunction(Object o, Method fonction, Object[] params, Mapping map) throws Exception {
        System.out.println("Fonction " + fonction.getName());
        ModelView mv = (ModelView) fonction.invoke(o, params);
        System.out.println("Vue " + mv.getVue());
        return mv;
    }

    public Object[] fillArgumentsOfFonction(Method fonction, HttpServletRequest request) throws Exception {
        Parameter[] arguments = fonction.getParameters();
        Object[] params = new Object[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            String value = request.getParameter(arguments[i].getName());
            System.out.println(arguments[i].getName());
            if (value != null) {
                params[i] = Util.castString(value, arguments[i].getType());
            }
        }
        return params;
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
            Mapping map = getMapping(data[data.length - 1]);
            Object o = this.instanceObject(map);
            Method fonction = o.getClass().getDeclaredMethod(map.getMethod(), map.getParamsType());
            this.fillAttributeOfObject(o, request);
            Object[] params = this.fillArgumentsOfFonction(fonction, request);
            ModelView mv = callModelAndFunction(o, fonction, params, map);
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
        ModelView vue = processRequest(request, response);
        RequestDispatcher dispa = request.getRequestDispatcher("/WEB-INF/vues/" + vue.getVue());
        dispa.forward(request, response);
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
