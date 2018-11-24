package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/vote")
public class VoteApi {

    private final VoteService voteService;

    @Autowired
    public VoteApi(VoteService voteService){
        this.voteService = voteService;
    }

    @PutMapping("/")
    public GenericOutput electionVote(@RequestBody VoteInput voteInput){
        return voteService.electionVote(voteInput);
    }

    @PutMapping("/multiple")
    public GenericOutput multipleElectionVote(@RequestBody List<VoteInput> voteInputList){
        return voteService.multiple(voteInputList);
    }

    @GetMapping("/{voterId}")
    public Boolean getVoteByVoterId(@PathVariable Long voterId) {
        return voteService.getVoteByVoterId(voterId);
    }
}
