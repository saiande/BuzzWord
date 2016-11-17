package data;

import apptemplate.AppTemplate;
import components.AppDataComponent;

/**
 * @author Ritwik Banerjee
 * @author Sai Ande
 */
public class GameData implements AppDataComponent {


    private String      username;
    private String      password;
    private int         level;
    public AppTemplate appTemplate;

    public GameData(AppTemplate appTemplate) {
        this(appTemplate, false);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public GameData(AppTemplate appTemplate, boolean initiateGame) {
        if (initiateGame) {
            this.appTemplate = appTemplate;
            setUsername(appTemplate.getGUI().getHome().getUsernameString());
            setPassword(appTemplate.getGUI().getHome().getPassWordString());
            setLevel(1);
        } else {
            this.appTemplate = appTemplate;
        }
    }


    @Override
    public void reset() {
        this.username = null;
        this.password = null;
        this.level = 1;
        appTemplate.getWorkspaceComponent().reloadWorkspace();
    }



}
