package com.ftn.es.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.es.dto.NewWorkDTO;
import com.ftn.es.dto.StringDTO;
import com.ftn.es.model.Casopis;
import com.ftn.es.model.NaucnaOblast;
import com.ftn.es.model.NaucniRad;
import com.ftn.es.repositoryjpa.CasopisRepository;
import com.ftn.es.repositoryjpa.KorisnikRepository;
import com.ftn.es.repositoryjpa.NaucnaOblastRepository;
import com.ftn.es.repositoryjpa.NaucniRadRepository;
import com.ftn.es.service.WorkService;


@RestController
@RequestMapping("/api/work")
public class WorkController {
	
	@Autowired
	private CasopisRepository casopisRepository;
	
	@Autowired
	private NaucnaOblastRepository naucnaOblastRepository;
	
	@Autowired
	private NaucniRadRepository naucniRadRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private WorkService workService;
	
	@GetMapping(path = "/getAllMagazines", produces = "application/json")
	public @ResponseBody ResponseEntity<List<Casopis>> getAllMagaz() {
		List<Casopis> cas = new ArrayList<>();
		cas = casopisRepository.findAll();
		return new ResponseEntity<List<Casopis>>(cas, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getNaucneOblasti", produces = "application/json")
	public @ResponseBody ResponseEntity<List<NaucnaOblast>> getNaucneOblasti() {
		List<NaucnaOblast> oblasti = naucnaOblastRepository.findAll();
		return new ResponseEntity<List<NaucnaOblast>>(oblasti, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/uploadPDF", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> uploadFile(@RequestParam("File") MultipartFile request) {
		String returnValue ="";
		try {
			returnValue = saveImage(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(returnValue, HttpStatus.OK);

	}
	
	public String saveImage(MultipartFile file) throws IOException {
		String folder = "src/main/resources/";
		byte[] bytes = file.getBytes();
		Path path = Paths.get(folder + file.getOriginalFilename());
		Files.write(path, bytes);
		return path.toAbsolutePath().toString();
	}
	
	@PostMapping(path = "/postWorkBasicInfo", produces = "application/json")
    public ResponseEntity<NaucniRad> postChooseMagazine(@RequestBody NewWorkDTO nw) {
		NaucniRad w = workService.napraviRad(nw);
		workService.indeksiraj(w);
        return new ResponseEntity<NaucniRad>(w, HttpStatus.OK);
    }
	
	@GetMapping("/pdfname/{wid}")
	public ResponseEntity<StringDTO> getp(@PathVariable Long wid) {
		NaucniRad r = naucniRadRepository.getOne(wid);
		return new ResponseEntity<StringDTO>(new StringDTO(r.getPutanja()), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/postReviewers/{wid}")
	public ResponseEntity<?> postRevs(@PathVariable Long wid, @RequestBody List<Long> req) {
		NaucniRad r = naucniRadRepository.getOne(wid);
		for(Long l : req) {
			r.getRecenzenti().add(korisnikRepository.getOne(l));
		}
		naucniRadRepository.save(r);
		
		return new ResponseEntity(HttpStatus.OK);
	}

}
