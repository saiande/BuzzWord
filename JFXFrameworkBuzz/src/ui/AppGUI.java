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
import java.util.Arrays;
import java.util.HashSet;

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
    protected   HelpScreen              help;             //help screen instance
    protected   ProfileScreen           profile;          //profile screen instance
    public      String                  applicationTitle; // the application title
    public      AppTemplate             apptemplate;      // apptemplate instance
    public      Pane                    clearPane;        // login screen
    public      Pane                    profilePane;      // create profile screen
    public      Pane                    hideScreen;       //Play Pause screen to block game board
    public      Pane                    hideQuitScreen;    // screen before quitting
    private     int                     appWindowWidth;   // optional parameter for window width that can be set by the application
    private     int                     appWindowHeight;  // optional parameter for window height that can be set by the application
    public GameData                     gamedata;
    public      HashSet<String>         animals;
    public      HashSet<String>         dictionary;
    public      HashSet<String>         cities;

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
        this.home = new HomeScreen(fileController, gamedata, appTemplate);
        this.level = new LevelSelectionScreen(fileController, gamedata);
        this.gameplay = new GamePlayScreen(fileController, gamedata);
        this.help = new HelpScreen(fileController, gamedata);
        this.profile = new ProfileScreen(fileController, gamedata);
        initializeScreen(Screen.HOME);
        home.initializeHomeHandlers(appTemplate);
        initializeWindow();                     // start the app window (without the application-specific workspace)
        animals = new HashSet<String>();
        animals.addAll(Arrays.asList("bird","deer", "bear", "lamb", "crab", "fish", "toad", "frog", "dove", "crow", "gull", "duck","swan", "kiwi", "wolf", "lynx", "moth", "mole", "worm", "gnat", "goat", "wasp", "calf", "mice", "boar", "pony", "horse", "lemur", "skunk", "otter", "hyena", "tiger", "whale", "bison", "dingo", "finch", "zebra", "shrew", "snake", "mouse", "robin", "panda", "quail", "puppy", "rhino", "goose", "eagle", "dingo", "camel", "ferret", "rabbit", "coyote", "jaguar", "monkey", "walrus", "beluga", "bobcat", "iguana", "turtle", "python", "hornet", "toucan", "turkey", "parrot", "falcon", "giraffe", "leopard", "panther", "ostrich", "swallow", "gorilla", "buffalo", "meerkat", "peacock", "catfish", "oarfish", "cheetah", "wallaby", "manatee", "dolphin", "zorilla", "vulture", "buzzard", "sunfish", "penguin", "spaniel", "terrier", "muskrat", "octopus", "termite", "cricket", "seagull", "sparrow", "ladybug"));
        cities = new HashSet<String>();
        cities.addAll(Arrays.asList("elko", "erie", "gary", "hilo", "lima", "lynn", "mesa", "nome", "reno", "rome", "troy", "waco", "york", "yuma", "akron", "baker", "belen", "boise", "butte", "craig", "dover", "fargo", "flint", "havre", "huron", "macon", "malta", "miami", "minot", "nephi", "ogden", "omaha", "ozark", "provo", "salem", "selma", "sitka", "tampa", "tulsa", "tyler", "ukiah", "utica", "albany", "auburn", "aurora", "austin", "bangor", "biloxi", "boston", "camden", "dallas", "dayton", "denver", "durham", "fresno", "helena", "ithica", "mobile", "monroe", "nassau", "newark", "orange", "oxford", "quincy", "tacoma", "towson", "tuscan", "topeka", "winona", "yakima", "atlanta", "augusta", "boulder", "buffalo", "chicago", "concord", "detroit", "hampton", "houston", "jackson", "lincoln", "madison", "memphis", "newport", "olympia", "oakland", "orlando", "phoenix", "raleigh", "roanoke", "seattle", "trenton", "ventura", "yonkers"));
        dictionary = new HashSet<String>();
        dictionary.addAll(Arrays.asList("able", "area", "back", "base", "been", "nest", "blue", "bush", "call", "coal", "cast", "city", "crop", "dead", "dawn", "disk", "draw", "each", "exit", "fact", "feet", "felt", "fire", "fuel", "game", "girl", "grow", "hang", "hear", "holy", "hurt", "jean", "join", "knee", "know", "lady", "life", "logo", "make", "menu", "milk", "mood", "must", "navy", "peck", "only", "nose", "over", "paid", "post", "rear", "rent", "same", "shop", "song", "suit", "task", "they", "view", "ward", "wash", "wine", "year", "zero", "about", "agent", "allow", "among", "brand", "brief", "chase", "coach", "doubt", "depth", "earth", "eager", "elite", "enemy", "fight", "false", "guess", "giant", "human", "hairy", "image", "issue", "layer", "lease", "media", "minus", "needs", "often", "ocean", "peace", "phase", "quiet", "radio", "rough", "serve", "shall", "solid", "theme", "title", "touch", "union", "upset", "visit", "voice", "women", "wrote", "young", "world", "would", "abroad", "answer", "beauty", "bridge", "combat", "credit", "decide", "danger", "editor", "effort", "equity", "famous", "foster", "global", "honest", "intent", "island", "jersey", "latter", "lesson", "manage", "merger", "nobody", "origin", "palace", "pocket", "reform", "robust", "safety", "single", "though", "unique", "valley", "walker", "weekly", "ability", "analyst", "auction", "brother", "between", "chronic", "chapter", "despite", "dynamic", "examine", "edition", "finance", "founder", "generic", "gallery", "highway", "initial", "illegal", "justice", "library", "mineral", "massive", "network", "pacific", "outlook", "privacy", "pioneer", "survive", "unknown", "version"));
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

    public HelpScreen getHelp() {
        return help;
    }

    public ProfileScreen getProfile() {
        return profile;
    }

    public void initializeWindow() throws IOException {
        PropertyManager propertyManager = PropertyManager.getManager();
        // SET THE WINDOW TITLE
        applicationTitle = "!! BUZZWORD !!";
        primaryStage.setTitle(applicationTitle);
        clearPane = home.getClearPane();
        hideScreen = gameplay.getHideScreen();
        hideQuitScreen = gameplay.getHideQuitScreen();
        profilePane = home.getProfilePane();
        appPane = new StackPane();
        appPane.getChildren().addAll(clearPane, profilePane, home, level, hideScreen, hideQuitScreen, gameplay, help, profile);
        home.toFront();
        primaryScene = appWindowWidth < 1 || appWindowHeight < 1 ? new Scene(appPane)
                : new Scene(appPane,
                appWindowWidth,
                appWindowHeight);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

}