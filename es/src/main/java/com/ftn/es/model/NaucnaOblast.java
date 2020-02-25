package com.ftn.es.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaucnaOblast {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "naucneOblasti")
	private List<Korisnik> korisnici;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "naucneOblasti")
	private List<Casopis> casopisi;
	
	@JsonIgnore
	@OneToMany(mappedBy="naucnaOblast")
	private List<NaucniRad> naucniRadovi;
	
}
