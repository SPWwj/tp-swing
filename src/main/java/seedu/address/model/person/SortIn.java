package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Object for sort list in.
 */
public class SortIn {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort in should only be ASC or DESC.";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String sortIn;

    /**
     * All valid sequences.
     */
    public enum ValidSequence { ASC, DESC; }

    /**
     * Constructs a {@code MrtStation}.
     *
     * @param sortIn A valid attribute to sort in.
     */
    public SortIn(String sortIn) {
        requireNonNull(sortIn);
        checkArgument(isValidSortIn(sortIn), MESSAGE_CONSTRAINTS);
        this.sortIn = sortIn.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid sort in attribute.
     */
    public static boolean isValidSortIn(String sequence) {
        try {
            ValidSequence enumValue = ValidSequence.valueOf(sequence.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return sortIn;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortIn)) {
            return false;
        }

        SortIn otherSortIn = (SortIn) other;
        return sortIn.equals(otherSortIn.sortIn);
    }

    @Override
    public int hashCode() {
        return sortIn.hashCode();
    }
}
