package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_ASSESSMENT_NAME_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_FULL_MARK_OP1;
import static seedu.address.logic.commands.AssessmentTestUtil.VALID_WEIGHTAGE_OP1;
import static seedu.address.testutil.TypicalAssessments.ASSIGNMENT_1;
import static seedu.address.testutil.TypicalAssessments.ASSIGNMENT_2;
import static seedu.address.testutil.TypicalAssessments.OP1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssessmentBuilder;

public class AssessmentTest {
    @Test
    public void isSameAssessment() {
        // same object -> return true
        assertTrue(ASSIGNMENT_1.isSameAssessment(ASSIGNMENT_1));

        // null -> returns false
        assertFalse(ASSIGNMENT_1.isSameAssessment(null));

        // same name, all other attributes different -> return true
        Assessment editedAssignment1 = new AssessmentBuilder().withName("Assignment 1")
                .withFullMark(VALID_WEIGHTAGE_OP1).withWeightage(VALID_FULL_MARK_OP1).build();
        assertTrue(ASSIGNMENT_1.isSameAssessment(editedAssignment1));

        // different name, all other attributes same -> returns false
        Assessment editedAssignment2 = new AssessmentBuilder(ASSIGNMENT_2).withName(VALID_ASSESSMENT_NAME_OP1).build();
        assertFalse(ASSIGNMENT_2.isSameAssessment(editedAssignment2));

        // name differs in case, all other attributes same -> returns false
        editedAssignment1 = new AssessmentBuilder(ASSIGNMENT_1).withName("aSSignment 1").build();
        assertFalse(ASSIGNMENT_1.isSameAssessment(editedAssignment1));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_ASSESSMENT_NAME_OP1 + "  ";
        Assessment editedOp1 = new AssessmentBuilder(OP1).withName(nameWithTrailingSpaces).build();
        assertFalse(OP1.isSameAssessment(editedOp1));
    }

    @Test
    public void equals() {
        // same values -> returns true

    }
}
