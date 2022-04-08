package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentsInTutorialList;

/**
 * Contains a list of all students attendance lists.
 * The students are in the same Tutorial.
 */
public class AttendanceList {
    private final ArrayList<Attendance> attendances;
    private final int weeks;

    /**
     * Constructs an AttendanceList.
     *
     * @param attendances the list of attendance records.
     * @param weeks the number of weeks of attendance to be recorded for.
     */
    public AttendanceList(ArrayList<Attendance> attendances, int weeks) {
        requireAllNonNull(attendances);
        this.attendances = attendances;
        this.weeks = weeks;
    }

    public int getWeeks() {
        return weeks;
    }

    public ArrayList<Attendance> getAttendances() {
        return attendances;
    }

    /**
     * Generates attendance records for students who do not have any records present in the list.
     *
     * @param uniqueStudentList the list of students in the tutorial.
     */
    public void generateAttendance(UniqueStudentsInTutorialList uniqueStudentList) {
        FilteredList<Person> studentList = uniqueStudentList.getStudentsInClass();
        for (Person p : studentList) {
            Student student = (Student) p;
            NusNetId studentId = student.getStudentId();
            boolean hasAttendencePresent = false;


            for (Attendance attendance : attendances) {
                if (attendance.getStudentId().equals(studentId)) {
                    hasAttendencePresent = true;
                    break;
                }
            }

            if (!hasAttendencePresent) {
                ArrayList<Integer> studentAttendance = new ArrayList<>();
                for (int i = 0; i < weeks - 1; i++) {
                    studentAttendance.add(0);
                }
                attendances.add(
                        new Attendance(
                                studentAttendance,
                                studentId,
                                new Comment("")));
            }
        }

        for (Iterator<Attendance> iterator = attendances.iterator(); iterator.hasNext();) {
            Attendance attendance = iterator.next();
            if (!uniqueStudentList.containsStudentWithId(attendance.getStudentId())) {
                iterator.remove();
            }
        }
    }

    /**
     * Returns the attendance of the specified student for the semester.
     *
     * @param studentId the NusNetId of a student.
     * @return the attendance list of the student for the semester.
     */
    public ArrayList<Attendance> getAttendancesByStudentID(NusNetId studentId) {
        requireNonNull(studentId);
        Attendance attendanceToAdd = null;
        ArrayList<Attendance> attendanceList = new ArrayList<>();
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                attendanceToAdd = attendance;
                break;
            }
        }
        for (int i = 0; i < weeks; i++) {
            attendanceList.add(attendanceToAdd);
        }
        return attendanceList;
    }

    /**
     * Marks the attendance for the all students.
     *
     * @param week the week that the attendance should be marked for the student.
     */
    public void markAllAttendance(int week) {
        requireNonNull(week);
        for (Attendance attendance : attendances) {
            attendance.markWeek(week);
        }
    }

    /**
     * Marks the attendance for the specified student.
     *
     * @param studentId the NusNetId of a student.
     * @param week the week that the attendance should be marked for the student.
     */
    public void markAttendanceForStudent(NusNetId studentId, int week) {
        requireAllNonNull(studentId, week);
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                attendance.markWeek(week);
                break;
            }
        }
    }

    /**
     * Unmarks the attendance for the all students.
     *
     * @param week the week that the attendance should be unmarked for the student.
     */
    public void unmarkAllAttendance(int week) {
        for (Attendance attendance : attendances) {
            attendance.unmarkWeek(week);
        }
    }

    /**
     * Unmarks the attendance for the specified student.
     *
     * @param studentId the NusNetId of a student.
     * @param week the week that the attendance should be unmarked for the student.
     */
    public void unmarkAttendanceForStudent(NusNetId studentId, int week) {
        requireAllNonNull(studentId, week);
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                attendance.unmarkWeek(week);
                break;
            }
        }
    }

    /**
     * Adds a comment for the specified student.
     *
     * @param studentId the NusNetId of a student.
     * @param comment the comment to be added.
     */
    public void addComment(NusNetId studentId, Comment comment) {
        requireAllNonNull(studentId, comment);
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                attendance.addComment(comment);
                break;
            }
        }
    }

    /**
     * Removes comment for the specified student.
     *
     * @param studentId the NusNetId of a student.
     */
    public void removeComment(NusNetId studentId) {
        requireNonNull(studentId);
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                attendance.addComment(new Comment(""));
                break;
            }
        }
    }

    /**
     * Views the comment for the specified student.
     *
     * @param studentId the NusNetId of a student.
     */
    public Comment viewComment(NusNetId studentId) {
        requireNonNull(studentId);
        Comment commentToView = new Comment("");
        for (Attendance attendance : attendances) {
            if (attendance.getStudentId().equals(studentId)) {
                commentToView = attendance.getComment();
                break;
            }
        }
        assert(commentToView != null);
        return commentToView;
    }
}
