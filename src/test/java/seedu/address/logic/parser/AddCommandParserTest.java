package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEAREST_MRT_STATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEC_LEVEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NEAREST_MRT_STATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEAREST_MRT_STATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SEC_LEVEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SEC_LEVEL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_BIOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_PHYSICS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEAREST_MRT_STATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEC_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BIOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_PHYSICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEAREST_MRT_STATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Subject;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new PersonBuilder(BOB).withSubjects(VALID_SUBJECT_PHYSICS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_PHYSICS, new AddCommand(expectedStudent));


        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new PersonBuilder(BOB).withSubjects(
                VALID_SUBJECT_PHYSICS, VALID_SUBJECT_BIOLOGY).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB
                        + NEAREST_MRT_STATION_DESC_BOB + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS,
                new AddCommand(expectedStudentMultipleTags));

        // multiple tags with enrol dates - all accepted
        Student expectedStudentMultipleTagsAndEnrolDates = new PersonBuilder(BOB).withSubjects(
                VALID_SUBJECT_PHYSICS, VALID_SUBJECT_BIOLOGY).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB
                        + NEAREST_MRT_STATION_DESC_BOB + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB
                + NEAREST_MRT_STATION_DESC_BOB + SUBJECT_DESC_PHYSICS;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple genders
        assertParseFailure(parser, GENDER_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // multiple secLevels
        assertParseFailure(parser, SEC_LEVEL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SEC_LEVEL));

        // multiple nearestMrtStations
        assertParseFailure(parser, NEAREST_MRT_STATION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NEAREST_MRT_STATION));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + NAME_DESC_AMY + ADDRESS_DESC_AMY + GENDER_DESC_AMY + SEC_LEVEL_DESC_AMY
                        + NEAREST_MRT_STATION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS,
                        PREFIX_EMAIL, PREFIX_PHONE, PREFIX_GENDER, PREFIX_SEC_LEVEL,
                        PREFIX_NEAREST_MRT_STATION));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid gender
        assertParseFailure(parser, INVALID_GENDER_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // invalid secLevel
        assertParseFailure(parser, INVALID_SEC_LEVEL_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SEC_LEVEL));

        // invalid nearestMrtStation
        assertParseFailure(parser, INVALID_NEAREST_MRT_STATION_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NEAREST_MRT_STATION));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedPersonString + INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedPersonString + INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedPersonString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid gender
        assertParseFailure(parser, validExpectedPersonString + INVALID_GENDER_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GENDER));

        // invalid secLevel
        assertParseFailure(parser, validExpectedPersonString + INVALID_SEC_LEVEL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_SEC_LEVEL));

        // invalid nearestMrtStation
        assertParseFailure(parser, validExpectedPersonString + INVALID_NEAREST_MRT_STATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NEAREST_MRT_STATION));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new PersonBuilder(AMY).withSubjects().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + GENDER_DESC_AMY + SEC_LEVEL_DESC_AMY
                        + NEAREST_MRT_STATION_DESC_AMY,
                new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_GENDER_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing secLevel prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + GENDER_DESC_BOB + VALID_SEC_LEVEL_BOB + NEAREST_MRT_STATION_DESC_BOB,
                expectedMessage);

        // missing nearestMrtPrefix prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + VALID_NEAREST_MRT_STATION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_GENDER_BOB + VALID_SEC_LEVEL_BOB
                        + VALID_NEAREST_MRT_STATION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, Address.MESSAGE_CONSTRAINTS);

        // invalid gender
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_GENDER_DESC + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, Gender.MESSAGE_CONSTRAINTS);

        // invalid secLevel
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB + INVALID_SEC_LEVEL_DESC + NEAREST_MRT_STATION_DESC_BOB
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, SecLevel.MESSAGE_CONSTRAINTS);

        // invalid nearestMrtStation
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + INVALID_NEAREST_MRT_STATION_DESC
                + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS, MrtStation.MESSAGE_CONSTRAINTS);

        // invalid subject
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB
                + INVALID_SUBJECT_DESC + VALID_SUBJECT_PHYSICS, Subject.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB + NEAREST_MRT_STATION_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + SEC_LEVEL_DESC_BOB
                + NEAREST_MRT_STATION_DESC_BOB + SUBJECT_DESC_BIOLOGY + SUBJECT_DESC_PHYSICS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
