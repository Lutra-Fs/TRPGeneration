package src.main;

import java.util.List;

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
