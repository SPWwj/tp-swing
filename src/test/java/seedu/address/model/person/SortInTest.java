package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortInTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortIn(null));
    }

    @Test
    public void isValidSortIn() {
        // blank sort in
        assertFalse(SortIn.isValidSortIn("")); // empty string
        assertFalse(SortIn.isValidSortIn(" ")); // spaces only

        // invalid sort in
        assertFalse(SortIn.isValidSortIn("ASCENDING"));
        assertFalse(SortIn.isValidSortIn("SHUN"));
        assertFalse(SortIn.isValidSortIn("DES"));

        // valid sort in
        assertTrue(SortIn.isValidSortIn("ASC"));
        assertTrue(SortIn.isValidSortIn("DESC"));
        assertTrue(SortIn.isValidSortIn("Asc"));
        assertTrue(SortIn.isValidSortIn("Desc"));
        assertTrue(SortIn.isValidSortIn("AsC"));
        assertTrue(SortIn.isValidSortIn("DeSC"));
        assertTrue(SortIn.isValidSortIn("asc"));
        assertTrue(SortIn.isValidSortIn("desc"));
    }
    @Test
    public void equals() {
        SortIn sortIn = new SortIn("ASC");

        // same values -> returns true
        assertTrue(sortIn.equals(new SortIn("ASC")));

        // same object -> returns true
        assertTrue(sortIn.equals(sortIn));

        // null -> returns false
        assertFalse(sortIn.equals(null));

        // different types -> returns false
        assertFalse(sortIn.equals(5.0f));

        // different values -> returns false
        assertFalse(sortIn.equals(new SortIn("DESC")));
    }
}
