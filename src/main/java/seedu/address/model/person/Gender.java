package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Students's gender in the address book.
 */
public class Gender {

    /**
     * Represents all possible genders.
     */
    private enum Genders {
        MALE,
        FEMALE,
        INVALID
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Please Enter a Valid Gender, M for Male, F for Female";
    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        value = gender;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String gender) {
        Genders g = Genders.INVALID;

        if (gender.equals("M")) {
            g = Genders.MALE;
        } else if (gender.equals("F")) {
            g = Genders.FEMALE;
        }

        switch (g) {
        case MALE:
            return true;
        case FEMALE:
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
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return value.equals(otherGender.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
