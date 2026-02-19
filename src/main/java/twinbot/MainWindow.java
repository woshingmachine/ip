package twinbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TwinBot twinBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image twinBotImage = new Image(this.getClass().getResourceAsStream("/images/DaTwinBot.png"));

    /**
     * Initializes the main window controller.
     * Binds the scroll pane to auto-scroll and loads the CSS stylesheet.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Load CSS stylesheet for better GUI styling
        String css = this.getClass().getResource("/styles.css").toExternalForm();
        this.getStylesheets().add(css);
    }

    /** Injects the TwinBot instance */
    public void setTwinBot(TwinBot tb) {
        twinBot = tb;
        String greeting = "What's up twin, I'm TWINBOT\nWhat can I do for you twin?";
        dialogContainer.getChildren().add(DialogBox.getTwinBotDialog(greeting, twinBotImage));
    }

    /**
     * Handles user input from the text field and generates appropriate responses.
     * Displays user messages in blue and bot responses in a distinct style.
     * Error responses are highlighted with red styling.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = twinBot.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        // Check if response is an error (error messages typically start with specific keywords)
        if (isErrorMessage(response)) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, twinBotImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getTwinBotDialog(response, twinBotImage));
        }

        userInput.clear();

        if (twinBot.isExitRequested()) {
            System.exit(0);
        }
    }

    /**
     * Determines if a response message is an error message.
     * Error messages typically contain keywords like "error", "invalid", "unknown", etc.
     * @param response The response message
     * @return true if the response is an error message, false otherwise
     */
    private boolean isErrorMessage(String response) {
        String lowerResponse = response.toLowerCase();
        return lowerResponse.contains("error")
                || lowerResponse.contains("invalid")
                || lowerResponse.contains("unknown")
                || lowerResponse.contains("cannot")
                || lowerResponse.contains("failed")
                || lowerResponse.contains("exception");
    }
}
