package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.ReservationRepository;
import tn.esprit.springproject.entity.Etudiant;

import tn.esprit.springproject.Repository.EtudiantRepository;
import tn.esprit.springproject.entity.Reservation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;


@Service
@AllArgsConstructor
public class EtudiantService implements iEtudiantService {

    EtudiantRepository etudiantRespository;
    ReservationRepository reservationRespository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRespository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRespository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRespository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRespository.findById(idEtudiant);
        if (optionalEtudiant.isPresent()) {
            return optionalEtudiant.get();
        } else {
            // Handle the case where the Etudiant with the given ID doesn't exist
            // You might want to throw an exception, return null, or handle it differently based on your application logic
            return null; // or throw an exception
        }
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRespository.deleteById(idEtudiant);

    }

    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, Long idReservation) {
        Etudiant etudiant = etudiantRespository.findByNomEtAndPrenomEt(nomEt, prenomEt);
        Optional<Reservation> optionalReservation = reservationRespository.findById(idReservation);
        if (etudiant != null && optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            etudiant.getReservations().add(reservation);
            return etudiantRespository.save(etudiant);
        } else {
            return null;
        }
    }


    public Page<Etudiant> findEtudiantsWithPagination(int offset, int pageSize) {
        return etudiantRespository.findAll(PageRequest.of(offset, pageSize));
    }

    public ByteArrayInputStream etudiantPDFReport(List<Etudiant> etudiants) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Add Content to PDF file ->
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Etudiant Structure", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            // Add PDF Table Header ->
            Stream.of("ID", "NOM", "Prenom", "CIN", "Ecole", "date de Naissance").forEach(headerTitle -> {
                PdfPCell header = new PdfPCell();
                Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                header.setBackgroundColor(Color.CYAN);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(2);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });

            for (Etudiant etudiant : etudiants) {
                PdfPCell idCell = new PdfPCell(new Phrase(String.valueOf(etudiant.getIdEtudiant())));
                idCell.setPaddingLeft(4);
                idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase(etudiant.getNomEt()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase(String.valueOf(etudiant.getPrenomEt())));
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameCell.setPaddingRight(4);
                table.addCell(lastNameCell);

                PdfPCell deptCell = new PdfPCell(new Phrase(String.valueOf(etudiant.getCin())));
                deptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                deptCell.setPaddingRight(4);
                table.addCell(deptCell);

                PdfPCell phoneNumCell = new PdfPCell(new Phrase(String.valueOf(etudiant.getEcole())));
                phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneNumCell.setPaddingRight(4);
                table.addCell(phoneNumCell);

                PdfPCell date = new PdfPCell(new Phrase(String.valueOf(etudiant.getDateNaissance())));
                date.setVerticalAlignment(Element.ALIGN_MIDDLE);
                date.setHorizontalAlignment(Element.ALIGN_CENTER);
                date.setPaddingRight(4);
                table.addCell(date);
            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }


}
