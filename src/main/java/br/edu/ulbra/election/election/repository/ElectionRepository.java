package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Election;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElectionRepository extends CrudRepository<Election, Long> {
    List<Election> findByYear(Integer year);
    Election findFirstByYearAndStateCodeAndDescription(Integer year, String stateCode, String description);
}




