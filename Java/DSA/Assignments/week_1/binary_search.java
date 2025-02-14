package Java.DSA.Assignments.week_1;

public class binary_search {

    public static int binarySearch(int[] numbers,int low, int high,int key) {


        while (high >= low) {

            int mid = (low + high) / 2;

            if(numbers[mid] < key){

                low = mid + 1;

            }
            else if (numbers[mid] > key) {
                high = mid - 1;
            }
            else {
                return mid;
            }

        }

        return -1;

        }

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5,6,7};
        int lower_boundary = 0;
        System.out.println(binarySearch(list, lower_boundary, list.length - 1,8));
    }
    }

