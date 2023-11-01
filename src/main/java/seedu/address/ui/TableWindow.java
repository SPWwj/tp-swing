package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.tableresults.GenderTableCommandResult;
import seedu.address.logic.commands.tableresults.SecLevelTableCommandResult;
import seedu.address.logic.commands.tableresults.SubjectTableCommandResult;
import seedu.address.model.tag.Subject;



/**
 * Controller of a table page.
 */
public class TableWindow extends UiPart<Stage> {
    public static final String FXML = "TableWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(TableWindow.class);

    @FXML
    private TableView<? extends CommandResult> table;

    /**
     * Constructor for creating TableWindow instance.
     * @param commandResult Table command result instance containing column titles and values.
     */
    public TableWindow(CommandResult commandResult) {
        super(FXML, new Stage());
        table = createTable(commandResult);
        initialize();
    }

    /**
     * Creates a table with table command result instance containing
     * given column titles and values.
     */
    public static TableView<? extends CommandResult> createTable(CommandResult commandResult) {
        if (commandResult instanceof GenderTableCommandResult) {
            GenderTableCommandResult genderTableCommandResult = (GenderTableCommandResult) commandResult;
            return createGenderTable(genderTableCommandResult);
        } else if (commandResult instanceof SecLevelTableCommandResult) {
            SecLevelTableCommandResult secLevelTableCommandResult = (SecLevelTableCommandResult) commandResult;
            return createSecLevelTable(secLevelTableCommandResult);
        } else {
            SubjectTableCommandResult subjectTableCommandResult = (SubjectTableCommandResult) commandResult;
            return createSubjectTable(subjectTableCommandResult);
        }
    }

    /**
     * Create a table with GenderTableCommandResult instance containing counts for each gender.
     * @param commandResult GenderTableCommandResult containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<GenderTableCommandResult> createGenderTable(GenderTableCommandResult commandResult) {
        TableView<GenderTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<GenderTableCommandResult, Integer> maleColumn = new TableColumn<>("Male");
        maleColumn.setCellValueFactory(new PropertyValueFactory<>("maleCount"));
        TableColumn<GenderTableCommandResult, Integer> femaleColumn = new TableColumn<>("Female");
        femaleColumn.setCellValueFactory(new PropertyValueFactory<>("femaleCount"));

        tableToCreate.getColumns().add(maleColumn);
        tableToCreate.getColumns().add(femaleColumn);

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Create a table with SecLevelTableCommandResult instance containing counts for each sec level.
     * @param commandResult SecLevelTableCommand instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<SecLevelTableCommandResult> createSecLevelTable(SecLevelTableCommandResult commandResult) {
        TableView<SecLevelTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<SecLevelTableCommandResult, Integer> sec1Column = new TableColumn<>("Sec 1");
        sec1Column.setCellValueFactory(new PropertyValueFactory<>("sec1Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec2Column = new TableColumn<>("Sec 2");
        sec2Column.setCellValueFactory(new PropertyValueFactory<>("sec2Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec3Column = new TableColumn<>("Sec 3");
        sec3Column.setCellValueFactory(new PropertyValueFactory<>("sec3Count"));
        TableColumn<SecLevelTableCommandResult, Integer> sec4Column = new TableColumn<>("Sec 4");
        sec4Column.setCellValueFactory(new PropertyValueFactory<>("sec4Count"));

        tableToCreate.getColumns().add(sec1Column);
        tableToCreate.getColumns().add(sec2Column);
        tableToCreate.getColumns().add(sec3Column);
        tableToCreate.getColumns().add(sec4Column);

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Create a table with SubjectTableCommandResult instance containing counts for each subject
     * @param commandResult SubjectTableCommandResult instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static TableView<SubjectTableCommandResult> createSubjectTable(SubjectTableCommandResult commandResult) {
        TableView<SubjectTableCommandResult> tableToCreate = new TableView<>();

        TableColumn<SubjectTableCommandResult, Integer> engColumn = new TableColumn<>(Subject.ENG);
        engColumn.setCellValueFactory(new PropertyValueFactory<>("engCount"));
        TableColumn<SubjectTableCommandResult, Integer> chiColumn = new TableColumn<>(Subject.CHI);
        chiColumn.setCellValueFactory(new PropertyValueFactory<>("chiCount"));
        TableColumn<SubjectTableCommandResult, Integer> emathColumn = new TableColumn<>(Subject.EMATH);
        emathColumn.setCellValueFactory(new PropertyValueFactory<>("emathCount"));
        TableColumn<SubjectTableCommandResult, Integer> amathColumn = new TableColumn<>(Subject.AMATH);
        amathColumn.setCellValueFactory(new PropertyValueFactory<>("amathCount"));
        TableColumn<SubjectTableCommandResult, Integer> phyColumn = new TableColumn<>(Subject.PHY);
        phyColumn.setCellValueFactory(new PropertyValueFactory<>("phyCount"));
        TableColumn<SubjectTableCommandResult, Integer> chemiColumn = new TableColumn<>(Subject.CHEMI);
        chemiColumn.setCellValueFactory(new PropertyValueFactory<>("chemiCount"));
        TableColumn<SubjectTableCommandResult, Integer> bioColumn = new TableColumn<>(Subject.BIO);
        bioColumn.setCellValueFactory(new PropertyValueFactory<>("bioCount"));
        TableColumn<SubjectTableCommandResult, Integer> geogColumn = new TableColumn<>(Subject.GEOG);
        geogColumn.setCellValueFactory(new PropertyValueFactory<>("geogCount"));
        TableColumn<SubjectTableCommandResult, Integer> histColumn = new TableColumn<>(Subject.HIST);
        histColumn.setCellValueFactory(new PropertyValueFactory<>("histCount"));
        TableColumn<SubjectTableCommandResult, Integer> socColumn = new TableColumn<>(Subject.SOC);
        socColumn.setCellValueFactory(new PropertyValueFactory<>("socCount"));

        tableToCreate.getColumns().add(engColumn);
        tableToCreate.getColumns().add(chiColumn);
        tableToCreate.getColumns().add(emathColumn);
        tableToCreate.getColumns().add(amathColumn);
        tableToCreate.getColumns().add(phyColumn);
        tableToCreate.getColumns().add(chemiColumn);
        tableToCreate.getColumns().add(bioColumn);
        tableToCreate.getColumns().add(geogColumn);
        tableToCreate.getColumns().add(histColumn);
        tableToCreate.getColumns().add(socColumn);

        tableToCreate.getItems().add(commandResult);

        tableToCreate.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableToCreate;
    }

    /**
     * Set up the root control, scene and css for the table window.
     */
    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(table);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/TableWindow.css");
        super.getRoot().setScene(scene);
    }

    /**
     * Shows the table window.
     */
    public void show() {
        logger.fine("Showing statistical table in another window.");
        super.getRoot().show();
        super.getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the table window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
