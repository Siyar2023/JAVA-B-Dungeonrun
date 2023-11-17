//MonsterEncounter
package com.siyar.demo;

import java.util.Scanner;

import static com.siyar.demo.Monster.random;

public class MonsterEncounter {


    Scanner scanner = new Scanner(System.in);

    public void monsterBattle(Player player, Monster monster) {

        do {
            System.out.println("""
                What do you want to do next?
                1: Attack
                2: Check health status
                3: Flee
                4: Exit The Game"""
            );

            try {

                switch (scanner.nextInt()) {
                    case 1 -> {
                        monster.defend(player);

                        if (monster.isAlive()) {
                            player.defend(monster);
                        } else {
                            player.setMonstersKilled(player.getMonstersKilled() + 1);
                            System.out.println("Congratulation! You killed the monster.\nReturning to main menu.");

                        }

                    }

                    case 2 -> System.out.println("\nPlayer health: " + player.getHealth() +
                            "\nMonster health: " + monster.monsterStats() + "\n"
                    );

                    case 3 -> {
                        double fleeChance = random.nextDouble();

                        if (fleeChance <= 0.5) {
                            System.out.println("SUCCESS! You managed to Escape.");

                        }

                        else {
                            System.out.println("FAIL! You didn't manage to Escape.");

                        }

                        if (player.playerDidFlee()) {
                            monster.setHealth(0);


                        }
                    }
                    case 4 -> {

                        System.out.println("Exiting The Game. Welcome Back Again");
                        System.exit(0);
                    }
                    default -> System.out.println("Plz Try Again.");

                }
            } catch (Exception e) {

                scanner.next();
                System.out.println("Only number!. Try again.");
            }

        } while (player.isAlive() && monster.isAlive());

    }
}
