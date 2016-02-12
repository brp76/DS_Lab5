
import java.io.*;
import java.util.*;

/**
 * StackSort is a program that will use two stacks to sort an array of integer values.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class StackSort {

    public static void main(String args[]) {

        int data[] = null;
        int result[] = null;

        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program sorts an array of integer values.");


        // Create an empty array of integers
        data = createArray(0, 1, 1);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with one integer
        data = createArray(1, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with two integers
        data = createArray(2, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 10 integers
        data = createArray(10, 0, 9999);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        // Create an array with 20 integers
        data = createArray(20, 0, 9);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();

        System.out.println("Please enter the number of values to sort");
        int size = getInt("   It should be an integer value greater than or equal to 1.");
        // Create an array of the given size

        data = createArray(size, 0, 99);
        System.out.println("Original array is: " + representationOfArray(data));
        result = doStackSort(data);
        System.out.println("Sorted array is: " + representationOfArray(result));
        System.out.println();


    }


    /**
     * Use two stacks to sort the data in an array
     *
     * @param data An array of integer values to be sorted.
     * @return     An array of sorted integers. 
     */
    private static int[] doStackSort(int data[]) {

    	int result[] = new int[data.length];

    	
    	// ADD CODE HERE TO SORT THE ARRAY USING TWO STACKS
    	VectorStack<Integer> lowerValues = new VectorStack<Integer>();
    	VectorStack<Integer> upperValues = new VectorStack<Integer>();
    	
    	// Push from to data to lowerValues and upperValues
    	int move = 0;
    	for (int i = 0; i < data.length; i ++) {
    		System.out.println(i+": ");
    		int add = data[i];
    		boolean inserted = false;
    		
    		if (upperValues.isEmpty() && lowerValues.isEmpty()){
    			upperValues.push(add);
    			
    			
    		} else if (upperValues.isEmpty() && !lowerValues.isEmpty()) {	// |L| A |~|
    			if (add >= lowerValues.peek()) { // |L| <= A ... |L|<--A
    				lowerValues.push(add);
    			} else if (add < lowerValues.peek()) { // |L| > A ... |L|-->|~| ... A-->|U|
    				while (add < lowerValues.peek()) {
        				move = lowerValues.pop();
        				upperValues.push(move);
        				inserted = false;
        				if (lowerValues.isEmpty()) {
        					lowerValues.push(add);
        					inserted = true;
        					break;
        				}
    				}
    				if(!inserted) {
    					lowerValues.push(add);
    				}
    				
    			}
    			
    			
    		} else if (!upperValues.isEmpty() && lowerValues.isEmpty()) {	// |~| A |U|
    			if (add <= upperValues.peek()) { // A <= |U| ... A-->|U|
    				upperValues.push(add);
    			} else if (add > upperValues.peek()) { // A > |U| ... |~|<--|U| ... |L|<--A
    				while (add > upperValues.peek()) {
        				move = upperValues.pop();
        				lowerValues.push(move);
        				inserted = false;
        				if (upperValues.isEmpty()) {
        					upperValues.push(add);
        					inserted = true;
        					break;
        				}
    				}
    				if(!inserted) {
    					upperValues.push(add);
    				}
    				
    			}
    			
    			
    		} else { // |L| A |R|
    			if (add <= upperValues.peek()) {
    				if (add > lowerValues.peek()) { // |L| <= A ... |L|<--A
        				upperValues.push(add);
        			} else if (add < lowerValues.peek()) { // |L| > A ... |L|-->|~| ... A-->|U|
        				while (add < lowerValues.peek()) {
            				move = lowerValues.pop();
            				upperValues.push(move);
            				inserted = false;
            				if (lowerValues.isEmpty()) {
            					lowerValues.push(add);
            					inserted = true;
            					break;
            				}
        				}
        				if(!inserted) {
        					lowerValues.push(add);
        				}
        			}
    			} else if (add > upperValues.peek()) {
    				while (add > upperValues.peek()) {
        				move = upperValues.pop();
        				lowerValues.push(move);
        				inserted = false;
        				if (upperValues.isEmpty()) {
        					upperValues.push(add);
        					inserted = true;
        					break;
        				}
    				}
    				if(!inserted) {
    					upperValues.push(add);
    				}
    			}
    		}
    		
    	/*	if (!upperValues.isEmpty()) {
    			System.out.println("  Upper: "+upperValues.peek());
    		} else {
    			System.out.println("  Upper: EMPTY");
    		}
    		if (!lowerValues.isEmpty()) {
    			System.out.println("  Lower: "+lowerValues.peek());
    		} else {
    			System.out.println("  Lower: EMPTY");
    		}*/
    		System.out.println(" Lower: " + lowerValues.toString());
    		System.out.println(" Upper: " + upperValues.toString());
    		
    		//upperValues.push(data[i]);
    	}
    	
    	while (!lowerValues.isEmpty()) {
    		move = lowerValues.pop();
    		upperValues.push(move);
    	}
    	
    	
    	
    	// upperValues to result - should be sorted
    	for (int j = 0; j < data.length; j ++) {
    		result[j] = upperValues.pop();
    	}
    	
        return result;

    }

    /**
     * Load an array with data values
     *
     * @param size The number of data values to generate and place in the array.
     * @param min The minimum value to generate.
     * @param max The maximum value to generate.
     * @return     An array of randomly generated integers. 
     */
    private static int[] createArray(int size, int min, int max) {

        Random generator = new Random();

        // If we get a negative size, just make the size 1
        if (size < 0) {
            size = 1;
        }
        // We need max > min for the random number generator to be happy

        if (max <= min) {
            max = min + 1;
        }

        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = min + generator.nextInt(max - min);
        }

        return data;
    }

    /**
     * Create a string with the data values from an array
     *
     * @param data An array of integer values.
     * @return A string representation of the array.
     */
    private static String representationOfArray(int data[]) {
        String result = new String("< ");
        for (int i = 0; i < data.length; i++) {
            result += data[i] + " ";
        }
        result += ">";

        return result;
    }

    /**
     * Get an integer value
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt) {
        Scanner input;
        int result = 10;        //default value is 10
        try {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }
}
