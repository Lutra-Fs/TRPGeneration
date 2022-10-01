package src.main;

public class TalkNPC extends NPC{
    final Sentence firstSentence;
    TalkNPC(String name, int x, int y, Sentence firstSentence) {
        super(name, x, y);
        this.firstSentence = firstSentence;
    }
    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.TALKING;
    }
}
