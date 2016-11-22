package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import data.GameData;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import propertymanager.PropertyManager;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class provides the basic user interface for this application, including all the file controls, but it does not
 * include the workspace, which should be customizable and application dependent.
 *
 * @author Richard McKenna, Ritwik Banerjee, Sai Ande
 */
public class AppGUI {
    public enum Screen {
        HOME, LEVEL, GAMEPLAY
    }

    protected   FileController          fileController;   // to react to file-related controls
    protected   Stage                   primaryStage;     // the application window
    protected   Scene                   primaryScene;     // the scene graph
    protected   StackPane               appPane;          // stack pane holding all screens
    protected   HomeScreen              home;             // home screen instance
    protected   LevelSelectionScreen    level;            // level screen instance
    protected   GamePlayScreen          gameplay;         // gameplay screen instance
    public      String                  applicationTitle; // the application title
    public      AppTemplate             apptemplate;      // apptemplate instance
    public      Pane                    clearPane;        // login screen
    public      Pane                    hideScreen;       //Play Pause screen to block game board
    private     int                     appWindowWidth;   // optional parameter for window width that can be set by the application
    private     int                     appWindowHeight;  // optional parameter for window height that can be set by the application
    public GameData                     gamedata;

    /**
     * This constructor initializes the file toolbar for use.
     *
     * @param initPrimaryStage The window for this application.
     * @param initAppTitle     The title of this application, which
     *                         will appear in the window bar.
     * @param app              The app within this gui is used.
     */
    public AppGUI(Stage initPrimaryStage, String initAppTitle, AppTemplate app) throws IOException, InstantiationException {
        this(initPrimaryStage, initAppTitle, app, -1, -1);
    }

    public AppGUI(Stage primaryStage, String applicationTitle, AppTemplate appTemplate, int appWindowWidth, int appWindowHeight) throws IOException, InstantiationException {
        try {
            Method getFileControllerClassMethod = appTemplate.getClass().getMethod("getFileControllerClass");
            String fileControllerClassName = (String) getFileControllerClassMethod.invoke(appTemplate);
            Class<?> klass = Class.forName("controller." + fileControllerClassName);
            Constructor<?> constructor = klass.getConstructor(AppTemplate.class);
            fileController = (FileController) constructor.newInstance(appTemplate);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        this.apptemplate = appTemplate;
        this.appWindowWidth = appWindowWidth;
        this.appWindowHeight = appWindowHeight;
        this.primaryStage = primaryStage;
        this.applicationTitle = applicationTitle;
        this.gamedata = (GameData) apptemplate.getDataComponent();
        this.home = new HomeScreen(fileController, gamedata);
        this.level = new LevelSelectionScreen(fileController, gamedata);
        this.gameplay = new GamePlayScreen(fileController, gamedata);
        initializeScreen(Screen.HOME);
        home.initializeHomeHandlers(appTemplate);
        initializeWindow();                     // start the app window (without the application-specific workspace)

    }

    public void initializeScreen(Screen screen) {
        if (screen == Screen.HOME)
            home.initialize();
        if (screen == Screen.LEVEL)
            level.initialize();
        if (screen == Screen.GAMEPLAY)
            gameplay.initialize();
    }

    public FileController getFileController() {
        return this.fileController;
    }

    public StackPane getAppPane() {
        return appPane;
    }

    public LevelSelectionScreen getLevel() {
        return level;
    }

    public GamePlayScreen getGameplay(){
        return gameplay;
    }

    public void setLevel(LevelSelectionScreen level) {
        this.level = level;
    }

    public Scene getPrimaryScene() {
        return primaryScene;
    }

    public Stage getWindow() {
        return primaryStage;
    }

    public HomeScreen getHome() {
        return home;
    }

    private void initializeWindow() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();
        // SET THE WINDOW TITLE
        applicationTitle = "!! BUZZWORD !!";
        primaryStage.setTitle(applicationTitle);
        clearPane = home.getClearPane();
        hideScreen = gameplay.getHideScreen();
        appPane = new StackPane();
        appPane.getChildren().addAll(clearPane, home, level, hideScreen, gameplay);
        home.toFront();
        primaryScene = appWindowWidth < 1 || appWindowHeight < 1 ? new Scene(appPane)
                : new Scene(appPane,
                appWindowWidth,
                appWindowHeight);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

}