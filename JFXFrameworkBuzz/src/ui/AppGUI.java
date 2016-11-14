package ui;

import apptemplate.AppTemplate;
import controller.FileController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import propertymanager.PropertyManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static settings.InitializationParameters.APP_IMAGEDIR_PATH;

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

    protected FileController fileController;   // to react to file-related controls
    protected Stage primaryStage;     // the application window
    protected Scene primaryScene;     // the scene graph
    protected StackPane appPane;
    protected HomeScreen home;

    public LevelSelectionScreen getLevel() {
        return level;
    }

    public GamePlayScreen getGameplay(){
        return gameplay;
    }

    public void setLevel(LevelSelectionScreen level) {
        this.level = level;
    }

    protected LevelSelectionScreen level;
    protected GamePlayScreen gameplay;
    public    String applicationTitle; // the application title
    public AppTemplate apptemplate;
    public Pane clearPane;
    private int appWindowWidth;  // optional parameter for window width that can be set by the application
    private int appWindowHeight; // optional parameter for window height that can be set by the application

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
        this.appWindowWidth = appWindowWidth;
        this.appWindowHeight = appWindowHeight;
        this.primaryStage = primaryStage;
        this.applicationTitle = applicationTitle;
        this.home = new HomeScreen(fileController);
        initializeScreen(Screen.HOME);
        home.initializeHomeHandlers(appTemplate);
        initializeWindow();                     // start the app window (without the application-specific workspace)

    }

    public void initializeScreen(Screen screen) {
        if (screen == Screen.HOME)
            home.initialize();
        if (screen == Screen.LEVEL)
            level.initialize();
        if (screen == Screen.GAMEPLAY) {
            gameplay.initialize();
            try {
                gameplay.initializeGamePlayHandlers(apptemplate);
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public FileController getFileController() {
        return this.fileController;
    }

    public StackPane getAppPane() {
        return appPane;
    }

    /**
     * Accessor method for getting this application's primary stage's,
     * scene.
     *
     * @return This application's window's scene.
     */
    public Scene getPrimaryScene() {
        return primaryScene;
    }

    /**
     * Accessor method for getting this application's window,
     * which is the primary stage within which the full GUI will be placed.
     *
     * @return This application's primary stage (i.e. window).
     */
    public Stage getWindow() {
        return primaryStage;
    }

    private void initializeWindow() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();


        // SET THE WINDOW TITLE
        applicationTitle = "!! BUZZWORD !!";
        primaryStage.setTitle(applicationTitle);
        //add the toolbar to the constructed workspace
        //level = new LevelSelectionScreen();
        //gameplay = new GamePlayScreen();
        clearPane = home.getClearPane();
        appPane = new StackPane();
        appPane.getChildren().addAll(clearPane, home);
        home.toFront();
        primaryScene = appWindowWidth < 1 || appWindowHeight < 1 ? new Scene(appPane)
                : new Scene(appPane,
                appWindowWidth,
                appWindowHeight);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    public HomeScreen getHome()
    {
        return home;
    }

}