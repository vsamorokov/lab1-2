package ru.nstu.lab1.ui;

import javax.swing.*;

public class UIAction extends JPanel {

    public UIAction(EmptyConsumer action, String buttonText) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> action.accept());
        add(button);
    }
}
