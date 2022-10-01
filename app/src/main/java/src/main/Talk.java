package src.main;

public class Talk extends Interaction {
    TalkNPC npc;
    Sentence curSentence;
    Talk(Player p,TalkNPC npc) {
        super(p);
        this.npc = npc;
        curSentence = npc.firstSentence;
    }
    void nextSentence() {
        if (curSentence.nextSentences.size() == 0) {
            interrupt();
        } else {
            curSentence = curSentence.nextSentences.get(0);
        }
    }
    void nextSentence(int index) throws GameException {
        if (index < curSentence.nextSentences.size()) {
            curSentence = curSentence.nextSentences.get(index);
        } else {
            throw new GameException("No such sentence");
        }
    }
    @Override
    void interrupt() {
        player.p = Player.PlayerState.NORMAL;
    }
}
