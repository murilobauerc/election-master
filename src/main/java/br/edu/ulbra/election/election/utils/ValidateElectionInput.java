package br.edu.ulbra.election.election.utils;

import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.ElectionInput;
import org.apache.commons.lang.StringUtils;

public class ValidateElectionInput {
    private ValidateElectionInput(){}

    /**
     * Throw an generic exception if state code or/and description is blank.
     * Throw an generic exception if year is null.
     * @param electionInput object that can be a state code, description or year.
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

    /**
     * Throw an generic exception if the election's description does not contain at least 5 letters.
     * @param electionInput  object which case it is the description to be searched.
     * @throws GenericOutputException if name goes wrong.
     */
    public static void validateDescriptionName(ElectionInput electionInput){
        if(lengthElectionsDescription(electionInput) < 5) {
            throw new GenericOutputException("The name must have at least 5 letters.");
        }
    }


    /**
     * Get the length of the election's description (already trimmed)
     * @param electionInput object which case it is the description to be searched.
     * @return an integer number that is the length of the election's name
     */
    public static int lengthElectionsDescription(ElectionInput electionInput){
        return electionInput.getDescription().trim().length();
    }

}
