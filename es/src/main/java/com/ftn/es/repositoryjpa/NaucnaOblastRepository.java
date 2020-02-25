package com.ftn.es.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.NaucnaOblast;

@Repository
public interface NaucnaOblastRepository extends JpaRepository<NaucnaOblast, Long>{

}
