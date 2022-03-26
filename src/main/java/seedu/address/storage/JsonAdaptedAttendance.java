package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.NusNetId;

public class JsonAdaptedAttendance {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "StudentResult's %s field is missing!";

    private final String studentId;
    private final String comments;
    private final List<String> studentAttendance = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorial} with the given tutorial details.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("studentId") String studentId, @JsonProperty("comments") String comments,
            @JsonProperty("studentAttendance") List<String> studentAttendance) {
        this.studentId = studentId;
        this.comments = comments;
        if (studentAttendance != null) {
            this.studentAttendance.addAll(studentAttendance);
        }
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        studentId = source.getStudentId().toString();
        comments = source.getComment().toString();
        studentAttendance.addAll(source.getAttendanceList()
            .stream()
            .map(x -> String.valueOf(x))
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (!NusNetId.isValidId(studentId)) {
            throw new IllegalValueException(NusNetId.MESSAGE_CONSTRAINTS);
        }

        final NusNetId modelStudentId = new NusNetId(studentId);
        final ArrayList<Integer> attendanceList = new ArrayList<>();
        attendanceList.addAll(studentAttendance.stream()
            .map(x -> Integer.parseInt(x))
            .collect(Collectors.toList()));
        final Comment modelComment = new Comment(comments);

        return new Attendance(attendanceList, modelStudentId, modelComment);
    }
}
