package src.main;
//import org.json.JSONException;
//import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.channels.AsynchronousFileChannel;
import java.util.ArrayList;
import java.util.List;

public class SaveParser {
    /**
     * read file
     * @author Xiangda Li
     * @param path Where the documents are stored
     * @return Return the Game object
     */
    public Game loadGameFromLocalFile(String path) {
        Game game = null;
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            JsonReader reader = new JsonReader(fileReader);
            game = new Gson().fromJson(reader, Game.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    /**
     * archive
     *
     * @param s    Game object
     * @param path Where the document is read
     */
    public void saveGameToLocalFile(Game s, String path) {
        File jsonFile = new File(path);
        try (FileWriter writer = new FileWriter(jsonFile)) {
            new GsonBuilder().create().toJson(s, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read file
     *
     * @param path Where the documents are stored
     * @return Return the List of Players
     */
    public List<Player> loadPlayersFromLocalFile(String path) {
        List<Player> players= null;
        final Type PLAYERS_LIST_TYPE = new TypeToken<List<Player>>() {
        }.getType();
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            JsonReader reader = new JsonReader(fileReader);
            players = new Gson().fromJson(reader,PLAYERS_LIST_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    /**
     * archive
     *
     * @param players    List of players
     * @param path Where the document is read
     */
    public void savePlayersToLocalFile(List<Player> players, String path) {
        File jsonFile = new File(path);
        try (FileWriter writer = new FileWriter(jsonFile)) {
            new GsonBuilder().create().toJson(players, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
