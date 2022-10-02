package src.main;

public class Talk extends Interaction {
    TalkNPC npc;
    Sentence curSentence;

    /**
     * Constructor for Talk
     *
     * @param p   the player
     * @param npc the npc
     * @author Jingqi DOU
     */
    Talk(Player p, TalkNPC npc) {
        super(p);
        this.npc = npc;
        curSentence = npc.firstSentence;
    }

    /**
     * turn to the next sentence
     * for npc to use
     *
     * @author Jingqi DOU
     */
    void nextSentence() {
        if (curSentence.nextSentences.isEmpty()) {
            interrupt();
        } else {
            curSentence = curSentence.nextSentences.get(0);
        }
    }

    /**
     * turn to the next sentence with a choice
     *
     * @param index the index of the choice
     * @throws GameException if the index is out of range
     * @author Jingqi DOU
     */
    void nextSentence(int index) throws GameException {
        if (index < curSentence.nextSentences.size()) {
            curSentence = curSentence.nextSentences.get(index);
        } else {
            throw new GameException("No such sentence");
        }
    }

    void nextSentence(String name) throws GameException {
        for (Sentence s : curSentence.nextSentences) {
            if (s.sentence.equals(name)) {
                curSentence = s;
                return;
            }
        }
        throw new GameException("No such sentence");
    }

    @Override
    void interrupt() {
        player.p = Player.PlayerState.NORMAL;
    }
}
