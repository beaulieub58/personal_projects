package Java.DSA.Assignments.week_4;
//figure 6.8 as guide

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

        for(; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if(child != currentSize && array[child + 1].compareTo(array[child]) < 0 ) {
                child++;
            }
            if(array[child].compareTo(tmp) < 0 ) {
                array[hole] = array[child];
            }
            else {
                break;
            }
        }
        array[hole] = tmp;
    }

    private void printHeap() {
        for(int i = 1; i <= currentSize; ++i) {
            System.out.print(array[i] + " ");
        }
    }

    public AnyType findMin() {
        return array[1];
    }

    public AnyType deleteMin() {
        AnyType min = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return min;
    }

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType [] array;


    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(15);
        Integer[] inputs = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};

        for(int i = 0; i < inputs.length; ++i) {
            heap.insert(inputs[i]);
        }

        System.out.println("6.2 A is:");
        heap.printHeap();
        System.out.println();
        System.out.println();
        BinaryHeap<Integer> builtHeap = new BinaryHeap<Integer>(inputs);
        System.out.println("6.2 B is:");
        builtHeap.printHeap();

        System.out.println();
        System.out.println();
        
        System.out.println("The result of performing 3 delete min operations is as follows for 6.2 A: ");
        heap.deleteMin();
        heap.deleteMin();
        heap.deleteMin();
        heap.printHeap();

        System.out.println();
        System.out.println();

        System.out.println("The result of performing 3 delete min operations is as follows for 6.2 B: ");
        builtHeap.deleteMin();
        builtHeap.deleteMin();
        builtHeap.deleteMin();
        builtHeap.printHeap();
        
        System.out.println();


    }
    
}
