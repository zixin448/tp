package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.person.NusNetId;

/**
 * Jackson-friendly version of {@link StudentResult}.
 */
public class JsonAdaptedStudentResult {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "StudentResult's %s field is missing!";

    private final String studentId;
    private final String score;

    /**
     * Constructs a {@code JsonAdaptedStudentResult} from the given details.
     */
    @JsonCreator
    public JsonAdaptedStudentResult(@JsonProperty("studentId") String studentId, @JsonProperty("score") String score) {
        this.studentId = studentId;
        this.score = score;
    }

    /**
     * Converts a given {@code StudentResult} into a {@code JsonAdaptedStudentResult} for Jackson use.
     */
    public JsonAdaptedStudentResult(StudentResult studentResult) {
        this.studentId = studentResult.getStudentId().id;
        this.score = studentResult.getScore().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code StudentResult} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student result.
     */
    public StudentResult toModelType(FullMark fullMark) throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NusNetId.class.getSimpleName()));
        }
        if (!NusNetId.isValidId(studentId)) {
            throw new IllegalValueException(NusNetId.MESSAGE_CONSTRAINTS);
        }
        final NusNetId modelId = new NusNetId(studentId);

        if (score == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Score.class.getSimpleName()));
        }
        if (!Score.isValidScoreGivenFullMark(score, fullMark)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        final Score modelScore = new Score(score, fullMark);

        return new StudentResult(modelId, modelScore);
    }
}
