package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

public class RemoveCommentCommand extends Command {
    public static final String COMMAND_WORD = "remove_comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes comments for a student. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Tan ";

    public static final String MESSAGE_SUCCESS = "Comment removed for student %s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The specified student does not exist.";

    private final Name studentToRemoveComment;

    /**
     * Creates an RemoveCommentCommand to remove a comment for the specified {@code Student}
     */
    public RemoveCommentCommand(Name studentName) {
        studentToRemoveComment = studentName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStudentWithName(studentToRemoveComment)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND));
        }

        Student student = (Student) model.getPersonWithName(studentToRemoveComment);
        TutorialName tutorialName = student.getTutorialName();
        Tutorial tutorial = model.getTutorialWithName(tutorialName);
        model.removeComment(tutorial, studentToRemoveComment);
        return CommandResult.createCommentCommandResult(String.format(MESSAGE_SUCCESS, studentToRemoveComment));
    }
}
