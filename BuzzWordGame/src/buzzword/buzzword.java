package buzzword;

import apptemplate.AppTemplate;
import components.AppComponentsBuilder;
import components.AppDataComponent;
import components.AppFileComponent;
import components.AppWorkspaceComponent;
import data.GameData;
import data.GameDataManager;
import gui.Workspace;
import ui.AppGUI;

/**
 * @author Ritwik Banerjee
 */
public class buzzword extends AppTemplate {

    public static void main(String[] args) {
        launch(args);
    }


    public String getFileControllerClass() {
        return "BuzzWordController";
    }

    @Override
    public AppComponentsBuilder makeAppBuilderHook() {
        return new AppComponentsBuilder() {
//            @Override
//            public AppDataComponent buildDataComponent() throws Exception {
//                return new GameData(buzzword.this);
//            }
//
//            @Override
//            public AppFileComponent buildFileComponent() throws Exception {
//                return new GameData();
//            }

            @Override
            public AppWorkspaceComponent buildWorkspaceComponent() throws Exception {
                return new Workspace(buzzword.this);
            }
        };
    }
}
