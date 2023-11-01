package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Students's Secondary School Level in the address book.
 */
public class SecLevel {

    /**
     * Represents all possible Secondary School Levels.
     */
    private enum SecLevels {
        SECONDARY_ONE,
        SECONDARY_TWO,
        SECONDARY_THREE,
        SECONDARY_FOUR,
        INVALID
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Please Enter a Valid Level, 1 for Secondary One, 2 for Secondary Two, so on and so forth.";
    public static final String SEC1 = "Sec 1";
    public static final String SEC2 = "Sec 2";
    public static final String SEC3 = "Sec 3";
    public static final String SEC4 = "Sec 4";
    public final String value;

    /**
     * Constructs a {@code SecLevel}.
     *
     * @param secLevel A valid secondary school level.
     */
    public SecLevel(String secLevel) {
        requireNonNull(secLevel);
        checkArgument(isValidSecLevel(secLevel), MESSAGE_CONSTRAINTS);
        value = secLevel;
    }

    /**
     * Returns true if a given string is a valid secondary school level.
     */
    public static boolean isValidSecLevel(String secLevel) {
        SecLevel.SecLevels level = SecLevels.INVALID;

        if (secLevel.equals("1")) {
            level = SecLevels.SECONDARY_ONE;
        } else if (secLevel.equals("2")) {
            level = SecLevels.SECONDARY_TWO;
        } else if (secLevel.equals("3")) {
            level = SecLevels.SECONDARY_THREE;
        } else if (secLevel.equals("4")) {
            level = SecLevels.SECONDARY_FOUR;
        }

        switch (level) {
        case SECONDARY_ONE:
            return true;
        case SECONDARY_TWO:
            return true;
        case SECONDARY_THREE:
            return true;
        case SECONDARY_FOUR:
            return true;
        default:
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SecLevel)) {
            return false;
        }

        SecLevel otherSecLevel = (SecLevel) other;
        return value.equals(otherSecLevel.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
