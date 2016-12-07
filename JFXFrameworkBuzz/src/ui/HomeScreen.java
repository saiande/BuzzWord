package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by sai on 11/7/16.
 */
public class HomeScreen extends BorderPane {

    //Controls for initial home screen
    protected AppTemplate       app;
    protected FileController    fileController;
    protected GameData          gamedata;
    protected Button            xButton;
    protected Label             title;
    protected HBox              closeHBox;
    protected HBox              titleHBox;
    protected VBox              topVBox;
    protected Button            createProfileButton;
    protected Button            loginButton;
    protected Button            help;
    protected VBox              list;

    //Controls for home screen after login
    protected Button    profileButton;
    protected ComboBox  selectMode;
    protected Button    startPlayingButton;
    protected Button    keepPlayingButton;
    protected VBox      secondList;

    protected String    modeTitle;          //used in level selection screen
    protected Pane      clearPane;
    protected Pane      profilePane;
    public String       usernameString;
    public String       passWordString;
    public int          animalLevel;
    public int          dictLevel;
    public int          citiesLevel;

    //constructor
    public HomeScreen(FileController fileController, GameData gamedata) {
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
        title.setText("!! BUZZWORD !!");
        title.setScaleX(2);
        title.setScaleY(2);
        titleHBox = new HBox(100);
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox);
        this.setTop(topVBox);
        //left side of screen including buttons
        createProfileButton = new Button();
        createProfileButton.setText("Create Profile");
        loginButton = new Button();
        loginButton.setText("Login");
        help = new Button();
        help.setText("Help");
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 50, 50, 50));
        list.getChildren().addAll(createProfileButton, loginButton, help);
        this.setLeft(list);
        //setting logo in the middle of the screen
        Image image = new Image("file:BuzzWordGame/resources/images/Logo.png");
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        imageview.setTranslateX(-90);
        imageview.setTranslateY(-20);
        this.setCenter(imageview);
        //clear pane for login information
        keepPlayingButton = new Button() ;
        clearPane = new Pane();

        profilePane = new Pane();

    }

    public Pane getClearPane() {
        return clearPane;
    }

    public Pane getProfilePane(){return profilePane;}

    public String getUsernameString() {
        return usernameString;
    }

    public String getPassWordString() {
        return passWordString;
    }

    public String getModeTitle() {
        return modeTitle;
    }

    public int getAnimalLevel() {return animalLevel; }

    public int getDictLevel() {return dictLevel; }

    public int getCitiesLevel() {return citiesLevel; }

    public void initializeHomeHandlers(AppTemplate app) throws InstantiationException {
        this.app = app;
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
        help.setOnAction(e -> {
            try {
                fileController.handleHelpRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        xButton.setOnAction(e -> {
            try {
                fileController.handleXRequest();
                System.exit(1);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }

    public void profileHandlers(AppTemplate app) throws InstantiationException{
        profilePane.setPrefSize(800, 650);
        profilePane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.2);");
        HBox    username = new HBox();
        HBox password = new HBox();
        HBox buttonBox = new HBox();
        VBox   box = new VBox();
        Label title = new Label();
        box.setPrefSize(400, 150);
        title.setText("Create a New Profile");
        Label  nameLabel = new Label();
        nameLabel.setText("Username: ");
        Label  passwordLabel = new Label();
        passwordLabel.setText("Password: ");
        TextField enterUsername = new TextField();
        PasswordField enterPassword = new PasswordField();
        Button createButton = new Button();
        createButton.setText("Create");
        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        buttonBox.getChildren().addAll(createButton, cancelButton);
        username.getChildren().addAll(nameLabel, enterUsername);
        password.getChildren().addAll(passwordLabel, enterPassword);
        box.getChildren().addAll(title, username, password, buttonBox);
        username.setPadding(new Insets(10,10,10,10));
        password.setPadding(new Insets(10, 10, 10, 10));
        buttonBox.setPadding(new Insets(10,10,10,10));
        box.setPadding(new Insets(50,50,50,50));
        profilePane.getChildren().addAll(box);
        box.setTranslateX(300);
        box.setTranslateY(300);
        box.setStyle("-fx-background-color: mediumpurple;");
        profilePane.toFront();

        createButton.setOnAction(e -> {
            try {
                usernameString = enterUsername.getText();
                passWordString = enterPassword.getText();
                ((GameData)(app.getDataComponent())).setUsername(usernameString);
                ((GameData)(app.getDataComponent())).setPassword(passWordString);
                ((GameData)(app.getDataComponent())).setAnimals(1);
                ((GameData)(app.getDataComponent())).setDict(1);
                ((GameData)(app.getDataComponent())).setCities(1);
                animalLevel = 1;
                dictLevel = 1;
                citiesLevel = 1;
                //box.setVisible(false);
                profilePane.toBack();
                fileController.handleCreateRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        cancelButton.setOnAction(e -> {
            try {
                //box.setVisible(false);
                profilePane.toBack();
                fileController.handleCancelRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
}

    public void loginHandlers(AppTemplate app) throws InstantiationException {
        clearPane.setPrefSize(800, 650);
        clearPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.2);");
        HBox    username = new HBox();
        HBox    password = new HBox();
        HBox    buttonBox = new HBox();
        VBox    box = new VBox();
        box.setPrefSize(400, 150);
        Label  nameLabel = new Label();
        nameLabel.setText("Username: ");
        Label  passwordLabel = new Label();
        passwordLabel.setText("Password: ");
        TextField enterUsername = new TextField();
        PasswordField enterPassword = new PasswordField();
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
        clearPane.toFront();

        enterButton.setOnAction(e -> {
            try {
                usernameString = enterUsername.getText();
                passWordString = enterPassword.getText();
                ((GameData)(app.getDataComponent())).setUsername(usernameString);
                ((GameData)(app.getDataComponent())).setPassword(passWordString);
                //box.setVisible(false);
                clearPane.toBack();
                fileController.handleEnterRequest();
                animalLevel = 1;
                dictLevel = 1;
                citiesLevel = 1;
                animalLevel = ((GameData)app.getDataComponent()).getAnimals();
                dictLevel = ((GameData)app.getDataComponent()).getDict();
                citiesLevel = ((GameData)app.getDataComponent()).getCities();
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

    public void toggleLoginButton(AppTemplate app) throws IOException
    {
        loginButton.setText("Logout");
        keepPlayingButton.setText("Keep Playing");
        list.getChildren().add(keepPlayingButton);
        keepPlayingButton.setOnAction(e -> {
            try {
                this.afterLoginProfileHandlers(gamedata.appTemplate);
            }  catch (InstantiationException e1) {
                e1.printStackTrace();
            }
        });
        loginButton.setOnAction(e -> {
            try {
                fileController.handleLogoutRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
    public void toggleProfileButton(AppTemplate app) throws IOException
    {
        list.getChildren().remove(createProfileButton);
        list.getChildren().add(profileButton);
    }


    public void afterLoginProfileHandlers(AppTemplate app) throws InstantiationException {
        secondList = new VBox(50);
        secondList.setTranslateY(-30);
        secondList.setStyle("-fx-background-color: mediumpurple;");
        secondList.setPadding(new Insets(200, 0, 50, 50));
        profileButton = new Button();
        try {
            profileButton.setText(fileController.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<String> options = FXCollections.observableArrayList("Animals", "Dictionary Words", "Cities");
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
                System.exit(1);
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
}
