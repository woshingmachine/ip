package twinbot;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 * Supports asymmetric styling for user messages, TwinBot messages, and error messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box with blue styling on the right.
     * @param text The user's message
     * @param img The user's image
     * @return A user dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.dialog.setStyle("-fx-background-color: #4a90e2; "
                + "-fx-text-fill: white; "
                + "-fx-padding: 10px 15px; "
                + "-fx-border-radius: 15; "
                + "-fx-font-size: 13; "
                + "-fx-wrap-text: true; ");
        return db;
    }

    /**
     * Creates a TwinBot dialog box with light blue styling on the left.
     * @param text The bot's message
     * @param img The bot's image
     * @return A bot dialog box
     */
    public static DialogBox getTwinBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #c8deff; "
                + "-fx-text-fill: #202124; "
                + "-fx-padding: 10px 15px; "
                + "-fx-border-radius: 15; "
                + "-fx-font-size: 13; "
                + "-fx-wrap-text: true; ");
        db.flip();
        return db;
    }

    /**
     * Creates an error dialog box with distinct red styling on the left.
     * @param text The error message
     * @param img The bot's image
     * @return An error dialog box with red styling
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #ffebee; "
                + "-fx-text-fill: #c62828; "
                + "-fx-padding: 10px 15px; "
                + "-fx-border-radius: 15; "
                + "-fx-border-color: #f44336; "
                + "-fx-border-width: 2; "
                + "-fx-font-size: 13; "
                + "-fx-font-weight: bold; "
                + "-fx-wrap-text: true; ");
        db.flip();
        return db;
    }
}
