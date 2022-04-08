package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttendances.BOBBY;
import static seedu.address.testutil.TypicalAttendances.ELISE;
import static seedu.address.testutil.TypicalAttendances.SASHA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttendanceBuilder;

public class AttendanceTest {

    @Test
    public void isSameAttendance() {

        // same object -> returns true
        assertTrue(ELISE.equals(ELISE));

        // null -> returns false
        assertFalse(ELISE.equals(null));

        // different name, all other attribute same -> returns false
        Attendance editedElise = new AttendanceBuilder()
                .withAttendanceList(new String[] {"1", "0", "1", "0", "1"})
                .withName("Elise Lim")
                .withComment("Answered a lot of questions").build();
        assertFalse(ELISE.equals(editedElise));

        // same name, all other attribute different -> returns false
        editedElise = new AttendanceBuilder()
                .withAttendanceList(new String[] {"1", "0", "0", "0", "0"})
                .withName("Elise Toh")
                .withComment("Answered some questions").build();
        assertFalse(ELISE.equals(editedElise));

        // different object -> return false
        assertFalse(ELISE.equals(SASHA));

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
