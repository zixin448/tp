package seedu.address.testutil;

import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;

import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.person.NusNetId;

public class StudentResultBuilder {
    public static final String DEFAULT_NUSNET_ID = "e1234567";
    public static final String DEFAULT_SCORE = VALID_SCORE_OP1;
    public static final FullMark DEFAULT_FULL_MARK = new FullMark(VALID_FULL_MARK_OP1);

    private NusNetId studentId;
    private Score score;

    /**
     * Creates a {@code StudentResultBuilder} with default fields.
     */
    public StudentResultBuilder() {
        studentId = new NusNetId(DEFAULT_NUSNET_ID);
        score = new Score(DEFAULT_SCORE, DEFAULT_FULL_MARK);
    }

    /**
     * Initializes the StudentResultBuilder with the data of {@code studentResultToCopy}
     */
    public StudentResultBuilder(StudentResult studentResultToCopy) {
        studentId = studentResultToCopy.getStudentId();
        score = studentResultToCopy.getScore();
    }

    /**
     * Sets the studentId of the StudentResult being built.
     */
    public StudentResultBuilder withNusNetId(String id) {
        this.studentId = new NusNetId(id);
        return this;
    }

    /**
     * Sets the score of the StudentResult being built given the score and Full Mark.
     */
    public StudentResultBuilder withScore(String score, String fm) {
        FullMark fullMark = new FullMark(fm);
        this.score = new Score(score, fullMark);
        return this;
    }

    public StudentResult build() {
        return new StudentResult(studentId, score);
    }

}
