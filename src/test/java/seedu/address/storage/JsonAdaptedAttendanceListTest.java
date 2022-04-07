package seedu.address.storage;

import static seedu.address.testutil.TypicalAssessments.ASSIGNMENT_1;

public class JsonAdaptedAttendanceListTest {
    private static final String INVALID_BLANK_ASSESSMENT_NAME = " ";
    private static final String INVALID_ASSESSMENT_NAME = "@##$%^Assessment";
    private static final String INVALID_WEIGHTAGE = "101";
    private static final String INVALID_NEGATIVE_FULLMARK = "-1";
    private static final String INVALID_POSITIVE_FULLMARK = "1001";

    private static final String VALID_ASSESSMENT_NAME = ASSIGNMENT_1.getAssessmentName().toString();
    private static final String VALID_WEIGHTAGE = ASSIGNMENT_1.getWeightage().toString();
    private static final String VALID_FULLMARK = ASSIGNMENT_1.getFullMark().toString();
}
