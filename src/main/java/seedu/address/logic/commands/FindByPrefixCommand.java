package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.TutorialContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Finds and lists all persons in address book whose attributes contains any of the argument keywords by prefix.
 * Keyword matching is case insensitive.
 */
public class FindByPrefixCommand extends FindCommand {

    public static final String COMMAND_WORD = "find";

    //to be edited
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

//    private final NameContainsKeywordsPredicate predicateName;
//    private final PhoneContainsKeywordsPredicate predicatePhone;
    private final EmailContainsKeywordsPredicate predicateEmail;
    private final AddressContainsKeywordsPredicate predicateAddress;
    private final StudentIdContainsKeywordsPredicate predicateStudentId;
    private final TutorialContainsKeywordsPredicate predicateTutorialName;

    public FindByPrefixCommand(NameContainsKeywordsPredicate predicateName,
                               EmailContainsKeywordsPredicate predicateEmail,
                               AddressContainsKeywordsPredicate predicateAddress,
                               StudentIdContainsKeywordsPredicate predicateStudentId,
                               TutorialContainsKeywordsPredicate predicateTutorialName) {
        super(predicateName);
        this.predicateEmail = predicateEmail;
        this.predicateAddress = predicateAddress;
        this.predicateStudentId = predicateStudentId;
        this.predicateTutorialName = predicateTutorialName;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> all = new ArrayList<>();
        model.updateFilteredPersonList(super.getPredicate());
        all.addAll(model.getFilteredPersonList());
        model.updateFilteredPersonList(predicateEmail);
        all.addAll(model.getFilteredPersonList());
        model.updateFilteredPersonList(predicateAddress);
        all.addAll(model.getFilteredPersonList());
        model.updateFilteredStudentList(predicateStudentId);
        all.addAll(model.getFilteredPersonList());
        model.updateFilteredPersonList(predicateTutorialName);
        all.addAll(model.getFilteredPersonList());

        // filter out duplicate contacts
        List<Person> allNoDupes = new ArrayList<>(new LinkedHashSet<>(all));

        model.setFilteredPersonsMultiPredList(allNoDupes);
        ObservableList<Person> hi = model.getFilteredPersonsMultiPredList();
        System.out.println(hi);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonsMultiPredList().size()));

    }


}
