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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the TwinBot instance */
    public void setTwinBot(TwinBot tb) {
        twinBot = tb;
        String greeting = "What's up twin, I'm TWINBOT\nWhat can I do for you twin?";
        dialogContainer.getChildren().add(DialogBox.getTwinBotDialog(greeting, twinBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * TwinBot's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = twinBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTwinBotDialog(response, twinBotImage));
        userInput.clear();

        if (twinBot.isExitRequested()) {
            System.exit(0);
        }
    }
}
