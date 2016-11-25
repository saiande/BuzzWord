package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Random;

import static javafx.scene.paint.Color.WHITE;

/**
 * Created by sai on 11/7/16.
 */
public class GamePlayScreen extends BorderPane {
    //top
    protected FileController fileController;
    protected GameData gamedata;
    protected Button xButton;
    protected HBox closeHBox;
    protected Label title;
    protected Label modeTitle;
    protected VBox titleVBox;
    protected VBox topVBox;
    //left
    protected Button profileButton;
    protected Button homeButton;
    protected VBox list;
    //bottom
    protected Label levelLabel;
    protected VBox bottomBox;
    protected Button pauseButton;
    //right
    protected Integer seconds;
    protected Label timeRemainingLabel;
    protected Label timerLabel;
    protected HBox timeBox;
    protected HBox wordGuessing;
    protected Label bun;
    protected VBox alreadyGuessedWords;
    protected ListView<String> wordsList;
    protected Label totalLabel;
    protected HBox total;
    protected Label pointsLabel;
    protected Label pointsNumber;
    protected VBox points;
    protected VBox right;
    //grid
    protected GridPane grid;

    protected Pane hideScreen;
    protected Button resumeButton;

    //constructor
    public GamePlayScreen(FileController fileController, GameData gamedata) {
        this.fileController = fileController;
        this.gamedata = gamedata;
        initialize();
    }

    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");
        //top
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        title = new Label();
        title.setText("!! BUZZWORD !!");
        title.setScaleX(1.5);
        title.setScaleY(1.5);
        modeTitle = new Label();
        modeTitle.setScaleX(2);
        modeTitle.setScaleY(2);
        titleVBox = new VBox(60);
        titleVBox.getChildren().addAll(title, modeTitle);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setTranslateY(-40);
        titleVBox.setTranslateX(10);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleVBox);
        this.setTop(topVBox);

        //left
        profileButton = new Button();
        homeButton = new Button();
        homeButton.setText("Home");
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 30, 30, 50));
        list.getChildren().addAll(profileButton, homeButton);
        this.setLeft(list);

        //right
        timeRemainingLabel = new Label();
        timeRemainingLabel.setText("Time Remaining: ");
        timerLabel = new Label();
        seconds = 60;
        timerLabel.setText(seconds.toString());
        timeBox = new HBox(10);
        timeBox.setPrefSize(150, 40);
        timeBox.setStyle("-fx-background-color: mediumpurple;");
        timeBox.getChildren().addAll(timeRemainingLabel, timerLabel);

        bun = new Label();
        bun.setText("B  U  ");
        wordGuessing = new HBox();
        wordGuessing.setPrefSize(150, 60);
        wordGuessing.getChildren().addAll(bun);
        wordGuessing.setStyle("-fx-background-color: lightpink;");

        alreadyGuessedWords = new VBox();
        alreadyGuessedWords.setPrefSize(150, 250);
        alreadyGuessedWords.setStyle("-fx-background-color: gray;");
        wordsList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "BUB                     10", "BAT                     10", "SOY                     10");
        wordsList.setItems(items);
        wordsList.setPrefSize(150, 250);
        alreadyGuessedWords.getChildren().addAll(wordsList);
        totalLabel = new Label();
        totalLabel.setText("Total:                     30");
        total = new HBox();
        total.setStyle("-fx-background-color: dimgray;");
        total.setPrefSize(150, 40);
        total.getChildren().addAll(totalLabel);
        total.setTranslateY(-20);
        pointsLabel = new Label();
        pointsLabel.setText("Target: ");
        pointsLabel.setScaleX(1.5);
        pointsLabel.setScaleY(1.5);
        pointsLabel.setTranslateX(40);
        pointsNumber = new Label();
        pointsNumber.setText("10 Points");
        pointsNumber.setTranslateX(30);
        pointsNumber.setScaleX(1.5);
        pointsNumber.setScaleY(1.5);
        points = new VBox();
        points.setPrefSize(100, 100);
        points.setStyle("-fx-background-color: mediumpurple;");
        points.getChildren().addAll(pointsLabel, pointsNumber);
        right = new VBox(20);
        right.getChildren().addAll(timeBox, wordGuessing, alreadyGuessedWords, total, points);
        this.setRight(right);
        //bottom
        levelLabel = new Label();
        levelLabel.setScaleX(1.5);
        levelLabel.setScaleY(1.5);
        pauseButton = new Button();
        bottomBox = new VBox(10);
        pauseButton.setText("Pause");
        bottomBox.getChildren().addAll(levelLabel, pauseButton);
        bottomBox.setTranslateX(350);
        bottomBox.setTranslateY(-60);
        this.setBottom(bottomBox);
        //middle
        grid = new GridPane();
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                Circle c = new Circle(34);
                grid.add(c, i, k);
                grid.setMargin(c, new Insets(0, 5, 5, 5));
            }
        }

//        for (int m = 0; m < 4; m++) {
//            for (int n = 0; n < 4; n++) {
//                Random r = new Random();
//                char c = (char) (r.nextInt(26) + 'a');
//                Label letter = new Label();
//                letter.setText(String.valueOf(c));
//                letter.setScaleX(2);
//                letter.setScaleY(2);
//                letter.setTextFill(WHITE)
//                grid.add(letter, m, n);
//                letter.setTranslateX(33);
//            }
//        }
        grid.setMaxSize(150, 150);
        grid.setTranslateY(-20);
        grid.setTranslateX(20);
        this.setCenter(grid);


        hideScreen = new Pane();
        hideScreen.setStyle("-fx-background-color: mediumpurple;");
        resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setTranslateX(300);
        resumeButton.setTranslateY(300);
        hideScreen.getChildren().addAll(resumeButton);

    }
    public Pane getHideScreen() {
        return hideScreen;
    }

    public void initializeGamePlayHandlers(AppTemplate app) throws InstantiationException {
        profileButton.setText(gamedata.getUsername());
        modeTitle.setText(fileController.getModeTitle());
        levelLabel.setText("Level: " + fileController.getLevel());
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
        pauseButton.setOnAction(e -> {
            try {
                hideScreen.toFront();
                fileController.handlePauseRequest();
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
    public void initializePauseHandlers(AppTemplate app) throws InstantiationException {
        resumeButton.setOnAction(e -> {
            try {
                hideScreen.toBack();
                fileController.handleResumeRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
    public void initializeOne() {
        int row, col, colFlag;
        String word = "";
        String[] colRowStr = new String[3];
        Random random = new Random();
        if(fileController.getModeTitle()=="Animals") {
            String[] animalsThree = {"ant", "ape", "bat", "bee", "bug", "cat", "cod", "doe", "dog", "eel", "elk", "emu", "fly", "fox", "man", "hen", "hog", "kit", "owl", "pig", "ram", "rat", "sow", "yak"};
            int index = random.nextInt(animalsThree.length);
            word = animalsThree[index];
            row = random.nextInt(3);
            col = random.nextInt(3);
            colFlag = 0;
        }
        else {
        String[] dictThree = {"act", "bar", "car", "dew", "eat", "far", "gym", "hey", "ink", "jet", "key", "log", "mad", "nap", "odd", "pal", "saw", "tan", "vet", "urn", "wed", "yap", "zoo"};
            int index = random.nextInt(dictThree.length);
            word = dictThree[index];
            row = random.nextInt(3);
            col = random.nextInt(3);
            colFlag = 0;
        }
            Label first = new Label();
            first.setText(word.substring(0,1));
            first.setScaleX(2);
            first.setScaleY(2);
            first.setTranslateX(33);
            first.setTextFill(WHITE);
            grid.add(first, col, row);
            colRowStr[0] = getColRowString(col, row);

            Label second = new Label();
            second.setText(word.substring(1, 2));
            second.setScaleX(2);
            second.setScaleY(2);
            second.setTranslateX(33);
            second.setTextFill(WHITE);
            if(col==3)
            {
                col = col-1;
                colFlag = 1;
            }
            else
            {
                col= col+1;
            }
            grid.add(second, col, row);
            colRowStr[1] = getColRowString(col, row);
            Label third = new Label();
            third.setText(word.substring(2, 3));
            third.setScaleX(2);
            third.setScaleY(2);
            third.setTranslateX(33);
            third.setTextFill(WHITE);
            if(col==3)
            {
                row = row-1;
                if(row>=0 && row<=3)
                {
                }
                else
                {
                    row = row+2;
                }
            }
            else
            {
                if(colFlag==1)
                {
                    col = col -1;
                }
                else {
                    col = col + 1;
                }

            }
        grid.add(third, col, row);
        colRowStr[2] = getColRowString(col, row);

        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                if((m+"xx"+n).equals(colRowStr[0])||(m+"xx"+n).equals(colRowStr[1])||(m+"xx"+n).equals(colRowStr[2]))
                    continue;

                else {
                    Random r = new Random();
                    char c = (char) (r.nextInt(26) + 'a');
                    Label letter = new Label();
                    letter.setText(String.valueOf(c));
                    letter.setScaleX(2);
                    letter.setScaleY(2);
                    letter.setTextFill(WHITE);
                    letter.setTranslateX(33);
                    grid.add(letter, m, n);
                }
            }
        }

        }

    public void initializeTwo()
    {
        int row, col, colFlag;
        String word = "";
        String[] colRowStr = new String[4];
        Random random = new Random();
        if(fileController.getModeTitle()=="Animals") {
            String[] animalsThree = {"ant", "ape", "bat", "bee", "bug", "cat", "cod", "doe", "dog", "eel", "elk", "emu", "fly", "fox", "man", "hen", "hog", "kit", "owl", "pig", "ram", "rat", "sow", "yak"};
            int index = random.nextInt(animalsThree.length);
            word = animalsThree[index];
            row = random.nextInt(3);
            col = random.nextInt(3);
            colFlag = 0;
        }
        else {
            String[] dictThree = {"act", "bar", "car", "dew", "eat", "far", "gym", "hey", "ink", "jet", "key", "log", "mad", "nap", "odd", "pal", "saw", "tan", "vet", "urn", "wed", "yap", "zoo"};
            int index = random.nextInt(dictThree.length);
            word = dictThree[index];
            row = random.nextInt(3);
            col = random.nextInt(3);
            colFlag = 0;
        }
        Label first = new Label();
        first.setText(word.substring(0,1));
        first.setScaleX(2);
        first.setScaleY(2);
        first.setTranslateX(33);
        first.setTextFill(WHITE);
        grid.add(first, col, row);
        colRowStr[0] = getColRowString(col, row);

        Label second = new Label();
        second.setText(word.substring(1, 2));
        second.setScaleX(2);
        second.setScaleY(2);
        second.setTranslateX(33);
        second.setTextFill(WHITE);
        if(col==3)
        {
            col = col-1;
            colFlag = 1;
        }
        else
        {
            col= col+1;
        }
        grid.add(second, col, row);
        colRowStr[1] = getColRowString(col, row);
        Label third = new Label();
        third.setText(word.substring(2, 3));
        third.setScaleX(2);
        third.setScaleY(2);
        third.setTranslateX(33);
        third.setTextFill(WHITE);
        if(col==3)
        {
            row = row-1;
            if(row>=0 && row<=3)
            {
            }
            else
            {
                row = row+2;
            }
        }
        else
        {
            if(colFlag==1)
            {
                col = col -1;
            }
            else {
                col = col + 1;
            }

        }
        grid.add(third, col, row);
        colRowStr[2] = getColRowString(col, row);

        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                if((m+"xx"+n).equals(colRowStr[0])||(m+"xx"+n).equals(colRowStr[1])||(m+"xx"+n).equals(colRowStr[2]))
                    continue;

                else {
                    Random r = new Random();
                    char c = (char) (r.nextInt(26) + 'a');
                    Label letter = new Label();
                    letter.setText(String.valueOf(c));
                    letter.setScaleX(2);
                    letter.setScaleY(2);
                    letter.setTextFill(WHITE);
                    letter.setTranslateX(33);
                    grid.add(letter, m, n);
                }
            }
        }

    }
    public void initializeThree()
    {

    }
    public void initializeFour()
    {

    }
    public String getColRowString(int col, int row)
    {
        return col+"xx"+row;
    }

}