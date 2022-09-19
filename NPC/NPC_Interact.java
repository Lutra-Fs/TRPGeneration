package NPC;
import java.util.Scanner;

public class NPC_Interact {
    Scanner myScanner = new Scanner(System.in);
    int playerHP;
    String playerWeapon;
    int choice;
    String reply;
    int enemyHP;
    int Gold;

    //Attack methods//
    public void fight() {
        System.out.println("\n-------------------------------\n");
        System.out.println("Your HP: " + playerHP);
        System.out.println("Enemy HP: " + enemyHP);
        System.out.println("\n1: Attack");
        System.out.println("2: Run");
        System.out.println("\n-------------------------------\n");

        choice = myScanner.nextInt();

        if (choice == 1) {
            ChooseWeapon();
            attack();
        } else if (choice == 2) {
            //Go back
        } else {
            fight();
        }
    }
    public void ChooseWeapon() {
        System.out.println("\n-------------------------------\n");
        System.out.println("Choose your weapon");
        System.out.println("Weapon list");
        //Show backpack weapon list

        System.out.println("\n-------------------------------\n");

        choice = myScanner.nextInt();
        if (choice == 1) {
            playerWeapon = "Knife";
        } else if (choice == 2) {
            playerWeapon = "Long Sword";
        } else if (choice == 3) {
            playerWeapon = "Axe";
        }
    }
    public void attack() {
        int playerDamage = 0;

        if (playerWeapon.equals("Knife")) {
            playerDamage = new java.util.Random().nextInt(3);
        } else if (playerWeapon.equals("Long Sword")) {
            playerDamage = new java.util.Random().nextInt(9);
        } else if (playerWeapon.equals("Axe")) {
            playerDamage = new java.util.Random().nextInt(6);
        }

        System.out.println("You attacked the enemy and gave " + playerDamage + " damage!");
        enemyHP = enemyHP - playerDamage;
        System.out.println("Enemy HP: " + enemyHP);

        if (enemyHP < 1) {
            win();
        } else if (enemyHP > 1) {
            int EnemyDamage = new java.util.Random().nextInt(4);
            System.out.println("The enemy attacked you and gave " + EnemyDamage + " damage!");
            playerHP = playerHP - EnemyDamage;
            System.out.println("Player HP: " + playerHP);
            if (playerHP < 1) {
                dead();
            } else if (playerHP > 1) {
                fight();
            }
        }

    }
    public void dead() {
        System.out.println("\n-------------------------------\n");
        System.out.println("You are dead!!!");
        System.out.println("\n\nGAME OVER");
        System.out.println("\n-------------------------------\n");

        //GO back

    }
    public void win() {
        System.out.println("\n-------------------------------\n");
        System.out.println("You killed the Enemy and collect 1 gold");
        System.out.println("\n-------------------------------\n");
        Gold += 1;
        //GO back
    }

    //Talk methods//
    public void talk() {
        System.out.println("\n-------------------------------\n");
        System.out.println("\nHello, would like to trade for something");
        System.out.println("Yes or No");
        System.out.println("\n-------------------------------\n");
        reply = myScanner.nextLine().toLowerCase();

        if (reply.equals("yes")) {
            Trading();
        } else if (reply.equals("no")) {
            //Go back
        } else {
            talk();
        }
    }

    public void Trading() {
        System.out.println("\n-------------------------------\n");
        System.out.println("Which one would you like");
        System.out.println("1: heal");
        System.out.println("2: Long Sword");
        System.out.println("3: Axe");
        System.out.println("4: Leave");
        System.out.println("\n-------------------------------\n");
        choice = myScanner.nextInt();

        if (choice == 1) {
            playerHP+=1;
            Trading();
        } else if (choice == 2) {
            //backpack add Long Sword
        } else if (choice == 3) {
            //backpack add Axe
        } else if (choice == 4) {
            //Go back
        }else {
            Trading();
        }
    }
}
