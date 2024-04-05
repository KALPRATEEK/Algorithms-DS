import java.util.ArrayList;
import java.util.List;

class Stack<T> {
    private List<T> stackList;

    public Stack() {
        this.stackList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public void push(T element) {
        stackList.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return stackList.remove(stackList.size() - 1);
    }

    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return stackList.get(stackList.size() - 1);
    }
}