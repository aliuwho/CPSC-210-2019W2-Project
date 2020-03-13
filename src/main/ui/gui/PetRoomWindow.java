package ui.gui;

import persistence.Saveable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetRoomWindow extends Window implements ActionListener {
    private Saveable saveable;

    public PetRoomWindow(Saveable saveable) {
        super("Pet Room", getScreenWidth() * 5 / 10, getScreenHeight() * 5 / 10);
        this.saveable = saveable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void createFrame() {

    }
}
