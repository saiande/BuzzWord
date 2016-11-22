package controller;

import apptemplate.AppTemplate;
import data.GameData;
import propertymanager.PropertyManager;
import ui.AppMessageDialogSingleton;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static settings.AppPropertyType.*;
import static settings.InitializationParameters.APP_WORKDIR_PATH;

/**
 * Created by sai on 11/7/16.
 */
public class BuzzWordController implements FileController {
    public AppTemplate  app;
    public GameData     gamedata;
    public String       modeTitle;
    public int          level;
    private Path        workFile;
    public String       username;

    //constructor
    public BuzzWordController(AppTemplate apptemplate)
    {
        this.app = apptemplate;
        this.gamedata = (GameData) this.app.getDataComponent();
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
    public void handleProfileRequest() throws IOException {

    }

    @Override
    public void handleSelectModeRequest() throws IOException {

        modeTitle = new String();
        modeTitle = app.getGUI().getHome().getModeTitle();
    }

    @Override
    public void handleStartPlayingRequest() throws IOException {
        app.getGUI().getLevel().toFront();
        //app.getGUI().getLevel().initialize();
        try {
            app.getGUI().getLevel().initializeLevelHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

//        try{
//        if(app.getGUI().getLevel() == null) {
//            LevelSelectionScreen level = new LevelSelectionScreen(this);
//            level.initialize();
//            level.initializeLevelHandlers(app);
//            app.getGUI().setLevel(level);
//            app.getGUI().getAppPane().getChildren().add(level);
//            level.toFront();
//        }
//        else {
//            app.getGUI().getLevel().toFront();
//
//        }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void handlePlayPauseRequest() throws IOException {
            app.getGUI().getGameplay().getHideScreen().toFront();

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
        level = 1;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
//        GamePlayScreen gameplay = new GamePlayScreen(this);
//        level = 1;
//        gameplay.initialize();
//        try {
//            gameplay.initializeGamePlayHandlers(app);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        app.getGUI().getAppPane().getChildren().add(gameplay);
//        gameplay.toFront();

    }

    @Override
    public void handleTwoRequest() throws IOException {
        level = 2;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleThreeRequest() throws IOException {
        level = 3;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleFourRequest() throws IOException {
        level = 4;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleFiveRequest() throws IOException {
        level = 5;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleSixRequest() throws IOException {
        level = 6;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleSevenRequest() throws IOException {
        level = 7;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleEightRequest() throws IOException {
        level = 8;
        app.getGUI().getGameplay().toFront();
        //app.getGUI().getGameplay().initialize();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleEnterRequest() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();
        Path        appDirPath  = Paths.get(propertyManager.getPropertyValue(APP_TITLE)).toAbsolutePath();
        read(appDirPath);
        app.getGUI().getHome().initialize();
        try {
            app.getGUI().getHome().afterLoginProfileHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    private void read(Path target) throws IOException {
        app.getFileComponent().loadData(app.getDataComponent(), target);
        GameData gamedatatest = (GameData) app.getDataComponent();

        workFile = target;
    }


    @Override
    public void handleCancelRequest() throws IOException {

    }

    @Override
    public void handleHelpRequest() throws IOException {

    }

    @Override
    public void handleCreateRequest() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();
        Path        appDirPath  = Paths.get(propertyManager.getPropertyValue(APP_TITLE)).toAbsolutePath();
        String username;
        username = gamedata.getUsername();
        Path        targetPath  = appDirPath.resolve(username+"_"+APP_WORKDIR_PATH.getParameter());

        saved(targetPath);

        app.getGUI().getHome().initialize();
        try {
            app.getGUI().getHome().afterLoginProfileHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResumeRequest() throws IOException {

    }


    private void saved(Path target) throws IOException {
        app.getFileComponent().saveData(app.getDataComponent(), target);
        GameData gamedatatest = (GameData) app.getDataComponent();

        workFile = target;
        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
        PropertyManager           props  = PropertyManager.getManager();
        dialog.show(props.getPropertyValue(SAVE_COMPLETED_TITLE), props.getPropertyValue(SAVE_COMPLETED_MESSAGE));

    }

    @Override
    public String getModeTitle() {
        return modeTitle;
    }

    @Override
    public int getLevel() {
        return level;
    }

    public String getUsername() throws IOException{
        username = gamedata.getUsername();
        return username;
    }

}
