package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tableresults.GenderTableCommandResult;
import seedu.address.logic.commands.tableresults.SecLevelTableCommandResult;
import seedu.address.logic.commands.tableresults.SubjectTableCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class TableCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Command command1 = new TableCommand("g/");
        Command command2 = new TableCommand("g/");
        Command command3 = new TableCommand("s/");

        // same args -> return true
        assertEquals(command1, command2);

        // different args -> return false
        assertNotEquals(command1, command3);

        // null -> return false
        assertNotEquals(command1, null);

        //different types -> return false
        assertFalse(command1.equals(0.5f));
    }

    @Test
    public void constructor_unknownArg_throwException() {
        assertThrows(CommandException.class, () -> new TableCommand("f/").execute(model));
    }


    @Test
    public void executeToCorrectCommandResult() throws CommandException {
        Command command1 = new TableCommand("g/");
        Command command2 = new TableCommand("l/");
        Command command3 = new TableCommand("s/");

        assertTrue(command1.execute(model) instanceof GenderTableCommandResult);
        assertTrue(command2.execute(model) instanceof SecLevelTableCommandResult);
        assertTrue(command3.execute(model) instanceof SubjectTableCommandResult);
    }
}
