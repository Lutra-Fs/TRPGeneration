package src.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TalkTest {

    Talk talk;
    Player p;
    TalkNPC npc;
    List<Sentence> sentences = new ArrayList<>();

    private TalkTest() {
        Sentence sentence1 = new Sentence("hello", sentences);
        Sentence sentence2 = new Sentence("Bye", sentences);
        sentences.add(sentence1);
        sentences.add(sentence2);
        npc = new TalkNPC("bug", sentence1);
        talk = new Talk(p, npc);
    }
    @Test
    void emptySentenceTest() {
        assertEquals("hello",talk.curSentence.sentence,"Wrong sentence");
    }
    @Test
    void nextSentenceTest1(){
        boolean thrown = false;
        try {
            talk.nextSentence(2);
        } catch (GameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void nextSentenceTest2(){
        boolean thrown = false;
        try {
            talk.nextSentence("hi");
        } catch (GameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
