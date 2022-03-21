package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddScoreCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.person.Name;

public class AddScoreCommandParser implements Parser<AddScoreCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddScoreCommand
     * and returns an AddScoreCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddScoreCommand parse(String userInput) throws ParseException {
        ArgumentMultimap multimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_ASSESSMENTNAME, PREFIX_NAME, PREFIX_SCORE);

        if (!arePrefixesPresent(multimap, PREFIX_ASSESSMENTNAME, PREFIX_NAME, PREFIX_SCORE)
                || !multimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddScoreCommand.MESSAGE_USAGE));
        }

        // Validation checks for the fields are carried out during construction
        AssessmentName assessmentName = ParserUtil.parseAssessmentName(multimap
                .getValue(PREFIX_ASSESSMENTNAME).get());
        Name name = ParserUtil.parseName(multimap.getValue(PREFIX_NAME).get());
        String score = ParserUtil.parseScore(multimap.getValue(PREFIX_SCORE).get());

        return new AddScoreCommand(assessmentName, name, score);
    }

    private boolean arePrefixesPresent(ArgumentMultimap multimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> multimap.getValue(prefix).isPresent());
    }
}
