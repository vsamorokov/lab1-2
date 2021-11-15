package ru.nstu.scala.part.data

import ru.nstu.java.part.data.{Action, Comparator, IList}

class MyList[T] extends IList[T]{

  private var head: Node = _
  private var tail: Node = _
  private var length: Int = 0

  def add(data: T): Unit = {
    if (head == null) {
      head = new Node(data)
      tail = head
      length += 1
      return
    }
    val newTail = new Node(data)
    newTail.prev = tail
    tail.next = newTail
    tail = newTail
    length += 1
  }

  def get(index: Int): T = {
    getNode(index).data
  }

  def forEach(action: T => Unit): Unit = {
    var tmp = head
    for (_ <- 0 until length) {
      action(tmp.data)
      tmp = tmp.next
    }
  }

  def size: Int = length

  def remove(index: Int): Unit = {
    val tmp = getNode(index)
    if (tmp != head) {
      tmp.prev.next = tmp.next
    }
    else {
      head = tmp.next
    }
    if (tmp != tail) {
      tmp.next.prev = tmp.prev
    }
    else {
      tail = tmp.prev
    }
    tmp.next = null
    tmp.prev = null
    length -= 1
  }

  def add(data: T, index: Int): Unit = {
    val tmp = getNode(index)
    val newNode = new Node(data)
    if (tmp != null) {
      tmp.prev.next = newNode
      newNode.prev = tmp.prev
    }
    else {
      head = newNode
    }
    newNode.next = tmp
    tmp.prev = newNode
    length += 1
  }

  def forEach(a: Action[T]): Unit = {
    var tmp = head
    for (_ <- 0 until length) {
      a.toDo(tmp.data)
      tmp = tmp.next
    }
  }

  def sort(comparator: Comparator[T]): Unit = {
    head = mergeSort(head, comparator)
    tail = getNode(length - 1)
  }

  private def mergeSort(h: Node, comparator: Comparator[T]): Node = {
    if (h == null || h.next == null) {
      return h
    }

    val middle = getMiddle(h)
    val middleNext = middle.next

    middle.next = null

    val left = mergeSort(h, comparator)
    val right = mergeSort(middleNext, comparator)

    sortedMerge(left, right, comparator)
  }

  private def sortedMerge(a: Node, b: Node, comparator: Comparator[T]): Node = {
    var result: Node = null
    if (a == null) return b
    if (b == null) return a
    if (comparator.compare(a.data, b.data) <= 0) {
      result = a
      result.next = sortedMerge(a.next, b, comparator)
    }
    else {
      result = b
      result.next = sortedMerge(a, b.next, comparator)
    }
    result.next.prev = result
    result
  }

  private def getMiddle(h: Node): Node = {
    var fast = h.next
    var slow = h
    while (fast != null) {
      fast = fast.next
      if (fast != null) {
        slow = slow.next
        fast = fast.next
      }
    }
    slow
  }

  private def getNode(index: Int): Node = {
    if (index < 0 || index >= length) throw new IndexOutOfBoundsException()
    var tmp = head
    for (_ <- 0 until index) {
      tmp = tmp.next
    }
    tmp
  }


  class Node(var data: T) {
    var next: Node = _
    var prev: Node = _
  }

}