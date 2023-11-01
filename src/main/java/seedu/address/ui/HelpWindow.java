package seedu.address.ui;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import javafx.embed.swing.SwingNode;
import javafx.scene.image.Image;

import javax.swing.*;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2324s1-cs2103t-w13-2.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    @FXML
    private SwingNode swingButtonContainer;
    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;
    @FXML
    private HBox helpMessageContainer;
    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        initSwingComponent();
        displayImageInSwingNode();

    }
    private void displayImageInSwingNode() {
        SwingNode swingNode = new SwingNode();
        SwingUtilities.invokeLater(() -> {
            Image fxImage = new Image("https://spirify.azurewebsites.net/icon-512.png");
            BufferedImage awtImage = SwingFXUtils.fromFXImage(fxImage, null);
            JLabel imageLabel = new JLabel(new ImageIcon(awtImage));
            swingNode.setContent(imageLabel);
        });
        helpMessageContainer.getChildren().add(swingNode);
    }

    private void initSwingComponent() {
        SwingUtilities.invokeLater(() -> {
            JButton swingButton = new JButton("Swing Button");
            swingButton.addActionListener(e -> {
                System.out.println("Swing button clicked!");
            });
            swingButtonContainer.setContent(swingButton);
        });
    }
    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
