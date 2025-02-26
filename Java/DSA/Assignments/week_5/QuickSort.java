package Java.DSA.Assignments.week_5;

public class QuickSort <AnyType extends Comparable<? super AnyType>> {
    
    //cutoff for insertion sort to take over
    private static final int CUTOFF = 3;

    public static <AnyType extends Comparable<? super AnyType>> void quicksort(AnyType[] a){
        quicksort( a, 0, a.length - 1 );
    }

    //method for swapping array references for pre array sorting
    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2){
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3( AnyType[] a, int left, int right)
    {   
        //sort the first, middle, and last elements of the array before moving the pivot over to right - 1
        int center = (left + right) / 2;
        if(a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if(a[right].compareTo(a[left] ) < 0)
            swapReferences(a, left, right);
        if(a[right].compareTo(a[center] ) < 0)
            swapReferences(a, center, right);

            // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a, int left, int right) {
        
        if(left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

                
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[++i].compareTo(pivot) < 0) {}
                while( a[--j].compareTo( pivot ) > 0) {}
                if(i < j) 
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1);   

            quicksort(a, left, i - 1);    
            quicksort(a, i + 1, right);   
        }
        else 
        //call insertion sort once threshold cutoff has been reachdd for subarray
            insertionSort(a, left, right);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType[] a, int left, int right ) {
        for( int p = left + 1; p <= right; p++ )
        {
            AnyType tmp = a[p];
            int j;

            for( j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j-- )
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    public static void main (String[] args) {
        Integer[] preSort = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        Integer[] descSort = {21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] randSort = {54, 20, 17, 18, 19, 16, 15, 2, 13, 9, 11, 10, 12, 8, 1, 4, 5, 6, 3, 14, 7};

        long startTime = System.nanoTime();
        quicksort(preSort, 0, preSort.length-1);
        long endTime = System.nanoTime();
        long timeDifference = endTime - startTime;
        for(int i = 0; i < preSort.length; ++i) {
            System.out.print(preSort[i]);
        }
        System.out.println();
        System.out.println("The nano run time for presorted lists is:");
        System.out.println(timeDifference);
        System.out.println();

        startTime = System.nanoTime();
        quicksort(descSort, 0, randSort.length-1);
        endTime = System.nanoTime();
        timeDifference = endTime - startTime;
        for(int i = 0; i < descSort.length; ++i) {
            System.out.print(descSort[i]);
        }
        System.out.println();
        System.out.println("The nano run time for descending sorted lists is:");
        System.out.println(timeDifference);
        System.out.println();

        startTime = System.nanoTime();
        quicksort(randSort, 0, preSort.length-1);
        endTime = System.nanoTime();
        timeDifference = endTime - startTime;
        for(int i = 0; i < randSort.length; ++i) {
            System.out.print(randSort[i]);
        }
        System.out.println();
        System.out.println("The nano run time a random list is:");
        System.out.println(timeDifference);
        System.out.println();
        
    }
}