package com.siyar.demo;

import java.util.Random;

public class Monster implements ICombat{

    static Random random = new Random();

    private final String name;
    private int health;
    private final int minBaseDamage;
    private final int maxBaseDamage;


    public static Monster newMonsterEncounter(int Monster) {


        if (Monster == 1) {
            return new Monster("Spider",30,2,5);
        } else if (Monster == 2) {
            return new Monster("Cobra",70,4,10);
        } else if (Monster == 3) {
            return new Monster("Dragon",100,10,15);
        } else {
            return new Monster("Kraken",300,20,30);
        }

    }

    public Monster(String name, int health, int minBaseDamage, int maxBaseDamage) {
        this.name = name;
        this.health = health;
        this.minBaseDamage = minBaseDamage;
        this.maxBaseDamage = maxBaseDamage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int fight() {
        return calculateDamage();
    }

    @Override
    public int calculateDamage() {
        return random.nextInt(minBaseDamage, maxBaseDamage + 1);
    }

    public void defend(Player player) {

        int damageTaken = player.fight();
        health -= damageTaken;
        System.out.println("\nMonster took " + damageTaken + " damage.");

    }


    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public String monsterStats() {
        return "Monster{" +
                "name = '" + name + '\'' +
                ", health = " + health +
                '}';
    }

}