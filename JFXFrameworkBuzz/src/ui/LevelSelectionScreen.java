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
public class LevelSelectionScreen extends BorderPane {
    protected FileController fileController;
    protected BorderPane levelBorderPane;
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
    protected HBox top;
    protected HBox firstLine;
    protected HBox secondLine;
    protected VBox levels;
    protected Label title;
    protected Pane clearPane;
    protected String username;
    protected String gamemode;


    public LevelSelectionScreen() {
        this.levelBorderPane = new BorderPane();
    }


    public void initialize() {
        title.setText("!! BUZZWORD !!");
        top.getChildren().add(title);
        profileButton.setText(username);
        homeButton.setText("Home");
        xButton.setText("x");
        top.getChildren().add(xButton);
        list.getChildren().addAll(profileButton, homeButton);
        levelBorderPane.setLeft(list);
        levelBorderPane.setTop(top);
        one.setText("1");
        two.setText("2");
        three.setText("3");
        four.setText("4");
        five.setText("5");
        six.setText("6");
        seven.setText("7");
        eight.setText("8");
        firstLine.getChildren().addAll(one, two, three, four);
        secondLine.getChildren().addAll(five, six, seven, eight);
        levels.getChildren().addAll(firstLine, secondLine);
        levelBorderPane.setCenter(levels);

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
