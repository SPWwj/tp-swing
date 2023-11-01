package seedu.address.logic.commands.tableresults;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;

public class SubjectTableCommandResultTest {
    @Test
    public void equals() {
        Map<String, Integer> titlesValuesMapping1 = new HashMap<>();
        titlesValuesMapping1.put("English", 50);
        titlesValuesMapping1.put("Chinese", 10);
        titlesValuesMapping1.put("Elementary Mathematics", 30);
        titlesValuesMapping1.put("Additional Mathematics", 20);
        titlesValuesMapping1.put("Chemistry", 40);
        titlesValuesMapping1.put("Physics", 50);
        titlesValuesMapping1.put("Biology", 25);
        titlesValuesMapping1.put("Geography", 15);
        titlesValuesMapping1.put("History", 20);
        titlesValuesMapping1.put("Social Studies", 5);

        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("English", 50);
        titlesValuesMapping2.put("Chinese", 10);
        titlesValuesMapping2.put("Elementary Mathematics", 30);
        titlesValuesMapping2.put("Additional Mathematics", 20);
        titlesValuesMapping2.put("Chemistry", 40);
        titlesValuesMapping2.put("Physics", 50);
        titlesValuesMapping2.put("Biology", 25);
        titlesValuesMapping2.put("Geography", 15);
        titlesValuesMapping2.put("History", 20);
        titlesValuesMapping2.put("Social Studies", 5);

        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("English", 50);
        titlesValuesMapping3.put("Chinese", 15);
        titlesValuesMapping3.put("Elementary Mathematics", 25);
        titlesValuesMapping3.put("Additional Mathematics", 20);
        titlesValuesMapping3.put("Chemistry", 40);
        titlesValuesMapping3.put("Physics", 30);
        titlesValuesMapping3.put("Biology", 25);
        titlesValuesMapping3.put("Geography", 55);
        titlesValuesMapping3.put("History", 20);
        titlesValuesMapping3.put("Social Studies", 5);

        CommandResult commandResult1 = new SubjectTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SubjectTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SubjectTableCommandResult(titlesValuesMapping3);

        // same values -> returns true
        assertTrue(commandResult1.equals(commandResult2));

        // same object -> returns true
        assertTrue(commandResult1.equals(commandResult1));
        assertTrue(commandResult2.equals(commandResult2));
        assertTrue(commandResult3.equals(commandResult3));


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
        titlesValuesMapping1.put("English", 50);
        titlesValuesMapping1.put("Chinese", 10);
        titlesValuesMapping1.put("Elementary Mathematics", 30);
        titlesValuesMapping1.put("Additional Mathematics", 20);
        titlesValuesMapping1.put("Chemistry", 40);
        titlesValuesMapping1.put("Physics", 50);
        titlesValuesMapping1.put("Biology", 25);
        titlesValuesMapping1.put("Geography", 15);
        titlesValuesMapping1.put("History", 20);
        titlesValuesMapping1.put("Social Studies", 5);

        Map<String, Integer> titlesValuesMapping2 = new HashMap<>();
        titlesValuesMapping2.put("English", 50);
        titlesValuesMapping2.put("Chinese", 10);
        titlesValuesMapping2.put("Elementary Mathematics", 30);
        titlesValuesMapping2.put("Additional Mathematics", 20);
        titlesValuesMapping2.put("Chemistry", 40);
        titlesValuesMapping2.put("Physics", 50);
        titlesValuesMapping2.put("Biology", 25);
        titlesValuesMapping2.put("Geography", 15);
        titlesValuesMapping2.put("History", 20);
        titlesValuesMapping2.put("Social Studies", 5);

        Map<String, Integer> titlesValuesMapping3 = new HashMap<>();
        titlesValuesMapping3.put("English", 50);
        titlesValuesMapping3.put("Chinese", 15);
        titlesValuesMapping3.put("Elementary Mathematics", 25);
        titlesValuesMapping3.put("Additional Mathematics", 20);
        titlesValuesMapping3.put("Chemistry", 40);
        titlesValuesMapping3.put("Physics", 30);
        titlesValuesMapping3.put("Biology", 25);
        titlesValuesMapping3.put("Geography", 55);
        titlesValuesMapping3.put("History", 20);
        titlesValuesMapping3.put("Social Studies", 5);

        CommandResult commandResult1 = new SubjectTableCommandResult(titlesValuesMapping1);
        CommandResult commandResult2 = new SubjectTableCommandResult(titlesValuesMapping2);
        CommandResult commandResult3 = new SubjectTableCommandResult(titlesValuesMapping3);

        // same values -> returns same hashcode
        assertEquals(commandResult1.hashCode(), commandResult2.hashCode());

        // different count values -> returns different hashcode
        assertNotEquals(commandResult1.hashCode(), commandResult3.hashCode());

    }

    @Test
    public void toStringMethod() {
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("English", 50);
        titlesValuesMapping.put("Chinese", 10);
        titlesValuesMapping.put("Elementary Mathematics", 30);
        titlesValuesMapping.put("Additional Mathematics", 20);
        titlesValuesMapping.put("Chemistry", 40);
        titlesValuesMapping.put("Physics", 50);
        titlesValuesMapping.put("Biology", 25);
        titlesValuesMapping.put("Geography", 15);
        titlesValuesMapping.put("History", 20);
        titlesValuesMapping.put("Social Studies", 5);
        CommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);
        String expected = SubjectTableCommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", showTable=" + commandResult.isShowTable()
                + ", showBarChart=" + commandResult.isShowBarChart()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void parseMappingCorrectly() {
        Map<String, Integer> titlesValuesMapping = new HashMap<>();
        titlesValuesMapping.put("English", 50);
        titlesValuesMapping.put("Chinese", 10);
        titlesValuesMapping.put("Elementary Mathematics", 30);
        titlesValuesMapping.put("Additional Mathematics", 20);
        titlesValuesMapping.put("Chemistry", 40);
        titlesValuesMapping.put("Physics", 50);
        titlesValuesMapping.put("Biology", 25);
        titlesValuesMapping.put("Geography", 15);
        titlesValuesMapping.put("History", 20);
        titlesValuesMapping.put("Social Studies", 5);
        SubjectTableCommandResult commandResult = new SubjectTableCommandResult(titlesValuesMapping);

        assertEquals(commandResult.getEngCount(), 50);
        assertEquals(commandResult.getChiCount(), 10);
        assertEquals(commandResult.getEmathCount(), 30);
        assertEquals(commandResult.getAmathCount(), 20);
        assertEquals(commandResult.getChemiCount(), 40);
        assertEquals(commandResult.getPhyCount(), 50);
        assertEquals(commandResult.getBioCount(), 25);
        assertEquals(commandResult.getGeogCount(), 15);
        assertEquals(commandResult.getHistCount(), 20);
        assertEquals(commandResult.getSocCount(), 5);
    }
}
