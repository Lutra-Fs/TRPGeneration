package src.main;

/**
 * initialize the saying for NPC
 *
 * @author Jingqi DOU
 * @author Bo ZHANG
 */
public class TalkNPC extends NPC {
    final Sentence firstSentence;

    /**
     * constructor for TalkNPC
     *
     * @param name          the name of the npc
     * @param x             the x coordinate of the npc
     * @param y             the y coordinate of the npc
     * @param firstSentence the first sentence of the npc
     * @author Jingqi DOU
     */
    TalkNPC(String name, int x, int y, Sentence firstSentence) {
        super(name, x, y);
        this.firstSentence = firstSentence;
    }

    @Override
    void interact(Player p) {
        p.p = Player.PlayerState.TALKING;
    }
}
