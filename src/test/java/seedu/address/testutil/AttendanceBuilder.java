package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Name;

public class AttendanceBuilder {

    public static final String[] DEFAULT_ATTENDANCE = { "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
    public static final String DEFAULT_NAME = "John Yeo";
    public static final String DEFAULT_COMMENT = "Has not handed in work";

    private ArrayList<Integer> attendanceList;
    private Name studentName;
    private Comment comment;

    /**
     * Creates a {@code AttendanceBuilder} with the default details.
     */
    public AttendanceBuilder() {
        attendanceList = convertStringArrToIntList(DEFAULT_ATTENDANCE);
        studentName = new Name(DEFAULT_NAME);
        comment = new Comment(DEFAULT_COMMENT);
    }

    /**
     * Sets the {@code ArrayList<String>} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withAttendanceList(String[] assessmentList) {
        this.attendanceList = convertStringArrToIntList(assessmentList);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withName(String studentName) {
        this.studentName = new Name(studentName);
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code Attendance} that we are building.
     */
    public AttendanceBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    private ArrayList<Integer> convertStringArrToIntList(String[] arr) {
        return new ArrayList<>(Arrays.asList(arr)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    public Attendance build() {
        return new Attendance(attendanceList, studentName, comment);
    };
}
