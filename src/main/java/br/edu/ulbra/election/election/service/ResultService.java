package br.edu.ulbra.election.election.service;


import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import br.edu.ulbra.election.election.output.v1.ElectionCandidateResultOutput;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.output.v1.ResultOutput;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {
    private final VoteRepository voteRepository;
    private final ElectionRepository electionRepository;
    private final CandidateClientService candidateClientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultService(VoteRepository voteRepository, ElectionRepository electionRepository, CandidateClientService candidateClientService, ModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
        this.candidateClientService = candidateClientService;
        this.modelMapper = modelMapper;
    }

    public ElectionCandidateResultOutput getById(Long candidateId){
        ElectionCandidateResultOutput electionCandidateResultOutput = new ElectionCandidateResultOutput();
        long getAllCandidatesByCandidateId;
        try {
            electionCandidateResultOutput.setCandidate(candidateClientService.getById(candidateId));
        } catch (FeignException e) {
            if (e.status() == 500) {
                throw new GenericOutputException("Invalid Candidate.");
            }
        }

        getAllCandidatesByCandidateId = voteRepository.findByCandidateId(candidateId).size();
        electionCandidateResultOutput.setTotalVotes(getAllCandidatesByCandidateId);
        return electionCandidateResultOutput;
    }

    public ResultOutput getResultByElection(Long electionId){
        ResultOutput resultOutput = new ResultOutput();
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException("Invalid election.");
        }
        resultOutput.setElection(modelMapper.map(election, ElectionOutput.class));
        List<Vote> votes = voteRepository.findByElection_Id(electionId);
        List<CandidateOutput> candidates = candidateClientService.getAll();
        List<ElectionCandidateResultOutput> electionCandidateResultOutputsList = new ArrayList();

        long blankVotes = 0;
        long nullVotes = 0;
        long allVotes = votes.size();
        for (Vote vote : votes) {
            if(vote.getBlankVote() && vote.getCandidateId() == null) {
                blankVotes++;
            }else if(vote.getNullVote() && vote.getCandidateId() == null){
                nullVotes++;
            }
        }

        resultOutput.setBlankVotes(blankVotes);
        resultOutput.setNullVotes(nullVotes);


        resultOutput.setTotalVotes(allVotes);

        for(int i = 0; i < candidates.size(); i++){
            if(candidates.get(i).getElectionOutput().getId().compareTo(electionId) == 0){
                electionCandidateResultOutputsList.add(getById(candidates.get(i).getId()));
            }
        }
        resultOutput.setCandidates(electionCandidateResultOutputsList);

        return resultOutput;
    }
}