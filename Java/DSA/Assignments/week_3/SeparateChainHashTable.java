package Java.DSA.Assignments.week_3;
import java.util.LinkedList;
import java.util.List;


public class SeparateChainHashTable<AnyType> {

    private static final int LIST_SIZE = 10;
    private List<AnyType> [] theLists;

    public SeparateChainHashTable() {
        this(LIST_SIZE);
    }
    @SuppressWarnings("unchecked")
    public  SeparateChainHashTable( int size) {

        theLists = new LinkedList[size];
        for (int i = 0; i < theLists.length; ++i) {
            theLists[i] = new LinkedList<>();
        }
    }

    private int myHash(AnyType x) {

        int hashVal = x.hashCode() % 10;
        return hashVal;

    }

    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myHash(x)];
        return whichList.contains(x);
    }

    public void insert(AnyType x) {
        List<AnyType> myList = theLists[myHash(x)];
        if(!myList.contains(x)) {
            myList.add(x);
            
        }
    }

    public void printBuckets() {

        for (int i = 0; i < theLists.length; ++i) {
            System.out.print("Index " + i + " ");

            for (AnyType item: theLists[i]) {
                System.out.print(item);
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        SeparateChainHashTable<Integer> output = new SeparateChainHashTable<>();

        int[] numbers = {437, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int i = 0; i < numbers.length; ++i) {

            output.insert(numbers[i]);

        }

        output.printBuckets();

        System.out.println();

    }
    
}
