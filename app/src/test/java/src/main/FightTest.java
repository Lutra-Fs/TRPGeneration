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
    void addHPTest1() {
        fighterStat.addHP(10);
        assertEquals(10, fighterStat.getHp(), "incorrect add HP");
    }

    @Test
    void addHPTest2() throws GameException {
        fighterStat.beAttacked(skill2);
        fighterStat.addHP(5);
        assertEquals(7, fighterStat.getHp(), "incorrect add HP");
    }

    @Test
    void addMPTest1() throws GameException {
        fighterStat.beAttacked(skill3);
        fighterStat.addMP(5);
        assertEquals(9, fighterStat.getMp(), "incorrect MP");
    }

    @Test
    void MPCostTest() throws GameException {
        fighterStat.beAttacked(skill3);
        assertEquals(4, fighterStat.getMp(), "incorrect MP");
    }

    @Test
    void beAttackTest() throws GameException {
        fighterStat.beAttacked(skill2);
        assertEquals(2, fighterStat.getHp(), "incorrect HP");
    }

    @Test
    void beAttackExceptionTest() {
        boolean thrown = false;
        try {
            fighterStat.beAttacked(skill1);
        } catch (GameException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void isDeadTest() throws GameException {
        fighterStat.beAttacked(skill3);
        assertTrue(fighterStat.isDead());
    }

}
