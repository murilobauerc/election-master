package br.edu.ulbra.election.election.utils;

import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.ElectionInput;
import org.apache.commons.lang.StringUtils;

public class ValidateElectionInput {
    private ValidateElectionInput(){}

    /**
     * Throw an generic exception if state code or/and description is blank.
     * Throw an generic exception if year is null.
     * @param electionInput can be a state code, description or year.
     * @param isUpdate boolean value that verifies whether it is updated in the database or not
     * @throws GenericOutputException if the fields state code, description or year goes wrong.
     */
    public static void validateInput(ElectionInput electionInput, boolean isUpdate){

        if (StringUtils.isBlank(electionInput.getStateCode())){
            throw new GenericOutputException(electionInput.getStateCode() + " Is an invalid state code.");
        }
        if (StringUtils.isBlank(electionInput.getDescription())){
            throw new GenericOutputException(electionInput.getDescription() + " Is an invalid description");
        }
        if (electionInput.getYear() == null){
            throw new GenericOutputException(electionInput.getYear() + " Is an invalid year");
        }
    }
}
