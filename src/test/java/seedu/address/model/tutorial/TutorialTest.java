package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_DAY_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TIME_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG1;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_TUTORIAL_NAME_TG2;
import static seedu.address.logic.commands.TutorialTestUtil.VALID_VENUE_TG2;
import static seedu.address.testutil.TypicalTutorials.T01;
import static seedu.address.testutil.TypicalTutorials.TG2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialBuilder;

public class TutorialTest {

    @Test
    public void isSameTutorial() {
        // same object -> returns true
        assertTrue(T01.isSameTutorial(T01));

        // null -> returns false
        assertFalse(T01.isSameTutorial(null));

        // same tutorial name, all other attributes different -> returns true
        Tutorial editedT01 = new TutorialBuilder(T01).withDay(VALID_DAY_TG2).withTime(VALID_TIME_TG2)
                        .withVenue(VALID_VENUE_TG2).build();
        assertTrue(T01.isSameTutorial(editedT01));

        // different tutorial name, all other attributes same -> returns false
        editedT01 = new TutorialBuilder(T01).withTutorialName(VALID_TUTORIAL_NAME_TG1).build();
        assertFalse(T01.isSameTutorial(editedT01));

        // tutorial name differs in case, all other attributes same -> returns false
        Tutorial editedTG2 = new TutorialBuilder(TG2).withTutorialName(VALID_TUTORIAL_NAME_TG2.toLowerCase()).build();
        assertFalse(TG2.isSameTutorial(editedTG2));

        // tutorial name has trailing spaces, all other attributes same -> returns false
        String tutorialNameWithTrailingSpaces = VALID_TUTORIAL_NAME_TG2 + " ";
        editedTG2 = new TutorialBuilder(TG2).withTutorialName(tutorialNameWithTrailingSpaces).build();
        assertFalse(TG2.isSameTutorial(editedTG2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Tutorial t01Copy = new TutorialBuilder(T01).build();
        assertTrue(T01.equals(t01Copy));

        // same object -> returns true
        assertTrue(T01.equals(T01));

        // null -> returns false
        assertFalse(T01.equals(null));

        // different type -> returns false
        assertFalse(T01.equals(5));

        // different tutorial -> returns false
        assertFalse(T01.equals(TG2));

        // different tutorial name -> returns false
        Tutorial editedT01 = new TutorialBuilder(T01).withTutorialName(VALID_TUTORIAL_NAME_TG2).build();
        assertFalse(T01.equals(editedT01));

        // different venue -> returns false
        editedT01 = new TutorialBuilder(T01).withVenue(VALID_VENUE_TG2).build();
        assertFalse(T01.equals(editedT01));

        // different day -> returns false
        editedT01 = new TutorialBuilder(T01).withDay(VALID_DAY_TG2).build();
        assertFalse(T01.equals(editedT01));

        // different time -> returns false
        editedT01 = new TutorialBuilder(T01).withTime(VALID_TIME_TG2).build();
        assertFalse(T01.equals(editedT01));
    }
}
