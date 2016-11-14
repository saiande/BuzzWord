package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Ritwik Banerjee
 */
public class LoginSingleton extends Stage {
    // HERE'S THE SINGLETON
    static LoginSingleton singleton;

    // GUI CONTROLS FOR OUR DIALOG
    HBox    username;
    HBox password;
    VBox   generalPane;
    Scene  scene;
    Label  nameLabel;
    Label  passwordLabel;
    TextField enterUsername;
    TextField enterPassword;
    Button enterButton;
    Button cancelButton;
    Stage primaryStage;
    String selection;
    HomeScreen home;

    // CONSTANT CHOICES
    public static final String ENTER    = "Enter";
    public static final String CANCEL = "Cancel";

    /**
     * Note that the constructor is private since it follows
     * the singleton design pattern.
     */
    public LoginSingleton() {
        init(primaryStage);
    }

    /**
     * The static accessor method for this singleton.
     *
     * @return The singleton object for this type.
     */
    public static LoginSingleton getSingleton() {
        if (singleton == null)
            singleton = new LoginSingleton();
        return singleton;
    }

    /**
     * This method initializes the singleton for use.
     *
     * @param primaryStage The window above which this dialog will be centered.
     */
    public void init(Stage primaryStage) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        // LABEL TO DISPLAY THE CUSTOM MESSAGE
        nameLabel = new Label();
        nameLabel.setText("Username: ");
        username = new HBox();
        enterUsername = new TextField();
        username.getChildren().addAll(nameLabel, enterUsername);
        passwordLabel = new Label();
        passwordLabel.setText("Password: ");
        password = new HBox();
        enterPassword = new TextField();
        password.getChildren().addAll(passwordLabel, enterPassword);

        // YES, NO, AND CANCEL BUTTONS
        enterButton = new Button(ENTER);
        cancelButton = new Button(CANCEL);

        // MAKE THE EVENT HANDLER FOR THESE BUTTONS
        EventHandler<ActionEvent> LoginHandler = event -> {
            LoginSingleton.this.selection = ((Button) event.getSource()).getText();
            LoginSingleton.this.hide();
            home.getClearPane().toBack();
        };


        // AND THEN REGISTER THEM TO RESPOND TO INTERACTIONS
        enterButton.setOnAction(LoginHandler);
        cancelButton.setOnAction(LoginHandler);

        // NOW ORGANIZE OUR BUTTONS
        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(enterButton);
        buttonBox.getChildren().add(cancelButton);

        // WE'LL PUT EVERYTHING HERE
        generalPane = new VBox();
        generalPane.setAlignment(Pos.CENTER);
        generalPane.getChildren().addAll(username, password);
        generalPane.getChildren().add(buttonBox);

        // MAKE IT LOOK NICE
        generalPane.setPadding(new Insets(10, 20, 20, 20));
        generalPane.setSpacing(10);

        // AND PUT IT IN THE WINDOW
        scene = new Scene(generalPane);
        this.setScene(scene);
    }

    /**
     * Accessor method for getting the selection the user made.
     *
     * @return Either YES, NO, or CANCEL, depending on which
     * button the user selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }

    /**
     * This method loads a custom message into the label
     * then pops open the dialog.
     *
     * @param title   The title to appear in the dialog window bar.
     * @param message Message to appear inside the dialog.
     */
    public void show(String title, String message) {
        // SET THE DIALOG TITLE BAR TITLE
        setTitle(title);

        // SET THE MESSAGE TO DISPLAY TO THE USER
        nameLabel.setText(message);

        // AND OPEN UP THIS DIALOG, MAKING SURE THE APPLICATION
        // WAITS FOR IT TO BE RESOLVED BEFORE LETTING THE USER
        // DO MORE WORK.
        showAndWait();
    }

}
