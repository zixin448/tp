package seedu.address.logic.commands;



import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;

import java.util.List;

public class RemoveStudentCommand extends Command {

    public static final String COMMAND_WORD = "remove_student";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from the tutorial. "
            + " Parameters: "
            + PREFIX_INDEX + "INDEX "
            + PREFIX_STUDENTID + "STUDENT_ID "
            + PREFIX_TUTORIALNAME + "TUTORIAL_NAME\n"
            + "Example:\n"
            + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 "
            + PREFIX_TUTORIALNAME + "G04\n"
            + COMMAND_WORD + " "
            + PREFIX_STUDENTID + "E0123456 "
            + PREFIX_TUTORIALNAME + "G04\n";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "Student %1$s has been removed from tutorial %2$s";
    public static final String MESSAGE_NOT_A_STUDENT = "This person is not a student!";
    public static final String MESSAGE_TUTORIAL_DOES_NOT_EXIST = "Tutorial %1$s does not exist!";
    public static final String MESSAGE_STUDENT_DOES_NOT_EXIST = "student %1$s is not in tutorial %2$s";

    private final Index toRemoveIndex;
    private final NusNetId toRemoveStudentId;
    private final TutorialName toRemoveFromTutorialName;

    public RemoveStudentCommand(NusNetId studentId, TutorialName tutorialName) {
        requireNonNull(studentId);
        requireNonNull(tutorialName);
        toRemoveIndex = null;
        toRemoveStudentId = studentId;
        toRemoveFromTutorialName = tutorialName;
    }

    public RemoveStudentCommand(Index index, TutorialName tutorialName) {
        requireNonNull(index);
        requireNonNull(tutorialName);
        toRemoveIndex = index;
        toRemoveStudentId = null;
        toRemoveFromTutorialName = tutorialName;

    }




    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (toRemoveStudentId != null) {
            Tutorial tutorial;
            Student studentToRemove;
            if (!model.hasTutorialWithName(toRemoveFromTutorialName)) {
                throw new CommandException(String.format(MESSAGE_TUTORIAL_DOES_NOT_EXIST, toRemoveFromTutorialName));
            }

            tutorial = model.getTutorialMatch(toRemoveFromTutorialName);

            if (!tutorial.containsStudentWithId(toRemoveStudentId)) {
                throw new CommandException(String.format(MESSAGE_STUDENT_DOES_NOT_EXIST, toRemoveStudentId,
                        toRemoveFromTutorialName));
            }
            studentToRemove = tutorial.getStudentWithId(toRemoveStudentId);
            model.removeStudent(studentToRemove);


            return CommandResult.
                    createStudentCommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS, toRemoveStudentId,
                            toRemoveFromTutorialName));
        } else {
            List<Person> lastShownList = model.getFilteredPersonList();
            if (toRemoveIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            if (!model.hasTutorialWithName(toRemoveFromTutorialName)) {
                throw new CommandException(String.format(MESSAGE_TUTORIAL_DOES_NOT_EXIST, toRemoveFromTutorialName));
            }

            Person personToRemove = lastShownList.get(toRemoveIndex.getZeroBased());

            if (!(personToRemove instanceof Student)) {
                throw new CommandException(MESSAGE_NOT_A_STUDENT);
            }

            Student studentToRemove = (Student) personToRemove;
            model.removeStudent(studentToRemove);
            NusNetId id = studentToRemove.getStudentId();

            return CommandResult.
                    createStudentCommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS, id,
                    toRemoveFromTutorialName));

        }
    }
}
