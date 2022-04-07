package seedu.address.testutil;

import static seedu.address.logic.commands.AttendancesTestUtil.VALID_ATTENDANCE_BOBBY;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_ATTENDANCE_KEVIN;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_COMMENT_BOBBY;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_COMMENT_KEVIN;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_NAME_BOBBY;
import static seedu.address.logic.commands.AttendancesTestUtil.VALID_NAME_KEVIN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.attendance.Attendance;

public class TypicalAttendances {
    public static final Attendance SASHA = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "0", "0", "0"}).withName("Sasha Low")
            .withComment("Was late to class").build();
    public static final Attendance ELISE = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "0", "1", "0", "1"}).withName("Elise Toh")
            .withComment("Answered a lot of questions").build();
    public static final Attendance TOM = new AttendanceBuilder()
            .withAttendanceList(new String[] {"0", "0", "0", "0", "0"}).withName("Tom Woh")
            .withComment("Has not attended all class so far").build();
    public static final Attendance BRUCE = new AttendanceBuilder()
            .withAttendanceList(new String[] {"1", "1", "1", "1", "1"}).withName("Bruce Wayne")
            .withComment("Attentive during class").build();

    // Manually added - Attendance's details found in {@code AttendancesUtil}
    public static final Attendance KEVIN = new AttendanceBuilder().withAttendanceList(VALID_ATTENDANCE_KEVIN)
            .withName(VALID_NAME_KEVIN).withComment(VALID_COMMENT_KEVIN).build();
    public static final Attendance BOBBY = new AttendanceBuilder().withAttendanceList(VALID_ATTENDANCE_BOBBY)
            .withName(VALID_NAME_BOBBY).withComment(VALID_COMMENT_BOBBY).build();

    private TypicalAttendances() {
    } // prevents instantiation

    public static List<Attendance> getTypicalAttendances() {
        return new ArrayList<>(Arrays.asList(SASHA, ELISE, TOM, BRUCE));
    }
}
