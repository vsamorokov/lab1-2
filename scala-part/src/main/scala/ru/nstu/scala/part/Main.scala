package ru.nstu.scala.part

import ru.nstu.java.part.data.ObjectBuilderFactory
import ru.nstu.java.part.ui.UI
import ru.nstu.scala.part.data.MyList
import ru.nstu.scala.part.ui.ListActionListenerImpl

object Main{
  def main(args: Array[String]): Unit = {

    val impl = new ListActionListenerImpl()
    new UI(impl)

    val builder = ObjectBuilderFactory.getBuilder("String")
    val lst = new MyList[AnyRef]()
    println("initial")
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.forEach(println)
    lst.sort(builder.getComparator)
    println("after sort")
    lst.forEach(println)
  }
}