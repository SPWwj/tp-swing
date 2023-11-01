package seedu.address.logic.commands.tableresults;

import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.tag.Subject;

/**
 * Represents for the command result for subject table generation.
 */
public class SubjectTableCommandResult extends CommandResult {

    private static final String MESSAGE_SUCCESS = "A table categorized by Subject is shown";
    private int engCount;
    private int chiCount;
    private int emathCount;
    private int amathCount;
    private int phyCount;
    private int chemiCount;
    private int bioCount;
    private int geogCount;
    private int histCount;
    private int socCount;


    /**
     * Constructor for SubjectTableCommandResult.
     * @param columnValueMapping hash map instance containing column titles(String) as keys and counts(Long) as values.
     */
    public SubjectTableCommandResult(Map<String, Integer> columnValueMapping) {
        super(MESSAGE_SUCCESS);
        this.engCount = columnValueMapping.get(Subject.ENG);
        this.chiCount = columnValueMapping.get(Subject.CHI);
        this.emathCount = columnValueMapping.get(Subject.EMATH);
        this.amathCount = columnValueMapping.get(Subject.AMATH);
        this.phyCount = columnValueMapping.get(Subject.PHY);
        this.chemiCount = columnValueMapping.get(Subject.CHEMI);
        this.bioCount = columnValueMapping.get(Subject.BIO);
        this.geogCount = columnValueMapping.get(Subject.GEOG);
        this.histCount = columnValueMapping.get(Subject.HIST);
        this.socCount = columnValueMapping.get(Subject.SOC);
    }

    /**
     * Check if this CommandResult instance is meant for creating table window.
     * @return always return true for SubjectTableCommandResult instance.
     */
    @Override
    public boolean isShowTable() {
        return true;
    }

    /**
     * Getter method for engCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for english category.
     */
    public int getEngCount() {
        return engCount;
    }

    /**
     * Getter method for chiCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for chinese category.
     */
    public int getChiCount() {
        return chiCount;
    }

    /**
     * Getter method for emathCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for elementary mathematics category.
     */
    public int getEmathCount() {
        return emathCount;
    }

    /**
     * Getter method for amathCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for additional mathematics category.
     */
    public int getAmathCount() {
        return amathCount;
    }

    /**
     * Getter method for phyCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for physics category.
     */
    public int getPhyCount() {
        return phyCount;
    }

    /**
     * Getter method for chemiCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for chemistry category.
     */
    public int getChemiCount() {
        return chemiCount;
    }

    /**
     * Getter method for bioCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for biology category.
     */
    public int getBioCount() {
        return bioCount;
    }

    /**
     * Getter method for geogCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for geography category.
     */
    public int getGeogCount() {
        return geogCount;
    }

    /**
     * Getter method for histCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for history category.
     */
    public int getHistCount() {
        return histCount;
    }

    /**
     * Getter method for socCount which will auto invoked by javafx PropertyValueFactory instance.
     * @return count for social studies category.
     */
    public int getSocCount() {
        return socCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SubjectTableCommandResult)) {
            return false;
        }

        SubjectTableCommandResult otherCommandResult = (SubjectTableCommandResult) other;
        return super.equals(otherCommandResult)
                && otherCommandResult.engCount == engCount
                && otherCommandResult.chiCount == chiCount
                && otherCommandResult.emathCount == emathCount
                && otherCommandResult.amathCount == amathCount
                && otherCommandResult.phyCount == phyCount
                && otherCommandResult.chemiCount == chemiCount
                && otherCommandResult.bioCount == bioCount
                && otherCommandResult.geogCount == geogCount
                && otherCommandResult.histCount == histCount
                && otherCommandResult.socCount == socCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getFeedbackToUser(), super.isShowHelp(), super.isExit(),
                engCount, chiCount, emathCount, amathCount, phyCount, chemiCount,
                bioCount, geogCount, histCount, socCount);
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
