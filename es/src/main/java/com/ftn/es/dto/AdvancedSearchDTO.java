package com.ftn.es.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedSearchDTO {

    private String magazineName;
    private boolean magazineCheck;

    private String title;
    private boolean titleCheck;

    private String keyTerms;
    private boolean keyTermsCheck;

    private String content;
    private boolean contentCheck;

    private String authors;
    private boolean authorsCheck;

    
}
