package com.ftn.es.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.Casopis;

@Repository
public interface CasopisRepository extends JpaRepository<Casopis, Long> {

}
