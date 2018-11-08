package br.edu.ulbra.election.election.util;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.VoteInput;

public class ValidateVoteInput {
    private ValidateVoteInput(){}

    public static void validateInput(VoteInput voteInput) {
        if (voteInput.getCandidateId() == null) {
            throw new GenericOutputException(voteInput.getCandidateId() + "Is an invalid candidate id");
        }

        if (voteInput.getElectionId() == null) {
            throw new GenericOutputException(voteInput.getElectionId() + "Is an invalid election id");
        }

        if (voteInput.getVoterId() == null) {
            throw new GenericOutputException(voteInput.getVoterId() + "Is an invalid voter id");
        }
    }
}
