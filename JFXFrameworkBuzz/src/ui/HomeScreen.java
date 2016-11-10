package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import propertymanager.PropertyManager;
import sun.font.TextLabel;

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
    protected Button selectMode;
    protected Button startPlayingButton;
    protected VBox list;
    protected VBox topVBox;
    protected HBox closeHBox;
    protected HBox titleHBox;
    protected Label title;

    public Pane getClearPane() {
        return clearPane;
    }

    protected static Pane clearPane;
    protected String username;


    public HomeScreen() {

    }
    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: pink;");
        createProfileButton = new Button();
        createProfileButton.setText("Create Profile");
        loginButton = new Button();
        loginButton.setText("Login");
        profileButton = new Button();
        profileButton.setText("Username");
        xButton = new Button();
        xButton.setText("x");
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        selectMode = new Button();
        selectMode.setText("Select Mode");
        startPlayingButton = new Button();
        startPlayingButton.setText("Start Playing");
        title = new Label();
        title.setText("!! BUZZWORD !!");
        title.setScaleX(2);
        title.setScaleY(2);
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: purple;");
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
        clearPane.setOpacity(0);
    }

    public void initializeHomeHandlers(AppTemplate app) throws InstantiationException {
        try {
            Method getFileControllerClassMethod = app.getClass().getMethod("getFileControllerClass");
            String fileControllerClassName = (String) getFileControllerClassMethod.invoke(app);
            Class<?> klass = Class.forName("controller." + fileControllerClassName);
            Constructor<?> constructor = klass.getConstructor(AppTemplate.class);
            fileController = (FileController) constructor.newInstance(app);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
        try {
            Method getFileControllerClassMethod = app.getClass().getMethod("getFileControllerClass");
            String fileControllerClassName = (String) getFileControllerClassMethod.invoke(app);
            Class<?> klass = Class.forName("controller." + fileControllerClassName);
            Constructor<?> constructor = klass.getConstructor(AppTemplate.class);
            fileController = (FileController) constructor.newInstance(app);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        clearPane.toFront();
        PropertyManager propertyManager = PropertyManager.getManager();
        LoginSingleton dialog = LoginSingleton.getSingleton();
        dialog.show(propertyManager.getPropertyValue("Login"),
                propertyManager.getPropertyValue(" "));

    }

    public void afterLoginProfileHandlers(AppTemplate app) throws InstantiationException {
        try {
            Method getFileControllerClassMethod = app.getClass().getMethod("getFileControllerClass");
            String fileControllerClassName = (String) getFileControllerClassMethod.invoke(app);
            Class<?> klass = Class.forName("controller." + fileControllerClassName);
            Constructor<?> constructor = klass.getConstructor(AppTemplate.class);
            fileController = (FileController) constructor.newInstance(app);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        createProfileButton.setVisible(false);
        loginButton.setVisible(false);
        profileButton.setText(username);
        list.getChildren().addAll(profileButton, selectMode, startPlayingButton);
        profileButton.setOnAction(e -> {
            try {
                fileController.handleProfileRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        selectMode.setOnAction(e -> {
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

    public Button initializeChildButton(String icon, String tooltip, boolean disabled) throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();

        URL imgDirURL = AppTemplate.class.getClassLoader().getResource(APP_IMAGEDIR_PATH.getParameter());
        if (imgDirURL == null)
            throw new FileNotFoundException("Image resources folder does not exist.");

        Button button = new Button();
        try (InputStream imgInputStream = Files.newInputStream(Paths.get(imgDirURL.toURI()).resolve(propertyManager.getPropertyValue(icon)))) {
            Image buttonImage = new Image(imgInputStream);
            button.setDisable(disabled);
            button.setGraphic(new ImageView(buttonImage));
            Tooltip buttonTooltip = new Tooltip(propertyManager.getPropertyValue(tooltip));
            button.setTooltip(buttonTooltip);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return button;
    }
}
