package loopInvariants;

import java.util.concurrent.ThreadLocalRandom;

public class Invariants {  
    
    /**Method that takes in an array and and two boundaries to look between.
     * It uses the left index as a pivot and moves all values lower or equal
     * than that to the left of it and all higher values to the right side
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
    
    static int select (int a[], int k) {
        return selectHelper(a, k, 0, a.length - 1);
    }
    
    static int selectHelper (int a[], int k, int left, int right) {
        if (right - left == 1) {
            return a[k-1];
        }
       
        int middle = partition (a, left, right);
       
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
    
    static int median (int a[]) {
        if (a.length % 2 != 0){
            return select(a, a.length / 2);
        }
        else {
            return (select(a, a.length / 2) + select(a, (a.length / 2) + 1)) / 2;
        }
    }
    
    static void quicksort (int a[]) {
        quicksortKernel (a, 0, a.length - 1);
    }
    
    static void quicksortKernel (int a[], int left, int right) {
        if (right - left >= 1) {
            int middle = partition (a, left, right);            
            quicksortKernel (a, left, middle - 1);
            quicksortKernel (a, middle + 1, right);
        }
    }
    public static void main (String [] argv) {
        int [] a = new int[20];
        
        //backwards sorted partition
        System.out.println("Reversed array (20-1)");
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length-i;
        }
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        System.out.println();
        partition (a, 0, a.length-1);
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        
        //randomized
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.println("\nRandomized array");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        System.out.println();
        
        partition (a, 0, a.length-1);
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        //randomized
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.println("\nRandomized array");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        System.out.println();
        
        System.out.println(select(a, 5));
        
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        //median        
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.println("\nRandomized array");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        

        System.out.println();
        System.out.print("Median: ");
        System.out.println(median(a));

        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        
        //quicksort
        for (int i = 0; i < a.length; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
        
        System.out.println("\nRandomized array");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }

        System.out.println();

        quicksort(a);
        
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }

    }
    
}
