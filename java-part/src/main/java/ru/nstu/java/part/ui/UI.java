package ru.nstu.java.part.ui;

import ru.nstu.java.part.data.ObjectBuilderFactory;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private final JList<Object> jList;

    public UI(ListActionListener actionListener) throws HeadlessException {
        this.jList = new JList<>(actionListener.getListModel());
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(jList));
        container.add(listPanel, BorderLayout.CENTER);

        JPanel right = new JPanel();
        container.add(right, BorderLayout.EAST);

        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        JPanel chooseType = new JPanel();
        right.add(chooseType);
        JComboBox<String> comboBox = new JComboBox<>(ObjectBuilderFactory.getAllTypes().toArray(new String[0]));
        chooseType.add(comboBox);
        comboBox.addActionListener(e -> {
            JComboBox source = (JComboBox) e.getSource();
            String selectedItem = (String) source.getSelectedItem();
            actionListener.onSelectType(selectedItem);
            right.add(new UIAction(actionListener::onAdd, "Add element"));
            right.add(new UIAction(() -> actionListener.onRemove(jList.getSelectedIndex()), "Remove element"));
            right.add(new UIAction((text) -> actionListener.onInsert(text, jList.getSelectedIndex()), "Insert element"));
            right.add(new UIAction(actionListener::onSort, "Sort"));
            right.add(new UIAction(actionListener::onSave, "Save"));
            right.add(new UIAction(actionListener::onLoad, "Load"));
            right.remove(chooseType);
            revalidate();
            repaint();
        });

        setTitle("Lab");
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
