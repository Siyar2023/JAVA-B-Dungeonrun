package com.siyar.demo;

import java.util.Scanner;

import static com.siyar.demo.Monster.newMonsterEncounter;

public class Game {

    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    Shop shop = new Shop();
    MonsterEncounter monsterEncounter = new MonsterEncounter();
    WriteScoreFile playerStats = new WriteScoreFile();


    public void mainMenu() {

        player.setPlayerName(scanner.nextLine());
        shop.addWeaponToList();
        boolean continueGame;

        do {
            continueGame = true;

            System.out.println("\nPlayer: " + player.getPlayerName() +
                    "\n Main Menu" +
                    "\n 1 - Fight a Monster" +
                    "\n 2 - Player status" +
                    "\n 3 - Shop" +
                    "\n 4 - Exit"
            );

            try {

                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.println("""
                                Select the Monster you want to fight?
                                1 - Spider (Easy)
                                2 - Cobra  (Normal)
                                3 - Dragon (Hard)
                                4 - Kraken (Very Hard)""");

                        try {

                            int Monster = scanner.nextInt();
                            System.out.println("\nYou selected monster: " + Monster);


                            monsterEncounter.monsterBattle(player, newMonsterEncounter(Monster));

                            if (player.isAlive()) {
                                player.setHealth(player.getMaxHealth());
                                player.setExperience(player.getExperience() + 15);
                                player.checkIfLevelUp();
                                player.setCurrency(player.getCurrency() + 20);
                            } else {
                                continueGame = false;
                            }

                        } catch (Exception e) {

                            scanner.next();
                            System.out.println("You can only choose from the available numbers");
                        }

                    }

                    case 2 -> {

                        if (player.currentWeapon.size() == 1) {
                            System.out.println("Player current status:\n" + player.playerStats());
                            System.out.println("Current weapon:\n" + player.getCurrentWeapon());
                        } else {
                            System.out.println("Player current status:\n" + player.playerStats());
                        }

                    }

                    case 3 -> shop.shopForItems(player);

                    case 4 -> {
                        System.out.println("Exiting the  Game");

                        if (player.currentWeapon.size() == 1) {
                            playerStats.writeScoreFile();
                        }

                        continueGame = false;
                    }

                    default -> System.out.println("Plz Choose an available action!.");
                }

            } catch (Exception e) {

                scanner.next();
                System.out.println("Only number. Try again.");
            }

        } while (continueGame);

        System.out.println(" Game Over!");

    }
}