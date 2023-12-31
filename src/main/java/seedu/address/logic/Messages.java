package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Student;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DETAILS = "The student index/name provided is not in the list";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_DATE_NUMBER_NOT_MATCHING =
            "The number of dates provided does not match the number of subjects.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Student student) {
        final StringBuilder builder = new StringBuilder();
        builder.append(student.getName())
                .append("; Phone: ")
                .append(student.getPhone())
                .append("; Email: ")
                .append(student.getEmail())
                .append("; Address: ")
                .append(student.getAddress())
                .append("; Gender: ")
                .append(student.getGender())
                .append("; Sec Level: ")
                .append(student.getSecLevel())
                .append("; Nearest MRT Station: ")
                .append(student.getNearestMrtStation())
                .append("; Subjects: ");
        student.getSubjects().forEach(builder::append);
        return builder.toString();
    }

}
