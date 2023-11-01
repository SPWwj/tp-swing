package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.barchartresults.GenderBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SecLevelBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SubjectBarChartCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class BarChartCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Command command1 = new BarChartCommand("g/");
        Command command2 = new BarChartCommand("g/");
        Command command3 = new BarChartCommand("s/");

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
        assertThrows(CommandException.class, () -> new BarChartCommand("f/").execute(model));
    }


    @Test
    public void executeToCorrectCommandResult() throws CommandException {
        Command command1 = new BarChartCommand("g/");
        Command command2 = new BarChartCommand("l/");
        Command command3 = new BarChartCommand("s/");

        assertTrue(command1.execute(model) instanceof GenderBarChartCommandResult);
        assertTrue(command2.execute(model) instanceof SecLevelBarChartCommandResult);
        assertTrue(command3.execute(model) instanceof SubjectBarChartCommandResult);
    }
}
