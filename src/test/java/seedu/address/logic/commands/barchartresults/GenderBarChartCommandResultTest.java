package seedu.address.logic.commands.barchartresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;

class GenderBarChartCommandResultTest {

    @Test
    public void constructor_nullColumnValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GenderBarChartCommandResult(null));
    }

    @Test
    public void equals() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20);
        titlesValuesMapping1.put("Female", 30);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Male", 20);
        titlesValuesMapping2.put("Female", 30);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Male", 10);
        titlesValuesMapping3.put("Female", 10);
        CommandResult commandResult1 = new GenderBarChartCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new GenderBarChartCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new GenderBarChartCommandResult(titlesValuesMapping3);

        // same values -> returns true
        assertTrue(commandResult1.equals(commandResult2));

        // same object -> returns true
        assertTrue(commandResult1.equals(commandResult1));
        assertTrue(commandResult2.equals(commandResult2));

        // null -> returns false
        assertFalse(commandResult1.equals(null));

        // different types -> returns false
        assertFalse(commandResult1.equals(0.5f));

        // different count values -> returns false
        assertFalse(commandResult1.equals(commandResult3));
    }

    @Test
    public void hashcode() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20);
        titlesValuesMapping1.put("Female", 30);
        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("Male", 20);
        titlesValuesMapping2.put("Female", 30);
        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("Male", 10);
        titlesValuesMapping3.put("Female", 10);
        CommandResult commandResult1 = new GenderBarChartCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new GenderBarChartCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new GenderBarChartCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20);
        titlesValuesMapping1.put("Female", 30);
        CommandResult commandResult = new GenderBarChartCommandResult(titlesValuesMapping1);
        String expected = GenderBarChartCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", showBarChart=" + commandResult.isShowBarChart()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("Male", 20);
        titlesValuesMapping1.put("Female", 30);
        GenderBarChartCommandResult commandResult = new GenderBarChartCommandResult(titlesValuesMapping1);
        assertEquals(commandResult.getMaleCount(), 20);
        assertEquals(commandResult.getFemaleCount(), 30);
    }

}
