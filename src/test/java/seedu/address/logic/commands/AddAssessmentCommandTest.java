package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.OP1;
import static seedu.address.testutil.TypicalAssessments.OP2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.StudentResult;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.AssessmentBuilder;

public class AddAssessmentCommandTest {
    @Test
    public void constructor_nullAssessment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssessmentCommand(null));
    }

    @Test
    public void execute_assessmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAssessmentAdded modelStub = new ModelStubAcceptingAssessmentAdded();
        Assessment validAssessment = new AssessmentBuilder().build();

        CommandResult commandResult = new AddAssessmentCommand(validAssessment).execute(modelStub);


        assertEquals(String.format(AddAssessmentCommand.MESSAGE_SUCCESS, validAssessment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAssessment), modelStub.assessmentsAdded);
    }

    @Test
    public void execute_duplicateAssessment_throwsCommandException() throws Exception {
        Assessment validAssessment = new AssessmentBuilder().build();
        AddAssessmentCommand addAssessmentCommand = new AddAssessmentCommand(validAssessment);
        ModelStubWithAssessment modelStub = new ModelStubWithAssessment(validAssessment);

        assertThrows(CommandException.class, AddAssessmentCommand.MESSAGE_DUPLICATE_ASSESSMENT, ()
            -> addAssessmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddAssessmentCommand addAssessmentOp1Command = new AddAssessmentCommand(OP1);
        AddAssessmentCommand addAssessmentOp2Command = new AddAssessmentCommand(OP2);

        // same object -> returns true
        assertTrue(addAssessmentOp1Command.equals(addAssessmentOp1Command));

        // same values -> returns true
        AddAssessmentCommand addAssessmentOp1CommandCopy = new AddAssessmentCommand(OP1);
        assertTrue(addAssessmentOp1Command.equals(addAssessmentOp1CommandCopy));

        // null -> returns false
        assertFalse(addAssessmentOp2Command.equals(null));

        // different assessment -> returns false
        assertFalse(addAssessmentOp1Command.equals(addAssessmentOp2Command));
    }

    /**
     * A default Model Stub that has all methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tutorial getTutorialWithName(TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorial(Tutorial tutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorial(Tutorial target, Tutorial editedTutorial) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Person> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<StudentResult> getDisplayAssessmentResults() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDisplayAssessmentResults(TutorialName tutName, AssessmentName assessmentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tutorial> getFilteredTutorialList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assessment> getAssessmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Assessment> getFilteredAssessmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAssessmentList(Predicate<Assessment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssessment(Assessment assessment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAssessment(Assessment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAssessmentWithName(AssessmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Assessment getAssessmentWithName(AssessmentName assessmentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Assessment removeAssessmentWithName(AssessmentName name) {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredTutorialList(Predicate<Tutorial> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TutorialName getTutorialNameOfStudent(Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attendance> getFilteredAttendanceList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addComment(Tutorial tutorial, Name studentToComment, Comment toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeComment(Tutorial tutorial, Name studentToRemoveComment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Comment getComment(Tutorial tutorial, Name studentToViewComment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Comment> getCommentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAttendanceList(Tutorial tutorial, Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FilteredList<Person> getAllStudentsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentWithName(Name studentName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentResult(Name studentName, AssessmentName assessmentName) {
            return false;
        }

        @Override
        public void addStudentResult(Name studentName, AssessmentName assessmentName, Score sc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeStudentResults(NusNetId studentId, TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setStudentResult(Name studentName, AssessmentName assessmentName, Score sc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudentWithId(NusNetId toAddStudentId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markAttendanceForClass(Tutorial tutorial, int week) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void markAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unmarkAttendanceForClass(Tutorial tutorial, int week) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void unmarkAttendanceForStudent(Tutorial tutorial, NusNetId studentId, int week) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorialWithName(TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPersonWithName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonsMultiPredList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Student getStudentWithId(NusNetId id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFilteredPersonsMultiPredList(List<Person> persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean tutorialHasStudentWithId(NusNetId id, TutorialName tutorialName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Displayable> getLastShownList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model Stub containing a single assessment.
     */
    private class ModelStubWithAssessment extends ModelStub {
        private final Assessment assessment;

        ModelStubWithAssessment(Assessment a) {
            assessment = a;
        }

        @Override
        public boolean hasAssessment(Assessment a) {
            requireNonNull(a);
            return a.isSameAssessment(assessment);
        }
    }

    /**
     * A model stub that always accepts the assessment being added.
     */
    private class ModelStubAcceptingAssessmentAdded extends ModelStub {
        final ArrayList<Assessment> assessmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAssessment(Assessment assessment) {
            requireNonNull(assessment);
            return assessmentsAdded.stream().anyMatch(assessment::isSameAssessment);
        }

        @Override
        public void addAssessment(Assessment assessment) {
            requireNonNull(assessment);
            assessmentsAdded.add(assessment);
        }
    }
}
