package com.siyar.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    List<Weapon> weaponList = new ArrayList<>();

    public void shopForItems(Player player) {

        System.out.println("******* WELCOME TO SHOP ********\nPleaze Select a Weapon for purchase :\n" + getWeaponList());

        boolean isShopping = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Players currency: " + player.getCurrency());
            System.out.println("""
                     1 - Cleaver   - price: 50
                     2 - Knife - price: 70
                     3 - Spear   - price: 200
                     4 - Exit shop
                     ********************************""");

            try {

                switch (scanner.next()) {
                    case "1" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(0).getPrice() <= 0 ) {
                            System.out.println("No Money to purchase this item.");
                        } else {
                            System.out.println("You bought an Cleaver for " + weaponList.get(0).getPrice());
                            player.setCurrency(player.getCurrency() - weaponList.get(0).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(0));
                        }

                    }

                    case "2" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(1).getPrice() <= 0 ) {
                            System.out.println("No Money to purchase this item.");
                        } else {
                            System.out.println("You bought a Knife for " + weaponList.get(1).getPrice());
                            player.setCurrency(player.getCurrency() - weaponList.get(1).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(1));
                        }
                    }

                    case "3" -> {
                        if (checkIfOutOfCurrency(player)) {
                            isShopping = false;
                        } else if ( player.getCurrency() - weaponList.get(2).getPrice() <= 0 ) {
                            System.out.println("No Money to purchase this item.");
                        } else {
                            System.out.println("You bought a Spear for " + weaponList.get(2).getPrice());
                            player.setCurrency(player.getCurrency() - weaponList.get(2).getPrice());
                            player.getCurrentWeapon().add(weaponList.get(2));
                        }
                    }

                    case "4" -> {
                        System.out.println("Exiting shop.");
                        isShopping = false;
                    }

                    default -> System.out.println("Please choose an item.");
                }

            } catch (Exception e) {

                scanner.next();
                System.out.println("Try Again!");
            }


        } while (isShopping);

    }

    public boolean checkIfOutOfCurrency(Player player) {

        if (player.getCurrency() <= 0) {
            System.out.println("No money! Thank you, come again!.");
            return true;
        } else {
            return false;
        }

    }

    public void addWeaponToList() {

        weaponList.add(new Cleaver("Cleaver",15,50));
        weaponList.add(new Knife("Knife",10,70));
        weaponList.add(new Spear("Spear",30,200));
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

}