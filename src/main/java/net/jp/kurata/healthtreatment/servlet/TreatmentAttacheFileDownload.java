/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jp.kurata.healthtreatment.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.jp.kurata.healthtreatment.jsf.TreatmentController;

/**
 *
 * @author yosh
 */
@WebServlet(name = "TreatmentAttacheFileDownload", urlPatterns = {"/TreatmentAttacheFileDownload"})
public class TreatmentAttacheFileDownload extends HttpServlet {

    @Inject
    TreatmentController treatmentController;

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
        try (OutputStream out = response.getOutputStream()) {
            AttachedFile treatmentAttachedFile = new AttachedFile(this.treatmentController.getFile());
            response.setContentType("application/force-download");
            response.setContentLength(treatmentAttachedFile.getDatafile().length);
            response.setHeader("Content-Disposition", "attachment; filename*=\"" + URLEncoder.encode(treatmentAttachedFile.getFilename(), "UTF-8") + "\"");
            out.write(treatmentAttachedFile.getDatafile());
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

class AttachedFile {

    private String filename;
    private byte[] datafile;

    public AttachedFile(File file) {
        this.setFileField(file);
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getDatafile() {
        return datafile;
    }

    public void setDatafile(byte[] datafile) {
        this.datafile = datafile;
    }

    final public void setFileField(File datafile) {
        int BUFFER_SIZE = 1024;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            InputStream inputStream = new FileInputStream(datafile);
            byte[] buffer = new byte[BUFFER_SIZE];
            for (int len = inputStream.read(buffer); len >= 0; len = inputStream.read(buffer)) {
                bout.write(buffer, 0, len);
            }
        } catch (IOException e) {

        } finally {

        }
        this.setDatafile(bout.toByteArray());
        this.setFilename(datafile.getName());
    }
}
