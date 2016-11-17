package controller;

import apptemplate.AppTemplate;
import data.GameData;
import propertymanager.PropertyManager;
import ui.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static settings.AppPropertyType.APP_TITLE;
import static settings.AppPropertyType.SAVE_COMPLETED_MESSAGE;
import static settings.AppPropertyType.SAVE_COMPLETED_TITLE;
import static settings.InitializationParameters.APP_WORKDIR_PATH;

/**
 * Created by sai on 11/7/16.
 */
public class BuzzWordController implements FileController {
    public AppTemplate app;
    public AppGUI gui;
    public GameData gamedata;
    public HomeScreen home;
    public String modeTitle;
    public int level;
    private Path workFile;
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
        try {
            app.getGUI().getHome().profileHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleLoginLogoutRequest() throws IOException {

    }

    @Override
    public void handleProfileRequest() throws IOException {

    }

    @Override
    public void handleSelectModeRequest() throws IOException {

        modeTitle = new String();
        modeTitle = app.getGUI().getHome().getModeTitle();
    }

    @Override
    public void handleStartPlayingRequest() throws IOException {
        try{
        if(app.getGUI().getLevel() == null) {
            LevelSelectionScreen level = new LevelSelectionScreen(this);
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
        PropertyManager propertyManager = PropertyManager.getManager();
        Path        appDirPath  = Paths.get(propertyManager.getPropertyValue(APP_TITLE)).toAbsolutePath();
        Path        targetPath  = appDirPath.resolve(APP_WORKDIR_PATH.getParameter());
            save(targetPath);

    }
    private void save(Path target) throws IOException {

        app.getFileComponent().saveData(app.getDataComponent(), target);
        GameData gamedatatest = (GameData) app.getDataComponent();

        workFile = target;
        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
        PropertyManager           props  = PropertyManager.getManager();
        dialog.show(props.getPropertyValue(SAVE_COMPLETED_TITLE), props.getPropertyValue(SAVE_COMPLETED_MESSAGE));

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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 1;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 2;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 3;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 4;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 5;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 6;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 7;
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
        GamePlayScreen gameplay = new GamePlayScreen(this);
        level = 8;
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

    @Override
    public String getModeTitle() {
        return modeTitle;
    }

    @Override
    public int getLevel() {
        return level;
    }

}
