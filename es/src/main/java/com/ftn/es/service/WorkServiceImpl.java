package com.ftn.es.service;

import java.io.File;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.es.dto.NewWorkDTO;
import com.ftn.es.model.NaucniRad;
import com.ftn.es.model.WorkES;
import com.ftn.es.repositoryes.WorkESRepository;
import com.ftn.es.repositoryjpa.CasopisRepository;
import com.ftn.es.repositoryjpa.KorisnikRepository;
import com.ftn.es.repositoryjpa.NaucnaOblastRepository;
import com.ftn.es.repositoryjpa.NaucniRadRepository;

@Service
public class WorkServiceImpl implements WorkService {
	
	@Autowired
	private NaucniRadRepository naucniRadRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private CasopisRepository casopisRepository;
	
	@Autowired
	private NaucnaOblastRepository naucnaOblastRepository;
	
	@Autowired
	private WorkESRepository workESRepository;

	@Override
	public NaucniRad napraviRad(NewWorkDTO nw) {
		NaucniRad nr = new NaucniRad();
		nr.setApstrakt(nw.getApstrakt());
		nr.setAutor(korisnikRepository.getOne(nw.getAutorId()));
		nr.setBrojKoautora(0);
		nr.setCasopis(casopisRepository.getOne(nw.getCasopisId()));
		nr.setKljucniPojmovi(nw.getKljucneReci());
		nr.setKoautori(new ArrayList<>());
		nr.setNaucnaOblast(naucnaOblastRepository.getOne(nw.getNaucnaOblastId()));
		nr.setPutanja(nw.getPutanja());
		nr.setTitle(nw.getNaslov());
		nr.setRecenzenti(new ArrayList<>());
		return naucniRadRepository.save(nr);
	}

	@Override
	public void indeksiraj(NaucniRad nr) {
		WorkES workES = new WorkES();
        workES.setId(nr.getId());
        workES.setKeyTerms(nr.getKljucniPojmovi());
        workES.setMagazineName(nr.getCasopis().getNaziv());
        workES.setScienceArea(nr.getNaucnaOblast().getId());
        workES.setWorkAbstract(nr.getApstrakt());
        workES.setStatus("approved");
        workES.setTitle(nr.getTitle());

        workES.setAuthors(nr.getAutor().getIme() + " " + nr.getAutor().getPrezime());

        File file = new File(nr.getPutanja());
        String parsedText="";
        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = PDDocument.load(file);
            parsedText = pdfStripper.getText(pdDoc);
        }catch (Exception e){
            e.printStackTrace();
        }
        workES.setWorkContent(parsedText);
        this.workESRepository.save(workES);
	}
	

}
