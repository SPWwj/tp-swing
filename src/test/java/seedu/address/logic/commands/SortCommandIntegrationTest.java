package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.SortIn;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void executeSortAscSuccess() {
        String sequence = "ASC";
        SortIn validSortIn = new SortIn(sequence);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(validSortIn);
        assertCommandSuccess(new SortCommand(validSortIn), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeSortDescSuccess() {
        String sequence = "DESC";
        SortIn validSortIn = new SortIn(sequence);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateSortedPersonList(validSortIn);
        assertCommandSuccess(new SortCommand(validSortIn), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeSortInvalidFailure() {
        String sequence = "Invalid";
        try {
            SortIn invalidSortIn = new SortIn(sequence);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }

}
