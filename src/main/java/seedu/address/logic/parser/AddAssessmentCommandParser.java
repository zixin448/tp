package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FULL_MARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Weightage;

public class AddAssessmentCommandParser implements Parser<AddAssessmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAssessmentCommand
     * and returns an AddAssessmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddAssessmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ASSESSMENT_NAME, PREFIX_WEIGHTAGE, PREFIX_FULL_MARK);

        if(!arePrefixesPresent(argumentMultimap, PREFIX_ASSESSMENT_NAME, PREFIX_WEIGHTAGE, PREFIX_FULL_MARK)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAssessmentCommand.MESSAGE_USAGE));
        }

        // Validation checks for the fields are carried out during construction
        AssessmentName assessmentName = new AssessmentName(argumentMultimap.getValue(PREFIX_ASSESSMENT_NAME).get());
        Weightage weightage = new Weightage(argumentMultimap.getValue(PREFIX_WEIGHTAGE).get());
        FullMark fullMark = new FullMark(argumentMultimap.getValue(PREFIX_FULL_MARK).get());

        Assessment assessment = new Assessment(assessmentName, weightage, fullMark);

        return new AddAssessmentCommand(assessment);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
