package src.main;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FightTest {
    List<Skill> skill = new ArrayList<>();
    Skill skill1;
    Skill skill2;
    Skill skill3;

    private FightTest() {
        skill1 = new Skill("base_attack", 5, 2);
        skill2 = new Skill("pouch_attack", 8, 4);
        skill3 = new Skill("ice_attack", 10, 6);
        skill.add(skill1);
        skill.add(skill2);
        skill.add(skill3);

    }

    FighterStat fighterStat = new FighterStat(10, 10, 10, 6, 0, 10, 15, skill);

    @Test
    void getSkillListNameTest() {
        assertEquals("base_attack", fighterStat.getSkills().get(0).getName(), "Incorrect skill name ");

    }
    @Test
    void getSkillListAtkTest() {
        assertEquals(8, fighterStat.getSkills().get(1).getAtk(), "Incorrect skill attack ");
    }

    @Test
    void getSkillTest() {
        assertEquals(skill1, fighterStat.getSkill(0), "Incorrect skill");
    }

    @Test
    void levelUpTest1() throws GameException {
        fighterStat.setExp(15);
        fighterStat.levelUp();
        assertEquals(1, fighterStat.getFighterLevel(), "incorrect level");
    }

    @Test
    void levelUpTest2() {
        boolean thrown = false;
        try {
            fighterStat.levelUp();
        } catch (GameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void setHPTest() {
        fighterStat.setHp(20);
        assertEquals(10, fighterStat.getHp(), "incorrect add HP");
    }

    @Test
    void setMPTest(){
        fighterStat.setMp(15);
        assertEquals(10, fighterStat.getMp(), "incorrect MP");
    }

    @Test
    void resetHPTest(){
        fighterStat.setHp(5);
        fighterStat.resetHP();
        assertEquals(10, fighterStat.getHp(), "incorrect reset HP");
    }
    @Test
    void resetMPTest(){
        fighterStat.setMp(5);
        fighterStat.resetMP();
        assertEquals(10, fighterStat.getMp(), "incorrect reset MP");
    }

    @Test
    void beAttackTest(){
        fighterStat.beAttacked(skill2);
        assertEquals(3, fighterStat.getHp(), "incorrect remain HP, check atk calculation");
    }

    @Test
    void isDeadTest(){
        fighterStat.setHp(-1);
        assertTrue(fighterStat.isDead());
    }
    @Test
    void level0CalcSkillAtkTest() {
        Skill s = fighterStat.calcSkillATK(skill3);
        assertEquals(10, s.getAtk(), "incorrect atk calculation");
    }
    @Test
    void level1CalcSkillAtkTest() {
        fighterStat.fighterLevel = 1;
        Skill s = fighterStat.calcSkillATK(skill3);
        assertEquals(20, s.getAtk(), "incorrect atk calculation");
    }
    @Test
    void getSkillByNameTest(){
        boolean thrown = false;
        try {
            fighterStat.getSkillByName("wqeqwe");
        } catch (GameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
