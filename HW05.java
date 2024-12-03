/*
 * There might be some chanes required in the main method to test
 */

public class HW05 {
    public static void main(String[] args) {
        ArrayHeap<Integer, String> heap = new ArrayHeap<>(500);

        // Insert elements into the heap
        heap.insert(10, "Ten");
        heap.insert(20, "Twenty");
        heap.insert(5, "Five");
        heap.insert(30, "Thirty");
        heap.insert(15, "Fifteen");

        // Display the heap in level-order
        System.out.println("Heap after insertions (level-order):");
        heap.levelorder();

        // Another heap
        ArrayHeap<Integer, String> heap2 = new ArrayHeap<>(500);
        heap2.insert(8, "Eight");
        heap2.insert(23, "Twenty-Three");
        heap2.insert(3, "Three");
        heap2.insert(27, "Twenty-Seven");

        System.out.println("\nHeap2 after insertions (level-order):");
        heap2.levelorder();

        // Merge two heaps
        System.out.println("\nMerged heap (level-order):");
        var mergedHeap = ArrayHeap.merge(heap, heap2);
        mergedHeap.levelorder();
        /* int[] array = {1, 5, 3, 2, 6, 4};
        heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        } */
    }

    public static void heapSort(int[] array) {
        /*
         * heap sort implementation
         */
    }

    public static void heapify(int[] array, int n, int parent) {
        /*
         * heapify implementation (required for heapSort)
         * array: whole array
         * n: length of subarray
         * parrent: index of parrent
         */
    }

    @SuppressWarnings("unused")
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class Entry<K extends Comparable<? super K>, V> implements Comparable<Entry<K, V>> {
    private final K key;
    private final V value;

    // Constructor
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getters
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    // Compare entries based on their keys
    @Override
    public int compareTo(Entry<K, V> other) {
        return this.key.compareTo(other.getKey());
    }

    @Override
    public String toString() {
        return "Key: " + key + ", Value: " + value;
    }
}





interface List <T> {
    int size();
    boolean isEmpty();
}

interface PriorityQueue <P, E> extends List <E> {
    E remove();
    E peek();
    void insert(P priority, E element);
}

/*
 * Array-based Min-heap implementation
 */
class ArrayHeap <K extends Comparable<? super K>, V> implements PriorityQueue <K, V> {

    private Entry<K,V>[] heap;
    private int size;

    /*
     * Constructor(int capacity)
     */

    /*
     * heapifyUp(index)
     */

     /*
      * heapifyDown(index)
      */

    /* merge two given heaps
     * static <K extends Comparable<? super K>, V> ArrayHeap<K, V> merge(ArrayHeap<K, V> heap1, ArrayHeap<K, V> heap2)
     */

    @SuppressWarnings("unchecked")
    public ArrayHeap(int capacity){
        heap = new Entry[capacity];
        size = 0;
    }

    @Override
    public void insert(K priority, V element) {
        if(size == heap.length){
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = new Entry<>(priority, element);
        heapify(size);
        size++;
        
    }

    private void heapify(int index){
        while(index > 0) {
            int parentIndex = (index -1) / 2;
            if(heap[index].compareTo(heap[parentIndex])< 0) {
                swap(index, parentIndex);
                index = parentIndex;
            }else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {

        while(index < size / 2) {
            int leftChild = 2*index + 1;
            int rightChild = 2* index + 2;
            int smallerChild = leftChild;
            
            if(rightChild < size  && heap[rightChild].compareTo(heap[leftChild]) < 0) {
                smallerChild = rightChild;
            }

            if(heap[index].compareTo(heap[smallerChild]) <= 0){
                break;
            }

            swap(index, smallerChild);
            index = smallerChild;
        }

    }

    @Override
    public V remove() {
        if(isEmpty()) {
            return null;
        }

        V removedValue = heap[0].getValue();

        heap[0] = heap[size -1];

        heap[size-1] = null;

        size--;
        heapifyDown(0);
        return removedValue;
    }

    @Override
    public V peek() {
        if(isEmpty()){
            return null;
        }

        return heap[0].getValue();
    }

    /*
    * adds elements to a list in BFS fashion
     * levelorder(List<V> list)
     */

    public void levelorder() {
        for(int i = 0; i < size ; i++){
            System.out.println(heap[i].getValue() + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size ==  0;
    }

    

    public Entry<K, V>[] getHeap() {
        // Convenience method
        return heap;
    }

    

    private void swap(int index, int otherIndex) {
        Entry<K, V> temp = heap[index];
        heap[index] = heap[otherIndex];
        heap[otherIndex] = temp;
    }

    public static <K extends Comparable<? super K>, V> ArrayHeap<K, V> merge(ArrayHeap<K, V> heap1, ArrayHeap<K, V> heap2) {
        ArrayHeap<K, V> mergedHeap = new ArrayHeap<>(heap1.size() + heap2.size());

        for (int i = 0; i < heap1.size(); i++) {
            mergedHeap.insert(heap1.heap[i].getKey(), heap1.heap[i].getValue());
        }
        for (int i = 0; i < heap2.size(); i++) {
            mergedHeap.insert(heap2.heap[i].getKey(), heap2.heap[i].getValue());
        }

        return mergedHeap;
    }
}