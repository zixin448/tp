package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_TAG, PREFIX_STUDENTID, PREFIX_TUTORIALNAME);

        String name = argMultimap.getValue(PREFIX_NAME).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_NAME).get();
        String phone = argMultimap.getValue(PREFIX_PHONE).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_PHONE).get();
        String email = argMultimap.getValue(PREFIX_EMAIL).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_EMAIL).get();
        String address = argMultimap.getValue(PREFIX_ADDRESS).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_ADDRESS).get();
        String tag = argMultimap.getValue(PREFIX_TAG).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_TAG).get();
        String studentId = argMultimap.getValue(PREFIX_STUDENTID).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_STUDENTID).get();
        String tutorialName = argMultimap.getValue(PREFIX_TUTORIALNAME).get() == null ? "NULL_INPUT" : argMultimap.getValue(PREFIX_TUTORIALNAME).get();

        boolean isPrefixPresent =
                !name.equals("NULL_INPUT")
                || !phone.equals("NULL_INPUT")
                || !email.equals("NULL_INPUT")
                || !address.equals("NULL_INPUT")
                || !tag.equals("NULL_INPUT")
                || !studentId.equals("NULL_INPUT")
                || !tutorialName.equals("NULL_INPUT");

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        if (isPrefixPresent) {
            String[] nameByPrefixKeywords = name.split("\\s+");
            String[] phoneByPrefixKeywords = phone.split("\\s+");
            String[] emailByPrefixKeywords = email.split("\\s+");
            String[] addressByPrefixKeywords = address.split("\\s+");
            String[] tagByPrefixKeywords = tag.split("\\s+");
            String[] studentIdByPrefixKeywords = studentId.split("\\s+");
            String[] tutorialNameByPrefixKeywords = tutorialName.split("\\s+");
            return new FindByPrefixCommand(
                    new NameContainsKeywordsPredicate(Arrays.asList(nameByPrefixKeywords)),
                    new EmailContainsKeywordsPredicate(Arrays.asList(emailByPrefixKeywords)),
                    new AddressContainsKeywordsPredicate(Arrays.asList(addressByPrefixKeywords)),
                    new StudentIdContainsKeywordsPredicate(Arrays.asList(studentIdByPrefixKeywords)),
                    new TutorialContainsKeywordsPredicate(Arrays.asList(tutorialNameByPrefixKeywords))
            );
        } else {
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

}
