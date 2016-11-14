package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import propertymanager.PropertyManager;

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

import static javafx.scene.input.KeyCode.PLAY;
import static settings.AppPropertyType.*;
import static settings.InitializationParameters.APP_IMAGEDIR_PATH;

/**
 * Created by sai on 11/7/16.
 */
public class GamePlayScreen extends BorderPane{
    protected FileController fileController;
    protected Button profileButton;
    protected Button homeButton;
    protected Button xButton;
    protected Button playPauseButton;
    protected Label modeTitle;
    protected VBox list;
    protected HBox closeHBox;
    protected VBox topVBox;
    protected VBox titleVBox;
    protected HBox timeBox;
    protected HBox top;
    protected Label title;
    protected HBox wordGuessing;
    protected Label timeLabel;
    protected VBox alreadyGuessedWords;
    protected VBox points;
    protected VBox right;
    protected Label pointsLabel;
    protected Label pointsNumber;
    protected VBox mainTopVBox;
    protected Label levelLabel;
    protected  VBox bottomBox;
    protected HBox total;
    protected Label totalLabel;
    protected GridPane grid;
    protected Circle CircleOne;
    protected Circle CircleTwo;
    protected Circle CircleThree;
    protected Circle CircleFour;
    protected Circle CircleFive;
    protected Circle CircleSix;
    protected Circle CircleSeven;
    protected Circle CircleEight;
    protected Circle CircleNine;
    protected Circle CircleTenth;
    protected Circle CircleEleven;
    protected Circle CircleTwelve;
    protected Circle CircleThirt;
    protected Circle CircleFourt;
    protected Circle CircleFift;
    protected Circle CircleSixt;
    protected Line lineOne;
    protected Line lineTwo;
    protected Line lineThree;
    protected Line lineFour;
    protected Line lineFive;
    protected Line lineSix;
    protected Line lineSeven;
    protected Line lineEight;
    protected Line lineNine;
    protected Line lineTen;
    protected Line lineEleven;
    protected Line lineTwelve;


    public GamePlayScreen() {

    }


    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");

        profileButton = new Button();
        profileButton.setText("Create Profile");
        homeButton = new Button();
        homeButton.setText("Home");
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        title = new Label();
        modeTitle = new Label();
        modeTitle.setScaleX(2);
        modeTitle.setScaleY(2);
        modeTitle.setText("modeTitle");
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 30, 30, 50));
        list.getChildren().addAll(profileButton, homeButton);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        titleVBox = new VBox(60);
        titleVBox.getChildren().addAll(modeTitle);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setTranslateY(-40);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleVBox);
        wordGuessing = new HBox();
        wordGuessing.setPrefSize(150, 40);
        wordGuessing.setStyle("-fx-background-color: lightpink;");
        alreadyGuessedWords = new VBox();
        alreadyGuessedWords.setPrefSize(150, 250);
        alreadyGuessedWords.setStyle("-fx-background-color: gray;");
        totalLabel = new Label();
        totalLabel.setText("Total   ");
        total = new HBox();
        total.setStyle("-fx-background-color: dimgray;");
        total.getChildren().addAll(totalLabel);
        alreadyGuessedWords.getChildren().addAll(total);
        total.setTranslateY(225);
        pointsLabel = new Label();
        pointsLabel.setText("Target: ");
        pointsLabel.setScaleX(1.5);
        pointsLabel.setScaleY(1.5);
        pointsLabel.setTranslateY(40);
        pointsLabel.setTranslateX(40);
        pointsNumber = new Label();
        pointsNumber.setText("75 Points");
        pointsNumber.setTranslateX(40);
        pointsNumber.setTranslateY(40);
        pointsNumber.setScaleX(1.5);
        pointsNumber.setScaleY(1.5);
        timeLabel = new Label();
        timeLabel.setText("Time Remaining: ");
        timeBox = new HBox(10);
        timeBox.setPrefSize(150, 40);
        timeBox.getChildren().addAll(timeLabel);
        timeBox.setStyle("-fx-background-color: mediumpurple;");
        points = new VBox();
        points.setPrefSize(100, 100);
        points.setStyle("-fx-background-color: mediumpurple;");
        points.getChildren().addAll(pointsLabel, pointsNumber);
        right = new VBox(20);
        right.setStyle("-fx-background-color: lightseagreen;");
        right.getChildren().addAll(timeBox, wordGuessing, alreadyGuessedWords, points);
        top = new HBox(20);
        top.setStyle("-fx-background-color: pink;");
        mainTopVBox = new VBox(20);
        mainTopVBox.getChildren().addAll(topVBox, top);
        levelLabel = new Label();
        levelLabel.setText("Level 1");
        playPauseButton = new Button();
        bottomBox = new VBox(10);
        bottomBox.getChildren().addAll(levelLabel);
        try {
            playPauseButton = initializeChildButton(bottomBox, PLAY_PAUSE_ICON.toString(), PLAY_PAUSE_TOOLTIP.toString(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        playPauseButton.setPrefSize(-1,-1);
        bottomBox.setTranslateX(350);
        bottomBox.setTranslateY(-25);

        grid = new GridPane();
        CircleOne = new Circle(35);
        CircleTwo = new Circle(35);
        CircleThree = new Circle(35);
        CircleFour = new Circle(35);
        CircleFive = new Circle(35);
        CircleSix = new Circle(35);
        CircleSeven = new Circle(35);
        CircleEight = new Circle(35);
        CircleNine = new Circle(35);
        CircleTenth = new Circle(35);
        CircleEleven = new Circle(35);
        CircleTwelve = new Circle(35);
        CircleThirt = new Circle(35);
        CircleFourt = new Circle(35);
        CircleFift = new Circle(35);
        CircleSixt = new Circle(35);
        grid.addRow(1, CircleOne, CircleTwo, CircleThree, CircleFour);
        grid.addRow(2, CircleFive, CircleSix, CircleSeven, CircleEight);
        grid.addRow(3, CircleNine, CircleTenth, CircleEleven, CircleTwelve);
        grid.addRow(4, CircleThirt, CircleFourt, CircleFift, CircleSixt);
        grid.setMargin(CircleOne, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleTwo, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleThree, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleFour, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleFive, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleSix, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleSeven, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleEight, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleNine, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleTenth, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleEleven, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleTwelve, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleThirt, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleFourt, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleFift, new Insets(0, 5, 5, 5));
        grid.setMargin(CircleSixt, new Insets(0, 5, 5, 5));


        grid.setPrefSize(150,150);
        grid.setTranslateY(60);
        grid.setTranslateX(70);
        this.setTop(mainTopVBox);
        this.setLeft(list);
        this.setRight(right);
        this.setBottom(bottomBox);
        this.setCenter(grid);
    }

    public void initializeGamePlayHandlers(AppTemplate app) throws InstantiationException {
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
        profileButton.setOnAction(e -> {
            try {
                fileController.handleProfileRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        homeButton.setOnAction(e -> {
            try {
                fileController.handleHomeRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        playPauseButton.setOnAction(e -> {
            try {
                fileController.handlePlayPauseRequest();
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
    public Button initializeChildButton(VBox box, String icon, String tooltip, boolean disabled) throws IOException {
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
            box.getChildren().add(button);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return button;
    }
}
