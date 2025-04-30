package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T a) {
            item = a;
            prev = null;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(T item) {
        Node temp = new Node(item);
        temp.next = head.next;
        temp.prev = head;
        head.next.prev = temp;
        head.next = temp;
        size += 1;
    }

    public void addLast(T item) {
        Node temp = new Node(item);
        temp.prev = tail.prev;
        temp.next = tail;
        tail.prev.next = temp;
        tail.prev = temp;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = head.next;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        } else {
            Node temp = head.next;
            head.next = head.next.next;
            head.next.prev = head;
            size--;
            return temp.item;
        }
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        } else {
            Node temp = tail.prev;
            tail.prev = temp.prev;
            tail.prev.next = tail;
            size--;
            return temp.item;
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            Node current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            T thisitem = get(i);
            T otheritem = other.get(i);
            if (thisitem == null || otheritem == null) {
                if (!(thisitem == null && otheritem == null)) {
                    return false;
                }
            } else if (thisitem != otheritem) {
                return false;
            }
        }
        return true;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(head.next, index, 0);
    }

    private T getRecursiveHelper(Node list, int index, int now) {
        if (index == now) {
            return list.item;
        } else {
            return getRecursiveHelper(list.next, index, now + 1);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node current;
        public LinkedListDequeIterator() {
            current = head.next;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public T next() {
            T a = current.item;
            current = current.next;
            return a;
        }
    }
}
