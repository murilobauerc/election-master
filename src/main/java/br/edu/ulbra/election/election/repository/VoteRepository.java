package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRepository extends CrudRepository <Vote,Long>{
    Vote findByVoterId(Long VoterId);
    List<Vote> findByElection_Id(Long ElectionId);
    List<Vote> findByCandidateId(Long CandidateId);
}
