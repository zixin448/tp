package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_TUTORIAL_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

/**
 * Deletes a class identified using it's displayed index from the address book, or using its tutorial name.
 */
public class DeleteClassCommand extends Command {

    public static final String COMMAND_WORD = "delete_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list,"
            + "or deletes the class identified by tutorial name.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_TUTORIALNAME + "TUTORIAL NAME \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTORIAL_SUCCESS = "Deleted Class: %1$s";

    private final TutorialName tutorialName;

    /**
     * Constructor for a DeleteClassCommand.
     * @param tutorialName The name of the class.
     */
    public DeleteClassCommand(TutorialName tutorialName) {
        this.tutorialName = tutorialName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return deleteByTutorialName(model);
    }

    private CommandResult deleteByTutorialName(Model model) throws CommandException {
        Tutorial tutorialToDelete = null;
        for (Tutorial tutorial : model.getAddressBook().getTutorialList()) {
            if (tutorial.getTutorialName().equals(tutorialName)) {
                tutorialToDelete = tutorial;
                break;
            }
        }

        if (tutorialToDelete == null) {
            throw new CommandException(MESSAGE_TUTORIAL_NOT_FOUND);
        } else {
            model.deleteTutorial(tutorialToDelete);
            return CommandResult.createClassCommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS,
                    tutorialToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteClassCommand // instanceof handles nulls
                && tutorialName.equals(((DeleteClassCommand) other).tutorialName)); // state check
    }
}

