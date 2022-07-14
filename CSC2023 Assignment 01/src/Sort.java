
/*****************************************************/
/*** Sort class currently contains some initial    ***/
/*** methods for implementing sorting algorithms   ***/
/***                                               ***/
/***     Initial Author: Jason Steggles 20/09/19   ***/
/***     Extended by: ?                            ***/
/*****************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Sort {

/** Size of array **/
private int size;

/** Number of used elements in array **/
private int usedSize;

/** Array of integers **/
private int[] A;

/** Global variables for counting sort comparisons **/
public int compIS; /** Global comparison count for Insertion Sort **/
public int compQS; /** Global comparison count for Quicksort **/
public int compNS; /** Global comparison count for newsort **/

/*****************/
/** Constructor **/
/*****************/
Sort(int max)
{
    /** Initialiase global sort count variables **/
    compIS = 0;
    compQS = 0;
    compNS = 0;
    
    /** Initialise size variables **/
    usedSize = 0;
    size = max;
    
    /** Create Array of Integers **/
    A = new int[size];
}

/*********************************************/
/*** Read a file of integers into an array ***/
/*********************************************/
public void readIn(String file)
{
   try
   {
       /** Initialise loop variable **/
       usedSize = 0;
       
       /** Set up file for reading **/
       FileReader reader = new FileReader(file);
       Scanner in = new Scanner(reader);
       
       /** Loop round reading in data while array not full **/
       while(in.hasNextInt() && (usedSize < size))
       {
           A[usedSize] = in.nextInt();
           usedSize++;
       }
       
    }
    catch (IOException e)
    {
       System.out.println("Error processing file " + file);
    }
   
}

/*********************/
/*** Display array ***/
/*********************/
public void display(int line, String header)
{
    /*** Integer Formatter - three digits ***/
    NumberFormat FI = NumberFormat.getInstance();
    FI.setMinimumIntegerDigits(3);

    /** Print header string **/
    System.out.print("\n"+header);

    /** Display array data **/
    for (int i=0;i<usedSize;i++)
    {
        /** Check if new line is needed **/
        if (i%line == 0) 
        { 
            System.out.println(); 
        }
        
        /** Display an array element **/
        System.out.print(FI.format(A[i])+" ");
    }
}

/************/
/*** Swap ***/
/************/
public void swap(int L, int R) {
	int temp = A[L];
	A[L] = A[R];
	A[R] = temp;
}

/**********************/
/*** Insertion Sort ***/
/**********************/
public void insertion() {
	compIS = 0;
	for(int i = 1; i < usedSize; i++) {
		int key = A[i]; //Current value to insert into "sorted side"
		int j = i;
		compIS++;
		//Iterates through all the values to the left of the key
		//Until the key is smaller than one of those values
		while ((j > 0) && (key < A[j-1])) {
			A[j] = A[j-1];
			j = j-1;
			compIS++;
		}
		//Inserts the key into the correct location
		A[j] = key;
	}
}

/******************/
/*** Quick Sort ***/
/******************/
public void initQuicksort() {
	//Calls the quicksort algorithm with the best boundary pointers
	compQS = 0;
	quicksort(0, usedSize-1);
}


public void quicksort(int L, int R) {
	if (R > L) { //Makes it stop if there is only one value left
		//Location of the pivot after the partition has finished
		int p = partition(L, R); //Splits the array into two
		quicksort(L, p-1); //Sorts the left side
		quicksort(p+1, R); //Sorts the right side
	}
}

public int partition(int L, int R) {
	int v = A[R]; //Chooses the pivot value
	int pL = L; //Start of the array to be sorted
	int pR = R; //End of the array to be sorted
	while (pL < pR) { //As long as the pointers haven't crossed
		while (A[pL] < v) { //Moves the left pointer until it hits a value that is larger than the pivot
			pL = pL + 1;
			compQS++;
		}
		compQS++;
		while ((A[pR] >= v) && (pR > L)) { //Moves the right pointer until it hits a value that is smaller than the pivot
			pR = pR - 1;
			compQS++;
		}
		compQS++;
		if (pL < pR) { //As long as the pointers haven't crossed
			swap(pL, pR); //Swaps the two values at the pointers
		}
	}
	swap(pL, R); //Puts the pivot in the correct place
	return pL; 
}

/****************/
/*** New Sort ***/
/****************/
public void newsort() {
	compNS = 0;
	int pos = 0; 
	while (pos < usedSize) {
		int min = findMinFrom(pos);
		for (int i = pos; i < usedSize; i++) { //Looks through the entire array so as not to miss duplicates
			compNS++;
			//Looks sequentially through the array for a value matching the minimum value
			if (A[i] == min) {
				//Once found it swaps it with the value at the start of the array
				//Or with the next value to the right if that value has already been swapped before.
				swap(i, pos);
				pos = pos + 1;
			}
		}
	}
}

public int findMinFrom(int pos) {
	int min = A[pos]; //Sets a temp value for the smallest value in the array
	for (int i = pos + 1; i < usedSize; i++) { //Looks through the entire array
		compNS++;
		//If the current value is smaller than the smallest value already found 
		if (A[i] < min) {
			min = A[i]; //Make that the new smallest
		}
	}
	return min;
}

}  /** End of Sort Class **/
