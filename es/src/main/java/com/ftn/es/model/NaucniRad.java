package com.ftn.es.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NaucniRad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@JsonIgnore
	@ManyToOne
	private Casopis casopis;
	
	@JsonIgnore
	@ManyToOne
	private NaucnaOblast naucnaOblast;
	
	private String kljucniPojmovi;
	
	private String apstrakt;
	
	@Column(nullable=true)
	private String putanja;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "NaucniRad_Koautori", joinColumns = {
			@JoinColumn(name = "naucni_rad_id") }, inverseJoinColumns = { @JoinColumn(name = "koautor_id") })
	private List<Koautor> koautori; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "NaucniRad_Recenzenti", joinColumns = {
			@JoinColumn(name = "naucni_rad_id") }, inverseJoinColumns = { @JoinColumn(name = "korisnik_id") })
	private List<Korisnik> recenzenti; 
	
	private int brojKoautora;
	
	@JsonIgnore
	@ManyToOne
	private Korisnik autor;
	
	//pdf
	
	//konacna verzija?!
	
}
