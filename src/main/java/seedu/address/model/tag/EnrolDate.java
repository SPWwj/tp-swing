package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an EnrolDate for a Subject in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDate(String)}
 */
public class EnrolDate {
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Date format should be MMM YYYY (e.g. Jul 2023)";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
    private final YearMonth enrolDate;

    public EnrolDate() {
        this.enrolDate = YearMonth.now();
    }

    /**
     * Constructs an {@code EnrolDate}.
     *
     * @param enrolDate A valid enrol date.
     */
    public EnrolDate(String enrolDate) {
        requireNonNull(enrolDate);
        checkArgument(isValidDate(enrolDate), MESSAGE_INVALID_DATE_FORMAT);
        this.enrolDate = YearMonth.parse(enrolDate, FORMATTER);;
    }

    /**
     * Checks if the given date is valid.
     *
     * @param date The given date String object.
     * @return true if the given date is valid, false otherwise.
     */
    public static boolean isValidDate(String date) {
        try {
            YearMonth.parse(date, FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolDate)) {
            return false;
        }

        EnrolDate otherdate = (EnrolDate) other;
        return enrolDate.equals(otherdate.enrolDate);
    }

    @Override
    public int hashCode() {
        return enrolDate.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return enrolDate.format(FORMATTER);
    }

}
