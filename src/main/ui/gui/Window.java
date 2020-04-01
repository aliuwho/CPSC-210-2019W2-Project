package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
    protected final Font font;

    //EFFECTS: creates a new window with a given name and size and a grid bag layout
    public Window(String name, int width, int height) {
//        this.width = width;
//        this.height = height;
        frame = new JFrame(name);
        frame.setPreferredSize(new Dimension(width, height));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        font = new Font(FONT_NAME, FONT_TYPE, FONT_SIZE);
        frame.setLayout(new GridBagLayout());
//        displayFrame();

    }

    // EFFECTS: builds frame with components
    protected abstract void createFrame();

    // EFFECTS: displays frame
    public void displayFrame() {
        createFrame();
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    // EFFECTS: creates button
    protected JButton createButton(String text, String command, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setActionCommand(command);
        button.addActionListener(actionListener);
        return button;
    }

    // EFFECTS: creates label with text
    protected JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    // EFFECTS: creates label with image
    protected JLabel createLabel(ImageIcon img) {
        JLabel label = new JLabel(img);
        label.setFont(font);
        return label;
    }

//    // EFFECTS: creates label with image and text
//    protected JLabel createLabel(String text, ImageIcon img) {
//        JLabel label = new JLabel(text, img, JLabel.CENTER);
//        label.setFont(font);
//        return label;
//    }

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

    // EFFECTS: sets frame visibility
//    public void setVisibility(boolean visibility) {
//        frame.setVisible(visibility);
//    }

    // EFFECTS: a simple error notice pops up
    public void errorWindow(String title, String message) {
        frame = new JFrame(title); // this be causing problems.
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setLocation(getScreenWidth() / 2, getScreenHeight() / 2);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel temp = new JLabel("");
        frame.getContentPane().add(temp, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, message);
        frame.dispose();
    }

    /**
     * taken from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     **/
    // EFFECTS: rescales srcImage to a width of w and height of h
    protected Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

}
