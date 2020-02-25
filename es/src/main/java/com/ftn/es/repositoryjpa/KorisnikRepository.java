package com.ftn.es.repositoryjpa;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.es.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	boolean existsByEmail(String username);

	@Query("select u from Korisnik u where u.id = ?#{principal.id}")
	Korisnik findCurrentUser();
	
	@Query("select u from Korisnik u where u.id != ?#{principal.id}")
	List<Korisnik> findUsersExceptSelf();
	
	Korisnik findByUsername(String username);
    Korisnik findOneByEmail(String email);
    Korisnik findOneById(Long id);
    List<Korisnik> findAllByRecenzent(String isReviewer);
    
    @Modifying
	@Query(value = "SELECT kr.korisnik_id FROM  casopis_recenzenti cu, korisnik_authority kr, korisnik_naucna_oblast kno where kr.authority_id=2 and kr.korisnik_id=cu.korisnik_id and cu.casopis_id=?2 and kno.korisnik_id=cu.korisnik_id and kno.naucna_oblast_id=?1", nativeQuery = true)
	List<BigInteger> nadjiRecenzenteNaucneOblasti(Long noid, Long cid);
    
    @Modifying
	@Query(value = "SELECT kr.korisnik_id FROM  korisnik_authority kr where kr.authority_id=2", nativeQuery = true)
	List<BigInteger> nadjiRecenzente();
}
