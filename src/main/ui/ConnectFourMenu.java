package ui;

public class ConnectFourMenu extends Menu {

    @Override
    public void processCommand(String command) {

    }

    @Override
    protected void farewell() {
        System.out.println("Thanks for playing!");
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t Column 1");
        System.out.println("\t Column 2");
        System.out.println("\t Column 3");
        System.out.println("\t Column 4");
        System.out.println("\t Column 5");
        System.out.println("\t Column 6");
    }
}
