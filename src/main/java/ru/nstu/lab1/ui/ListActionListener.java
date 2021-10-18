package ru.nstu.lab1.ui;

public interface ListActionListener {

    void onAdd();

    void onInsert();

    void onRemove();

    void onSort();

    void onSave();

    void onLoad();

    void onSelectType(String type);
}
