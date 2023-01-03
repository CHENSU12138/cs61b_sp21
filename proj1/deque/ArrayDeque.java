package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int length;
    private int front;
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    

    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index, int length) {
        if (index == length - 1) {
            return 0;
        }
        return index + 1;
    }

    public int size() {
        return size;
    }

    private void resize() {
        T[] reArray = (T[]) new Object[length * 2];
        int arr1 = front;
        int arr2 = length;
        while (arr1 != last) {
            reArray[arr2] = array[arr1];
            arr1 = plusOne(arr1, length);
            arr2 = plusOne(arr2, length * 2);
        }
        front = length;
        last = arr2;
        length *= 2;
        array = reArray;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int arr1 = front;
        int arr2 = length / 4;
        while (arr1 != last) {
            newArray[arr2] = array[arr1];
            arr1 = plusOne(arr1, length);
            arr2 = plusOne(arr2, length / 2);
        }
        front = length / 4;
        last = arr2;
        length /= 2;
        array = newArray;
    }

    public void addFirst(T x) {
        if (size == length - 1) {
            resize();
        }
        front = minusOne(front);
        array[front] = x;
        size++;
    }

    public void addLast(T x) {
        if (size == length - 1) {
            resize();
        }
        array[last] = x;
        last = plusOne(last, length);
        size++;
    }

    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T rest = array[front];
        front = plusOne(front, length);
        size--;
        return rest;
    }

    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }


        last = minusOne(last);
        size--;
        return array[last];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int arr = front;
        for (int i = 0; i < index; i++) {
            arr = plusOne(arr, length);
        }
        return array[arr];
    }

    public void printDeque() {
        int arr = front;
        while (arr != last) {
            System.out.print(array[arr] + " ");
            arr = plusOne(arr, length);
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
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

    private class ArrayDequeIterator implements Iterator<T> {
        private int iter;

        private ArrayDequeIterator() {
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
