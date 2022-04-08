package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttendances.E0123456;
import static seedu.address.testutil.TypicalAttendances.E6543210;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttendanceBuilder;

public class AttendanceTest {

    private Attendance e0234567 = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "1", "0", "1"})
            .withNusNetId("e0234567")
            .withComment("Answered one question")
            .build();

    @Test
    public void getStudentId() {

        // same Id
        Attendance eliseCopy = new AttendanceBuilder(E0123456).build();
        assertTrue(E0123456.getStudentId().equals(eliseCopy.getStudentId()));

        // different Id
        assertFalse(E0123456.getStudentId().equals(e0234567.getStudentId()));

    }

    @Test
    public void getAttendanceStatusByWeek_invalidIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> e0234567.getAttendanceStatusByWeek(-1));
    }

    @Test
    public void getAttendanceStatusByWeek() {

        // valid week, present
        assertEquals(e0234567.getAttendanceStatusByWeek(INDEX_FIRST.getOneBased()), "Status: Present");

        // valid week, absent
        assertEquals(e0234567.getAttendanceStatusByWeek(INDEX_SECOND.getOneBased()), "Status: Absent");

    }

    @Test
    public void equals() {

        // same values -> returns true
        Attendance eliseCopy = new AttendanceBuilder(E0123456).build();
        assertTrue(E0123456.equals(eliseCopy));

        // same object -> returns true
        assertTrue(E0123456.equals(E0123456));

        // null -> returns false
        assertFalse(E0123456.equals(null));

        // different type -> returns false
        assertFalse(E0123456.equals(1));

        // different attendance -> returns false
        assertFalse(E0123456.equals(E6543210));

        // different name -> returns false
        Attendance editedE0123456 = new AttendanceBuilder(E0123456).withNusNetId("e0223456").build();
        assertFalse(E0123456.equals(editedE0123456));

        // different attendance list -> returns false
        editedE0123456 = new AttendanceBuilder(E0123456).withAttendanceList(new String[] {"1", "0", "1", "0", "0"})
                .build();
        assertFalse(E0123456.equals(editedE0123456));

        // different comment -> returns false
        editedE0123456 = new AttendanceBuilder(E0123456).withComment("Changed comment").build();
        assertFalse(E0123456.equals(editedE0123456));
    }

}
