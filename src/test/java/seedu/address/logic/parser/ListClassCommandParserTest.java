package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListClassCommand;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialHasSameDay;
import seedu.address.testutil.TutorialBuilder;

import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtilTest.TUTORIAL_DAY_DESC_1;
import static seedu.address.logic.parser.ParserUtilTest.TUTORIAL_DAY_DESC_2;
import static seedu.address.testutil.TypicalTutorials.T01;

public class ListClassCommandParserTest {
    private ListClassCommandParser parser = new ListClassCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tutorial expectedClass = new TutorialBuilder(T01).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TUTORIAL_DAY_DESC_1,
                new ListClassCommand(new TutorialHasSameDay(expectedClass.getDay())));

        // multiple days - last day accepted
        assertParseSuccess(parser, TUTORIAL_DAY_DESC_2 + TUTORIAL_DAY_DESC_1,
                new ListClassCommand(new TutorialHasSameDay(expectedClass.getDay())));

        //


    }

}
