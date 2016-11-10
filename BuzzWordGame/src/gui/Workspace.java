package gui;

import apptemplate.AppTemplate;
import components.AppWorkspaceComponent;
import controller.BuzzWordController;
import javafx.stage.Stage;
import ui.AppGUI;

import java.io.IOException;

/**
 * Created by sai on 11/7/16.
 */
public class Workspace extends AppWorkspaceComponent {

    AppTemplate app; // the actual application
    AppGUI gui; // the GUI inside which the application sits
    BuzzWordController controller;

    public Workspace(AppTemplate initApp) throws IOException {
        app = initApp;
        gui = app.getGUI();
        controller = (BuzzWordController) gui.getFileController();    //new HangmanController(app, startGame); <-- THIS WAS A MAJOR BUG!??
        app.getGUI();
    }

    @Override
    public void reloadWorkspace() {

    }
}
