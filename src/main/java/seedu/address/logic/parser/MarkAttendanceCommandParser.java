package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAttendanceCommand
     * and returns an MarkAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALNAME, PREFIX_NAME, PREFIX_WEEK);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIALNAME, PREFIX_WEEK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAttendanceCommand.MESSAGE_USAGE));
        }

        TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
        Name studentName;
        boolean isStudentNamePresent = false;
        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            isStudentNamePresent = true;
            studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        } else {
            studentName = null;
        }
        int week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());

        return new MarkAttendanceCommand(tutorialName, studentName, week, !isStudentNamePresent);
    }

}
