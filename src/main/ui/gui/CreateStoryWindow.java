//package ui.gui;
//
//import sun.applet.resources.MsgAppletViewer_es;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class CreateStoryWindow extends Window implements ActionListener {
//    private JTextField enterName;
//
//    public CreateStoryWindow() {
//        super("Create a Story", getScreenWidth() * 4 / 10, getScreenHeight() * 2 / 10);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("enterStoryName")) {
//            String storyName = enterName.getText();
//            if (!storyName.equals("")) {
//                storyName = removeSpaces(storyName);
////                WriteStoryWindow wsw = new WriteStoryWindow(saveable,storyName);
////                wsw.displayFrame();
//                frame.dispose();
//            } else {
//                errorWindow("Not a valid story name", "Invalid. Please enter a different name!");
//            }
//        }
//    }
//
//    @Override
//    protected void createFrame() {
//        GridBagConstraints constraints = new GridBagConstraints();
//        frame.add(createLabel("What would you like to call your story?"));
//        constraints.gridy = 1;
//        enterName = new JTextField(20);
//        enterName.setFont(font);
//        frame.add(enterName, constraints);
//        constraints.gridy = 2;
////        frame.add(createLabel(storyName), constraints);
//        constraints.gridy = 3;
//        frame.add(createButton("Submit", "enterStoryName", this), constraints);
//    }
//
//    // EFFECTS: removes spaces from a given string
//    private String removeSpaces(String input) {
//        StringBuilder ret = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            String character = input.substring(i, i + 1);
//            if (character.equals(" ")) {
//                ret.append("_");
//            } else {
//                ret.append(character);
//            }
//        }
//        return ret.toString();
//    }
//}
