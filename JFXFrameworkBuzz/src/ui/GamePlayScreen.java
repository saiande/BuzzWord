package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import propertymanager.PropertyManager;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
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
    protected PropertyManager propertyManager = PropertyManager.getManager();
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
    protected Label bun;
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
    protected Timeline timeline;
    protected Label timerLabel;
    protected Integer seconds;
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
    protected Line lineOneD;
    protected Line lineTwoD;
    protected Line lineThreeD;
    protected Line lineFourD;
    protected Line lineFiveD;
    protected Line lineSixD;
    protected Line lineSevenD;
    protected Line lineEightD;
    protected Line lineNineD;
    protected Line lineTenD;
    protected Line lineElevenD;
    protected Line lineTwelveD;
    protected Label labelOne;
    protected Label labelTwo;
    protected Label labelThree;
    protected Label labelFour;
    protected Label labelFive;
    protected Label labelSix;
    protected Label labelSeven;
    protected Label labelEight;
    protected Label labelNine;
    protected Label labelTen;
    protected Label labelEleven;
    protected Label labelTwelve;
    protected Label labelThirt;
    protected Label labelFourt;
    protected Label labelFift;
    protected Label labelSixt;
    protected Label sampleWord;
    protected Label samples;
    protected ListView<String> wordsList;


    public GamePlayScreen(FileController fileController) {
        this.fileController = fileController;

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
        title.setText("!! BUZZWORD !!");
        title.setScaleX(1.5);
        title.setScaleY(1.5);
        modeTitle = new Label();
        modeTitle.setScaleX(2);
        modeTitle.setScaleY(2);
        modeTitle.setText(fileController.getModeTitle());
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 30, 30, 50));
        list.getChildren().addAll(profileButton, homeButton);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        titleVBox = new VBox(60);
        titleVBox.getChildren().addAll(title, modeTitle);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setTranslateY(-60);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleVBox);
        wordGuessing = new HBox();
        wordGuessing.setPrefSize(150, 60);
        bun = new Label();
        bun.setText("B  U  ");
        wordGuessing.getChildren().addAll(bun);
        wordGuessing.setStyle("-fx-background-color: lightpink;");
        alreadyGuessedWords = new VBox();
        alreadyGuessedWords.setPrefSize(150, 250);
        alreadyGuessedWords.setStyle("-fx-background-color: gray;");
        sampleWord = new Label();
        sampleWord.setText("BUB     10\n  CAT       10");
        wordsList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "BUB                     10", "BAT                     10", "SOY                     10");
        wordsList.setItems(items);
        wordsList.setPrefSize(150, 250);
        totalLabel = new Label();
        totalLabel.setText("Total:                     30");
        total = new HBox();
        total.setStyle("-fx-background-color: dimgray;");
        total.setPrefSize(150, 40);
        total.getChildren().addAll(totalLabel);
        alreadyGuessedWords.getChildren().addAll(wordsList);
        total.setTranslateY(-20);
        pointsLabel = new Label();
        pointsLabel.setText("Target: ");
        pointsLabel.setScaleX(1.5);
        pointsLabel.setScaleY(1.5);
        pointsLabel.setTranslateY(30);
        pointsLabel.setTranslateX(40);
        pointsNumber = new Label();
        pointsNumber.setText("75 Points");
        pointsNumber.setTranslateX(30);
//        pointsNumber.setTranslateY(40);
        pointsNumber.setScaleX(1.5);
        pointsNumber.setScaleY(1.5);
        timeLabel = new Label();
        timeLabel.setText("Time Remaining: ");
        timeBox = new HBox(10);
        timeBox.setPrefSize(150, 40);

        timeBox.setStyle("-fx-background-color: mediumpurple;");
        timerLabel = new Label();
        seconds = 60;
        timerLabel.setText(seconds.toString());
        timeBox.getChildren().addAll(timeLabel, timerLabel);
        points = new VBox();
        points.setPrefSize(100, 100);
        points.setStyle("-fx-background-color: mediumpurple;");
        points.getChildren().addAll(pointsLabel, pointsNumber);
        right = new VBox(20);
        right.setStyle("-fx-background-color: lightseagreen;");
        right.getChildren().addAll(timeBox, wordGuessing, alreadyGuessedWords, total, points);
        top = new HBox(20);
        top.setStyle("-fx-background-color: pink;");
        mainTopVBox = new VBox(20);
        mainTopVBox.getChildren().addAll(topVBox, top);
        levelLabel = new Label();
        levelLabel.setText("Level: "+fileController.getLevel());
        playPauseButton = new Button();
        bottomBox = new VBox(10);
        bottomBox.getChildren().addAll(levelLabel);
//        try {
//            playPauseButton = initializeChildButton(bottomBox, PLAY_PAUSE_ICON.toString(), PLAY_PAUSE_TOOLTIP.toString(), false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Tooltip buttonTooltip = new Tooltip(propertyManager.getPropertyValue(PLAY_PAUSE_TOOLTIP.toString()));
//        playPauseButton.setTooltip(buttonTooltip);
        playPauseButton.setText("Play/ Pause");
        bottomBox.getChildren().addAll(playPauseButton);
        playPauseButton.setPrefSize(-1,-1);
        bottomBox.setTranslateX(350);
        bottomBox.setTranslateY(-60);

//        for(int i=0; i<4; i++)
//        {
//            for(int k=0; k<4; k++)
//            {
//                Circle c = new Circle(32);
//                grid.add(c, i, k);
//            }
//        }
        grid = new GridPane();
        CircleOne = new Circle(32);
        CircleTwo = new Circle(32);
        CircleThree = new Circle(32);
        CircleFour = new Circle(32);
        CircleFive = new Circle(32);
        CircleSix = new Circle(32);
        CircleSeven = new Circle(32);
        CircleEight = new Circle(32);
        CircleNine = new Circle(32);
        CircleTenth = new Circle(32);
        CircleEleven = new Circle(32);
        CircleTwelve = new Circle(32);
        CircleThirt = new Circle(32);
        CircleFourt = new Circle(32);
        CircleFift = new Circle(32);
        CircleSixt = new Circle(32);


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


        lineOne = new Line();
        lineOne.setStartX(50);
        lineOne.setEndX(90);
        lineOne.setStartY(100);
        lineOne.setEndY(100);
        lineOne.setScaleX(1.5);
        lineOne.setScaleY(1.5);

        lineTwo = new Line();
        lineTwo.setStartX(90);
        lineTwo.setEndX(130);
        lineTwo.setStartY(100);
        lineTwo.setEndY(100);
        lineTwo.setScaleX(1.5);
        lineTwo.setScaleY(1.5);

        lineThree = new Line();
        lineThree.setStartX(130);
        lineThree.setEndX(170);
        lineThree.setStartY(100);
        lineThree.setEndY(100);
        lineThree.setScaleX(1.5);
        lineThree.setScaleY(1.5);

        lineFour = new Line();
        lineFour.setStartX(50);
        lineFour.setEndX(90);
        lineFour.setStartY(200);
        lineFour.setEndY(200);
        lineFour.setScaleX(1.5);
        lineFour.setScaleY(1.5);

        lineFive = new Line();
        lineFive.setStartX(90);
        lineFive.setEndX(130);
        lineFive.setStartY(200);
        lineFive.setEndY(200);
        lineFive.setScaleX(1.5);
        lineFive.setScaleY(1.5);

        lineSix = new Line();
        lineSix.setStartX(130);
        lineSix.setEndX(170);
        lineSix.setStartY(200);
        lineSix.setEndY(200);
        lineSix.setScaleX(1.5);
        lineSix.setScaleY(1.5);

        lineSeven = new Line();
        lineSeven.setStartX(50);
        lineSeven.setEndX(90);
        lineSeven.setStartY(300);
        lineSeven.setEndY(300);
        lineSeven.setScaleX(1.5);
        lineSeven.setScaleY(1.5);

        lineEight = new Line();
        lineEight.setStartX(90);
        lineEight.setEndX(130);
        lineEight.setStartY(300);
        lineEight.setEndY(300);
        lineEight.setScaleX(1.5);
        lineEight.setScaleY(1.5);

        lineNine = new Line();
        lineNine.setStartX(130);
        lineNine.setEndX(170);
        lineNine.setStartY(300);
        lineNine.setEndY(300);
        lineNine.setScaleX(1.5);
        lineNine.setScaleY(1.5);

        lineTen = new Line();
        lineTen.setStartX(50);
        lineTen.setEndX(90);
        lineTen.setStartY(400);
        lineTen.setEndY(400);
        lineTen.setScaleX(1.5);
        lineTen.setScaleY(1.5);

        lineEleven = new Line();
        lineEleven.setStartX(90);
        lineEleven.setEndX(130);
        lineEleven.setStartY(400);
        lineEleven.setEndY(400);
        lineEleven.setScaleX(1.5);
        lineEleven.setScaleY(1.5);

        lineTwelve = new Line();
        lineTwelve.setStartX(130);
        lineTwelve.setEndX(170);
        lineTwelve.setStartY(400);
        lineTwelve.setEndY(400);
        lineTwelve.setScaleX(1.5);
        lineTwelve.setScaleY(1.5);

        lineOneD = new Line();
        lineOneD.setStartX(0);
        lineOneD.setEndX(0);
        lineOneD.setStartY(150);
        lineOneD.setEndY(170);
        lineOneD.setTranslateX(-40);
        lineOneD.setTranslateY(40);
        lineOneD.setScaleX(1.5);
        lineOneD.setScaleY(1.5);

        lineTwoD = new Line();
        lineTwoD.setStartX(0);
        lineTwoD.setEndX(0);
        lineTwoD.setStartY(170);
        lineTwoD.setEndY(190);
        lineTwoD.setTranslateX(-40);
        lineTwoD.setTranslateY(40);
        lineTwoD.setScaleX(1.5);
        lineTwoD.setScaleY(1.5);

        lineThreeD = new Line();
        lineThreeD.setStartX(0);
        lineThreeD.setEndX(0);
        lineThreeD.setStartY(190);
        lineThreeD.setEndY(210);
        lineThreeD.setTranslateX(-40);
        lineThreeD.setTranslateY(40);
        lineThreeD.setScaleX(1.5);
        lineThreeD.setScaleY(1.5);

        lineFourD = new Line();
        lineFourD.setStartX(100);
        lineFourD.setEndX(100);
        lineFourD.setStartY(150);
        lineFourD.setEndY(170);
        lineFourD.setTranslateX(-40);
        lineFourD.setTranslateY(40);
        lineFourD.setScaleX(1.5);
        lineFourD.setScaleY(1.5);

        lineFiveD = new Line();
        lineFiveD.setStartX(100);
        lineFiveD.setEndX(100);
        lineFiveD.setStartY(170);
        lineFiveD.setEndY(190);
        lineFiveD.setTranslateX(-40);
        lineFiveD.setTranslateY(40);
        lineFiveD.setScaleX(1.5);
        lineFiveD.setScaleY(1.5);

        lineSixD = new Line();
        lineSixD.setStartX(100);
        lineSixD.setEndX(100);
        lineSixD.setStartY(190);
        lineSixD.setEndY(210);
        lineSixD.setTranslateX(-40);
        lineSixD.setTranslateY(40);
        lineSixD.setScaleX(1.5);
        lineSixD.setScaleY(1.5);

        lineSevenD = new Line();
        lineSevenD.setStartX(200);
        lineSevenD.setEndX(200);
        lineSevenD.setStartY(150);
        lineSevenD.setEndY(170);
        lineSevenD.setTranslateX(-40);
        lineSevenD.setTranslateY(40);
        lineSevenD.setScaleX(1.5);
        lineSevenD.setScaleY(1.5);

        lineEightD = new Line();
        lineEightD.setStartX(200);
        lineEightD.setEndX(200);
        lineEightD.setStartY(170);
        lineEightD.setEndY(190);
        lineEightD.setTranslateX(-40);
        lineEightD.setTranslateY(40);
        lineEightD.setScaleX(1.5);
        lineEightD.setScaleY(1.5);

        lineNineD = new Line();
        lineNineD.setStartX(200);
        lineNineD.setEndX(200);
        lineNineD.setStartY(190);
        lineNineD.setEndY(210);
        lineNineD.setTranslateX(-40);
        lineNineD.setTranslateY(40);
        lineNineD.setScaleX(1.5);
        lineNineD.setScaleY(1.5);

        lineTenD = new Line();
        lineTenD.setStartX(300);
        lineTenD.setEndX(300);
        lineTenD.setStartY(150);
        lineTenD.setEndY(170);
        lineTenD.setTranslateX(-40);
        lineTenD.setTranslateY(40);
        lineTenD.setScaleX(1.5);
        lineTenD.setScaleY(1.5);

        lineElevenD = new Line();
        lineElevenD.setStartX(300);
        lineElevenD.setEndX(300);
        lineElevenD.setStartY(170);
        lineElevenD.setEndY(190);
        lineElevenD.setTranslateX(-40);
        lineElevenD.setTranslateY(40);
        lineElevenD.setScaleX(1.5);
        lineElevenD.setScaleY(1.5);

        lineTwelveD = new Line();
        lineTwelveD.setStartX(300);
        lineTwelveD.setEndX(300);
        lineTwelveD.setStartY(190);
        lineTwelveD.setEndY(210);
        lineTwelveD.setTranslateX(-40);
        lineTwelveD.setTranslateY(40);
        lineTwelveD.setScaleX(1.5);
        lineTwelveD.setScaleY(1.5);

        grid.add(lineOne, 1, 1);
        grid.add(lineTwo, 2, 1);
        grid.add(lineThree, 3, 1);
        grid.add(lineFour, 1, 2);
        grid.add(lineFive, 2, 2);
        grid.add(lineSix, 3, 2);
        grid.add(lineSeven, 1, 3);
        grid.add(lineEight, 2, 3);
        grid.add(lineNine, 3, 3);
        grid.add(lineTen, 1, 4);
        grid.add(lineEleven, 2, 4);
        grid.add(lineTwelve,3 , 4);

        grid.add(lineOneD, 1, 1);
        grid.add(lineTwoD, 2, 1);
        grid.add(lineThreeD, 3, 1);
        grid.add(lineFourD, 4, 1);
        grid.add(lineFiveD, 1, 2);
        grid.add(lineSixD, 2, 2);
        grid.add(lineSevenD, 3, 2);
        grid.add(lineEightD, 4, 2);
        grid.add(lineNineD, 1, 3);
        grid.add(lineTenD, 2, 3);
        grid.add(lineElevenD, 3, 3);
        grid.add(lineTwelveD,4 , 3);

        labelOne = new Label();
        labelOne.setText("B");
        labelOne.setScaleX(2);
        labelOne.setScaleY(2);
        labelOne.setTextFill(Color.WHITE);
        grid.add(labelOne, 1, 1);
        labelOne.setTranslateX(-42);

        labelTwo = new Label();
        labelTwo.setText("U");
        labelTwo.setScaleX(2);
        labelTwo.setScaleY(2);
        labelTwo.setTextFill(Color.WHITE);
        grid.add(labelTwo, 2, 1);
        labelTwo.setTranslateX(-42);

        labelThree = new Label();
        labelThree.setText("B");
        labelThree.setScaleX(2);
        labelThree.setScaleY(2);
        labelThree.setTextFill(Color.WHITE);
        grid.add(labelThree, 3, 1);
        labelThree.setTranslateX(-42);

        labelFour = new Label();
        labelFour.setText("C");
        labelFour.setScaleX(2);
        labelFour.setScaleY(2);
        labelFour.setTextFill(Color.WHITE);
        grid.add(labelFour, 4, 1);
        labelFour.setTranslateX(-42);

        labelFive = new Label();
        labelFive.setText("A");
        labelFive.setScaleX(2);
        labelFive.setScaleY(2);
        labelFive.setTextFill(Color.WHITE);
        grid.add(labelFive, 1, 2);
        labelFive.setTranslateX(-42);

        labelSix = new Label();
        labelSix.setText("T");
        labelSix.setScaleX(2);
        labelSix.setScaleY(2);
        labelSix.setTextFill(Color.WHITE);
        grid.add(labelSix, 2, 2);
        labelSix.setTranslateX(-42);

        labelSeven = new Label();
        labelSeven.setText("G");
        labelSeven.setScaleX(2);
        labelSeven.setScaleY(2);
        labelSeven.setTextFill(Color.WHITE);
        grid.add(labelSeven, 3, 2);
        labelSeven.setTranslateX(-42);

        labelEight = new Label();
        labelEight.setText("P");
        labelEight.setScaleX(2);
        labelEight.setScaleY(2);
        labelEight.setTextFill(Color.WHITE);
        grid.add(labelEight, 4, 2);
        labelEight.setTranslateX(-42);

        labelNine = new Label();
        labelNine.setText("L");
        labelNine.setScaleX(2);
        labelNine.setScaleY(2);
        labelNine.setTextFill(Color.WHITE);
        grid.add(labelNine, 1, 3);
        labelNine.setTranslateX(-42);

        labelTen = new Label();
        labelTen.setText("S");
        labelTen.setScaleX(2);
        labelTen.setScaleY(2);
        labelTen.setTextFill(Color.WHITE);
        grid.add(labelTen, 2, 3);
        labelTen.setTranslateX(-42);

        labelEleven = new Label();
        labelEleven.setText("Y");
        labelEleven.setScaleX(2);
        labelEleven.setScaleY(2);
        labelEleven.setTextFill(Color.WHITE);
        grid.add(labelEleven, 3, 3);
        labelEleven.setTranslateX(-42);

        labelTwelve = new Label();
        labelTwelve.setText("N");
        labelTwelve.setScaleX(2);
        labelTwelve.setScaleY(2);
        labelTwelve.setTextFill(Color.WHITE);
        grid.add(labelTwelve, 4, 3);
        labelTwelve.setTranslateX(-42);

        labelThirt = new Label();
        labelThirt.setText("M");
        labelThirt.setScaleX(2);
        labelThirt.setScaleY(2);
        labelThirt.setTextFill(Color.WHITE);
        grid.add(labelThirt, 1, 4);
        labelThirt.setTranslateX(-42);

        labelFourt = new Label();
        labelFourt.setText("S");
        labelFourt.setScaleX(2);
        labelFourt.setScaleY(2);
        labelFourt.setTextFill(Color.WHITE);
        grid.add(labelFourt, 2, 4);
        labelFourt.setTranslateX(-42);

        labelFift = new Label();
        labelFift.setText("O");
        labelFift.setScaleX(2);
        labelFift.setScaleY(2);
        labelFift.setTextFill(Color.WHITE);
        grid.add(labelFift, 3, 4);
        labelFift.setTranslateX(-42);

        labelSixt = new Label();
        labelSixt.setText("A");
        labelSixt.setScaleX(2);
        labelSixt.setScaleY(2);
        labelSixt.setTextFill(Color.WHITE);
        grid.add(labelSixt, 4, 4);
        labelSixt.setTranslateX(-42);

        grid.setPrefSize(150,150);
        grid.setTranslateY(40);
        grid.setTranslateX(70);
        this.setTop(mainTopVBox);
        this.setLeft(list);
        this.setRight(right);
        this.setBottom(bottomBox);
        this.setCenter(grid);
    }

    public void initializeGamePlayHandlers(AppTemplate app) throws InstantiationException {

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
