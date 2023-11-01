package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.model.person.NameContainsKeywordPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithNoSpace_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new NameContainsKeywordPredicate("Alice"));
        assertParseSuccess(parser, "Alice", expectedSearchCommand);

        // multiple whitespaces before and/or after keyword
        assertParseSuccess(parser, " \n Alice \t", expectedSearchCommand);
    }

    @Test
    public void parse_validArgsWithSpace_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new NameContainsKeywordPredicate("Alice Tan"));
        assertParseSuccess(parser, "Alice Tan", expectedSearchCommand);

        // multiple whitespaces before and/or after keyword
        assertParseSuccess(parser, " \n Alice Tan \t", expectedSearchCommand);
    }

}
