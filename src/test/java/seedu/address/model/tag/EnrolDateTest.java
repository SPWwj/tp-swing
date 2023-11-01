package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EnrolDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EnrolDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EnrolDate(invalidDate));
    }

    @Test
    public void isValidDate_validDate_success() {
        assertTrue(EnrolDate.isValidDate("Jul 2023"));
    }

    @Test
    public void isValidDate_invalidFormat_failure() {
        assertFalse(EnrolDate.isValidDate("2023-July"));
        assertFalse(EnrolDate.isValidDate("July 2023"));
        assertFalse(EnrolDate.isValidDate("2023 Jul"));
        assertFalse(EnrolDate.isValidDate("2023 07"));
        assertFalse(EnrolDate.isValidDate("07 2023"));
    }

    @Test
    public void equals() {
        EnrolDate enrolDate1 = new EnrolDate("Oct 2023");
        EnrolDate enrolDate2 = new EnrolDate("Oct 2023");
        EnrolDate enrolDate3 = new EnrolDate("Nov 2005");

        //same values -> equal
        assertEquals(enrolDate1, enrolDate2);

        //null -> not equal
        assertNotEquals(enrolDate1, null);

        // different values -> not equal
        assertNotEquals(enrolDate1, enrolDate3);

        // different type -> return false
        assertFalse(enrolDate3.equals(0.5f));
    }
}
