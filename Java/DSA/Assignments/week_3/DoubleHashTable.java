package Java.DSA.Assignments.week_3;


public class DoubleHashTable<AnyType> {

    private static final int LIST_SIZE = 10;
    private Integer[] theList;

    public DoubleHashTable() {
        this(LIST_SIZE);
    }

    public  DoubleHashTable( int size) {
        theList = (Integer[]) new Integer[size];

    }

    private int myHash(int x) {

        int hashVal = x  % 10;
        return hashVal;

    }

    private int myDoubleHash(int x) {

        int hashVal = 7 - (x % 7);
        return hashVal;

    }

    public void insert(int x) {

        int bucketIndex = myHash(x);
        int i = 0;

        while (i < LIST_SIZE) {

            int probe = (bucketIndex + i * myDoubleHash(x)) % LIST_SIZE;
            
            if(theList[probe] == null) {
                theList[probe] = x;
                return;
            }
         i += 1;

        }

     }

     public void printBuckets() {
        for (int i = 0; i < LIST_SIZE; ++i) {
            System.out.println("Index " + i + " " + theList[i]);
        }
     }




    public static void main(String[] args) {

        DoubleHashTable<Integer> output = new DoubleHashTable<>();

        int[] numbers = {437, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int i = 0; i < numbers.length; ++i) {

            output.insert(numbers[i]);

        }

        output.printBuckets();

        System.out.println();

    }
    
}