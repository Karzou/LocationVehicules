package com.controller;

import com.connection.EMF;
import com.entity.Facture;
import com.exception.ServiceException;
import com.service.FactureService;
import com.service.PdfGeneratorService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/pdf")
public class PdfServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(PdfServlet.class);

    //doget Méthode pour appeller la page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Ajout d'une ligne de type info dans le log
        logger.info("je passe par la page facture");

        EntityManager em = EMF.getEM();

        //objet de type FactureService permettant de faire des traitements sur la DB (ajouter factures, m à j factures,...)
        PdfGeneratorService pdfGeneratorService = new PdfGeneratorService(em);
        InputStream inputStream = null;
        try {
            if ("facture".equals(request.getParameter("type"))) {
                //permert d'aller rechercher la valeur du paramètre de facture id
                String factureId = request.getParameter("factureId");
                inputStream = pdfGeneratorService.generateFacturePdf(Integer.valueOf(factureId));
            } else if ("contrat".equals(request.getParameter("type"))) {
                String contratId = request.getParameter("contratId");
                inputStream = pdfGeneratorService.generateContratPdf(Integer.valueOf(contratId));
            } else
            {
                throw new Exception("La tâche à faire est inconnue!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (logger.isInfoEnabled()) {
                logger.info("Fermeture de l'EntityManager");
            }
            em.close();
        }

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=Documentation.pdf");
        OutputStream responseOutputStream = response.getOutputStream();

        byte[] buf = new byte[4096];
        int len = -1;

        while ((len = inputStream.read(buf)) != -1) {
            responseOutputStream.write(buf, 0, len);
        }
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    //utiliser pour un formulaires avec le submit
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
