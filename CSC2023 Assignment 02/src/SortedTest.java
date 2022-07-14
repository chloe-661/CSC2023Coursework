import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Purpose: The SortedTest class is used to compare the implemented algorithms
 *           in term of the number of sheets used WHEN the list of
 *           shapes is SORTED
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2019
 * extended by @author
 */

public class SortedTest {
	
	static Comparator<Shape> byWidth =  new Comparator<Shape>() {
        public int compare(Shape s1, Shape s2) {
        	if(s1.getWidth() > s2.getWidth()) 
    			return 1; 
    	    if(s1.getWidth() < s2.getWidth()) 
    	    	return -1;
    	    else                   
    	    	return 0;
        }
    };
    
    static Comparator<Shape> byHeight =  new Comparator<Shape>() {
        public int compare(Shape s1, Shape s2) {
        	if(s1.getHeight() > s2.getHeight()) 
    			return 1; 
    	    if(s1.getHeight() < s2.getHeight()) 
    	    	return -1;
    	    else                   
    	    	return 0;
        }
    };
        
    static Comparator<Shape> byArea =  new Comparator<Shape>() {
        public int compare(Shape s1, Shape s2) {
        	if(s1.getWidth()*s1.getHeight() > s2.getWidth()*s2.getHeight()) 
    			return 1; 
    	    if(s1.getWidth()*s1.getHeight() < s2.getWidth()*s2.getHeight()) 
    	    	return -1;
    	    else                   
    	    	return 0;
        }
    };
	
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("**************** Sorted Test ****************");
		System.out.println("*********************************************");
		System.out.println();
		
		//NEXT FIT --------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\nTESTING Next Fit ALGORITHM ONLY");
		System.out.println("Sorting by Width");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byWidth, "nextfit");
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\n\nTESTING Next Fit ALGORITHM ONLY");
		System.out.println("Sorting by Height");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byHeight, "nextfit");
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\n\nTESTING Next Fit ALGORITHM ONLY");
		System.out.println("Sorting by Height * Width");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byArea, "nextfit");
		
		//FIRST FIT --------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\nTESTING First Fit ALGORITHM ONLY");
		System.out.println("Sorting by Width");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byWidth, "firstfit");
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\n\nTESTING First Fit ALGORITHM ONLY");
		System.out.println("Sorting by Height");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byHeight, "firstfit");
		
		//TABLE HEADER FOR PRINTING
		System.out.println("\n\nTESTING First Fit ALGORITHM ONLY");
		System.out.println("Sorting by Height * Width");
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		testSorted(byArea, "firstfit");

	}
	
	public static void testSorted(Comparator<Shape> sortBy, String sort) {
		//DEFINES THE STARTING PARAMETERS FOR THE TEST
		int noOfTests = 8;
		int noOfShapes = 10;
		int increment = 10000;
		int noOfRep = 5;
		
		Generator g = new Generator();
		Algorithms a = new Algorithms();
		
		//REPEATS THE TEST FOR A LARGER NUMBER OF SHAPES EACH TIME
		for (int i = 0; i < noOfTests; i++) {
			
			//KEEPS TRACK OF OVERALL RESULTS
			long UNSORTEDcumulativeTime = 0;
			long SORTEDcumulativeTime = 0;
			long SORTEDDesccumulativeTime = 0;
			
			double UNSORTEDcumulativeSheets = 0;
			double SORTEDcumulativeSheets = 0;
			double SORTEDDseccumulativeSheets = 0;
			
			//REPEATS THE SAME TEST OVER AND OVER TO ALLOW FOR AVERAGE CALCULATION
			for (int j = 0; j < noOfRep; j++) {
				List<Sheet> results;
				List<Shape> shapes = g.generateShapeList(noOfShapes);
				long startTime = 0;
				long endTime = 0;
				
				//TEST UNSORTED
				startTime = System.nanoTime();;
				if (sort == "nextfit") {
					results = a.nextFit(shapes);
				}
				else if (sort == "firstfit") {
					results = a.firstFit(shapes);
				}
				else {
					results = null;
				}
				endTime = System.nanoTime();
				UNSORTEDcumulativeTime += endTime - startTime;
				UNSORTEDcumulativeSheets += results.size();
				
				Collections.sort(shapes, sortBy);
				
				//TEST SORTED (Ascending)
				endTime = System.nanoTime();;
				if (sort == "nextfit") {
					results = a.nextFit(shapes);
				}
				else if (sort == "firstfit") {
					results = a.firstFit(shapes);
				}
				else {
					results = null;
				}
				endTime = System.nanoTime();;
				SORTEDcumulativeTime += endTime - startTime;
				SORTEDcumulativeSheets += results.size();
				
				Collections.reverse(shapes);
				
				//TEST SORTED (Descending)
				endTime = System.nanoTime();;
				if (sort == "nextfit") {
					results = a.nextFit(shapes);
				}
				else if (sort == "firstfit") {
					results = a.firstFit(shapes);
				}
				else {
					results = null;
				}
				endTime = System.nanoTime();;
				SORTEDDesccumulativeTime += endTime - startTime;
				SORTEDDseccumulativeSheets += results.size();
			}
			
			//WORKS OUT AVERAGES FOR TIME
			//IN MILLISECONDS
			double UNSORTEDaverageTime =  ((double) UNSORTEDcumulativeTime / (double) noOfRep) / 1000000;
			double SORTEDaverageTime =  ((double) SORTEDcumulativeTime / (double) noOfRep) / 1000000;
			double SORTEDDescaverageTime =  ((double) SORTEDDesccumulativeTime / (double) noOfRep) / 1000000;
			
			//WORKS OUT AVERAGES FOR SHEETS, ROUNDS THE VALUE
			double UNSORTEDaverageSheets =  Math.round(UNSORTEDcumulativeSheets / noOfRep);
			double SORTEDaverageSheets =  Math.round(SORTEDcumulativeSheets / noOfRep);
			double SORTEDDescaverageSheets =  Math.round(SORTEDDseccumulativeSheets / noOfRep);
			
			//PRINTS IN TABLE FORMAT
			String UNSORTED = "Unsorted";
			String SORTEDASC = "Sorted (Asc)";
			String SORTEDDESC = "Sorted (Des)";
			System.out.format("\n| %-13d | %-12d | %-12s | %-10.0f | %-10.3f", noOfShapes, noOfRep, UNSORTED, UNSORTEDaverageSheets, UNSORTEDaverageTime);
			System.out.format("\n|               |              | %-12s | %-10.0f | %-10.3f", SORTEDASC, SORTEDaverageSheets, SORTEDaverageTime);
			System.out.format("\n|               |              | %-12s | %-10.0f | %-10.3f", SORTEDDESC, SORTEDDescaverageSheets, SORTEDDescaverageTime);
			System.out.print("\n----------------------------------------------------------------------------");
			
			//ALLOWS FOR THE PROGRAM TO GO 10, 100, 1000 BEFORE INCREMENTING BY 10000 EACH TIME
			if (noOfShapes < 10000) {
				noOfShapes *= 10;
			}
			else {
				noOfShapes += increment;
			}
		}
	}

}
