package ru.nstu.scala.part

import ru.nstu.java.part.data.ObjectBuilderFactory
import ru.nstu.scala.part.data.MyList

object Main{
  def main(args: Array[String]): Unit = {

    val builder = ObjectBuilderFactory.getBuilder("String")
    val lst = new MyList[AnyRef]()
    println("initial")
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.add(builder.create())
    lst.forEach(println)
    lst.sort(builder.getComparator.compare)
    println("after sort")
    lst.forEach(println)
  }
}