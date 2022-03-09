package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

public class AddStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private String message = "Temporary message.";

    private Name name = new Name("Alice");
    private NusNetId studentId = new NusNetId("E0123456");
    private TutorialName tutorialName = new TutorialName("G04");

    @Test
    public void execute() {
        assertCommandFailure(new AddStudentCommand(name, studentId, tutorialName), model, message);
    }
}
