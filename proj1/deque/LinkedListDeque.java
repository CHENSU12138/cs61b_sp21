package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private Node sentinel;
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node clist = new Node(item, sentinel, sentinel.next);
        sentinel.next.previous = clist;
        sentinel.next = clist;
        size++;
    }

    public void addLast(T item) {
        Node clist = new Node(item, sentinel.previous, sentinel);
        sentinel.previous.next = clist;
        sentinel.previous = clist;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rest = sentinel.next.item;
        sentinel.next.next.previous = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return rest;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rest = sentinel.previous.item;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return rest;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node getlist = sentinel;
        for (int i = 0; i <= index; i++) {
            getlist = getlist.next;
        }
        return getlist.item;
    }

    private T getRecursiveHelper(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelper(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public void printDeque() {
        Node printlist = sentinel.next;
        while (printlist != sentinel) {
            System.out.print(printlist.item + " ");
            printlist = printlist.next;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> o1 = (Deque<T>) o;
        if (o1.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(o1.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }

    private class Node {
        private T item;
        private Node previous;
        private Node next;

        private Node(T n, Node preprevious, Node nenext) {
            item = n;
            previous = preprevious;
            next = nenext;

        }

        private Node(Node senpre, Node sennext) {
            previous = senpre;
            next = sennext;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private int iter;

        private LinkedListIterator() {
            iter = 0;
        }

        public boolean hasNext() {
            return iter < size;
        }

        public T next() {
            T item = get(iter);
            iter += 1;
            return item;
        }
    }

}
