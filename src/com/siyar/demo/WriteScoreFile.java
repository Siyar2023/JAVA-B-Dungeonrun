package com.siyar.demo;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;

import java.io.FileWriter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Enclosed.class)
public class WriteScoreFile {

    Player player = new Player();

    public void writeScoreFile() {

        try {
            FileWriter playerFileWriter = new FileWriter("Player_Results.txt");

            playerFileWriter.write(player.getPlayerName() +
                    "\nLevel reached: " + player.getLevel() +
                    "\nWeapon used: " + player.getCurrentWeapon() +
                    "\nMonsters defeated: " + player.getMonstersKilled()
            );
            playerFileWriter.close();
            System.out.println(" Check the text-file named 'Player_Results.txt' to see final score stats,");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No Working");
        }

    }

    public static class PlayerTests {

        Player player;

        @BeforeEach // @BeforeEach
        void setup() {
            player = new Player();
        }

        @Test
        @DisplayName("Checking to see if the right amount of damage from player is returned")
        public void calculateDamageTest() {

            assertTrue (
                    player.getMinDamage() <= player.calculateDamage() &&
                            player.getMaxDamage() >= player.calculateDamage());
        }

        @Test
        @DisplayName ("Checking if players level increases when maxExperience is reached")
        public void checkIfLeveledUpTest() {
            player.setExperience(player.getMaxExperience());
            player.checkIfLevelUp();

            assertEquals(2, player.getLevel());
        }

        @Test
        @DisplayName("Testing if player can lose the game.")
        public void isAliveTest() {
            player.setHealth(0);

            assertFalse(player.isAlive());
        }

        @RepeatedTest(5)
        @DisplayName("Check if the random amount of exp is added to players experience")
        public void experienceAmountXTest() {
            Random random = new Random();
            player.setExperience(19);

            int x = random.nextInt(1, 10);
            System.out.println(x);
            player.setExperience(player.getExperience() + x);
            player.checkIfLevelUp();

        }
    }
}
