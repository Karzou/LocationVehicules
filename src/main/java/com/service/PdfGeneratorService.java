package com.service;

import com.entity.*;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.io.IOException;

public class PdfGeneratorService {
    private static final Logger LOGGER = LogManager.getLogger(PdfGeneratorService.class);

    EntityManager em;

    //si pas utilise, a enlever
    public PdfGeneratorService(EntityManager em) {
        this.em = em;
    }

    public InputStream generateFacturePdf(int factureId) throws ServiceException, IOException {

        FactureService factureService = new FactureService(em);
        //JE RECUPERE LA FACTURE
        Facture facture = factureService.findById(factureId);

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        manageReservation(font, contentStream, facture.getContratsByIdContrat());

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 640);
        contentStream.drawString("Numéro facture : " + String.valueOf(facture.getIdFacture()));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 620);
        contentStream.drawString("Date facture : " + String.valueOf(facture.getDateFacture()));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 600);
        contentStream.drawString("Prix : " + String.valueOf(facture.getPrixFacture()) + " €");
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();

// Save the results and ensure that the document is properly closed:
//        document.save( "Hello World.pdf");
//
//        document.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }

    private void manageReservation(PDFont font, PDPageContentStream contentStream, Contrat contratsByIdContrat) throws IOException {
        final Reservation reservation = contratsByIdContrat.getReservation();
        //Continuation du chemin
        final Utilisateur utilisateursByIdUtilisateur = reservation.getUtilisateursByIdUtilisateur();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 700);
        contentStream.drawString("Nom : " + String.valueOf(utilisateursByIdUtilisateur.getNomUtilisateur()));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 680);
        contentStream.drawString("Prénom : " + String.valueOf(utilisateursByIdUtilisateur.getPrenomUtilisateur()));
        contentStream.endText();

        final Adresse adressesByIdAdresse = utilisateursByIdUtilisateur.getAdressesByIdAdresse();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 660);
        contentStream.drawString("Adresse : " + String.valueOf(adressesByIdAdresse.getRue() + " " + adressesByIdAdresse.getNumero()));
        contentStream.endText();
    }


    public InputStream generateContratPdf(int contratId) throws ServiceException, IOException {

        ContratService contratService = new ContratService(em);
        //JE RECUPERE LA FACTURE
        Contrat contrat = contratService.findById(contratId);

        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        manageReservation(font, contentStream, contrat);

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 640);
        contentStream.drawString("Numéro de contrat : " + String.valueOf(contrat.getIdContrat()));
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 620);
        contentStream.drawString("Acompte : " + String.valueOf(contrat.getAcompte()) + " €");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 600);
        contentStream.drawString("Caution : " + String.valueOf(contrat.getCaution()) + " €");
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.moveTextPositionByAmount(100, 580);
        contentStream.drawString("Etat: " + String.valueOf(contrat.getEtat()));
        contentStream.endText();

// Make sure that the content stream is closed:
        contentStream.close();

// Save the results and ensure that the document is properly closed:
//        document.save( "Hello World.pdf");
//
//        document.close();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }
}
