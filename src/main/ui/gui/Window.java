package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

// creates a Window for the GUI
public abstract class Window {
    //    private String name;
//    private int width;
//    private int height;
//    protected int screenWidth;
//    protected int screenHeight;
    protected JFrame frame;
    public static final int FONT_SIZE = 16;
    public static final String FONT_NAME = "Comic Sans MS";
    public static final int FONT_TYPE = Font.PLAIN;
    protected Font font;

    //EFFECTS: creates a new window
    public Window(String name, int width, int height) {
//        this.width = width;
//        this.height = height;
        frame = new JFrame(name);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        font = new Font(FONT_NAME, FONT_TYPE, FONT_SIZE);

    }

    // EFFECTS: builds frame with components
    public abstract void createFrame();

    // EFFECTS: displays frame
    protected void displayFrame() {
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // EFFECTS: creates button
    public JButton createButton(String text, String command, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setActionCommand(command);
        button.addActionListener(actionListener);
        return button;
    }

    // EFFECTS: returns screen width
    public static int getScreenWidth() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.width;
    }

    // EFFECTS: returns screen height
    public static int getScreenHeight() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }
}
