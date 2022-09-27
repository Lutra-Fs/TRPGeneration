package src.main;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.AsynchronousFileChannel;

public class SaveParser {
    /**
     * read file
     * @param path Where the documents are stored
     * @return Return the Game object
     */
    public static Game saveFoldertoStatus(String path){
        Game game = new Game();
        try {
            File jsonFile = new File("storage/sdcard/MyIdea/MyCompositions/" + path + ".json");
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            JSONObject jsonObject = new JSONObject(jsonStr);
            game = (Game)jsonObject.get(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }


    /**
     * archive
     * @param s Game object
     * @param path  Where the document is read
     */
    public static void statustoSaveFolder(Game s,String path){
        JSONObject jsonObject = new JSONObject();
        try {
            //Create JSON format data with path as KEY Game as value
            jsonObject.put(path,s);
            //file path
            File file = new File("storage/sdcard/MyIdea/MyCompositions/" + path + ".json");
            //Write data to JSON file
            Writer output = null;
            output = new BufferedWriter(new FileWriter(file));
            output.write(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
