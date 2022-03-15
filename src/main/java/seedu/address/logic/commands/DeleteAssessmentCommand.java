package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.CommandResult.createAssessmentCommandResult;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;

public class DeleteAssessmentCommand extends Command {
    public static final String COMMAND_WORD = "delete_assessment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the assessment identified by the assessment name from the addressbook. "
            + "Parameters: "
            + PREFIX_ASSESSMENTNAME + "ASSESSMENT NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ASSESSMENTNAME + "assignment1\n";

    public static final String MESSAGE_SUCCESS = "Deleted assessment: %1$s";
    private static final String MESSAGE_ASSESSMENT_NOT_FOUND = "An assessment with the provided name "
            + "does not exist in the address book";

    private final AssessmentName name;

    /**
     * Creates a DeleteAssessmentCommand to remove the assessment with the given {@code AssessmentName}.
     */
    public DeleteAssessmentCommand(AssessmentName name) {
        requireNonNull(name);
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAssessmentByName(name)) {
            throw new CommandException(MESSAGE_ASSESSMENT_NOT_FOUND);
        }

        Assessment removed = model.removeAssessmentByName(name);
        return createAssessmentCommandResult(String.format(MESSAGE_SUCCESS, removed));
    }
}
