package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
public class LevelSelectionScreen extends BorderPane {
    protected FileController fileController;
    protected Button profileButton;
    protected Button homeButton;
    protected Button xButton;
    protected Button one;
    protected Button two;
    protected Button three;
    protected Button four;
    protected Button five;
    protected Button six;
    protected Button seven;
    protected Button eight;
    protected VBox list;
    protected HBox closeHBox;
    protected HBox titleHBox;
    protected  VBox topVBox;
    protected HBox firstLine;
    protected HBox secondLine;
    protected VBox levels;
    protected Label title;



    public LevelSelectionScreen() {
        initialize();

    }


    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");

        profileButton = new Button();
        profileButton.setText("Username");
        homeButton = new Button();
        homeButton.setText("Home");
        xButton = new Button();
        xButton.setText("x");
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
        list.getChildren().addAll(profileButton, homeButton);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        titleHBox = new HBox(100);
        titleHBox.getChildren().add(title);
        titleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox);
        one = new Button();
        one.setText("1");
        one.setScaleX(2);
        one.setScaleY(2);
        one.setTranslateX(-60);
        two = new Button();
        two.setText("2");
        two.setScaleX(2);
        two.setScaleY(2);
        two.setTranslateX(-20);
        three = new Button();
        three.setText("3");
        three.setScaleX(2);
        three.setScaleY(2);
        three.setTranslateX(20);
        four = new Button();
        four.setText("4");
        four.setScaleX(2);
        four.setScaleY(2);
        four.setTranslateX(70);
        five = new Button();
        five.setText("5");
        five.setScaleX(2);
        five.setScaleY(2);
        five.setTranslateX(-60);
        six = new Button();
        six.setText("6");
        six.setScaleX(2);
        six.setScaleY(2);
        six.setTranslateX(-20);
        seven = new Button();
        seven.setText("7");
        seven.setScaleX(2);
        seven.setScaleY(2);
        seven.setTranslateX(20);
        eight = new Button();
        eight.setText("8");
        eight.setScaleX(2);
        eight.setScaleY(2);
        eight.setTranslateX(70);
        firstLine = new HBox();
        firstLine.setPadding(new Insets(50, 50, 50, 50));
        firstLine.getChildren().addAll(one, two, three, four);
        firstLine.setTranslateX(30);
        secondLine = new HBox();
        secondLine.setPadding(new Insets(50, 50, 50, 50));
        secondLine.getChildren().addAll(five, six, seven, eight);
        firstLine.setTranslateX(30);
        levels = new VBox();

        levels.getChildren().addAll(firstLine, secondLine);
        levels.setTranslateX(100);
        levels.setTranslateY(75);
        this.setTop(topVBox);
        this.setCenter(levels);
        this.setLeft(list);
        this.toFront();
    }

    public void initializeLevelHandlers(AppTemplate app) throws InstantiationException {
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
        one.setOnAction(e -> {
            try {
                fileController.handleOneRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        two.setOnAction(e -> {
            try {
                fileController.handleTwoRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        three.setOnAction(e -> {
            try {
                fileController.handleThreeRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        four.setOnAction(e -> {
            try {
                fileController.handleFourRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        five.setOnAction(e -> {
            try {
                fileController.handleFiveRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        six.setOnAction(e -> {
            try {
                fileController.handleSixRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        seven.setOnAction(e -> {
            try {
                fileController.handleSevenRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        eight.setOnAction(e -> {
            try {
                fileController.handleEightRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
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
