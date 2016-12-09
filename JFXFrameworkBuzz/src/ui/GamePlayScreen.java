package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import propertymanager.PropertyManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import static settings.AppPropertyType.GAME_LOST_MESSAGE;
import static settings.AppPropertyType.GAME_WON_MESSAGE;

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
    protected Button replayLevel;
    protected Button nextLevel;
    protected VBox list;
    //bottom
    protected Label levelLabel;
    protected VBox bottomBox;
    protected Button pauseButton;
    //right
    protected static final Integer seconds = 10;
    protected Label timeRemainingLabel;
    protected Label timerLabel;
    protected HBox timeBox;
    protected Timeline timeline;
    protected HBox wordGuessing;
    protected Label guess;
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
    protected Pane hideQuitScreen;
    protected Button resumeButton;
    protected Button anotherResume;
    protected Button quitButton;

    //protected Circle[] circleList = new Circle[16];
    protected ArrayList<Circle> circleList = new ArrayList<Circle>();
    protected String[] labelList = new String[16];
    protected Circle[] allCircleList = new Circle[16];
    protected ObservableList<String> items;

    protected String highlightWord;
    protected String highlightWord1;
    protected String word;
    protected  int index;
    protected Circle prev;
    protected int animals = 1;
    protected int dict = 1;
    protected int cities = 1;
    protected boolean animOne;
    protected boolean animTwo;
    protected boolean animThree;
    protected boolean animFour;
    protected boolean dictOne;
    protected boolean dictTwo;
    protected boolean dictThree;
    protected boolean dictFour;
    protected boolean citiOne;
    protected boolean citiTwo;
    protected boolean citiThree;
    protected boolean citiFour;
    ArrayList<Circle> wordCircles = new ArrayList<Circle>();
    public final KeyCombination keyControlR = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_ANY);
    public final KeyCombination keyControlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY);

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
        replayLevel = new Button();
        replayLevel.setText("Replay Level");
        nextLevel = new Button();
        nextLevel.setText("Next Level");
        nextLevel.setDisable(true);
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 30, 30, 50));
        list.getChildren().addAll(profileButton, homeButton, replayLevel, nextLevel);
        this.setLeft(list);

        //right
        timeRemainingLabel = new Label();
        timeRemainingLabel.setText("Time Remaining: ");
        timerLabel = new Label();
        timerLabel.setText(seconds.toString());
        timeBox = new HBox(10);
        timeBox.setPrefSize(150, 40);
        timeBox.setStyle("-fx-background-color: mediumpurple;");
        timeBox.getChildren().addAll(timeRemainingLabel, timerLabel);

        guess = new Label();
        wordGuessing = new HBox();
        wordGuessing.setPrefSize(150, 60);
        wordGuessing.getChildren().addAll(guess);
        wordGuessing.setStyle("-fx-background-color: lightpink;");

        alreadyGuessedWords = new VBox();
        alreadyGuessedWords.setPrefSize(150, 250);
        alreadyGuessedWords.setStyle("-fx-background-color: gray;");
        wordsList = new ListView<String>();
        items = FXCollections.observableArrayList("hi");

        alreadyGuessedWords.getChildren().addAll(wordsList);
        totalLabel = new Label();
        totalLabel.setText("Total: ");
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

        hideQuitScreen = new Pane();
        hideQuitScreen.setStyle("-fx-background-color: mediumpurple;");
        quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setTranslateX(300);
        quitButton.setTranslateY(200);
        Label sure = new Label();
        sure.setText("Are you sure you want to quit?");
        sure.setScaleX(2);
        sure.setScaleY(2);
        sure.setTextFill(BLACK);
        sure.setTranslateX(100);
        sure.setTranslateY(100);
        anotherResume = new Button();
        anotherResume.setText("Resume");
        anotherResume.setTranslateY(200);
        anotherResume.setTranslateX(200);
        hideQuitScreen.getChildren().addAll(sure, quitButton, anotherResume);

    }
    public Pane getHideScreen() {
        return hideScreen;
    }

    public Pane getHideQuitScreen() { return hideQuitScreen; }

    public void resetGrid()
    {

        prev = new Circle();
        pauseButton.setDisable(false);
        guess.setText("");
        items = FXCollections.observableArrayList("hi");
        items.clear();
        alreadyGuessedWords.getChildren().clear();
        totalLabel.setText("Total: ");
        grid.getChildren().clear();
        highlightWord = "";
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                int index = i*4+k;
                Circle c = new Circle(34);
                grid.add(c, i, k);
                grid.setMargin(c, new Insets(0, 5, 5, 5));
                c.setOnDragDetected(event -> {
                    Circle c3 = (Circle) event.getSource();
                    c3.startFullDrag();

                });
                c.setOnMouseDragEntered(event -> {
                    highlight(c, index);
                    guess.setText(highlightWord);
                    //circleList[index] = c;
                    circleList.add(c);

                });
                c.setOnMouseReleased(event -> {
                    //makeLine(circleList);
                    checkWord(highlightWord);
                });
                allCircleList[index] = c;

            }
        }

        grid.setMaxSize(150, 150);
        grid.setTranslateY(-20);
        grid.setTranslateX(20);
        this.setCenter(grid);

    }

    public void timer()
    {
        IntegerProperty timeSeconds = new SimpleIntegerProperty(seconds);
        timerLabel.textProperty().bind(timeSeconds.asString());
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(seconds), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
        timeline.setOnFinished(event ->{
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            PropertyManager props = PropertyManager.getManager();
            dialog.show(props.getPropertyValue(GAME_LOST_MESSAGE), "the word was: "+word);
            grid.setDisable(true);
            highlightWhole(wordCircles);
            wordCircles.clear();
        });
    }

    public void initializeGamePlayHandlers(AppTemplate app) throws InstantiationException {
        profileButton.setText(gamedata.getUsername());
        modeTitle.setText(fileController.getModeTitle());
        levelLabel.setText("Level: " + fileController.getLevel());
        profileButton.setOnAction(e -> {
            try {
                timeline.stop();
                fileController.handleProfileRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        homeButton.setOnAction(e -> {
            try {
                timeline.stop();
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
                timeline.pause();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        replayLevel.setOnAction(e -> {
            try {
                fileController.handleReplayRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
        {
            if(keyControlR.match(e))
                try {
                    fileController.handleReplayRequest();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });
        xButton.setOnAction(e -> {
            try {
                hideQuitScreen.toFront();
                timeline.pause();
                anotherResume.setOnAction(d -> {
                    try {
                        fileController.handleResumeRequest();
                        timeline.play();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.exit(1);
                    }
                });
                quitButton.setOnAction(d -> {
                    try {
                        hideQuitScreen.toBack();
                        fileController.handleXRequest();
                        System.exit(1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.exit(1);
                    }
                });
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
                timeline.play();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }

    public void initializeOne()
    {

        grid.setDisable(false);
        nextLevel.setDisable(true);
        int row, col;
        String[] colRowStr = new String[4];
        Random random = new Random();
        row = random.nextInt(3);
        col = random.nextInt(3);

        if(fileController.getModeTitle()=="Animals") {
            String[] animalsFour = {"bird","deer", "bear", "lamb", "crab", "fish", "toad", "frog", "dove", "crow", "gull", "duck","swan", "kiwi", "wolf", "lynx", "moth", "mole", "worm", "gnat", "goat", "wasp", "calf", "mice", "boar", "pony"};
            int index = random.nextInt(animalsFour.length);
            word = animalsFour[index];
            System.out.println(word);
            animOne = true;
        }
        else if (fileController.getModeTitle() == "Cities")
        {
            String[] citiesFour = {"elko", "erie", "gary", "hilo", "lima", "lynn", "mesa", "nome", "reno", "rome", "troy", "waco", "york", "yuma"};
            int index = random.nextInt(citiesFour.length);
            word = citiesFour[index];
            System.out.println(word);
            citiOne = true;
        }
        else {
            String[] dictFour = {"able", "area", "back", "base", "been", "nest", "blue", "bush", "call", "coal", "cast", "city", "crop", "dead", "dawn", "disk", "draw", "each", "exit", "fact", "feet", "felt", "fire", "fuel", "game", "girl", "grow", "hang", "hear", "holy", "hurt", "jean", "join", "knee", "know", "lady", "life", "logo", "make", "menu", "milk", "mood", "must", "navy", "peck", "only", "nose", "over", "paid", "post", "rear", "rent", "same", "shop", "song", "suit", "task", "they", "view", "ward", "wash", "wine", "year", "zero"};
            int index = random.nextInt(dictFour.length);
            word = dictFour[index];
            System.out.println(word);
            dictOne = true;
        }
        Label first = new Label();
        first.setText(word.substring(0,1));
        first.setScaleX(2);
        first.setScaleY(2);
        first.setTranslateX(33);
        first.setTextFill(WHITE);
        grid.add(first, col, row);
        first.setMouseTransparent(true);
        labelList[col*4+row] = first.getText();
        wordCircles.add(allCircleList[col*4+row]);

        colRowStr[0] = getColRowString(col, row);

        for(int i=1; i<4; i++)
        {
            Label l = new Label();
            l.setText(word.substring(i, i+1));
            l.setScaleX(2);
            l.setScaleY(2);
            l.setTranslateX(33);
            l.setTextFill(WHITE);

            if(row < 3 && col < 3)
            {
                col++;
            }
            else if (row < 3 && col == 3)
            {
                row++;
            }
            else if (row == 3 && col < 3)
            {
                col--;
                if(col < 0) {
                    col++;
                    row--;
                }
            }
            else if(row == 3 && col == 3)
            {
                col--;
            }
            grid.add(l, col, row);
            l.setMouseTransparent(true);
            labelList[col*4+row] = l.getText();
            wordCircles.add(allCircleList[col*4+row]);

            colRowStr[i] = getColRowString(col, row);
        }
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                if((m+"xx"+n).equals(colRowStr[0])||(m+"xx"+n).equals(colRowStr[1])||(m+"xx"+n).equals(colRowStr[2])||(m+"xx"+n).equals(colRowStr[3]))
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
                    letter.setMouseTransparent(true);
                    labelList[m*4+n] = letter.getText();
                }
            }
        }
        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
        {
            if(keyControlR.match(e))
                try {
                    fileController.handleReplayRequest();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });
        check();
        play();
    }

    public void initializeTwo()
    {

        grid.setDisable(false);
        pointsNumber.setText("20 Points");
        nextLevel.setDisable(true);
        int row, col;
        String[] colRowStr = new String[5];
        Random random = new Random();
        row = random.nextInt(3);
        col = random.nextInt(3);

        if(fileController.getModeTitle()=="Animals") {
            String[] animalsFive = {"horse", "lemur", "skunk", "otter", "hyena", "tiger", "whale", "bison", "dingo", "finch", "zebra", "shrew", "snake", "mouse", "robin", "panda", "quail", "puppy", "rhino", "goose", "eagle", "dingo", "camel"};
            int index = random.nextInt(animalsFive.length);
            word = animalsFive[index];
            System.out.println(word);
            animTwo = true;
        }
        else if(fileController.getModeTitle()=="Cities") {
            String[] citiesFive = {"akron", "baker", "belen", "boise", "butte", "craig", "dover", "fargo", "flint", "havre", "huron", "macon", "malta", "miami", "minot", "nephi", "ogden", "omaha", "ozark", "provo", "salem", "selma", "sitka", "tampa", "tulsa", "tyler", "ukiah", "utica"};
            int index = random.nextInt(citiesFive.length);
            word = citiesFive[index];
            System.out.println(word);
            citiTwo = true;
        }
        else {
            String[] dictFive = {"about", "agent", "allow", "among", "brand", "brief", "chase", "coach", "doubt", "depth", "earth", "eager", "elite", "enemy", "fight", "false", "guess", "giant", "human", "hairy", "image", "issue", "layer", "lease", "media", "minus", "needs", "often", "ocean", "peace", "phase", "quiet", "radio", "rough", "serve", "shall", "solid", "theme", "title", "touch", "union", "upset", "visit", "voice", "women", "wrote", "young", "world", "would"};
            int index = random.nextInt(dictFive.length);
            word = dictFive[index];
            System.out.println(word);
            dictTwo = true;
        }
        Label first = new Label();
        first.setText(word.substring(0,1));
        first.setScaleX(2);
        first.setScaleY(2);
        first.setTranslateX(33);
        first.setTextFill(WHITE);
        grid.add(first, col, row);
        first.setMouseTransparent(true);
        labelList[col*4+row] = first.getText();
        wordCircles.add(allCircleList[col*4+row]);
        colRowStr[0] = getColRowString(col, row);

        for(int i=1; i<5; i++)
        {
            Label l = new Label();
            l.setText(word.substring(i, i+1));
            l.setScaleX(2);
            l.setScaleY(2);
            l.setTranslateX(33);
            l.setTextFill(WHITE);

            if(row < 3 && col < 3)
            {
                col++;
            }
            else if (row < 3 && col == 3)
            {
                row++;
            }
            else if (row == 3 && col < 3)
            {
                col--;
                if(col < 0) {
                    col++;
                    row--;
                }
            }
            else if(row == 3 && col == 3)
            {
                col--;
            }
            grid.add(l, col, row);
            l.setMouseTransparent(true);
            labelList[col*4+row] = l.getText();
            wordCircles.add(allCircleList[col*4+row]);
            colRowStr[i] = getColRowString(col, row);
        }
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                if((m+"xx"+n).equals(colRowStr[0])||(m+"xx"+n).equals(colRowStr[1])||(m+"xx"+n).equals(colRowStr[2])||(m+"xx"+n).equals(colRowStr[3])||(m+"xx"+n).equals(colRowStr[4]))
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
                    letter.setMouseTransparent(true);
                    labelList[m*4+n] = letter.getText();
                }
            }
        }
        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
        {
            if(keyControlR.match(e))
                try {
                    fileController.handleReplayRequest();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });
        play();
    }
    public void initializeThree()
    {

        grid.setDisable(false);
        pointsNumber.setText("30 Points");
        nextLevel.setDisable(true);
        int row, col;
        String[] colRowStr = new String[6];
        Random random = new Random();
        row = random.nextInt(3);
        col = random.nextInt(3);

        if(fileController.getModeTitle()=="Animals") {
            String[] animalsSix = {"ferret", "rabbit", "coyote", "jaguar", "monkey", "walrus", "beluga", "bobcat", "iguana", "turtle", "python", "hornet", "toucan", "turkey", "parrot", "falcon"};
            int index = random.nextInt(animalsSix.length);
            word = animalsSix[index];
            System.out.println(word);
            animThree = true;
        }
        else if(fileController.getModeTitle()=="Cities") {
            String[] citiesSix = {"albany", "auburn", "aurora", "austin", "bangor", "biloxi", "boston", "camden", "dallas", "dayton", "denver", "durham", "fresno", "helena", "ithica", "mobile", "monroe", "nassau", "newark", "orange", "oxford", "quincy", "tacoma", "towson", "tuscan", "topeka", "winona", "yakima"};
            int index = random.nextInt(citiesSix.length);
            word = citiesSix[index];
            System.out.println(word);
            citiThree = true;
        }
        else {
            String[] dictSix = {"abroad", "answer", "beauty", "bridge", "combat", "credit", "decide", "danger", "editor", "effort", "equity", "famous", "foster", "global", "honest", "intent", "island", "jersey", "latter", "lesson", "manage", "merger", "nobody", "origin", "palace", "pocket", "reform", "robust", "safety", "single", "though", "unique", "valley", "walker", "weekly"};
            int index = random.nextInt(dictSix.length);
            word = dictSix[index];
            System.out.println(word);
            dictThree = true;
        }
        Label first = new Label();
        first.setText(word.substring(0,1));
        first.setScaleX(2);
        first.setScaleY(2);
        first.setTranslateX(33);
        first.setTextFill(WHITE);
        grid.add(first, col, row);
        first.setMouseTransparent(true);
        labelList[col*4+row] = first.getText();
        wordCircles.add(allCircleList[col*4+row]);
        colRowStr[0] = getColRowString(col, row);

            for(int i=1; i<6; i++)
            {
                Label l = new Label();
                l.setText(word.substring(i, i+1));
                l.setScaleX(2);
                l.setScaleY(2);
                l.setTranslateX(33);
                l.setTextFill(WHITE);

                if(row < 3 && col < 3)
                {
                    col++;
                }
                else if (row < 3 && col == 3)
                {
                    row++;
                }
                else if (row == 3 && col < 3)
                {
                    col--;
                    if(col < 0) {
                        col++;
                        row--;
                    }
                }
                else if(row == 3 && col == 3)
                {
                    col--;
                }
                grid.add(l, col, row);
                l.setMouseTransparent(true);
                labelList[col*4+row] = l.getText();
                colRowStr[i] = getColRowString(col, row);
            }
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                if((m+"xx"+n).equals(colRowStr[0])||(m+"xx"+n).equals(colRowStr[1])||(m+"xx"+n).equals(colRowStr[2])||(m+"xx"+n).equals(colRowStr[3])||(m+"xx"+n).equals(colRowStr[4])||(m+"xx"+n).equals(colRowStr[5]))
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
                    letter.setMouseTransparent(true);
                    labelList[m*4+n] = letter.getText();
                    wordCircles.add(allCircleList[col*4+row]);
                }
            }
        }
        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
        {
            if(keyControlR.match(e))
                try {
                    fileController.handleReplayRequest();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        });
        play();
        }

        public void initializeFour() {

            grid.setDisable(false);
            pointsNumber.setText("40 Points");
                nextLevel.setDisable(true);
                int row, col;
                String[] colRowStr = new String[7];
                Random random = new Random();
                row = random.nextInt(3);
                col = random.nextInt(3);

                if (fileController.getModeTitle() == "Animals") {
                    String[] animalsSeven = {"giraffe", "leopard", "panther", "ostrich", "swallow", "gorilla", "buffalo", "meerkat", "peacock", "catfish", "oarfish", "cheetah", "wallaby", "manatee", "dolphin", "zorilla", "vulture", "buzzard", "sunfish", "penguin", "spaniel", "terrier", "muskrat", "octopus", "termite", "cricket", "seagull", "sparrow", "ladybug"};
                    int index = random.nextInt(animalsSeven.length);
                    word = animalsSeven[index];
                    System.out.println(word);
                    animFour = true;
                }
                else if (fileController.getModeTitle() == "Cities") {
                String[] citiesSeven = {"atlanta", "augusta", "boulder", "buffalo", "chicago", "concord", "detroit", "hampton", "houston", "jackson", "lincoln", "madison", "memphis", "newport", "olympia", "oakland", "orlando", "phoenix", "raleigh", "roanoke", "seattle", "trenton", "ventura", "yonkers"};
                int index = random.nextInt(citiesSeven.length);
                word = citiesSeven[index];
                System.out.println(word);
                citiFour = true;
                }
                else {
                    String[] dictSeven = {"ability", "analyst", "auction", "brother", "between", "chronic", "chapter", "despite", "dynamic", "examine", "edition", "finance", "founder", "generic", "gallery", "highway", "initial", "illegal", "justice", "library", "mineral", "massive", "network", "pacific", "outlook", "privacy", "pioneer", "survive", "unknown", "version"};
                    int index = random.nextInt(dictSeven.length);
                    word = dictSeven[index];
                    System.out.println(word);
                    dictFour = true;
                }
                Label first = new Label();
                first.setText(word.substring(0, 1));
                first.setScaleX(2);
                first.setScaleY(2);
                first.setTranslateX(33);
                first.setTextFill(WHITE);
                grid.add(first, col, row);
                first.setMouseTransparent(true);
                labelList[col*4+row] = first.getText();
                wordCircles.add(allCircleList[col*4+row]);
                colRowStr[0] = getColRowString(col, row);

                for (int i = 1; i < 7; i++) {
                    Label l = new Label();
                    l.setText(word.substring(i, i + 1));
                    l.setScaleX(2);
                    l.setScaleY(2);
                    l.setTranslateX(33);
                    l.setTextFill(WHITE);

                    if (row < 3 && col < 3) {
                        col++;
                    } else if (row < 3 && col == 3) {
                        row++;
                    } else if (row == 3 && col < 3) {
                        col--;
                        if (col < 0) {
                            col++;
                            row--;
                        }
                    } else if (row == 3 && col == 3) {
                        col--;
                    }
                    grid.add(l, col, row);
                    l.setMouseTransparent(true);
                    labelList[col*4+row] = l.getText();
                    wordCircles.add(allCircleList[col*4+row]);
                    colRowStr[i] = getColRowString(col, row);
                }
                for (int m = 0; m < 4; m++) {
                    for (int n = 0; n < 4; n++) {
                        if ((m + "xx" + n).equals(colRowStr[0]) || (m + "xx" + n).equals(colRowStr[1]) || (m + "xx" + n).equals(colRowStr[2]) || (m + "xx" + n).equals(colRowStr[3]) || (m + "xx" + n).equals(colRowStr[4]) || (m + "xx" + n).equals(colRowStr[5])|| (m + "xx" + n).equals(colRowStr[6]))
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
                            letter.setMouseTransparent(true);
                            labelList[m*4+n] = letter.getText();

                        }
                    }
                }
            gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
            {
                if(keyControlR.match(e))
                    try {
                        fileController.handleReplayRequest();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            });
            play();
        }


    public String getColRowString(int col, int row)
    {
        return col+"xx"+row;
    }


    public void play()
    {
        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyTyped((KeyEvent event) -> {
            char c = event.getCharacter().charAt(0);
            gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed((KeyEvent event1) -> {
                if (event1.getCode().equals(KeyCode.ENTER))
                    checkWord(highlightWord1);
            });
//            if (c < 'a' || c > 'z')
//            {
//                PropertyManager manager = PropertyManager.getManager();
//                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
//                dialog.show(manager.getPropertyValue(TRY_AGAIN), "Please press something in A-Z.");
//            }
            gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
            {
                if(keyControlR.match(e))
                    try {
                        fileController.handleReplayRequest();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            });
            if(c >= 'a' && c <= 'z' )
            {
                ArrayList<Integer> already = new ArrayList<Integer>();
                String guesss = String.valueOf(c);
                guess.setText(guesss);
                highlightWord1 = guesss;
                for(int i = 0; i< labelList.length; i++)
                {
                    if(guesss.equals(labelList[i]))
                    {
                        highlight(allCircleList[i], i);
                        already.add(i);
                        circleList.add(allCircleList[i]);
                        ArrayList<String> path = new ArrayList<String>();
                        path.add(guesss);
                    }

                }
                playNext(already);
            }
        });
    }

    public void playNext(ArrayList<Integer> arr)
    {

        gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyTyped((KeyEvent event) -> {
            highlightWord1 = highlightWord1 + event.getCharacter().charAt(0);
            ArrayList<Integer> already = new ArrayList<Integer>();
            guess.setText(highlightWord1);
            for(int i=0; i< arr.size(); i++) {
                index = arr.get(i);
                int ind = 0;
                char c = event.getCharacter().charAt(0);
                gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed((KeyEvent event1) -> {
                    if (event1.getCode().equals(KeyCode.ENTER))
                        checkWord(highlightWord1);
                });
                  if(c >= 'a' || c <= 'z') {
                        String guesss = String.valueOf(c);
                        if ((index != 0 && index !=4 && index != 8 && index != 12) && (index - 1 >= 0 && index - 1 <= 15) && (guesss.equals(labelList[index - 1]))) {
                            ind = index - 1;
                            highlight(allCircleList[ind], ind);
                            already.add(ind);
                            circleList.add(allCircleList[ind]);
                        }
                        if ((index != 3 && index != 7 && index != 11 && index != 15) && (index + 1 >= 0 && index + 1 <= 15) && (guesss.equals(labelList[index + 1]))) {
                            ind = index + 1;
                            highlight(allCircleList[ind], ind);
                            already.add(ind);
                            circleList.add(allCircleList[ind]);
                        }
                        if ((index != 0 && index != 1 && index != 2 && index != 3) && (index - 4 >= 0 && index - 4 <= 15) && (guesss.equals(labelList[index - 4]))) {
                            ind = index - 4;
                            highlight(allCircleList[ind], ind);
                            already.add(ind);
                            circleList.add(allCircleList[ind]);
                        }
                        if ((index != 12 && index != 13 && index != 14 && index != 15) && (index + 4 >= 0 && index + 4 <= 15) && (guesss.equals(labelList[index + 4]))) {
                            ind = index + 4;
                            highlight(allCircleList[ind], ind);
                            already.add(ind);
                            circleList.add(allCircleList[ind]);
                        }
                        else
                            continue;
                }
            }

            playNext(already);
        });
    }

    public boolean highlightCheck(int ind)
    {
        boolean first = false;

            if(allCircleList[ind].getStyle() == ("-fx-effect: innershadow(gaussian, #9370db, 4, 4, 0, 0);"))
                first = true;
            else
                first = false;
        return first;
    }

    public String highlight(Circle c, int index) {
        c.setStyle("-fx-effect: innershadow(gaussian, #9370db, 4, 4, 0, 0);");
        highlightWord = highlightWord + labelList[index];
        return highlightWord;
    }
    public void highlightWhole(ArrayList<Circle> circleList)
    {
        for(int i =0; i<circleList.size(); i++)
        {
            circleList.get(i).setStyle("-fx-effect: innershadow(gaussian, #9370db, 4, 4, 0, 0);");
        }
    }

    public void unhighlight(ArrayList<Circle> list)
    {
        highlightWord = "";
        for(int i=0; i<list.size(); i++) {
            if(list.get(i)!= null)
                list.get(i).setStyle("-fx-effect: innershadow(gaussian, #9370db, 0, 0, 0, 0);");
        }
    }
    public void checkWord(String highlightWord) {
        if (highlightWord.equals(word)) {
            items = FXCollections.observableArrayList(highlightWord);
            wordsList.setItems(items);
            wordsList.setPrefSize(150, 250);
            alreadyGuessedWords.getChildren().addAll(wordsList);
            if(word.length() == 4)
                totalLabel.setText("Total:                     10");
            if(word.length() == 5)
                totalLabel.setText("Total:                     20");
            if(word.length() == 6)
                totalLabel.setText("Total:                     30");
            if(word.length() == 7)
                totalLabel.setText("Total:                     40");
            nextLevel.setDisable(false);
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            PropertyManager props = PropertyManager.getManager();
            dialog.show(props.getPropertyValue(GAME_WON_MESSAGE), props.getPropertyValue(""));
            timeline.stop();
            grid.setDisable(true);

            if(animFour == true)
                animals = 4;
            else if (animThree == true)
                animals = 4;
            else if (animTwo == true)
                animals = 3;
            else if (animOne == true)
                animals = 2;

            if(dictFour ==true)
                dict = 4;
            else if (dictThree == true)
                dict = 4;
            else if (dictTwo == true)
                dict = 3;
            else if (dictOne == true)
                dict = 2;

            if(citiFour == true)
                cities = 4;
            else if (citiThree == true)
                cities = 4;
            else if (citiTwo == true)
                cities = 3;
            else if (citiOne == true)
                cities = 2;

            gamedata.appTemplate.getGUI().getLevel().setLevel(gamedata.appTemplate);
            gamedata.setAnimals(animals);
            gamedata.setDict(dict);
            gamedata.setCities(cities);

            pauseButton.setDisable(true);
            nextLevel.setOnAction(e -> {
                try {
                    fileController.handleNextRequest();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.exit(1);
                }
            });
            gamedata.appTemplate.getGUI().getPrimaryScene().setOnKeyPressed(e ->
            {
                if(keyControlN.match(e))
                    try {
                        fileController.handleNextRequest();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            });


        } else {
            System.out.println("not a word");
            guess.setText(" ");
            highlightWord1 = "";
            unhighlight(circleList);
            play();
        }
    }
    public void check() {
        for (int i = 0; i < labelList.length; i++) {
            if(word.substring(0,1) == labelList[i])
            {
                if(checkNext(i, labelList[i])!= true);
                resetGrid();

            }

        }
    }
    public boolean checkNext(int i, String w)
    {
        if(word.equals(w)&& (gamedata.appTemplate.getGUI().getAnimals().contains(word)||gamedata.appTemplate.getGUI().getDictionary().contains(word)||gamedata.appTemplate.getGUI().getCities().contains(word)))
        {
            return true;
        }
        else {
            for (int k = 0; k < 4; k++) {
                if ((i != 0 && i !=4 && i != 8 && i != 12) && (i - 1 >= 0 && i - 1 <= 15) &&labelList[i - 1] == word.substring(word.indexOf(w), word.indexOf(w) + 1)) {
                    w = w + labelList[i - 1];
                    return checkNext(i - 1, w);
                } else if ((i != 3 && i != 7 && i != 11 && i != 15) && (i + 1 >= 0 && i + 1 <= 15) &&labelList[i + 1] == word.substring(word.indexOf(w), word.indexOf(w) + 1)) {
                    w = w + labelList[i + 1];
                    return checkNext(i + 1, w);
                } else if (labelList[i + 4] == word.substring(word.indexOf(w), word.indexOf(w) + 4)) {
                    w = w + labelList[i + 4];
                    return checkNext(i + 4, w);
                } else if ((i != 0 && i != 1 && i != 2 && i != 3) && (i - 4 >= 0 && i - 4 <= 15) && labelList[i - 4] == word.substring(word.indexOf(w), word.indexOf(w) - 4)) {
                    w = w + labelList[i - 4];
                    return checkNext(i - 4, w);
                }

            }
        }
        return checkNext(i, w);
    }

}
