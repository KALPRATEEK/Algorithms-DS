import java.util.ArrayList;
import java.util.List;

class Heap<T extends Comparable<T>> {
    private List<T> heapList;

    public Heap() {
        this.heapList = new ArrayList<>();
    }

    public int size() {
        return heapList.size();
    }

    public void add(T element) {
        heapList.add(element);
        heapifyUp(size() - 1);
    }

    public T remove() {
        if (size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        T removedElement = heapList.get(0);
        heapList.set(0, heapList.get(size() - 1));
        heapList.remove(size() - 1);

        heapifyDown(0);
        return removedElement;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heapList.get(index).compareTo(heapList.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size() && heapList.get(leftChild).compareTo(heapList.get(largest)) > 0) {
                largest = leftChild;
            }

            if (rightChild < size() && heapList.get(rightChild).compareTo(heapList.get(largest)) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heapList.get(i);
        heapList.set(i, heapList.get(j));
        heapList.set(j, temp);
    }

    public boolean isEmpty() {
        return heapList.isEmpty();
    }

}
