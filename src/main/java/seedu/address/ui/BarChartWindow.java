package seedu.address.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.barchartresults.GenderBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SecLevelBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SubjectBarChartCommandResult;
import seedu.address.model.tag.Subject;



/**
 * Controller of a table page.
 */
public class BarChartWindow extends UiPart<Stage> {
    public static final String FXML = "BarChartWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(BarChartWindow.class);

    @FXML
    private BarChart<String, Number> barChart;

    /**
     * Constructor for creating LineChartWindow instance.
     * @param commandResult Table command result instance containing column titles and values.
     */
    public BarChartWindow(CommandResult commandResult) {
        super(FXML, new Stage());
        barChart = createBarChart(commandResult);
        initialize();
    }

    /**
     * Creates a table with table command result instance containing
     * given column titles and values.
     */
    public static BarChart<String, Number> createBarChart(CommandResult commandResult) {
        if (commandResult instanceof GenderBarChartCommandResult) {
            GenderBarChartCommandResult genderBarChartCommandResult = (GenderBarChartCommandResult) commandResult;
            return createGenderBarChart(genderBarChartCommandResult);
        } else if (commandResult instanceof SecLevelBarChartCommandResult) {
            SecLevelBarChartCommandResult secLevelBarChartCommandResult = (SecLevelBarChartCommandResult) commandResult;
            return createSecLevelBarChart(secLevelBarChartCommandResult);
        } else {
            SubjectBarChartCommandResult subjectBarChartCommandResult = (SubjectBarChartCommandResult) commandResult;
            return createSubjectBarChart(subjectBarChartCommandResult);
        }
    }

    /**
     * Create a table with GenderTableCommandResult instance containing counts for each gender.
     * @param commandResult GenderTableCommandResult containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createGenderBarChart(GenderBarChartCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Male", "Female")));
        xAxis.setLabel("Gender");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between genders");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Male", commandResult.getMaleCount()));
        series.getData().add(new XYChart.Data<>("Female", commandResult.getFemaleCount()));

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Create a table with SecLevelTableCommandResult instance containing counts for each sec level.
     * @param commandResult SecLevelTableCommand instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createSecLevelBarChart(SecLevelBarChartCommandResult commandResult) {

        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Sec 1", "Sec 2", "Sec 3", "Sec 4")));
        xAxis.setLabel("Sec Level");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between Sec Levels");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Sec 1", commandResult.getSec1Count()));
        series.getData().add(new XYChart.Data<>("Sec 2", commandResult.getSec2Count()));
        series.getData().add(new XYChart.Data<>("Sec 3", commandResult.getSec3Count()));
        series.getData().add(new XYChart.Data<>("Sec 4", commandResult.getSec4Count()));

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Create a table with SubjectTableCommandResult instance containing counts for each subject
     * @param commandResult SubjectTableCommandResult instance containing column titles and counts mapping.
     * @return a TableView instance generated with given column titles and counts from argument passed in.
     */
    private static BarChart<String, Number> createSubjectBarChart(SubjectBarChartCommandResult commandResult) {
        CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                Subject.ENG, Subject.CHI, Subject.EMATH, Subject.AMATH, Subject.PHY, Subject.CHEMI,
                Subject.BIO, Subject.GEOG, Subject.HIST, Subject.SOC)));
        xAxis.setLabel("Subjects");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Students");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Comparison between Subjects");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(Subject.ENG, commandResult.getEngCount()));
        series.getData().add(new XYChart.Data<>(Subject.CHI, commandResult.getChiCount()));
        series.getData().add(new XYChart.Data<>(Subject.EMATH, commandResult.getEmathCount()));
        series.getData().add(new XYChart.Data<>(Subject.AMATH, commandResult.getAmathCount()));
        series.getData().add(new XYChart.Data<>(Subject.PHY, commandResult.getPhyCount()));
        series.getData().add(new XYChart.Data<>(Subject.CHEMI, commandResult.getChemiCount()));
        series.getData().add(new XYChart.Data<>(Subject.BIO, commandResult.getBioCount()));
        series.getData().add(new XYChart.Data<>(Subject.GEOG, commandResult.getGeogCount()));
        series.getData().add(new XYChart.Data<>(Subject.HIST, commandResult.getHistCount()));
        series.getData().add(new XYChart.Data<>(Subject.SOC, commandResult.getSocCount()));

        barChart.getData().add(series);

        return barChart;

    }

    /**
     * Set up the root control, scene and css for the table window.
     */
    public void initialize() {
        BorderPane root = new BorderPane();
        root.setCenter(barChart);

        Scene scene = new Scene(root, 500, 300);
        scene.getStylesheets().add("resources/view/LineChartWindow.css");
        super.getRoot().setScene(scene);
    }

    /**
     * Shows the table window.
     */
    public void show() {
        logger.fine("Showing bar chart in another window.");
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
