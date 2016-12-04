package controller;

import apptemplate.AppTemplate;
import data.GameData;
import data.GameDataManager;
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
            app.getGUI().getHome().toggleLoginButton(app);
            app.getGUI().getHome().toggleProfileButton(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleCreateProfileRequest() throws IOException {
        try {
            app.getGUI().initializeWindow();
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
        app.getGUI().getLevel().setLevel(app);
        try {
            app.getGUI().getLevel().initializeLevelHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handlePauseRequest() throws IOException {
        try {
            app.getGUI().getGameplay().initializePauseHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleXRequest() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();
        Path        appDirPath  = Paths.get(propertyManager.getPropertyValue(APP_TITLE)).toAbsolutePath();
        Path        targetPath  = ((GameDataManager)app.getFileComponent()).getP();
            save(targetPath);

    }
    private void save(Path target) throws IOException {

        app.getFileComponent().saveData(app.getDataComponent(), target);
        GameData gamedatatest = (GameData) app.getDataComponent();

        workFile = target;
        //System.exit(1);
//        AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
//        PropertyManager           props  = PropertyManager.getManager();
//        dialog.show(props.getPropertyValue(SAVE_COMPLETED_TITLE), props.getPropertyValue(SAVE_COMPLETED_MESSAGE));

    }

    @Override
    public void handleLoginRequest() throws IOException {
        try {
            app.getGUI().initializeWindow();
            app.getGUI().getHome().loginHandlers(app);
        } catch (InstantiationException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void handleOneRequest() throws IOException {
        level = 1;
        //app.getGUI().getGameplay().initialize();
        app.getGUI().getGameplay().resetGrid();
        app.getGUI().getGameplay().toFront();
        app.getGUI().getGameplay().initializeOne();
        app.getGUI().getGameplay().timer();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleTwoRequest() throws IOException {
        level = 2;
        //app.getGUI().getGameplay().initialize();
        app.getGUI().getGameplay().resetGrid();
        app.getGUI().getGameplay().toFront();
        app.getGUI().getGameplay().initializeTwo();
        app.getGUI().getGameplay().timer();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleThreeRequest() throws IOException {
        level = 3;
        //app.getGUI().getGameplay().initialize();
        app.getGUI().getGameplay().resetGrid();
        app.getGUI().getGameplay().toFront();
        app.getGUI().getGameplay().initializeThree();
        app.getGUI().getGameplay().timer();
        try {
            app.getGUI().getGameplay().initializeGamePlayHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleFourRequest() throws IOException {
        level = 4;
        //app.getGUI().getGameplay().initialize();
        app.getGUI().getGameplay().resetGrid();
        app.getGUI().getGameplay().toFront();
        app.getGUI().getGameplay().initializeFour();
        app.getGUI().getGameplay().timer();
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
            app.getGUI().getHome().toggleLoginButton(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResumeRequest() throws IOException {
        app.getGUI().getGameplay().toFront();
    }

    @Override
    public void handleLogoutRequest() throws IOException {
        gamedata.reset();
        app.getGUI().getHome().toFront();
        app.getGUI().getHome().initialize();
        try {
            app.getGUI().getHome().initializeHomeHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleReplayRequest() throws IOException {
        if(level == 1)
            handleOneRequest();
        else if (level == 2)
            handleTwoRequest();
        else if (level == 3)
            handleThreeRequest();
        else
            handleFourRequest();
    }

    @Override
    public void handleNextRequest() throws IOException {
        if(level == 1)
            handleTwoRequest();
        else if (level == 2)
            handleThreeRequest();
        else if (level == 3)
            handleFourRequest();
        else
            handleFourRequest();
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
