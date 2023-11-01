package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SortIn;
import seedu.address.model.tag.EnrolDate;
import seedu.address.model.tag.Subject;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DATE = "06 2023";
    private static final String INVALID_SORT_IN = "INVALID";


    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_SUBJECT_1 = "Additional Mathematics";
    private static final String VALID_SUBJECT_2 = "English";
    private static final String VALID_ENROL_DATE_1 = "Jun 2023";
    private static final String VALID_ENROL_DATE_2 = "Dec 2021";
    private static final String VALID_SORT_IN_ASC = "ASC";
    private static final String VALID_SORT_IN_DESC = "ASC";

    private static final String WHITESPACE = " \t\r\n";

    private static final String VALID_INDEX_PREAMBLE = "10 ";
    private static final String VALID_NAME_PREAMBLE = "John  ";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () ->
                ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null, null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
        EnrolDate date = new EnrolDate(VALID_ENROL_DATE_1);
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG, date));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Subject expectedSubject = new Subject(VALID_SUBJECT_1);
        assertEquals(expectedSubject, ParserUtil.parseTag(VALID_SUBJECT_1));
        EnrolDate date = new EnrolDate(VALID_ENROL_DATE_1);
        assertEquals(expectedSubject, ParserUtil.parseTag(VALID_SUBJECT_1, date));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_SUBJECT_1 + WHITESPACE;
        Subject expectedSubject = new Subject(VALID_SUBJECT_1);
        assertEquals(expectedSubject, ParserUtil.parseTag(tagWithWhitespace));
        EnrolDate date = new EnrolDate(VALID_ENROL_DATE_1);
        assertEquals(expectedSubject, ParserUtil.parseTag(tagWithWhitespace, date));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
        EnrolDate date1 = new EnrolDate(VALID_ENROL_DATE_1);
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, date1));
        EnrolDate date2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null, dates));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_SUBJECT_1, INVALID_TAG)));
        EnrolDate date1 = new EnrolDate(VALID_ENROL_DATE_1);
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                Arrays.asList(VALID_SUBJECT_1, INVALID_TAG), date1));
        EnrolDate date2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(
                Arrays.asList(VALID_SUBJECT_1, INVALID_TAG), dates));
    }

    @Test
    public void parseTags_mismatchedSubjectsAndDates_throwsParseException() {
        EnrolDate date1 = new EnrolDate(VALID_ENROL_DATE_1);
        EnrolDate date2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_SUBJECT_1), dates));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Subject> actualSubjectSet = ParserUtil.parseTags(Arrays.asList(VALID_SUBJECT_1, VALID_SUBJECT_2));
        Set<Subject> expectedSubjectSet = new HashSet<Subject>(Arrays.asList(
                new Subject(VALID_SUBJECT_1), new Subject(VALID_SUBJECT_2)));
        assertEquals(expectedSubjectSet, actualSubjectSet);
        EnrolDate date1 = new EnrolDate(VALID_ENROL_DATE_1);
        actualSubjectSet = ParserUtil.parseTags(Arrays.asList(VALID_SUBJECT_1, VALID_SUBJECT_2), date1);
        assertEquals(expectedSubjectSet, actualSubjectSet);
        EnrolDate date2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> dates = new ArrayList<>();
        actualSubjectSet = ParserUtil.parseTags(Arrays.asList(VALID_SUBJECT_1, VALID_SUBJECT_2), dates);
        assertEquals(expectedSubjectSet, actualSubjectSet);
    }

    @Test
    public void parseDates_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDates(null));
    }

    @Test
    public void parseDates_invalidValue_throwsParseException() {
        Collection<String> dates = new ArrayList<>();
        dates.add(INVALID_DATE);
        assertThrows(ParseException.class, () -> ParserUtil.parseDates(dates));
    }

    @Test
    public void parseDates_validValueWithoutWhitespace_returnsTag() throws Exception {
        Collection<String> dates = new ArrayList<>();
        dates.add(VALID_ENROL_DATE_1);
        dates.add(VALID_ENROL_DATE_2);
        EnrolDate expectedDate1 = new EnrolDate(VALID_ENROL_DATE_1);
        EnrolDate expectedDate2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> enrolDates = new ArrayList<>();
        enrolDates.add(expectedDate1);
        enrolDates.add(expectedDate2);
        assertEquals(enrolDates, ParserUtil.parseDates(dates));
    }

    @Test
    public void parseDates_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        Collection<String> dates = new ArrayList<>();
        String dateWithWhitespace1 = WHITESPACE + VALID_ENROL_DATE_1 + WHITESPACE;
        String dateWithWhitespace2 = WHITESPACE + VALID_ENROL_DATE_2 + WHITESPACE;
        dates.add(dateWithWhitespace1);
        dates.add(dateWithWhitespace2);
        EnrolDate expectedDate1 = new EnrolDate(VALID_ENROL_DATE_1);
        EnrolDate expectedDate2 = new EnrolDate(VALID_ENROL_DATE_2);
        Collection<EnrolDate> enrolDates = new ArrayList<>();
        enrolDates.add(expectedDate1);
        enrolDates.add(expectedDate2);
        assertEquals(enrolDates, ParserUtil.parseDates(dates));
    }

    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate(null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsTag() throws Exception {
        EnrolDate expectedDate = new EnrolDate(VALID_ENROL_DATE_1);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_ENROL_DATE_1));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_ENROL_DATE_1 + WHITESPACE;
        EnrolDate expectedDate = new EnrolDate(VALID_ENROL_DATE_1);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseSortIn_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseSortInAsc_validValueWithoutWhitespace_returnsTag() throws Exception {
        SortIn validSortIn = new SortIn(VALID_SORT_IN_ASC);
        assertEquals(validSortIn, ParserUtil.parseSortIn(VALID_SORT_IN_ASC));
    }

    @Test
    public void parseSortInDesc_validValueWithoutWhitespace_returnsTag() throws Exception {
        SortIn validSortIn = new SortIn(VALID_SORT_IN_DESC);
        assertEquals(validSortIn, ParserUtil.parseSortIn(VALID_SORT_IN_DESC));
    }

    @Test
    public void parseSortIn_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSortIn(INVALID_SORT_IN));
    }

    @Test
    public void parseValidIndexPreamble() throws Exception {
        Index expectedIndex = Index.fromOneBased(Integer.parseInt(VALID_INDEX_PREAMBLE.trim()));
        assertEquals(expectedIndex, ParserUtil.parsePreamble(VALID_INDEX_PREAMBLE));
    }

    @Test
    public void parseValidNamePreamble() throws Exception {
        Name expectedName = new Name(VALID_NAME_PREAMBLE.trim());
        assertEquals(expectedName, ParserUtil.parsePreamble(VALID_NAME_PREAMBLE));
    }
}
