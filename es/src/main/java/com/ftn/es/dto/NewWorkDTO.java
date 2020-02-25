package com.ftn.es.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewWorkDTO {

	private Long id;
	
	private Long casopisId;
	
	private Long autorId;
	
	private String naslov;
	
	private String kljucneReci;
	
	private String apstrakt;
	
	private Long naucnaOblastId;
	
	private String putanja;
	
}
