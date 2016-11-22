package data;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import components.AppDataComponent;
import components.AppFileComponent;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ritwik Banerjee
 *@author Sai Ande
 */
public class GameDataManager implements AppFileComponent {
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String ANIMALS = "ANIMALS";
    public static final String DICT = "DICT";

    @Override
    public void saveData(AppDataComponent data, Path to) {
        GameData       gamedata    = (GameData) data;
        String username = gamedata.getUsername();
        String password  = gamedata.getPassword();
        int animals = gamedata.getAnimals();
        int dict = gamedata.getDict();

        JsonFactory jsonFactory = new JsonFactory();

        try (OutputStream out = Files.newOutputStream(to)) {

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

    @Override
    public void loadData(AppDataComponent data, Path from) throws IOException {
        GameData gamedata = (GameData) data;
        String username = gamedata.getUsername();
        String password = gamedata.getPassword();
        JsonFactory jsonFactory = new JsonFactory();


        // gamedata.reset();
        File path = new File(from.toString());
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(username)) {
                Path p = Paths.get(files[i].getAbsolutePath());
                JsonParser jsonParser = jsonFactory.createParser(Files.newInputStream(p));
                jsonParser.nextToken();
                System.out.println(jsonParser.nextToken());
                if (jsonParser.getValueAsString() == password) ;
                {
                    jsonParser.nextToken();
                    gamedata.setAnimals(jsonParser.getValueAsInt());
                    jsonParser.nextToken();
                    gamedata.setDict(jsonParser.getValueAsInt());
                }
            }
            else
                System.out.println("No user");
        }
    }


    /** This method will be used if we need to export data into other formats. */
    @Override
    public void exportData(AppDataComponent data, Path filePath) throws IOException { }
}
