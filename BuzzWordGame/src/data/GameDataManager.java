package data;

import com.fasterxml.jackson.core.*;
import components.AppDataComponent;
import components.AppFileComponent;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Ritwik Banerjee
 *@author Sai Ande
 */
public class GameDataManager implements AppFileComponent {

    public static final String USERNAME  = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String LEVEL  = "LEVEL";

    @Override
    public void saveData(AppDataComponent data, Path to) {
        GameData       gamedata    = (GameData) data;
        String username = gamedata.getUsername();
        String password  = gamedata.getPassword();
        int level = gamedata.getLevel();

        JsonFactory jsonFactory = new JsonFactory();

        try (OutputStream out = Files.newOutputStream(to)) {

            JsonGenerator generator = jsonFactory.createGenerator(out, JsonEncoding.UTF8);

            generator.writeStartObject();

            generator.writeStringField(USERNAME, gamedata.getUsername());
            generator.writeStringField(PASSWORD, gamedata.getPassword());
            generator.writeNumberField(LEVEL, gamedata.getLevel());

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
        gamedata.reset();

        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser  = jsonFactory.createParser(Files.newInputStream(from));

        while (!jsonParser.isClosed()) {
            JsonToken token = jsonParser.nextToken();
            if (JsonToken.FIELD_NAME.equals(token)) {
                String fieldname = jsonParser.getCurrentName();
                switch (fieldname) {
                    case USERNAME:
                        jsonParser.nextToken();
                        gamedata.setUsername(jsonParser.getValueAsString());
                        break;
                    case PASSWORD:
                        jsonParser.nextToken();
                        gamedata.setPassword(jsonParser.getValueAsString());
                        break;
                    case LEVEL:
                        jsonParser.nextToken();
                        gamedata.setLevel(jsonParser.getIntValue());
                    default:
                        throw new JsonParseException(jsonParser, "Unable to load JSON data");
                }
            }
        }
    }

    /** This method will be used if we need to export data into other formats. */
    @Override
    public void exportData(AppDataComponent data, Path filePath) throws IOException { }
}
