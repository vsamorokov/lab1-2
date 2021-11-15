package ru.nstu.java.part.ui;

import ru.nstu.java.part.data.ListUtils;
import ru.nstu.java.part.data.ObjectBuilderFactory;
import ru.nstu.java.part.data.builder.ObjectBuilder;
import ru.nstu.java.part.data.List;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class UI extends JFrame implements ListActionListener {
    private static final String filename = "file.dat";

    private List<Object> items = new List<>();
    private final DefaultListModel<Object> listModel = new DefaultListModel<>();
    private final JList<Object> jList = new JList<>(listModel);
    private ObjectBuilder builder;

    public UI() throws HeadlessException {
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
            builder = ObjectBuilderFactory.getBuilder(selectedItem);
            right.add(new UIAction(this::onAdd, "Add element"));
            right.add(new UIAction(this::onRemove, "Remove element"));
            right.add(new UIAction(this::onInsert, "Insert element"));
            right.add(new UIAction(this::onSort, "Sort"));
            right.add(new UIAction(this::onSave, "Save"));
            right.add(new UIAction(this::onLoad, "Load"));
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


    @Override
    public void onAdd(String text) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data);
        listModel.addElement(data);
    }

    @Override
    public void onInsert(String text) {
        if (text.isEmpty()) return;
        Object data = builder.createFromString(text);
        items.add(data, jList.getSelectedIndex());
        listModel.add(jList.getSelectedIndex(), data);
    }

    @Override
    public void onRemove() {
        items.remove(jList.getSelectedIndex());
        listModel.remove(jList.getSelectedIndex());
    }

    @Override
    public void onSort() {
        items.sort(builder.getComparator());
        listModel.clear();
        items.forEach(listModel::addElement);
    }

    @Override
    public void onSave() {
        try {
            ListUtils.saveToFile(filename, items, builder);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write list to a file");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        try {
            items = ListUtils.loadFromFile(filename, builder);
            listModel.clear();
            items.forEach(listModel::addElement);
        } catch (Exception e) {
            System.err.println("Unable to read list from a file");
            e.printStackTrace();
        }
    }

    @Override
    public void onSelectType(String type) {
        try {
            while (items.size() != 0) {
                items.remove(0);
            }

            builder = ObjectBuilderFactory.getBuilder(type);

        } catch (Exception ignored) {

        }
    }

}
