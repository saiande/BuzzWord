package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sai on 11/7/16.
 */
public class GamePlayScreen extends BorderPane{
    protected FileController fileController;
    protected BorderPane gameplayBorderPane;
    protected Button profileButton;
    protected Button homeButton;
    protected Button xButton;
    protected Button playPauseButton;
    protected VBox list;
    protected HBox top;
    protected Label title;
    protected HBox wordGuessing;
    protected HBox time;
    protected VBox alreadyGuessedWords;
    protected VBox points;
    protected VBox right;
    protected String username;
    protected Label level;


    public GamePlayScreen() {
        this.gameplayBorderPane = new BorderPane();
    }


    public void initialize() {
        title.setText("!! BUZZWORD !!");
        top.getChildren().add(title);
        profileButton.setText(username);
        homeButton.setText("Home");
        playPauseButton.setText("play");
        xButton.setText("x");
        top.getChildren().add(xButton);
        list.getChildren().addAll(profileButton, homeButton);
        Image image = new Image("Logo.png");
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        gameplayBorderPane.setCenter(imageview);
        gameplayBorderPane.setLeft(list);
        gameplayBorderPane.setTop(top);
        wordGuessing.setStyle("-fx-background-color: gray;");
        time.setStyle("-fx-background-color: black;");
        alreadyGuessedWords.setStyle("-fx-background-color: red;");
        points.setStyle("-fx-background-color: blue;");
        right.getChildren().addAll(time, wordGuessing, alreadyGuessedWords, points);
        gameplayBorderPane.setRight(right);
        gameplayBorderPane.setBottom(playPauseButton);
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
        xButton.setOnAction(e -> {
            try {
                fileController.handleXRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
    }
}
