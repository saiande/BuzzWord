package controller;

import apptemplate.AppTemplate;
import data.GameData;
import ui.AppGUI;
import ui.HomeScreen;
import ui.LevelSelectionScreen;

import java.io.IOException;

/**
 * Created by sai on 11/7/16.
 */
public class BuzzWordController implements FileController {
    public AppTemplate app;
    public AppGUI gui;
    public GameData gamedata;
    public BuzzWordController(AppTemplate apptemplate)
    {
        this.app = apptemplate;
    }

    @Override
    public void handleHomeRequest() throws IOException {

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

    @Override
    public void handleSelectModeRequest() throws IOException {

    }

    @Override
    public void handleStartPlayingRequest() throws IOException {
        gui.initializeScreen(AppGUI.Screen.LEVEL);
    }

    @Override
    public void handlePlayPauseRequest() throws IOException {

    }

    @Override
    public void handleXRequest() throws IOException {

    }

    @Override
    public void handleLoginRequest() throws IOException {
        HomeScreen homescreen = new HomeScreen();
        try {
            homescreen.loginHandlers(app);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleOneRequest() throws IOException {
        gui.initializeScreen(AppGUI.Screen.GAMEPLAY);
    }

    @Override
    public void handleTwoRequest() throws IOException {

    }

    @Override
    public void handleThreeRequest() throws IOException {

    }

    @Override
    public void handleFourRequest() throws IOException {

    }

    @Override
    public void handleFiveRequest() throws IOException {

    }

    @Override
    public void handleSixRequest() throws IOException {

    }

    @Override
    public void handleSevenRequest() throws IOException {

    }

    @Override
    public void handleEightRequest() throws IOException {

    }
}
