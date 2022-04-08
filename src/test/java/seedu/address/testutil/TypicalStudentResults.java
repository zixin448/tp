package seedu.address.testutil;

import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP2;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_SCORE_OP2;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_AARON;
import static seedu.address.logic.commands.StudentTestUtil.VALID_STUDENT_ID_BILL;

import seedu.address.model.assessment.StudentResult;

/**
 * A utility class containing {@code StudentResult} objects to be used in tests.
 */
public class TypicalStudentResults {

    // Manually added - Student result's details found in {@code StudentTestUtil} and {@code AssessmentTestUtil}
    public static final StudentResult AARON_RESULT_FOR_OP1 = new StudentResultBuilder()
            .withNusNetId(VALID_STUDENT_ID_AARON)
            .withScore(VALID_SCORE_OP1, VALID_FULL_MARK_OP1).build();
    public static final StudentResult BILL_RESULT_FOR_OP2 = new StudentResultBuilder()
            .withNusNetId(VALID_STUDENT_ID_BILL)
            .withScore(VALID_SCORE_OP2, VALID_FULL_MARK_OP2).build();
}
