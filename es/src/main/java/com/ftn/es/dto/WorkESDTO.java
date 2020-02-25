package com.ftn.es.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkESDTO {

	private Long id;
	
    private String title;
    
    private String workAbstract;
    
    private String highlight;
    
    private boolean openAcess;
    
    private String magazineName;
    
    private String authors;
	
}
