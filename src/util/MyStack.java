package util;

public class MyStack<T> {
    private MyLinkedList<T> list;

    public MyStack() {
        this.list = new MyLinkedList<>();
    }

    public void push(T element) {
        list.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return list.removeLast();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}