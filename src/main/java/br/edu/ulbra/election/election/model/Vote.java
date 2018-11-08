package br.edu.ulbra.election.election.model;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long electionId;

    @Column(nullable = false)
    private Long voterId;

    @Column(nullable = false)
    private Long candidateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

}
