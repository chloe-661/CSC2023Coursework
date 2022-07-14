
/*************************************************/
/***  Simple test class for Sort class         ***/
/***                                           ***/
/***  Author: Jason Steggles    20/09/2019     ***/
/*************************************************/


public class TestSort {
	public static Sort sortTest = new Sort(1000);
	
	public static void main(String[] args) {
		 /**Chose a file**/
        String file = "test1.txt";
        
        
        /** Choose a test **/
        /** - displayArrayTest...() Shows the array before and after **/
        /** - test...() Just shows the comparison count **/
        displayArrayTestIS(file);
//      displayArrayTestQS(file);
//      displayArrayTestNS(file);
//      testIS(file);
//      testQS(file);
//      testNS(file);
        
    }
	
	public static void testIS(String file) {
		/** Reads in the array **/
        sortTest.readIn(file);
		
		/** Insertion Sort Test **/
        sortTest.insertion();
    	System.out.println("\nInsertion Sort Completed on file: " + file + "\nComparisons: " + sortTest.compIS);
	}
	
	public static void testQS(String file) {
		/** Reads in the array **/
        sortTest.readIn(file);
		
		/** Quick Sort Test **/
        sortTest.initQuicksort();
    	System.out.println("\nQuick Sort Completed on file: " + file + "\nComparisons: " + sortTest.compQS);
	}
	
	public static void testNS(String file) {
		/** Reads in the array **/
        sortTest.readIn(file);
		
		/** New Sort Test **/
        sortTest.newsort();
    	System.out.println("\nNew Sort Completed on file: " + file + "\nComparisons: " + sortTest.compNS);
	}
	
	public static void displayArrayTestIS(String file) {
		/** Reads in the array & Displays the array **/
        sortTest.readIn(file);
        sortTest.display(6,"Values in file: " + file);
		
		/** Insertion Sort Test **/
        sortTest.insertion();
    	sortTest.display(6,"\nAfter Insertion Sort");
    	System.out.println("\n\nComparisons: " + sortTest.compIS);
	}
	
	public static void displayArrayTestQS(String file) {
		/** Reads in the array & Displays the array **/
        sortTest.readIn(file);
        sortTest.display(6,"Values in file: " + file);
		
        /** Quick Sort Test **/
        sortTest.initQuicksort();
        sortTest.display(6,"\nAfter Quick Sort");
        System.out.println("\n\nComparisons: " + sortTest.compQS);
	}
	
	public static void displayArrayTestNS(String file) {
		/** Reads in the array & Displays the array **/
        sortTest.readIn(file);
        sortTest.display(6,"Values in file: " + file);
		
        /** New Sort Test **/
        sortTest.newsort();
        sortTest.display(6,"\nAfter New Sort");
        System.out.println("\n\nComparisons: " + sortTest.compNS);
	}
    
} /** End of TestSort class **/
