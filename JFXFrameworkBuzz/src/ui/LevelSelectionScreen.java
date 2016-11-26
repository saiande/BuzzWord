package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by sai on 11/7/16.
 */
public class LevelSelectionScreen extends BorderPane {

    protected FileController fileController;
    protected GameData gamedata;
    protected Button xButton;
    protected HBox closeHBox;
    protected Label title;
    protected HBox titleHBox;
    protected Label modeTitle;
    protected HBox modeTitleHBox;
    protected VBox topVBox;
    protected Button profileButton;
    protected Button homeButton;
    protected VBox list;

    protected Button one;
    protected Button two;
    protected Button three;
    protected Button four;
    protected HBox firstLine;
    protected Label rules;

    protected int level;

    public LevelSelectionScreen(FileController fileController, GameData gamedata) {
        this.fileController = fileController;
        this.gamedata = gamedata;
        initialize();
    }


    public void initialize() {
        this.setPrefSize(800, 650);
        this.setStyle("-fx-background-color: lightseagreen;");
        //top of screen, including close button and titles
        xButton = new Button();
        xButton.setText("x");
        xButton.setTooltip(new Tooltip("Close"));
        xButton.setTranslateX(760);
        xButton.setTranslateY(10);
        closeHBox = new HBox(200);
        closeHBox.getChildren().add(xButton);
        title = new Label();
        title.setText("!! BUZZWORD !!");
        title.setTranslateX(350);
        title.setScaleY(2);
        title.setScaleX(2);
        titleHBox = new HBox();
        titleHBox.getChildren().addAll(title);
        modeTitle = new Label();
        modeTitle.setText(fileController.getModeTitle());
        modeTitle.setScaleX(2);
        modeTitle.setScaleY(2);
        modeTitleHBox = new HBox(100);
        modeTitleHBox.getChildren().add(modeTitle);
        modeTitleHBox.setAlignment(Pos.CENTER);
        topVBox = new VBox(50);
        topVBox.getChildren().addAll(closeHBox, titleHBox, modeTitleHBox);
        this.setTop(topVBox);

        //left side of screen including buttons
        profileButton = new Button();
            profileButton.setText(gamedata.getUsername());

        homeButton = new Button();
        homeButton.setText("Home");
        list = new VBox(50);
        list.setTranslateY(-30);
        list.setStyle("-fx-background-color: mediumpurple;");
        list.setPadding(new Insets(200, 50, 50, 50));
        list.getChildren().addAll(profileButton, homeButton);
        this.setLeft(list);

        //middle of screen displayng levels
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

        firstLine = new HBox();
        firstLine.setPadding(new Insets(50, 50, 50, 50));
        firstLine.getChildren().addAll(one, two, three, four);
        firstLine.setTranslateX(180);
        firstLine.setTranslateY(100);
        this.setCenter(firstLine);

        rules = new Label();
        rules.setText("Level 1: You may only choose four letter words \n Level 2: You may only choose five letter words \n Level 3: You may only choose six letter words \n Level 4: You may only choose seven letter words");
        rules.setScaleX(1.5);
        rules.setScaleY(1.5);
        rules.setStyle("-fx-background-color: white;");
        rules.setTranslateX(270);
        rules.setTranslateY(-150);
        this.setBottom(rules);
    }

    public void setLevel(AppTemplate app)
    {
        int level = 0;
        if(fileController.getModeTitle()=="Animals")
            level = gamedata.appTemplate.getGUI().getHome().getAnimalLevel();
        else
            level = gamedata.appTemplate.getGUI().getHome().getDictLevel();

        if(level ==1)
        {
            one.setDisable(false);
            two.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
        }
        else if(level ==2)
        {
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(true);
            four.setDisable(true);
        }
        else if(level == 3)
        {
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(false);
            four.setDisable(true);
        }
        else
        {
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
        }
    }

    public void initializeLevelHandlers(AppTemplate app) throws InstantiationException {
        modeTitle.setText(fileController.getModeTitle());
        profileButton.setText(gamedata.getUsername());

        one.setOnAction(e -> {
            level = 1;
            try {
                fileController.handleOneRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        two.setOnAction(e -> {
            level = 2;
            try {
                fileController.handleTwoRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        three.setOnAction(e -> {
            level = 3;
            try {
                fileController.handleThreeRequest();
            } catch (IOException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        });
        four.setOnAction(e -> {
            level = 4;
            try {
                fileController.handleFourRequest();
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