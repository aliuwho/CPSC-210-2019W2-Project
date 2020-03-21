package ui;

import ui.gui.UsernameWindow;

/**
 * used to run the class
 */
public class Main {
    public static void main(String[] args) {
        // Pair p = new Pair("dog", "./data/tobs.jpg");
        // p.paint();
        // System.out.println(p.getWord());
//        Scanner input = new Scanner(System.in);
//        MainMenu m = new MainMenu(input);
//        m.runApp();
        UsernameWindow user = new UsernameWindow();
        user.displayFrame();

//        MainMenuWindow test = new MainMenuWindow(new Saveable());
    }

}
