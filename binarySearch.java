public class binarySearch {

    /**
     * Returns true if the target value is found in the indicated portion of the data array.
     * This search only considers the array portion from data[low] to data[high] inclusive.
     * 
     * 
     */
    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high)
            return false; // interval empty; no match
        else {
            int mid = (low + high) / 2; // floors (rounds down) integer
            if (target == data[mid])
                return true; // found a match
            else if (target < data[mid])
                return binarySearch(data, target, low, mid - 1); // recur left of the middle
            else
                return binarySearch(data, target, mid + 1, high); // recur right of the middle
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,3,4,5 };
        System.out.println(binarySearch(a, 7, 0, a.length-1));
    }
}