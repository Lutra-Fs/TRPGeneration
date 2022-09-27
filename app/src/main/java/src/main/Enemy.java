package src.main;

public class Enemy extends NPC {

    private final EnemyState level;
    FighterStat state;

    /**
     * @param name  NPC name
     * @param x     location x-coordinate
     * @param y     location y-coordinate
     * @param level NPC level
     * @param state NPC statement
     */
    public Enemy(String name, int x, int y, int level, FighterStat state) {
        super(name, x, y);
        this.level = EnemyState.getEnemyState(level);
        this.state = state;
    }

    /**
     * @param p player state
     */
    @Override
    void interact(Player p) {
        //Set player state to fighting
        p.p = Player.PlayerState.FIGHTING;
    }

    enum EnemyState {
        NORMAL,
        ELITE,
        BOSS;

        static EnemyState getEnemyState(int level) {
            return switch (level) {
                case 2 -> ELITE;
                case 3 -> BOSS;
                default -> NORMAL; // incl. level 1
            };
        }
    }

    EnemyState getLevel() {
        return level;
    }
}
