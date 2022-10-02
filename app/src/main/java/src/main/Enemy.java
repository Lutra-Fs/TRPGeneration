package src.main;

/**
 * Enemy class is used to create enemy in the game
 *
 * @author Bo ZHANG
 * @author Juhao Tao
 */
public class Enemy extends NPC {

    private final EnemyState level;
    FighterStat state;

    /**
     * constructor for Enemy
     *
     * @param name  NPC name
     * @param x     location x-coordinate
     * @param y     location y-coordinate
     * @param level NPC level
     * @param state NPC statement
     * @author Juhao Tao
     * @author Bo ZHANG
     */
    public Enemy(String name, int x, int y, int level, FighterStat state) {
        super(name, x, y);
        this.level = EnemyState.getEnemyState(level);
        this.state = state;
    }

    @Override
    void interact(Player p) {
        //Set player state to fighting
        p.p = Player.PlayerState.FIGHTING;
    }

    /**
     * get the level of enemy
     *
     * @return level
     * @author Bo ZHANG
     */
    EnemyState getLevel() {
        return level;
    }

    enum EnemyState {
        NORMAL,
        ELITE,
        BOSS;

        /**
         * get current state for the enemy
         *
         * @param level level of enemy
         * @return EnemyState
         * @author Bo ZHANG
         */
        static EnemyState getEnemyState(int level) {
            return switch (level) {
                case 2 -> ELITE;
                case 3 -> BOSS;
                default -> NORMAL; // incl. level 1
            };
        }
    }
}
