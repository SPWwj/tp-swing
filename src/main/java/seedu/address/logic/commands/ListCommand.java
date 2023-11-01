package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private static Logger logger = Logger.getLogger(ListCommand.class.getName());

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "listing all students");
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.log(Level.INFO, MESSAGE_SUCCESS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
