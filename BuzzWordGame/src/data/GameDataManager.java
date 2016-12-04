package data;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import components.AppDataComponent;
import components.AppFileComponent;
import propertymanager.PropertyManager;
import ui.AppMessageDialogSingleton;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static settings.AppPropertyType.PROPERTIES_LOAD_ERROR_TITLE;
import static settings.AppPropertyType.TRY_AGAIN;

/**
 * @author Ritwik Banerjee
 *@author Sai Ande
 */
public class GameDataManager implements AppFileComponent {
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ANIMALS = "ANIMALS";
    public static final String DICT = "DICT";
    public Path p;

    public String passwordEncode(String password) {

        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public void saveData(AppDataComponent data, Path to) {
        GameData       gamedata    = (GameData) data;
        String username = gamedata.getUsername();
        String password  = gamedata.getPassword();
        int animals = gamedata.getAnimals();
        int dict = gamedata.getDict();
        password = passwordEncode(password);

        JsonFactory jsonFactory = new JsonFactory();

        try (OutputStream out = Files.newOutputStream(to)) {
         p = to;
            JsonGenerator generator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);


                generator.writeStartObject();

                generator.writeStringField(USERNAME, username);
                generator.writeStringField(PASSWORD, password);
                generator.writeNumberField(ANIMALS, animals);
                generator.writeNumberField(DICT, dict);

                generator.writeEndObject();

            generator.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Path getP() {
        return p;
    }

    @Override
    public void loadData(AppDataComponent data, Path from) throws IOException {
        GameData gamedata = (GameData) data;
        String username = gamedata.getUsername();
        String password = gamedata.getPassword();
        JsonFactory jsonFactory = new JsonFactory();
        password = passwordEncode(password);

        File path = new File(from.toString());
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(username)) {
                p = Paths.get(files[i].getAbsolutePath());
                JsonParser jsonParser = jsonFactory.createParser(Files.newInputStream(p));
                jsonParser.nextToken();
                jsonParser.nextToken();
                jsonParser.nextToken();
                jsonParser.nextToken();
                jsonParser.nextToken();
                //System.out.println(jsonParser.getValueAsString());
                if (jsonParser.getValueAsString().equals(password))
                {
                    jsonParser.nextToken();
                    jsonParser.nextToken();
                    //System.out.println(jsonParser.getValueAsInt());
                    gamedata.setAnimals(jsonParser.getValueAsInt());
                    jsonParser.nextToken();
                    jsonParser.nextToken();
                    //System.out.println(jsonParser.getValueAsInt());
                    gamedata.setDict(jsonParser.getValueAsInt());
                    gamedata.appTemplate.getGUI().getHome().toFront();
                    try {
                        gamedata.appTemplate.getGUI().getHome().afterLoginProfileHandlers(gamedata.appTemplate);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                    PropertyManager props = PropertyManager.getManager();
                    dialog.show(props.getPropertyValue(PROPERTIES_LOAD_ERROR_TITLE), props.getPropertyValue(TRY_AGAIN));
                    gamedata.appTemplate.getGUI().getHome().toFront();
                    try {
                        gamedata.appTemplate.getGUI().getHome().initializeHomeHandlers(gamedata.appTemplate);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /** This method will be used if we need to export data into other formats. */
    @Override
    public void exportData(AppDataComponent data, Path filePath) throws IOException { }
}
