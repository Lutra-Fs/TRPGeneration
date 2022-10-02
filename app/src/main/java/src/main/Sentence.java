package src.main;

import java.util.List;

/**
 * Sentence class is used to find and play the sentence of an NPC
 *
 * @author Jingqi DOU
 * @author Bo ZHANG
 */
public class Sentence {
    String sentence;
    List<Sentence> nextSentences;

    /**
     * constructor
     *
     * @param sentence      sentence
     * @param nextSentences next sentences
     * @author Jingqi DOU
     */
    Sentence(String sentence, List<Sentence> nextSentences) {
        this.sentence = sentence;
        this.nextSentences = nextSentences;
    }
}
