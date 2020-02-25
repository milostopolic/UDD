package com.ftn.es.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ftn.es.model.Korisnik;
import com.ftn.es.model.NaucnaOblast;

@Service
public interface KorisnikService {

    Korisnik findByEmail(String email);
    Korisnik findByUsername(String username);
    Korisnik saveSystemUser(Korisnik systemUser);
    List<Korisnik> findAll();
    int checkCredentials(String username, String email);
    void deleteSystemUser(Korisnik systemUser);
    Set<Korisnik> findReviewersByScienceArea(NaucnaOblast scienceArea);
    Korisnik findOneById(Long id);
    List<Korisnik> findAllreviewers();
}
