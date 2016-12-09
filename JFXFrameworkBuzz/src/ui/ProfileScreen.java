package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by sai on 12/8/16.
 */
public class ProfileScreen extends BorderPane {
    protected AppTemplate app;
    protected FileController fileController;
    protected GameData gamedata;
    protected Button xButton;
    protected HBox closeHBox;
    protected HBox titleHBox;
    protected Label title;
    protected VBox topVBox;
    protected Label current;
    protected HBox username;
    protected HBox password;
    protected Label usernameLabel;
    protected Label passwordLabel;
    protected Label actualUsername;
    protected Label actualPassword;
    protected VBox box;
    protected Button back;
    protected Button change;
    protected HBox buttons;
    protected TextField usernameText;
    protected PasswordField passwordText;


    public ProfileScreen(FileController fileController, GameData gamedata) {
        this.fileController = fileController;
        this.gamedata = gamedata;
        initialize();
    }

    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");
        //top of the screen including close button and title
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        title = new Label();
        title.setText("Profile Settings");
        title.setScaleX(2);
        title.setScaleY(2);
        titleHBox = new HBox(100);
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox);
        this.setTop(topVBox);

        current = new Label();
        current.setText("Current Profile Information");
        username = new HBox(10);
        password = new HBox(10);
        usernameLabel = new Label();
        passwordLabel = new Label();
        usernameLabel.setText("Username: ");
        passwordLabel.setText("Password: ");
        actualUsername = new Label();
        actualPassword = new Label();
        actualPassword.setText(gamedata.getPassword());
        actualUsername.setText(gamedata.getUsername());
        username.getChildren().addAll(usernameLabel, actualUsername);
        password.getChildren().addAll(passwordLabel, actualPassword);
        box = new VBox(10);
        buttons = new HBox(10);
        back = new Button();
        back.setText("Back");
        change = new Button();
        change.setText("Change");
        buttons.getChildren().addAll(back, change);
        box.getChildren().addAll(current, username, password, buttons);
        this.setCenter(box);

        back.setOnAction(e -> {
            this.toBack();
        });

        change.setOnAction(e ->{
            initializeChange();
        });


    }

    public void initializeChange()
    {
        HBox username = new HBox(10);
        HBox password = new HBox(10);
        Label usernameLabel = new Label();
        Label passwordLabel = new Label();
        usernameLabel.setText("Username: ");
        passwordLabel.setText("Password: ");
        usernameText = new TextField();
        passwordText = new PasswordField();
        username.getChildren().addAll(usernameLabel, usernameText);
        password.getChildren().addAll(passwordLabel, passwordText);
        VBox box = new VBox(10);
        HBox buttons = new HBox(10);
        Button back = new Button();
        back.setText("Back");
        Button enter = new Button();
        enter.setText("Enter");
        buttons.getChildren().addAll(back, enter);
        box.getChildren().addAll(username, password, buttons);
        this.setCenter(box);

        enter.setOnAction(e ->{
            ((GameData)(gamedata.appTemplate.getDataComponent())).setUsername(usernameText.getText());
            ((GameData)(gamedata.appTemplate.getDataComponent())).setPassword(passwordText.getText());
            try {
                fileController.handleCreateRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }
}
