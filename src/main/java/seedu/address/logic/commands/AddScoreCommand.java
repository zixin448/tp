package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.person.Name;
import seedu.address.model.tutorial.TutorialName;

/**
 * Adds a score for a student in a particular assessment.
 */
public class AddScoreCommand extends Command {
    public static final String COMMAND_WORD = "add_score";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student's score in the assessment. "
            + "Parameters: "
            + PREFIX_ASSESSMENTNAME + "ASSESSMENT NAME "
            + PREFIX_NAME + "NAME "
            + PREFIX_SCORE + "SCORE";

    public static final String MESSAGE_SUCCESS = "New score added: %1$s; Student: %2$s\n"
            + "Listed all scores of Class: %3$s for Assessment: %4$s with Full mark: %5$s";
    public static final String MESSAGE_ASSESSMENT_NOT_FOUND = "An assessment with the provided name "
            + "does not exist in the address book";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "There is no student with "
            + "the given name in the address book";
    public static final String MESSAGE_DUPLICATE_STUDENT_RESULT = "This student's result for the assessment "
            + "is already inside camNUS";
    public static final String MESSAGE_INVALID_SCORE = "The given score is larger than the assessment's "
            + "full marks";

    private final AssessmentName assessmentName;
    private final Name studentName;
    private final String score;

    /**
     * Creates an AddScoreCommand to
     */
    public AddScoreCommand(AssessmentName assessmentName, Name studentName, String score) {
        requireAllNonNull(assessmentName, studentName, score);
        this.assessmentName = assessmentName;
        this.studentName = studentName;
        this.score = score;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAssessmentByName(assessmentName)) {
            throw new CommandException(MESSAGE_ASSESSMENT_NOT_FOUND);
        }

        if (!model.hasStudentWithName(studentName)) {
            throw new CommandException(MESSAGE_STUDENT_NOT_FOUND);
        }

        // checks if the address book already has a result for a student in an assessment
        if (model.hasStudentResult(studentName, assessmentName)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_RESULT);
        }

        FullMark fullMark = model.getAssessmentByName(assessmentName).getFullMark();
        if (!Score.isValidScoreGivenFullMark(score, fullMark)) {
            throw new CommandException(MESSAGE_INVALID_SCORE);
        }

        Score sc = new Score(score, fullMark);
        TutorialName tutName = model.getTutorialNameOfStudent(studentName);

        model.addStudentResult(studentName, assessmentName, sc);
        model.updateDisplayAssessmentResults(tutName, assessmentName);

        return CommandResult.createScoreCommandResult(
                String.format(MESSAGE_SUCCESS, sc, studentName, tutName, assessmentName, fullMark));
    }
}
