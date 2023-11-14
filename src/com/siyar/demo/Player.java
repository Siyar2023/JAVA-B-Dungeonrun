package com.siyar.demo;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import static com.siyar.demo.Colors.*;

public class Player implements ICombat{

    private static final String GREEN = "Green";

    Random random = new Random();

    // Player stats
    private String playerName;
    private int strength = 0;
    private int intelligence = 0;
    private int agility = 0;
    private int health = 50;
    private int maxHealth = 50;
    private int level = 1;
    private int minDamage = 5;
    private int maxDamage = 10;
    private int currency = 0;
    private int experience = 0;
    private int maxExperience = 20;
    private int monstersDestroyed = 0;

    List<Weapon> currentWeapon = new ArrayList<>();

    @Override
    public boolean isAlive() {
        return getHealth() > 0;
    }

    public void checkIfLevelUp() {

        if (experience >= maxExperience) {
            System.out.println("\n  LEVEL UP.\nYou Rock! You increased your stats.");

            level += 1;
            experience = 0;
            maxExperience += 15;
            maxHealth += 10;
            health = maxHealth;
            strength += 2;
            intelligence += 2;
            agility += 2;
            minDamage += 1;
            maxDamage += 1;
        }
    }

    @Override
    public int fight() {
        return calculateDamage();
    }

    @Override
    public int calculateDamage() {

        int damageDone = 0;
        int noWeaponDamage = ((random.nextInt(minDamage, maxDamage)) + strength);

        if (currentWeapon.size() == 0) {

            if ( intelligence >= random.nextInt(1,40) ) {
                damageDone += noWeaponDamage * 2;
            }else {
                damageDone += noWeaponDamage;
            }
        } else {
            if ( intelligence >= random.nextInt(1,40) ) {
                damageDone += ((noWeaponDamage + currentWeapon.get(0).getDamage()) * 2);
            }else {
                damageDone += noWeaponDamage + currentWeapon.get(0).getDamage();
            }

        }

        return damageDone;
    }

    public void defend(Monster monster) {

        if (playerDidDodge()) {
            System.out.println("You dodged the monsters attack.");
        } else {
            int damageTaken = monster.fight();

            System.out.println("You took " + damageTaken + " damage.");
            health -= damageTaken;

            if (health <= 0) {
                System.out.println("YOU ARE DEAD! GAME OVER!  " + monster.getName() + " Win the Game.");
            }
        }

    }

    public boolean playerDidDodge() {

        boolean didDodge;
        int dodge = random.nextInt(1,50);

        didDodge = dodge >= 1 && dodge <= getAgility();

        return didDodge;
    }

    public boolean playerDidFlee() {

        return random.nextInt(10) == 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getAgility() {
        return agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        checkIfLevelUp();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public int getMonstersKilled() {
        return monstersDestroyed;
    }

    public void setMonstersKilled(int monstersKilled) {
        this.monstersDestroyed = monstersKilled;
    }


    public List<Weapon> getCurrentWeapon() {
        return currentWeapon;
    }

    public String playerStats() {
        return  "Player name = " + playerName + RESET + RED +
                ", strength = " + strength + RESET + RED +
                ", intelligence = " + intelligence + RESET + BLUE +
                ", agility = " + agility + RESET + RED +
                ", health = " + health + RESET + YELLOW +
                ",\nlevel = " + level + RESET + PURPLE +
                ", minDamage = " + minDamage + RESET + YELLOW +
                ", maxDamage = " + maxDamage + RESET + RED +
                ", currency = " + currency + RESET + BLUE +
                ", experience = " + experience + RESET +
                '}';
    }

}