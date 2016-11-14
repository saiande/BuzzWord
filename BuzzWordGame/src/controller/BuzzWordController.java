package controller;

import apptemplate.AppTemplate;
import data.GameData;
import ui.AppGUI;
import ui.GamePlayScreen;
import ui.HomeScreen;
import ui.LevelSelectionScreen;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by sai on 11/7/16.
 */
public class BuzzWordController implements FileController {
    public AppTemplate app;
    public AppGUI gui;
    public GameData gamedata;
    public HomeScreen home;
    String modeTitle;
    public BuzzWordController(AppTemplate apptemplate)
    {
        this.app = apptemplate;
    }

    @Override
    public void handleHomeRequest() throws IOException {

        app.getGUI().getHome().toFront();
        app.getGUI().getHome().initialize();
        try {
            app.getGUI().getHome().initializeHomeHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //app.getGUI().getAppPane().getChildren().get(2).toBack();
    }

    @Override
    public void handleCreateProfileRequest() throws IOException {

    }

    @Override
    public void handleLoginLogoutRequest() throws IOException {

    }

    @Override
    public void handleProfileRequest() throws IOException {

    }

    public String getModeTitle() {
        return modeTitle;
    }

    @Override
    public void handleSelectModeRequest() throws IOException {
        modeTitle = " ";
        modeTitle = app.getGUI().getHome().getSelectMode().getValue().toString();
    }

    @Override
    public void handleStartPlayingRequest() throws IOException {
        try{
        if(app.getGUI().getLevel() == null) {
            LevelSelectionScreen level = new LevelSelectionScreen();
            level.initialize();
            level.initializeLevelHandlers(app);
            app.getGUI().setLevel(level);
            app.getGUI().getAppPane().getChildren().add(level);
            level.toFront();
        }
        else {
            app.getGUI().getLevel().toFront();

        }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handlePlayPauseRequest() throws IOException {

    }

    @Override
    public void handleXRequest() throws IOException {

    }

    @Override
    public void handleLoginRequest() throws IOException {
        try {
            app.getGUI().getHome().loginHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleOneRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleTwoRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleThreeRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleFourRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleFiveRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleSixRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleSevenRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleEightRequest() throws IOException {
        GamePlayScreen gameplay = new GamePlayScreen();
        gameplay.initialize();
        try {
            gameplay.initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        app.getGUI().getAppPane().getChildren().add(gameplay);
        gameplay.toFront();
    }

    @Override
    public void handleEnterRequest() throws IOException {

        app.getGUI().getHome().initialize();
        try {
            app.getGUI().getHome().afterLoginProfileHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCancelRequest() throws IOException {

    }

}
