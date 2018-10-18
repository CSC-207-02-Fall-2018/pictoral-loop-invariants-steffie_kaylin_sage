    /* ****************************************************************
     * Sage Kaplan-Goland, Steffie Ochoa, Kaylin Kuhn                *
     * Box # 3712          4093           3986                       *
     * Program for CSC 207                                           *
     *   Pictorial Loop Invariants Lab                               *
     * Assignment for Friday, October 19                             *
     *****************************************************************/


    /* ***************************************************************
     * Academic honesty certification:                               *
     *   Written/online sources used:                                *
     *     None
     *   Help obtained                                               *
     *     Dr. Henry M. Walker                                       *
     *   My signature below confirms that the above list of sources  *
     *   is complete AND that I have not talked to anyone else       *
     *   [e.g., CSC 161 students] about the solution to this problem *
     *                                                               *
     *   Signature:                                                  *
     *****************************************************************/

package loopInvariants;

import java.util.concurrent.ThreadLocalRandom;

/**Class Invariants has a number of methods related to partitioning and quicksort
 * as well as code to test the correctness of said methods. This class has methods
 * which can partition an array, find the kth smallest element, find the median,
 * and sort using the quicksort method
 * 
 * @author kaplango, @author ochoaste, @author kuhnkayl
 *
 */
public class Invariants {  
    
    /**Method that takes in an array and and two boundaries to look between.
     * It uses the left index as a pivot and moves all values lower or equal
     * than that to the left of it and all higher values to the right side
     * 
     * pre: left and right are in the range [0, a.length), and left < right
     * post: a is partitioned around value a[left], and the new index of 
     *      a[left] is returned
     * 
     * @param a the array that we are partitioning
     * @param left the index that we want to start the partition at
     * @param right the index that we want to end the partition at
     * @return an array with items between index left and right partitioned around 
     *  the value a[left]
     */
    static int partition (int a[], int left, int right) {        
        int leftStart = left;
        int pivot = a[left];        
        
        while (left <= right) {
            /*if the farthest right value is already greater than the pivot
             * then we can leave it there and decrement the right index so that
             * we are next going to look at the next from last value
             */            
            if (a[right] > pivot) {
                right--;
            }
            /*If the furthest left value is already less than or equal to the pivot
             * we can leave it there and increment the left index so that we are next
             * going to look at the next from first value
             */
            else if (a[left] <= pivot) {
                left++;
            }
            /*If neither of the previous statements are true, that means that a[left]
             * and a[right] are on incorrect sides of the pivot so we can swap them
             */
            else {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                
                left++;
                right--;
            }
        }
        
        /*At the end, we need to move the pivot to its final position, since we know it
         * will be left at the start. We need to swap it with a[right] because after all of
         * the decrements the spot at index right will be the last of the left group (i.e.
         * right is less than left so right refers to the last of the left values)
         */
        a[leftStart] = a[right];
        a[right] = pivot;
        
        return right;
    }
    
    /**Method that selects the kth smallest element from an array a. That is, if k = 1, this
     * would be the smallest element. If k = 20, this would be the 20th elt
     * 
     * pre: k is in the range (0, a.length]
     * post: a is partially sorted and the value of the kth smallest elt (at index k -1) is
     *      returned
     *  
     * @param a the array in which to find the desired element
     * @param k the rank of the element to be returned (1 being smallest, a.length being largest
     * @return the value of the kth smallest elt
     */
    static int select (int a[], int k) {
        return selectHelper(a, k, 0, a.length - 1);
    }
    
    /**Method used to handle processing in search of select() method. This includes all of
     * the variables needed for recursion but not necessary for the function call itself. 
     * This method should only be used in the select() function and not called in the code
     * 
     * pre: k is in the range (0, a.length], left and right are in range [0, a.length - 1]
     *      left < right
     * post:       
     *      
     * @param a the array in which to find the desired element
     * @param k the rank of the element to be returned (1 being smallest, a.length being largest
     * @param left the leftmost value on the array to start searching from
     * @param right the rightmost value on the array to start searching from
     * @return the value of the kth smallest elt
     */
    static int selectHelper (int a[], int k, int left, int right) {
        /*If there is only one elt left in the range, this means that it must be the correct
         * value
         */
        if (right - left == 1) {
            return a[k-1];
        }
       
        int middle = partition (a, left, right);
       
        /*If the item you just placed is at index k-1, this means that it is the desired value.
         * Otherwise, run the same function on either the right or left side of the partition,
         * depending on whether middle is less than or greater than k
         * 
         */
        if (middle == k - 1) {
            return a[middle];
        }
        else if (middle < k - 1){
            return selectHelper(a, k, middle + 1, right);
        }
        else {
            return selectHelper(a, k, left, middle - 1);
        }
    }
    
    /**Method which finds the median value of a given array a. Array a will also be partially
     * sorted in the process
     * 
     * pre: none
     * post: returns the median of a
     * 
     * @param a the array to be evaluated
     * @return the median of a
     */
    static int median (int a[]) {
        //If there are an odd number of elt, we can just return the middle one 
        if (a.length % 2 != 0){
            return select(a, (a.length / 2) + 1);
        }
        //if there are an even number of elts, we need to average the two middle elts
        else {
            return (select(a, a.length / 2) + select(a, (a.length / 2) + 1)) / 2;
        }
    }
    
    /**Method for sorting an array to be in ascending order using the quicksort method
     * 
     * pre: none
     * post: a is sorted in ascending order
     * 
     * @param a the array to be sorted
     */
    static void quicksort (int a[]) {
        quicksortKernel (a, 0, a.length - 1);
    }
    
    /**Helper function to handle processing for the quicksort() method. This uses the
     * partition() method to place elements in their correct locations and recursively
     * does this for smaller subsets of the array until all subsets have only 1 elt,
     * meaning that the array is sorted. This method should only be used in the 
     * quicksort() method and not called in the code itself
     * 
     * pre: left and right are in the range [0, a.length), left < right
     * 
     * @param a the array to be sorted
     * @param left the leftmost value to look at for sorting
     * @param right the rightmost value to look at for sorting
     */
    static void quicksortKernel (int a[], int left, int right) {
        if (right - left >= 1) {
            int middle = partition (a, left, right);            
            quicksortKernel (a, left, middle - 1);
            quicksortKernel (a, middle + 1, right);
        }
    }
    public static void main (String [] argv) {
        int [] a = new int[20];        
        
        //Test partition on randomized array
        //initialize a with random numbers
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.print("\nRandomized array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }
        
        System.out.println();
        
        partition (a, 0, a.length-1);
        
        //output the newly partitioned a
        System.out.print("Partitioned array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }
        
        //Test select on a randomized array
        //initialize a with new random numbers
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.println();

        System.out.print("\nRandomized array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }
        
        System.out.println();
        
        int selVal = 5;
        System.out.print("Selecting " + selVal + "th smallest value: ");
        System.out.println(select(a, 5));
        
        //Test median on randomized array
        //initialize a with new random numbers
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.print("\nRandomized array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }
        

        System.out.println();
        System.out.print("Median: ");
        System.out.println(median(a));
        
        System.out.print("Partially sorted array to check median: "); 
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }
        
        System.out.println();

        //Test quicksort on randomized array
        //initialize a with new random numbers
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.print("\nRandomized array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }

        System.out.println();

        quicksort(a);
        
        System.out.print("Sorted array: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length-1) {
                System.out.print(", ");
            }
        }

    }
    
}
