package com.ftn.es.controller;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.es.dto.AdvancedSearchDTO;
import com.ftn.es.dto.RecenzentDTO;
import com.ftn.es.dto.WorkESDTO;
import com.ftn.es.model.Korisnik;
import com.ftn.es.model.NaucniRad;
import com.ftn.es.model.UserES;
import com.ftn.es.model.WorkES;
import com.ftn.es.repositoryes.UserESRepository;
import com.ftn.es.repositoryes.WorkESRepository;
import com.ftn.es.repositoryjpa.KorisnikRepository;
import com.ftn.es.repositoryjpa.NaucniRadRepository;

@RestController
@RequestMapping(path = "/api/search")
public class SearchController {

	@Autowired
	private WorkESRepository workESRepository;

	@Autowired
	private UserESRepository userESRepository;

	@Autowired
	private NaucniRadRepository naucniRadRepository;

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Autowired
	ElasticsearchOperations elasticsearchOperations;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/byTitle/{phrase}/{title}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchByTitle(@PathVariable String title, @PathVariable Long phrase) throws Exception {
		String query = "";
		
        query = "{" +
                "    \"query\": {";
        if(phrase == 0)
        	query += "  \"match\" : {";
        else if(phrase == 1)
        	query += "  \"match_phrase\" : {";
        
        query+= "            \"title\" : {" +
                "                \"query\" : \"" + title + "\"," +
                "                \"analyzer\":\"serbian\"" +
                "            }" +
                "        }" +
                "    }," +
                "    \"highlight\" : {" +
                "        \"fields\" : {" +
                "            \"title\" : {" +
                "            	\"type\":\"plain\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";
        
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetVal(locatedNode, "title");

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/byMagazineName/{phrase}/{magazineName}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchByMagazineName(@PathVariable String magazineName,	@PathVariable Long phrase) throws Exception {

		String query = "";
		
		query = "{" +
                "    \"query\": {";
        if(phrase == 0)
        	query += "  \"match\" : {";
        else if(phrase == 1)
        	query += "  \"match_phrase\" : {";
        
        query+= "            \"magazineName\" : {" +
                "                \"query\" : \"" + magazineName + "\"," +
                "                \"analyzer\":\"serbian\"" +
                "            }" +
                "        }" +
                "    }," +
                "    \"highlight\" : {" +
                "        \"fields\" : {" +
                "            \"magazineName\" : {" +
                "            	\"type\":\"plain\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetVal(locatedNode, "magazineName");

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/byKeyTerms/{phrase}/{keyTerms}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchByKeyTerms(@PathVariable String keyTerms, @PathVariable Long phrase) throws Exception {

		String query = "";
		
		query = "{" +
                "    \"query\": {";
        if(phrase == 0)
        	query += "  \"match\" : {";
        else if(phrase == 1)
        	query += "  \"match_phrase\" : {";
        
        query+= "            \"keyTerms\" : {" +
                "                \"query\" : \"" + keyTerms + "\"," +
                "                \"analyzer\":\"serbian\"" +
                "            }" +
                "        }" +
                "    }," +
                "    \"highlight\" : {" +
                "        \"fields\" : {" +
                "            \"keyTerms\" : {" +
                "            	\"type\":\"plain\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetVal(locatedNode, "keyTerms");

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/byAuthors/{phrase}/{authors}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchByAuthors(@PathVariable String authors, @PathVariable Long phrase) throws Exception {

		String query = "";
		
		query = "{" +
                "    \"query\": {";
        if(phrase == 0)
        	query += "  \"match\" : {";
        else if(phrase == 1)
        	query += "  \"match_phrase\" : {";
        
        query+= "            \"authors\" : {" +
                "                \"query\" : \"" + authors + "\"" +
                "            }" +
                "        }" +
                "    }," +
                "    \"highlight\" : {" +
                "        \"fields\" : {" +
                "            \"authors\" : {" +
                "            	\"type\":\"plain\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetVal(locatedNode, "authors");

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/byWorkContent/{phrase}/{workContent}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchByWorkContent(@PathVariable String workContent, @PathVariable Long phrase) throws Exception {

		String query = "";
		
		query = "{" +
                "    \"query\": {";
        if(phrase == 0)
        	query += "  \"match\" : {";
        else if(phrase == 1)
        	query += "  \"match_phrase\" : {";
        
        query+= "            \"workContent\" : {" +
                "                \"query\" : \"" + workContent + "\"," +
                "                \"analyzer\":\"serbian\"" +
                "            }" +
                "        }" +
                "    }," +
                "    \"highlight\" : {" +
                "        \"fields\" : {" +
                "            \"workContent\" : {" +
                "            	\"type\":\"plain\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetVal(locatedNode, "workContent");

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/byScienceAreas/{scienceAreas}", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> searchBySA(@PathVariable String scienceAreas) {
		String[] areas = scienceAreas.split("-");
		List<Long> areasIds = new ArrayList<>();
		for (int i = 0; i < areas.length; i++) {
			areasIds.add(Long.parseLong(areas[i]));
		}
		List<WorkES> workES = new ArrayList<>();
		workES = workESRepository.findByScienceAreaIn(areasIds);

		List<WorkESDTO> retVal = new ArrayList<>();
		for (WorkES wes : workES) {
			WorkESDTO dto = new WorkESDTO();

			dto.setId(wes.getId());
			dto.setTitle(wes.getTitle());
			dto.setMagazineName(wes.getMagazineName());
			dto.setWorkAbstract(wes.getWorkAbstract());
			dto.setAuthors(wes.getAuthors());
			retVal.add(dto);
		}

		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);
	}	

	@PostMapping(path = "/advancedSearch", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<WorkESDTO>> advancedSearch(@RequestBody AdvancedSearchDTO dto) throws Exception {

		String must = "\"must\" : [\n";

		String should = "\"should\" : [\n";

		boolean shouldBe = false;
		boolean mustBe = false;
		if (dto.isMagazineCheck() && !dto.getMagazineName().equals("")) {
			must += "{ \"match\" : { \"magazineName\" : \"" + dto.getMagazineName() + "\" } },";
			mustBe = true;
		} else if (!dto.isMagazineCheck() && !dto.getMagazineName().equals("")) {
			should += "{ \"match\" : { \"magazineName\" : \"" + dto.getMagazineName() + "\" } },";
			shouldBe = true;
		}

		if (dto.isTitleCheck() && !dto.getTitle().equals("")) {
			must += "{ \"match\" : { \"title\" : \"" + dto.getTitle() + "\" } },";
			mustBe = true;

		} else if (!dto.isTitleCheck() && !dto.getTitle().equals("")) {
			should += "{ \"match\" : { \"title\" : \"" + dto.getTitle() + "\" } },";
			shouldBe = true;
		}

		if (dto.isKeyTermsCheck() && !dto.getKeyTerms().equals("")) {
			must += "{ \"match\" : { \"keyTerms\" : \"" + dto.getKeyTerms() + "\" } },";
			mustBe = true;

		} else if (!dto.isKeyTermsCheck() && !dto.getKeyTerms().equals("")) {
			should += "{ \"match\" : { \"keyTerm\" : \"" + dto.getKeyTerms() + "\" } },";
			shouldBe = true;
		}

		if (dto.isAuthorsCheck() && !dto.getAuthors().equals("")) {
			must += "{ \"match\" : { \"authors\" : \"" + dto.getAuthors() + "\" } },";
			mustBe = true;

		} else if (!dto.isAuthorsCheck() && !dto.getAuthors().equals("")) {
			should += "{ \"match\" : { \"authors\" : \"" + dto.getAuthors() + "\" } },";
			shouldBe = true;
		}

		if (dto.isContentCheck() && !dto.getContent().equals("")) {
			must += "{ \"match\" : { \"workContent\" : \"" + dto.getContent() + "\" } },";
			mustBe = true;

		} else if (!dto.isContentCheck() && !dto.getContent().equals("")) {
			should += "{ \"match\" : { \"workContent\" : \"" + dto.getContent() + "\" } },";
			shouldBe = true;
		}
		must = must.substring(0, must.length() - 1);
		should = should.substring(0, should.length() - 1);
		must += "],";
		should += "],";
		String query="{\n" +
                "  \"query\": {\n" +
                "    \"bool\" : {\n";
		if (mustBe) {
			query += must;
		}

		if (shouldBe) {
			query += should;
		}
		
		query+="         \"boost\" : 1.0\n" +
                "    }\n" +
                "  },\n" +
                "\"highlight\" : {\n" +
                "        \"fields\" : {\n" +
                "            \"magazineName\" : {" +
                "               \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"title\" : {" +
                    "           \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"keyTerms\" : {" +
                    "             \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "       \"content\" : {" +
                    "               \t\"type\":\"plain\"\n" +
                            "},\n" +
                    "        \"authors\" : {" +
                    "               \t\"type\":\"plain\"\n" +
                            "}\n" +
                "        }\n" +
                "    }"+
                "}";

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> retVal = getRetValAdvanced(locatedNode);
		return new ResponseEntity<List<WorkESDTO>>(retVal, HttpStatus.OK);

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/indexReviewers")
	public ResponseEntity<?> indexReviewers() {

		List<BigInteger> rec = new ArrayList<>();
		List<Korisnik> korisnici = new ArrayList<>();
		rec = this.korisnikRepository.nadjiRecenzente();
		for (BigInteger b : rec) {
			korisnici.add(korisnikRepository.getOne(b.longValue()));
		}

		for (Korisnik korisnik : korisnici) {
			UserES userES = new UserES();
			userES.setId(korisnik.getId());
			userES.setReviewerId(korisnik.getId());
			userES.setName(korisnik.getIme() + " " + korisnik.getPrezime());
			userES.setLocation(new GeoPoint(Double.parseDouble(korisnik.getLatitude()),	Double.parseDouble(korisnik.getLongitude())));
			this.userESRepository.save(userES);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/getReviewersByScArea/{workId}")
	public ResponseEntity<List<RecenzentDTO>> getRevsByScArea(@PathVariable Long workId) {
		List<BigInteger> br = new ArrayList<>();
		NaucniRad nr = naucniRadRepository.getOne(workId);
		br = korisnikRepository.nadjiRecenzenteNaucneOblasti(nr.getNaucnaOblast().getId(), nr.getCasopis().getId());

		List<RecenzentDTO> retVal = new ArrayList<>();
		for (BigInteger b : br) {
			retVal.add(new RecenzentDTO(korisnikRepository.getOne(b.longValue())));
		}

		return new ResponseEntity<List<RecenzentDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/getMoreLikeThisReviewers/{workId}", produces = "application/json")
	public ResponseEntity<Set<RecenzentDTO>> getRevsByMoreLikeThis(@PathVariable Long workId) throws Exception {
		Set<RecenzentDTO> retVal = new HashSet<>();

		NaucniRad work = naucniRadRepository.getOne(workId);

		File file = new File(work.getPutanja());
		String parsedText = "";
		try {

			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDDocument pdDoc = PDDocument.load(file);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		parsedText = StringEscapeUtils.escapeJson(parsedText);

		String query="{" +
                "    \"query\": {" +
                "        \"more_like_this\" : {" +
                "            \"fields\" : [\"workContent\"]," +
                "            \"like\" : \""+parsedText+"\"," +
                "            \"min_term_freq\" : 1," +
                "            \"max_query_terms\" : 12," +
                "            \"min_doc_freq\": 1," +
                "            \"analyzer\" : \"serbian\""+
                "        }" +
                "    }" +
                "}";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/es/work/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		JsonNode locatedNode = rootNode.path("hits").path("hits");
		List<WorkESDTO> works = getRetVal(locatedNode, "workContent");
		for (WorkESDTO dto : works) {
			NaucniRad twork = this.naucniRadRepository.getOne(dto.getId());

			for (Korisnik rw : twork.getRecenzenti()) {
				RecenzentDTO choseReviewersDTO = new RecenzentDTO(rw);
				retVal.add(choseReviewersDTO);
			}
		}

		return new ResponseEntity<Set<RecenzentDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(path = "/getReviewersByLocation/{workId}", produces = "application/json")
	public ResponseEntity<List<RecenzentDTO>> getRevsByLocation(@PathVariable Long workId) throws Exception {

		NaucniRad work = naucniRadRepository.getOne(workId);
		Korisnik author = work.getAutor();

		Double lat = Double.parseDouble(author.getLatitude());
		Double lon = Double.parseDouble(author.getLongitude());

		String query="{" +
                "    \"query\": {" +
                "        \"bool\" : {" +
                "            \"must\" : {" +
                "                \"match_all\" : {}" +
                "            },\n" +
                "            \"filter\" : {" +
                "            \"bool\" : {"+
                "               \"must_not\" : {"+
                "                \"geo_distance\" : {" +
                "                    \"distance\" : \"100km\"," +
                "                    \"location\" : {" +
                "                        \"lat\" :"+lat+"," +
                "                        \"lon\" :"+lon+"" +
                "                    }" +
                "                }" +
                "               }"+
                "              }"+
                "            }" +
                "        }" +
                "    }"+
                "}";

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonquery = objectMapper.readTree(query);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<JsonNode> request = new HttpEntity<>(jsonquery);
		String fooResourceUrl = "http://localhost:9200/us/user/_search?pretty";
		ResponseEntity<String> response = restTemplate.postForEntity(fooResourceUrl, request, String.class);
		JsonNode rootNode = objectMapper.readTree(response.getBody());
		List<RecenzentDTO> retVal = this.getReviewersFromResponse(rootNode); 
		
		return new ResponseEntity<List<RecenzentDTO>>(retVal, HttpStatus.OK);
	}
	
	//  ************ POMOCNE FUNKCIJE ************
	public List<WorkESDTO> getRetVal(JsonNode node, String highlight) {
		List<WorkESDTO> retVal = new ArrayList<>();
		for (int i = 0; i < node.size(); i++) {
			WorkESDTO dto = new WorkESDTO();
			Long workId = Long.parseLong(node.get(i).path("_source").path("id").asText());
			dto.setAuthors(node.get(i).path("_source").path("authors").asText());
			dto.setId(workId);
			dto.setMagazineName(node.get(i).path("_source").path("magazineName").asText());
			dto.setTitle(node.get(i).path("_source").path("title").asText());
			dto.setWorkAbstract(node.get(i).path("_source").path("workAbstract").asText());
			String highText = "";
			for (int j = 0; j < node.get(i).path("highlight").path(highlight).size(); j++) {
				highText += node.get(i).path("highlight").path(highlight).get(j).asText() + "...";
			}
			dto.setHighlight(highText);
			retVal.add(dto);
		}
		return retVal;

	}
	
	public List<WorkESDTO> getRetValAdvanced(JsonNode node) {
		List<WorkESDTO> retVal = new ArrayList<>();
		for (int i = 0; i < node.size(); i++) {
			WorkESDTO dto = new WorkESDTO();
			Long workId = Long.parseLong(node.get(i).path("_source").path("id").asText());
			dto.setAuthors(node.get(i).path("_source").path("authors").asText());
			dto.setId(workId);
			dto.setMagazineName(node.get(i).path("_source").path("magazineName").asText());
			dto.setTitle(node.get(i).path("_source").path("title").asText());
			dto.setWorkAbstract(node.get(i).path("_source").path("workAbstract").asText());
			String highText = node.get(i).path("highlight").toString();
			highText = highText.replace("\"", "");
			highText = highText.replace("{", " ");
			highText = highText.replace("}", " ");
			highText = highText.replace("[", " ");
			highText = highText.replace("]", " ");
			dto.setHighlight(highText);
			retVal.add(dto);
		}
		return retVal;

	}

	public List<RecenzentDTO> getReviewersFromResponse(JsonNode rootNode) {
		List<RecenzentDTO> reviewers = new ArrayList<>();
		JsonNode locatedNode = rootNode.path("hits").path("hits");

		for (int i = 0; i < locatedNode.size(); i++) {
			RecenzentDTO choseReviewersDTO = new RecenzentDTO();
			Long reviewerId = Long.parseLong(locatedNode.get(i).path("_source").path("reviewerId").asText());
			choseReviewersDTO.setId(reviewerId);
			choseReviewersDTO.setIme(locatedNode.get(i).path("_source").path("name").asText());
			reviewers.add(choseReviewersDTO);
		}

		return reviewers;
	}

}
