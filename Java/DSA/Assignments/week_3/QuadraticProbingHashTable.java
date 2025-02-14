package Java.DSA.Assignments.week_3;


public class QuadraticProbingHashTable<AnyType> {

    private static final int LIST_SIZE = 10;
    private AnyType[] theList;

    public QuadraticProbingHashTable() {
        this(LIST_SIZE);
    }

    @SuppressWarnings("unchecked")
    public  QuadraticProbingHashTable( int size) {
        theList = (AnyType[]) new Object[size];

    }

    private int myHash(AnyType x) {

        int hashVal = x.hashCode() % 10;
        return hashVal;

    }

    public void insert(AnyType x) {

        int bucketIndex = myHash(x);

        for(int i = 0; i < LIST_SIZE; ++i) {

            int probe = (bucketIndex + (i*i)) % LIST_SIZE;
            
            if(theList[probe] == null) {
                theList[probe] = x;
                return;
            }

        }

     }

     public void printBuckets() {
        for (int i = 0; i < LIST_SIZE; ++i) {
            System.out.println("Index " + theList[i]);
        }
     }




    public static void main(String[] args) {

        QuadraticProbingHashTable<Integer> output = new QuadraticProbingHashTable<>();

        int[] numbers = {437, 1323, 6173, 4199, 4344, 9679, 1989};

        for (int i = 0; i < numbers.length; ++i) {

            output.insert(numbers[i]);

        }

        output.printBuckets();

        System.out.println();

    }
    
}