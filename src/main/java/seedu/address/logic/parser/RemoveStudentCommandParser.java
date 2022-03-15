package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

public class RemoveStudentCommandParser implements Parser<RemoveStudentCommand> {

    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap;

        if (args.contains(PREFIX_STUDENTID.getPrefix())) {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID, PREFIX_TUTORIALNAME);

            if (!arePrefixesPresent(argMultimap,PREFIX_STUDENTID, PREFIX_TUTORIALNAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveStudentCommand.MESSAGE_USAGE));
            }

            NusNetId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new RemoveStudentCommand(studentId, tutorialName);
        } else {
            argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_TUTORIALNAME);

            if (!arePrefixesPresent(argMultimap,PREFIX_INDEX, PREFIX_TUTORIALNAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveStudentCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new RemoveStudentCommand(index, tutorialName);
        }




    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
