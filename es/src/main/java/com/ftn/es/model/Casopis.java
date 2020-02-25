package com.ftn.es.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Casopis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String naziv;
	
	private String issn;
	
	private boolean aktiviran;
	
	private String nacinPlacanja;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Casopis_NaucnaOblast", joinColumns = {
			@JoinColumn(name = "casopis_id") }, inverseJoinColumns = { @JoinColumn(name = "naucna_oblast_id") })
	private List<NaucnaOblast> naucneOblasti; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Casopis_Recenzenti", joinColumns = {
			@JoinColumn(name = "casopis_id") }, inverseJoinColumns = { @JoinColumn(name = "korisnik_id") })
	private List<Korisnik> recenzenti; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Casopis_Urednici", joinColumns = {
			@JoinColumn(name = "casopis_id") }, inverseJoinColumns = { @JoinColumn(name = "korisnik_id") })
	private List<Korisnik> urednici; 
	
	@JsonIgnore
	@ManyToOne
    protected Korisnik glavniUrednik;
	
	@JsonIgnore
	@OneToMany(mappedBy="casopis")
	private List<NaucniRad> naucniRadovi;
	
	
	

}
