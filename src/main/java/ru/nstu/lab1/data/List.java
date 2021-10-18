package ru.nstu.lab1.data;

public class List<T> {

    private Node head;
    private Node tail;
    private int length;

    public void add(T data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
            length++;
            return;
        }
        Node newTail = new Node(data);
        newTail.prev = tail;
        tail.next = newTail;
        tail = newTail;
        length++;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void remove(int index) {
        Node tmp = getNode(index);
        if (tmp != head) {
            tmp.prev.next = tmp.next;
        }
        else {
            head = tmp.next;
        }
        if (tmp != tail) {
            tmp.next.prev = tmp.prev;
        }
        else {
            tail = tmp.prev;
        }
        tmp.next = tmp.prev = null;
        length--;
    }

    public int size(){
        return length;
    }

    public void add(T data, int index) {
        Node tmp = getNode(index);
        Node newNode = new Node(data);
        if (tmp != head) {
            tmp.prev.next = newNode;
            newNode.prev = tmp.prev;
        }
        else {
            head = newNode;
        }
        newNode.next = tmp;
        tmp.prev = newNode;
        length++;
    }

    public void forEach(Action<T> a) {
        Node tmp = head;
        for (int i = 0; i < length; i++) {
            a.toDo(tmp.data);
            tmp = tmp.next;
        }
    }

    public void sort(Comparator comparator) {
        head = mergeSort(head, comparator);
        tail = getNode(length - 1);
    }

    private Node mergeSort(Node h, Comparator comparator)
    {
        if (h == null || h.next == null) {
            return h;
        }

        Node middle = getMiddle(h);
        Node middleNext = middle.next;

        middle.next = null;

        Node left = mergeSort(h, comparator);

        Node right = mergeSort(middleNext, comparator);

        return sortedMerge(left, right, comparator);
    }

    private Node sortedMerge(Node a, Node b, Comparator comparator)
    {
        Node result;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (comparator.compare(a.data, b.data) <= 0) {
            result = a;
            result.next = sortedMerge(a.next, b, comparator);
        }
        else {
            result = b;
            result.next = sortedMerge(a, b.next, comparator);
        }
        result.next.prev = result;
        return result;
    }

    private Node getMiddle(Node h)
    {
        if (h == null)
            return null;
        Node fast = h.next;
        Node slow = h;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();
        Node tmp = head;

        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            next = prev = null;
        }
    }
}
