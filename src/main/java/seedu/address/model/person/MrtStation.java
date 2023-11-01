package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Mrt Station in the address book.
 */
public class MrtStation {
    public static final String MESSAGE_CONSTRAINTS =
            "MRT Station names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String mrtStationName;

    /**
     * Constructs a {@code MrtStation}.
     *
     * @param mrtStationName A valid MRT Station Name.
     */
    public MrtStation(String mrtStationName) {
        requireNonNull(mrtStationName);
        checkArgument(isValidMrtStationName(mrtStationName), MESSAGE_CONSTRAINTS);
        this.mrtStationName = mrtStationName;
    }

    /**
     * Returns true if a given string is a valid MRT Station Name.
     */
    public static boolean isValidMrtStationName(String mrtStationName) {
        return mrtStationName.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return mrtStationName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MrtStation)) {
            return false;
        }

        MrtStation otherMrtStation = (MrtStation) other;
        return mrtStationName.equals(otherMrtStation.mrtStationName);
    }

    @Override
    public int hashCode() {
        return mrtStationName.hashCode();
    }
}
