package com.tp_eburtis.tps.repository;

import com.tp_eburtis.tps.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
