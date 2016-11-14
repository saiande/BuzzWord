package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import propertymanager.PropertyManager;
import sun.font.TextLabel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static settings.AppPropertyType.*;
import static settings.InitializationParameters.APP_IMAGEDIR_PATH;

/**
 * Created by sai on 11/7/16.
 */
public class HomeScreen extends BorderPane {

    protected FileController fileController;
    protected Button createProfileButton;
    protected Button loginButton;
    protected Button profileButton;
    protected Button xButton;

    public ComboBox getSelectMode() {
        return selectMode;
    }

    protected ComboBox selectMode;
    protected Button startPlayingButton;
    protected VBox list;
    protected VBox secondList;
    protected VBox topVBox;
    protected HBox closeHBox;
    protected HBox titleHBox;
    protected Label title;
    protected String modeTitle;
    protected static Pane clearPane;
    public Pane getClearPane() {
        return clearPane;
    }


    public HomeScreen(FileController fileController) {
        this.fileController = fileController;

    }
    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");

        createProfileButton = new Button();
        createProfileButton.setText("Create Profile");
        loginButton = new Button();
        loginButton.setText("Login");
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        title = new Label();
        title.setText("!! BUZZWORD !!");
        title.setScaleX(2);
        title.setScaleY(2);
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 50, 50, 50));
        list.getChildren().addAll(createProfileButton, loginButton);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        titleHBox = new HBox(100);
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox);

        Image image = new Image("file:BuzzWordGame/resources/images/Logo.png");
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        imageview.setTranslateX(-90);
        imageview.setTranslateY(-20);
        this.setTop(topVBox);
        this.setCenter(imageview);
        this.setLeft(list);
        clearPane = new Pane();
        clearPane.setPrefSize(800, 650);
        clearPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.2);");
    }


    public void initializeHomeHandlers(AppTemplate app) throws InstantiationException {
        createProfileButton.setOnAction(e -> {
            try {
                fileController.handleCreateProfileRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        loginButton.setOnAction(e -> {
            try {
                fileController.handleLoginRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        xButton.setOnAction(e -> {
            try {
                fileController.handleXRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
    public void loginHandlers(AppTemplate app) throws InstantiationException {


        clearPane.toFront();
//        PropertyManager propertyManager = PropertyManager.getManager();
//        LoginSingleton dialog = LoginSingleton.getSingleton();
//        dialog.show(propertyManager.getPropertyValue("Login"),
//                propertyManager.getPropertyValue("Username "));
        HBox    username = new HBox();
        HBox password = new HBox();
        HBox buttonBox = new HBox();
        VBox   box = new VBox();
        box.setPrefSize(400, 150);
        Label  nameLabel = new Label();
        nameLabel.setText("Username: ");
        Label  passwordLabel = new Label();
        passwordLabel.setText("Password: ");
        TextField enterUsername = new TextField();
        TextField enterPassword = new TextField();
        Button enterButton = new Button();
        enterButton.setText("Enter");
        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        buttonBox.getChildren().addAll(enterButton, cancelButton);
        username.getChildren().addAll(nameLabel, enterUsername);
        password.getChildren().addAll(passwordLabel, enterPassword);
        box.getChildren().addAll(username, password, buttonBox);
        username.setPadding(new Insets(10,10,10,10));
        password.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.setPadding(new Insets(10,10,10,10));
        box.setPadding(new Insets(50,50,50,50));
        clearPane.getChildren().addAll(box);
        box.setTranslateX(300);
        box.setTranslateY(300);
        box.setStyle("-fx-background-color: mediumpurple;");

        enterButton.setOnAction(e -> {
            try {
                box.setVisible(false);
                clearPane.toBack();
                fileController.handleEnterRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        cancelButton.setOnAction(e -> {
            try {
                box.setVisible(false);
                clearPane.toBack();
                fileController.handleCancelRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });

    }


    public void afterLoginProfileHandlers(AppTemplate app) throws InstantiationException {

        secondList = new VBox(50);
        secondList.setTranslateY(-30);
        secondList.setStyle("-fx-background-color: mediumpurple;");
        secondList.setPadding(new Insets(200, 0, 50, 50));
        profileButton = new Button();
        profileButton.setText("Username");
        ObservableList<String> options = FXCollections.observableArrayList("Dictionary Words", "Animals", "Places", "Science", "Famous People");
        selectMode = new ComboBox(options);
        selectMode.setPromptText("Select Mode");
        selectMode.setTranslateX(-30);
        startPlayingButton = new Button();
        startPlayingButton.setText("Start Playing");
        secondList.getChildren().addAll(profileButton, selectMode, startPlayingButton);
        this.setLeft(secondList);

        profileButton.setOnAction(e -> {
            try {
                fileController.handleProfileRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        selectMode.setOnAction(e -> {
            modeTitle = selectMode.getValue().toString();
            try {
                fileController.handleSelectModeRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        startPlayingButton.setOnAction(e -> {
            try {
                fileController.handleStartPlayingRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        xButton.setOnAction(e -> {
            try {
                fileController.handleXRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
    public String getModeTitle() {
        return modeTitle;
    }
}
