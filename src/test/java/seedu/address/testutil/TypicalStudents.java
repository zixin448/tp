package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Student;

public class TypicalStudents {

    public static final Student ALEX = new StudentBuilder().withName("Alex Lee")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alex@example.com")
            .withPhone("91234567").withTags("student").withStudentId("e0543216")
            .withTutorialName("TG01").build();
    public static final Student BERNARD = new StudentBuilder().withName("Bernard Toh")
            .withAddress("123, Wood East Ave 6, #08-123").withEmail("bernardt@gmail.com")
            .withPhone("81234567").withTags("student").withStudentId("e0523416")
            .withTutorialName("TG01").build();
    public static final Student CHARLIE = new StudentBuilder().withName("Charlie Johnson")
            .withAddress("321, Jurong North Ave 9, #10-123").withEmail("cjohnson@hotmail.com")
            .withPhone("83214567").withTags("student").withStudentId("e0523614")
            .withTutorialName("TG01").build();
    public static final Student DENSON = new StudentBuilder().withName("Denson Loh")
            .withAddress("321, Ang Mo Kio West Ave 9, #10-101").withEmail("dsnlh@hotmail.com")
            .withPhone("83214567").withTags("student").withStudentId("e0563214")
            .withTutorialName("TG01").build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALEX, BERNARD, CHARLIE, DENSON));
    }
}
