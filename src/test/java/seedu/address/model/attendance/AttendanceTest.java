package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttendances.BOBBY;
import static seedu.address.testutil.TypicalAttendances.ELISE;
import static seedu.address.testutil.TypicalAttendances.SASHA;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttendanceBuilder;

public class AttendanceTest {

    private Attendance abby = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "1", "0", "1"})
            .withName("ABBY")
            .withComment("Answered one question")
            .build();

    @Test
    public void getStudentName() {

        // same name
        Attendance eliseCopy = new AttendanceBuilder(ELISE).build();
        assertTrue(ELISE.getStudentName().equals(eliseCopy.getStudentName()));

        // different name
        assertFalse(ELISE.getStudentName().equals(SASHA.getStudentName()));

    }

    @Test
    public void getAttendanceStatusByWeek_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> abby.getAttendanceStatusByWeek(-1));
    }

    @Test
    public void getAttendanceStatusByWeek() {

        // valid week, present
        assertEquals(abby.getAttendanceStatusByWeek(INDEX_FIRST.getOneBased()), "Status: Present");

        // valid week, absent
        assertEquals(abby.getAttendanceStatusByWeek(INDEX_SECOND.getOneBased()), "Status: Absent");

    }

    @Test
    public void equals() {

        // same values -> returns true
        Attendance eliseCopy = new AttendanceBuilder(ELISE).build();
        assertTrue(ELISE.equals(eliseCopy));

        // same object -> returns true
        assertTrue(ELISE.equals(ELISE));

        // null -> returns false
        assertFalse(ELISE.equals(null));

        // different type -> returns false
        assertFalse(ELISE.equals(1));

        // different attendance -> returns false
        assertFalse(ELISE.equals(BOBBY));

        // different name -> returns false
        Attendance editedElise = new AttendanceBuilder(ELISE).withName("Elisa").build();
        assertFalse(ELISE.equals(editedElise));

        // different attendance list -> returns false
        editedElise = new AttendanceBuilder(ELISE).withAttendanceList(new String[] {"1", "0", "0", "0", "0"}).build();
        assertFalse(ELISE.equals(editedElise));

        // different comment -> returns false
        editedElise = new AttendanceBuilder(ELISE).withComment("Changed comment").build();
        assertFalse(ELISE.equals(editedElise));
    }

}
