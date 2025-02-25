package Java.DSA.Assignments.week_5;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity +1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for(AnyType item:items )
            array[i++] = item;
        printHeap();
        buildHeap();
    }


    @SuppressWarnings("unchecked")
    private void enlargeArray(int newSize) {
        AnyType[] oldArray = array;
        array = (AnyType[]) new Comparable[newSize];
        for (int i = 0; i < oldArray.length; ++i) {
            array[i] = oldArray[i];
        }
    }

    private void insert(AnyType x) {
        if(currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        int hole = ++currentSize;
        for(; hole > 1 && x.compareTo(array[hole/2]) < 0; hole /= 2) {
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    private void buildHeap() {
        for (int i = currentSize/2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];

        for(; hole * 2 <= currentSize; hole = child ) {
            child = hole * 2;
            if(child != currentSize && array[child + 1].compareTo(array[child]) > 0 ) {
                child++;
            }
            if(array[child].compareTo(tmp) > 0 ) {
                array[hole] = array[child];
            }
            else {
                break;
            }
        }
        array[hole] = tmp;
        printHeap();
    }
    //printing the heap based off the currentSize member
    private void printHeap() {
        for(int i = 1; i <= currentSize; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    //wrote a separate print method for printing the entire heap in the instance that current size decrements
    private void printTotalHeap(int size) {

        for (int i = 1; i <= size; ++i) {
            System.out.print(array[i] + " ");
        }

    }

    public AnyType findMax() {
        //return the first element in the max heap
        return array[1];
    }

    public void deleteMax() {
        //return the first element in the max heap, which is always the max
        AnyType max = findMax();
        //show the current size for help
        System.out.println("current size is: " + currentSize);
        //put the last array element at the front of the list
        array[1] = array[currentSize];
        //put the last element of the list as the max
        array[currentSize] = max;
        //percolate the new root node down as it is not the max always and decrement current size so we dont
        //keep swapping them in and out of place
        --currentSize;
        percolateDown(1);
    }

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType [] array;
    public int size;


    public static void main(String[] args) {
        Integer[] inputs = {142, 543, 123, 65, 453, 879, 572, 434, 111, 242, 811, 102};
        //process of building max heap
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(inputs);
        System.out.println();

        //swap root with last array element, and decrement current size and then percolate down
        for (int i = 0; i < inputs.length; ++i) {
            heap.deleteMax();
        }
        //use a separate print method for the sorted heap since the other only prints the current size
        //and since we're decrementing in find max, current size will always be less than the actual size of
        //the input array
        System.out.println();
        System.out.println("The final heap sorted heap is: ");
        heap.printTotalHeap(inputs.length);
        System.out.println();

    }
    
}
