package src.main;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SaveParserTest {
    Player testPlayer;
    Level testLevel;
    Game testPath = new Game(testPlayer, testLevel);
    @Test
    void testName() throws IOException {
        SaveParser saveUnderTest = new SaveParser(testPlayer);
        testPath.player.name = "testName1";
        String saveName = SaveParser.createSaveFile(testPath);
        SaveParser.loadSave("saveName", testPlayer, testLevel);
        assertEquals(testPath.player.name, "testName1", "Incorrect name");
    }

    @Test
    void testLevel() throws IOException {
        SaveParser saveUnderTest = new SaveParser(testPlayer);
        testPath.level = testLevel;
        String saveName = SaveParser.createSaveFile(testPath);
        SaveParser.loadSave("saveName", testPlayer, testLevel);
        assertEquals(testPath.level, 1, "Incorrect Level");
    }

}
