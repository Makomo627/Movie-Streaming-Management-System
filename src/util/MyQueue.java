package util;

public class MyQueue<T> {
    private MyLinkedList<T> list;

    public MyQueue() {
        this.list = new MyLinkedList<>();
    }

    public void enqueue(T element) {
        list.add(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public MyLinkedList<T> getElements() {
        return list;
    }
}