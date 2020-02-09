package ui;

import java.util.Scanner;

public class PetSelectMenu extends Menu {
    @Override
    public void processCommand(String command) {

    }

    @Override
    protected void displayMenu() {
        notReady();
    }

    @Override
    protected void farewell() {
        System.out.println("You have selected a pet!");
    }
}
