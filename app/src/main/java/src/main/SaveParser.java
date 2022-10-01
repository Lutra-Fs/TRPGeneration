package src.main;


import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;


import java.io.*;
import java.lang.reflect.Type;


public class SaveParser extends Interaction {
    /**
     * Constructor for Interaction
     *
     * @param p the player
     * @author Bo ZHANG
     */
    SaveParser(Player p) {
        super(p);
    }

    static void createSaveFile(Game g) throws IOException {
        // create path, by default the save will be in saves folder
        Level l = g.level;
        Player p = g.player;
        String path = "saves";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // make dir for the save, which is the time when the save is created
        // like 2020-04-20-20-20-20
        path += "/" + java.time.LocalDateTime.now();
        file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        // create save file
        String playerPath = path + "/player.json";
        String levelPath = path + "/level.json";
        file = new File(playerPath);
        if (!file.createNewFile()) {
            throw new IOException("Failed to create save file");
        }

        file = new File(levelPath);
        if (!file.createNewFile()) {
            throw new IOException("Cannot create level file");
        }

        // write player and level to file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String playerJson = gson.toJson(p);
        String levelJson = gson.toJson(l);
        try (FileWriter writer = new FileWriter(playerPath)) {
            writer.write(playerJson);
        }
        try (FileWriter writer = new FileWriter(levelPath)) {
            writer.write(levelJson);
        }
        // create game file which contains the path of the original game file
        String gamePath = path + "/game.json";
        file = new File(gamePath);
        if (!file.createNewFile()) {
            throw new IOException("Cannot create game file");
        }
        try (FileWriter writer = new FileWriter(gamePath)) {
            writer.write(l.gamePath);
        }
    }

    static Player loadPlayer(String path) throws IOException {
        if (path.contains("..")) {
            throw new IOException("Invalid path");
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Player.class);
        }
    }

    static Level loadLevel(String path) throws IOException {
        if (path.contains("..")) {
            throw new IOException("Invalid path");
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(NPC.class, new NPCAdapter()).create();
        try (FileReader reader = new FileReader(path)) {
            Level l = gson.fromJson(reader, Level.class);
            Location.setMax(l.maxX, l.maxY);
            return l;
        }
    }

    /**
     * load a game from a save file
     *
     * @param path path of the save file relative to 'saves' folder under the project root
     * @return the game
     * @throws IOException if the file cannot be read
     */
    static void loadSave(String path, Player p, Level l) throws IOException {
        if (path.contains("..")) {
            throw new IOException("Invalid path");
        }
        path = "saves/" + path;
        p = loadPlayer(path + "/player.json");
        l = loadLevel(path + "/level.json");

    }

    /**
     * the method to load a game defination file
     *
     * @param gamePath relative path to 'Games' folder which is under the root of the game
     * @return the game object
     */
    static Game loadGame(String gamePath) throws IOException {
        // sanitize the path
        if (gamePath.contains("..")) {
            throw new IOException("Invalid path");
        }
        gamePath = "games/" + gamePath;
        Player p = loadPlayer(gamePath + "/player.json");
        Level l = loadLevel(gamePath + "/levels/level1.json");
        Game g = new Game(p, l);
        l.gamePath = gamePath;
        return g;
    }

    static void loadNextLevel(Level l) throws IOException {
        String path = l.gamePath + "/levels/level" + (l.curLevel + 1) + ".json";
        l = loadLevel(path);
    }

    @Override
    void interrupt() {
        player.p = Player.PlayerState.NORMAL;
    }

}

class NPCAdapter implements JsonSerializer<Object>, JsonDeserializer<Object> {
    private static final String CLASS_TYPE = "CLASS_TYPE";

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String className = jsonObject.get(CLASS_TYPE).getAsString();
        try {
            return context.deserialize(jsonObject, Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = new Gson();
        gson.toJson(src, src.getClass());
        JsonObject jsonObject = gson.toJsonTree(src).getAsJsonObject();
        jsonObject.addProperty(CLASS_TYPE, src.getClass().getName());
        return jsonObject;
    }

}
