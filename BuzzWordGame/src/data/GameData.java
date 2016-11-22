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
    private int      animals;
    public int       dict;
    public AppTemplate appTemplate;

    public int getAnimals() {
        return animals;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }

    public int getDict() {
        return dict;
    }

    public void setDict(int dict) {
        this.dict = dict;
    }

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

    public GameData(AppTemplate appTemplate, boolean initiateGame) {
        if (initiateGame) {
            this.appTemplate = appTemplate;
            setUsername(appTemplate.getGUI().getHome().getUsernameString());
            setPassword(appTemplate.getGUI().getHome().getPassWordString());
            setAnimals(1);
            setDict(1);
        } else {
            this.appTemplate = appTemplate;
        }
    }


    @Override
    public void reset() {
        this.username = null;
        this.password = null;
        this.animals = 1;
        this.dict = 1;
        appTemplate.getWorkspaceComponent().reloadWorkspace();
    }



}
