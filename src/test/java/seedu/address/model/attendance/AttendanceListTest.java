package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.StudentTestUtil.INVALID_STUDENT_ID_ADAM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.EVE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueStudentsInTutorialList;
import seedu.address.model.tutorial.TutorialName;


public class AttendanceListTest {

    private final AttendanceList attendanceList = new AttendanceList(new ArrayList<>(), 3);

    @Test
    public void generateAttendance_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.generateAttendance(null));
    }

    @Test
    public void generateAttendance_oneStudentList_generateSuccessful() {
        List<Person> studentList = new ArrayList<>();
        studentList.add(ALEX);
        ObservableList<Person> observableStudentList = FXCollections.observableList(studentList);
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                new TutorialName("TG01")));
        assertEquals(attendanceList.getAttendances().size(), 1);
    }

    @Test
    public void getAttendancesByStudentID_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.getAttendancesByStudentID(null));
    }

    @Test
    public void getAttendancesByStudentID_validId_returnsAttendanceList() {
        List<Person> studentList = new ArrayList<>();
        studentList.add(EVE);
        ObservableList<Person> observableStudentList = FXCollections.observableList(studentList);
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                EVE.getTutorialName()));
        assertTrue(attendanceList.getAttendancesByStudentID(EVE.getStudentId()).size() == 3);
    }

    @Test
    public void getAttendancesByStudentID_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList
                .getAttendancesByStudentID(new NusNetId(INVALID_STUDENT_ID_ADAM)));
    }

    @Test
    public void markAttendanceForStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> attendanceList.markAttendanceForStudent(null, 2));
    }

    @Test
    public void markAttendanceForStudent_validId_successfulMark() {
        List<Person> studentList = new ArrayList<>();
        studentList.add(EVE);
        ObservableList<Person> observableStudentList = FXCollections.observableList(studentList);
        attendanceList.generateAttendance(new UniqueStudentsInTutorialList(observableStudentList,
                EVE.getTutorialName()));
        attendanceList.markAttendanceForStudent(EVE.getStudentId(), 2);
        Attendance eveAttendance = attendanceList.getAttendances().get(0); //EVE's attendance
        assertTrue(eveAttendance.getAttendanceList().get(1) == 1);
    }

    @Test
    public void markAttendanceForStudent_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> attendanceList
                .markAttendanceForStudent(new NusNetId(INVALID_STUDENT_ID_ADAM), 2));
    }


}
