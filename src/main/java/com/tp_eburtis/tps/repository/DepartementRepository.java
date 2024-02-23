package com.tp_eburtis.tps.repository;

import com.tp_eburtis.tps.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface DepartementRepository  extends JpaRepository<Departement, Long> {
}
