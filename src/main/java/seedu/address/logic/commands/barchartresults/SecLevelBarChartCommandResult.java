package seedu.address.logic.commands.barchartresults;

import static java.util.Objects.requireNonNull;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;


/**
 * Represents the command result for sec level table generation.
 */
public class SecLevelBarChartCommandResult extends CommandResult {
    private static final String MESSAGE_SUCCESS = "A bar chart categorized by SecLevel is shown";
    private int sec1Count;
    private int sec2Count;
    private int sec3Count;
    private int sec4Count;


    /**
     * Constructor for SecLevelBarChartCommandResult.
     * @param columnValueMapping A hashmap instance containing column titles(String) as keys and counts(Long) as values.
     */
    public SecLevelBarChartCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS);
        requireNonNull(columnValueMapping);
        this.sec1Count = columnValueMapping.get("Sec 1");
        this.sec2Count = columnValueMapping.get("Sec 2");
        this.sec3Count = columnValueMapping.get("Sec 3");
        this.sec4Count = columnValueMapping.get("Sec 4");
    }

    /**
     * Check if this CommandResult instance is meant for table window generation.
     * @return always return true for SecLevelBarChartCommandResult instance.
     */
    @Override
    public boolean isShowBarChart() {
        return true;
    }

    /**
     * Getter method for sec1Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec1 category.
     */
    public int getSec1Count() {
        return sec1Count;
    }

    /**
     * Getter method for sec2Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec2 category.
     */
    public int getSec2Count() {
        return sec2Count;
    }

    /**
     * Getter method for sec3Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec3 category.
     */
    public int getSec3Count() {
        return sec3Count;
    }

    /**
     * Getter method for sec4Count which will auto invoked by javafx PropertyValueFactory instance.
     * @return counts for sec4 category.
     */
    public int getSec4Count() {
        return sec4Count;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SecLevelBarChartCommandResult)) {
            return false;
        }

        SecLevelBarChartCommandResult otherCommandResult = (SecLevelBarChartCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.sec1Count == sec1Count
                && otherCommandResult.sec2Count == sec2Count
                && otherCommandResult.sec3Count == sec3Count
                && otherCommandResult.sec4Count == sec4Count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(),
                sec1Count, sec2Count, sec3Count, sec4Count);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", MESSAGE_SUCCESS)
                .add("showHelp", isShowHelp())
                .add("showTable", isShowTable())
                .add("showBarChart", isShowBarChart())
                .add("exit", isExit())
                .toString();
    }
}
