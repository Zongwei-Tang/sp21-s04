package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] truelist;
    private int size;
    private int first;
    private int last;

    public ArrayDeque(){
        truelist = (T[]) new Object[8];
        size = 0;
        first = 7;
        last = 7;
    }

    public void addFirst(T item){
        if (size + 1 > truelist.length){
            resize(truelist.length * 2);
        }
        truelist[first] = item;
        first = (first - 1 + truelist.length) % truelist.length;
        size += 1;
    }

    public void addLast(T item){
        if (size+1 > truelist.length){
            resize(truelist.length * 2);
        }
        last = (last + 1) % truelist.length;
        truelist[last] = item;
        size += 1;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = (first - 1 + truelist.length) % truelist.length;
        for (int j = 0; j < size; j++){
            System.out.print(truelist[i] + " ");
            i = (i + 1) % truelist.length;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (size <= 0){
            return null;
        }
        if ((truelist.length >= 16) && (double) (size - 1) / truelist.length < 0.25){
            resize(truelist.length / 2);
        }
        first = (first + 1) % truelist.length;
        T a = truelist[first];
        truelist[first] = null;
        size -= 1;
        return a;
    }

    public T removeLast(){
        if (size <= 0){
            return null;
        }
        if ((truelist.length >= 16) && (double) (size - 1) / truelist.length < 0.25){
            resize(truelist.length / 2);
        }
        T a = truelist[last];
        truelist[last] = null;
        last = (last - 1 + truelist.length) % truelist.length;
        size -= 1;
        return a;
    }

    public T get(int index){
        if (index >= size || index < 0){
            return null;
        }
        int j = (first + 1) % truelist.length;
        for (int i = 0; i < index; i++){
            j = (j + 1) % truelist.length;
        }
        return truelist[j];
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o){
        if (!(o instanceof ArrayDeque)){
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque <T>) o;
        if (other.size() != size){
            return false;
        }
        for (int i = 0; i < size; i++){
            T thisitem = this.get(i);
            T otheritem = other.get(i);
            if (thisitem == null || otheritem == null){
                if (!(thisitem == null && otheritem == null)){
                    return false;
                }
            }
            else if (!(this.get(i).equals(other.get(i)))){
                return false;
            }
        }
        return true;
    }

    public class ArrayDequeIterator implements Iterator<T>{
        private int current;
        public ArrayDequeIterator(){
            current = first;
        }
        @Override
        public boolean hasNext() {
            return truelist[(current + 1) % truelist.length] != null;
        }

        @Override
        public T next() {
            current = (current + 1) % truelist.length;
            return truelist[current];
        }
    }

    public void resize(int capacity){
        T[] newlist = (T[]) new Object[capacity];
        int current = (first + 1) % truelist.length;
        for (int i = 0; i < size; i++){
            newlist[i] = truelist[current];
            current = (current + 1) % truelist.length;
        }
        truelist = newlist;
        first = capacity - 1;
        last = size;
    }
}
