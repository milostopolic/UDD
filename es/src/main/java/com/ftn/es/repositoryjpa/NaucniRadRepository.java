package com.ftn.es.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.NaucniRad;

@Repository
public interface NaucniRadRepository extends JpaRepository<NaucniRad, Long> {

}
