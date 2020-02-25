package com.ftn.es.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.es.model.Korisnik;
import com.ftn.es.model.NaucnaOblast;
import com.ftn.es.repositoryjpa.KorisnikRepository;


@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public Korisnik findByEmail(String email) {
        return this.korisnikRepository.findOneByEmail(email);
    }

    @Override
    public Korisnik saveSystemUser(Korisnik systemUser) {
        return this.korisnikRepository.saveAndFlush(systemUser);
    }

    @Override
    public List<Korisnik> findAll() {
        return this.korisnikRepository.findAll();
    }

    @Override
    public int checkCredentials(String username, String email) {
        Korisnik systemUser=null;
        int retval=0;
        systemUser=this.korisnikRepository.findByUsername(username);
        if(systemUser != null){
            retval+=1;
        }

        systemUser=this.korisnikRepository.findOneByEmail(email);
        if(systemUser != null){
            retval+=0;
        }

        return retval;
    }

    @Override
    public Set<Korisnik> findReviewersByScienceArea(NaucnaOblast scienceArea) {
        return null;
//        return this.korisnikRepository.findSystemUsersByReviewerAndScientificAreasContaining("yes",scienceArea);
    }

    @Override
    public Korisnik findByUsername(String username) {
        return this.korisnikRepository.findByUsername(username);
    }

    @Override
    public void deleteSystemUser(Korisnik systemUser) {
        this.korisnikRepository.delete(systemUser);
    }

    @Override
    public Korisnik findOneById(Long id) {
        return this.korisnikRepository.findOneById(id);
    }

    @Override
    public List<Korisnik> findAllreviewers() {
        List<Korisnik> systemUsers=this.korisnikRepository.findAllByRecenzent("yes");
        return  systemUsers;
    }

}
