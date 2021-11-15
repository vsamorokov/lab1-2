package ru.nstu.scala.part.ui

import ru.nstu.java.part.data.{IList, List, ListUtils}
import ru.nstu.java.part.ui.AbstractListActionListener
import ru.nstu.scala.part.data.MyList

import java.io.FileNotFoundException

class ListActionListenerImpl extends AbstractListActionListener {

  var items: IList[Object] = new MyList[Object]

  override def onAdd(text: String): Unit = {
    if (text.isEmpty) return
    val value = builder.createFromString(text)
    items.add(value)
    listModel.addElement(value)
  }

  override def onInsert(text: String, index: Int): Unit = {
    if (text.isEmpty) return
    val value = builder.createFromString(text)
    items.add(value, index)
    listModel.add(index, value)
  }

  override def onRemove(index: Int): Unit = {
    items.remove(index)
    listModel.remove(index)
  }

  override def onSort(): Unit = {
    items.sort(builder.getComparator)
    listModel.clear()
    items.forEach(el => listModel.addElement(el))
  }

  override def onSave(): Unit = {

    try ListUtils.saveToFile(filename, items, builder)
    catch {
      case e: FileNotFoundException =>
        System.err.println("Unable to write list to a file")
        e.printStackTrace()
    }
  }

  override def onLoad(): Unit = {
    try {
      items = ListUtils.loadFromFile(filename, builder, new List[AnyRef])
      listModel.clear()
      items.forEach(el => listModel.addElement(el))
    } catch {
      case e: Exception =>
        System.err.println("Unable to read list from a file")
        e.printStackTrace()
    }
  }
}
