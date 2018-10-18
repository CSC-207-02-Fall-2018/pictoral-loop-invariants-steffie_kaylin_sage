/*****************************************************************
 * Steffie Ochoa, Sage Kaplan-Goland, Kaylin Kuhn                *
 * PO Box  4093,  3712,               3986                       *
 * Pictorial Loop Invariants                                     *
 * Submission for October 19th, 2018                             *
 *****************************************************************/


/* ***************************************************************
 * Academic honesty certification:                               *
 *   Written/online sources used:                                *
 *     none    						                             *  
 *                                                               *          
 *   Help obtained                                               *
 *     none    						                             *  
 *                                                               *
 *   My signature below confirms that the above list of sources  *
 *   is complete AND that I have not talked to anyone else       *
 *   [e.g., CSC 207 students] about the solution to this problem *
 *                                                               *
 *   Signature:  Steffie Ochoa, Sage Kaplan-Goland, Kaylin Kuhn  *
 *****************************************************************/

package loopInvariants;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class creates DutchFlag objects that are 
 * arrays with an assortment of red, whites, and blues. 
 * We also create methods to sort the randomized DutchFlag
 * objects so that they follow the ordering of the Dutch
 * flag which is red, white, and blue. 
 * @author ochoaste, @author kaplango, @author kuhnkayl
 */

public class DutchFlag {
	public static final int size = 15;

	// Gives the values 0, 1, 2 to red, white, and blue respectively
	enum color {red, white, blue};
	color colors [] = new color [size];

	/**
	 * The DutchFlag constructor, for a color array
	 * this constructor will fill the specified color 
	 * array with a random assortment of 'red', 'white', 
	 * and 'blue' colors. 
	 */
	DutchFlag() {
		for (int i = 0; i < size; i++) {
			// Produces random numbers from 0-2 
			int val = ThreadLocalRandom.current().nextInt(0, 3);

			switch (val) {
			// Assigns the value 0 to equal 'red'
			case 0:
				colors[i] = color.red;
				break;

				// Assigns the value 1 to equal 'white' 
			case 1:
				colors[i] = color.white;
				break;

				// Assigns the value 2 to equal 'blue' 
			case 2:
				colors[i] = color.blue;
				break;
			}

		}
	}

	/**
	 * invariantA: 
	 * Will sort through an array with type color
	 * with the pictorial loop invariant sectioning of: 
	 * red, white, blue, colors unprocessed 
	 * 
	 */
	public void invariantA() {
		// All color sections begin at index 0 
		int white1 = 0;
		int blue1 = 0;
		int unproc1 = 0;

		// Ensures the loop goes till the end of the array
		while (unproc1 < colors.length) {

			if (colors[unproc1] == color.blue) {
				/*
				 * If the value at the unprocessed index
				 * is blue, leave it where it is located 
				 */
				unproc1++;
			}

			else if (colors[unproc1] == color.white) {
				/*
				 * Swap the value at the index blue1 with 
				 * the value at the unprocessed index
				 */
				colors[unproc1] = colors[blue1];
				colors[blue1] = color.white;

				// Increment indexes 
				unproc1++;
				blue1++;
			}

			else {
				/*
				 * Conducts a triple swap to move the 
				 * to create the red section of the 
				 * Dutch Flag. Swaps the unprocessed
				 * index value with that of the blue1
				 * index value, holds the value at
				 * index white1 in a temp variable, 
				 * then turns the value at index white1
				 * to be red, and changes the value
				 * at index blue1 to be the temp variable.
				 */
				colors[unproc1] = colors[blue1];
				color temp = colors[white1];
				colors[white1] = color.red;
				colors[blue1] = temp;

				// Increment all color section indexes
				white1++;
				blue1++;
				unproc1++;
			}
		}
	}

	/**
	 * invariantB: 
	 * Will sort through an array with type color
	 * with the pictorial loop invariant sectioning of: 
	 * red, white, colors unprocessed, blue
	 * 
	 */
	public void invariantB() {
		/*
		 *  The color indexes of white1 and unproc1 
		 *  begin at 0, the color index of blue1 begins
		 *  at the left hand side of the array
		 */
		int white1 = 0;
		int unproc1 = 0;
		int blue1 = colors.length;

		// Ensures we go on until we reach the blue section
		while (unproc1 < blue1) {

			/*
			 * Decrements the blue1 index to insert the 
			 * new blue value towards the end of the array 
			 */
			if (colors[unproc1] == color.blue) {
				blue1--;

				colors[unproc1] = colors[blue1];
				colors[blue1] = color.blue;
			}

			/*
			 * If the value at the new unprocessed color
			 * is white, leave it in the middle of the 
			 * array 
			 */
			else if  (colors[unproc1] == color.white) {
				unproc1++;
			}

			/*
			 * If the value at the new unprocessed color
			 * is red, swap the values at the index 
			 */
			else {
				colors[unproc1] = colors [white1];
				colors[white1] = color.red;

				unproc1++;
				white1++;
			}
		}
	}

	/**
	 * toString method will print out the entire 
	 * color array with commons between each 
	 * value of the array except for the last value
	 */
	public String toString() {
		for (int i = 0; i < colors.length; i++) {
			if (i != colors.length -1) {
				System.out.print(colors[i] + ", ");
			}
			else {
				System.out.print(colors[i]);
			}
		}
		return "";
	}

	public static void main(String [] args) {
		
		DutchFlag flagtestA = new DutchFlag();
		
		// Testing for invariantA method
		System.out.println("Initial Array For invariantA method:");
		System.out.println(flagtestA);
		System.out.println("Sorted Array w/invariantA method:");
		flagtestA.invariantA();
		System.out.println(flagtestA);

		System.out.println();
		DutchFlag flagiestB = new DutchFlag();

		// Testing for invariantB method
		System.out.println("Initial Array For invariantB method:");
		System.out.println(flagiestB);
		System.out.println("Sorted Array w/invariantB method:");
		flagiestB.invariantB();
		System.out.println(flagiestB);
	}
}

