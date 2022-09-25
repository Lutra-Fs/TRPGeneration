public class Enemy {

    private int level;
    FighterStat state;

    public Enemy(int level, FighterStat state) {
        this.level = level;
        this.state = state;
    }

    public FighterStat getState() {
        return state;
    }

    public void setState(FighterStat state) {
        this.state = state;
    }

    public int getEnemyLevel() {
        return level;
    }

    public void setEnemyLevel(int enemyLevel) {
        this.level = enemyLevel;
    }


}
