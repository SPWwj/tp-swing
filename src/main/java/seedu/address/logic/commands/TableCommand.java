package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.OrganizeData;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.tableresults.GenderTableCommandResult;
import seedu.address.logic.commands.tableresults.SecLevelTableCommandResult;
import seedu.address.logic.commands.tableresults.SubjectTableCommandResult;
import seedu.address.model.Model;

/**
 * Calculate counts for each category(to be specified, eg: by gender, Sec-Level, Subject)
 * for table generation.
 */
public class TableCommand extends Command {
    public static final String COMMAND_WORD = "table";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": show the statistics of student in a table "
            + "by the attributes. \n"
            + "[" + PREFIX_GENDER + "] "
            + "[" + PREFIX_SEC_LEVEL + "] "
            + "[" + PREFIX_SUBJECT + "]...\n"
            + "Example: " + COMMAND_WORD + PREFIX_GENDER;

    public static final String MESSAGE_INCORRECT_COMMAND = "To view a table, please do one of the following:\n"
                                                        + COMMAND_WORD + " " + PREFIX_GENDER + " or\n"
                                                        + COMMAND_WORD + " " + PREFIX_SEC_LEVEL + " or\n"
                                                        + COMMAND_WORD + " " + PREFIX_SUBJECT;
    private final String args;

    /**
     * Constructor for TableCommand.
     * @param args represents the category for table, eg: s/, l/, g/
     */
    public TableCommand(String args) {
        this.args = args.trim();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch(this.args) {
        case "g/":
            return executeGender(model);
        case "l/":
            return executeSecLevel(model);
        case "s/":
            return executeSubject(model);
        default:
            throw new CommandException(MESSAGE_INCORRECT_COMMAND);

        }
    }

    /**
     * Generate GenderTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return GenderTableCommandResult instance containing the column titles and values.
     */
    private GenderTableCommandResult executeGender(Model model) {
        return new GenderTableCommandResult(OrganizeData.byGender(model));
    }

    /**
     * Generate SecLevelTableCommandResult instance
     * @param model instance of Model subclass, e.g. ModelManager instance
     * @return SecLevelTableCommandResult instance containing column titles and values.
     */
    private SecLevelTableCommandResult executeSecLevel(Model model) {
        return new SecLevelTableCommandResult(OrganizeData.bySecLevel(model));
    }

    /**
     * Generate SubjectTableCommandResult instance.
     * @param model instance of Model subclass, e.g. ModelManager instance.
     * @return SubjectTableCommandResult instance containing column titles and values.
     */
    private SubjectTableCommandResult executeSubject(Model model) {
        return new SubjectTableCommandResult(OrganizeData.bySubject(model));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TableCommand)) {
            return false;
        }

        TableCommand otherCommand = (TableCommand) other;
        return otherCommand.args.equals(this.args);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("category: ", args)
                .toString();
    }

}
