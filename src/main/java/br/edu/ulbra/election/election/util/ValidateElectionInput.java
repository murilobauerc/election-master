package br.edu.ulbra.election.election.util;

import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.ElectionInput;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(trimInsideOut(electionInput.getDescription()).length() < 5)  {
            throw new GenericOutputException("The description must have at least 5 letters.");
        }
    }

    public static void validateYearElection(ElectionInput electionInput){
        if(electionInput.getYear() < 2000 && electionInput.getYear() > 2200) {
            throw new GenericOutputException("The year must be greater than or equal to 2000 and less than 2200.");
        }
    }

    /**
     * Removes blank spaces in the beginning and the end
     * Replaces multiple blank spaces,if exists, to a single one
     *
     * @param word any string
     * @return trimmed string and treated at all
     */
    public static String trimInsideOut(String word){
        word = word.trim();
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(word);
        word = matcher.replaceAll(" ");

        return word;
    }
}
