package com.ftn.es.dto;

import com.ftn.es.model.Korisnik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecenzentDTO {
	
	private Long id;
	
	private String ime;
	
	public RecenzentDTO(Korisnik k) {
		this.id = k.getId();
		this.ime = k.getIme() + " " + k.getPrezime();
	}

}
