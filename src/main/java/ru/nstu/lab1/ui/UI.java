package ru.nstu.lab1.ui;

import ru.nstu.lab1.data.List;
import ru.nstu.lab1.data.TypeBuilderFactory;
import ru.nstu.lab1.data.builder.TypeBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UI extends JFrame implements ListActionListener {
    private static final String filename = "file.dat";

    private final List<Object> items = new List<>();
    private final DefaultListModel<Object> listModel = new DefaultListModel<>();
    private final JList<Object> jList = new JList<>(listModel);
    private TypeBuilder builder;

    public UI() throws HeadlessException {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(jList));
        container.add(listPanel, BorderLayout.CENTER);

        JPanel center = new JPanel();
        container.add(center, BorderLayout.EAST);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JPanel chooseType = new JPanel();
        center.add(chooseType);
        JComboBox<String> comboBox = new JComboBox<>(TypeBuilderFactory.getAllTypes().toArray(new String[0]));
        chooseType.add(comboBox);
        comboBox.addActionListener(e -> {
            JComboBox source = (JComboBox) e.getSource();
            String selectedItem = (String) source.getSelectedItem();
            builder = TypeBuilderFactory.getBuilder(selectedItem);
            center.add(new UIAction(this::onAdd, "Add element"));
            center.add(new UIAction(this::onRemove, "Remove element"));
            center.add(new UIAction(this::onInsert, "Insert element"));
            center.add(new UIAction(this::onSort, "Sort"));
            center.add(new UIAction(this::onSave, "Save"));
            center.add(new UIAction(this::onLoad, "Load"));
            center.remove(chooseType);
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
    public void onAdd() {
        Object data = builder.create();
        items.add(data);
        listModel.addElement(data);
    }

    @Override
    public void onInsert() {
        Object data = builder.create();
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
        try (OutputStream os = new FileOutputStream(filename)) {
            items.forEach(item -> builder.save(item, os));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        while (items.size() != 0) {
            items.remove(0);
        }
        listModel.clear();
        try (InputStream is = new FileInputStream(filename)) {
            Object loaded;
            while ((loaded = builder.load(is)) != null) {
                items.add(loaded);
                listModel.addElement(loaded);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelectType(String type) {
        try {
            while (items.size() != 0) {
                items.remove(0);
            }

            builder = TypeBuilderFactory.getBuilder(type);

        } catch (Exception ignored) {

        }
    }

}
