package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentHasTutorialNamePredicate;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * Lists all tutorials in the address book to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list_student";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    public static final String MESSAGE_SUCCESS_CLASS = "Listed students from Class: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List all students or the students identified by the index "
            + "number used in the displayed class list, or by tutorial name.\n"
            + "Parameters: [INDEX] (must be a positive integer)\n"
            + "[" + PREFIX_TUTORIALNAME + "TUTORIAL NAME] \n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final TutorialName tutorialName;

    /**
     * Constructor for a ListStudentCommand.
     * @param targetIndex The index of the class in the list.
     * @param tutorialName The name of the class.
     */
    public ListStudentCommand(Index targetIndex, TutorialName tutorialName) {
        this.targetIndex = targetIndex;
        this.tutorialName = tutorialName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        if (targetIndex == null && tutorialName == null) {
            return CommandResult.createStudentCommandResult(MESSAGE_SUCCESS);
        }
        if (targetIndex == null) {
            return listByTutorialName(model);
        }
        return listByIndex(model);
    }

    private CommandResult listByTutorialName(Model model) throws CommandException {
        Tutorial tutorialToList = null;
        for (Tutorial tutorial : model.getAddressBook().getTutorialList()) {
            if (tutorial.getTutorialName().equals(tutorialName)) {
                tutorialToList = tutorial;
                break;
            }
        }

        if (tutorialToList == null) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        } else {
            model.updateFilteredStudentList(new StudentHasTutorialNamePredicate(tutorialToList.getTutorialName()));
            return CommandResult.createStudentCommandResult(String.format(MESSAGE_SUCCESS_CLASS, tutorialToList));
        }
    }

    private CommandResult listByIndex(Model model) throws CommandException {
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        Tutorial tutorialToList = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredStudentList(new StudentHasTutorialNamePredicate(tutorialToList.getTutorialName()));
        return CommandResult.createStudentCommandResult(String.format(MESSAGE_SUCCESS_CLASS, tutorialToList));
    }
}
