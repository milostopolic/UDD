package com.ftn.es.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ime;

	private String prezime;

	private String grad;

	private String drzava;

	private String email;

	private boolean recenzent;

	private String titula;

	private String username;

	private String password;

	@Column
	private String longitude;

	@Column
	private String latitude;

	@Column(name = "enabled", nullable = false, columnDefinition = "boolean default false")
	private boolean enabled;

	@Column(name = "non_locked", nullable = false, columnDefinition = "boolean default true")
	private boolean nonLocked;

	@Column(name = "aktiviran", nullable = false, columnDefinition = "boolean default false")
	private boolean aktiviran;

	@ManyToMany
	@JoinTable(name = "Korisnik_NaucnaOblast", joinColumns = {
			@JoinColumn(name = "korisnik_id") }, inverseJoinColumns = { @JoinColumn(name = "naucna_oblast_id") })
	private List<NaucnaOblast> naucneOblasti;

	@ManyToMany(mappedBy = "recenzenti")
	private List<Casopis> casopisiRec;

	@ManyToMany(mappedBy = "urednici")
	private List<Casopis> casopisiUred;

	@OneToMany(mappedBy = "glavniUrednik")
	private List<Casopis> casopisiGlUred;

	@OneToMany(mappedBy = "autor")
	private List<NaucniRad> naucniRadovi;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "korisnik_authority", joinColumns = @JoinColumn(name = "korisnik_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities;

	@JsonIgnore
	@ManyToMany(mappedBy = "recenzenti")
	private List<NaucniRad> naucniRadoviRecenziranje;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
