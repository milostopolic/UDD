package com.ftn.es.repositoryjpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.Koautor;

@Repository
public interface KoautorRepository extends JpaRepository<Koautor, Long> {

}
