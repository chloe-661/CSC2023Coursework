import java.util.List;

/**
 * @Purpose: The PerformanceTest class is used to compare the implemented
 *           algorithms in term of time and the number of sheets used
 *
 *           You can add additional methods if you need to in this class
 * 
 * @author RYK
 * @since 30/10/2019
 * extended by @author
 */

public class PerformanceTest {
	

	public static void main(String[] args) {
		
		System.out.println("***********************************************");
		System.out.println("*********** Performance analysis **************");
		System.out.println("***********************************************");

		System.out.println();

		//DEFINES THE STARTING PARAMETERS FOR THE TEST
		int noOfTests = 8;
		int noOfShapes = 10;
		int increment = 10000;
		int noOfRep = 5;
		
		Generator g = new Generator();
		Algorithms a = new Algorithms();
		
		//TABLE HEADER FOR PRINTING
		System.out.println("| Num Of Shapes | Num Of Tests | Type of Test | Avg Sheets | Avg Time (mls) |");
		System.out.print  ("============================================================================");
		
		//REPEATS THE TEST FOR A LARGER NUMBER OF SHAPES EACH TIME
		for (int i = 0; i < noOfTests; i++) {
			
			//KEEPS TRACK OF OVERALL RESULTS
			long NFcumulativeTime = 0;
			long FFcumulativeTime = 0;
			double NFcumulativeSheets = 0;
			double FFcumulativeSheets = 0;
			
			//REPEATS THE SAME TEST OVER AND OVER TO ALLOW FOR AVERAGE CALCULATION
			for (int j = 0; j < noOfRep; j++) {
				List<Sheet> results;
				List<Shape> shapes = g.generateShapeList(noOfShapes);
				long startTime = 0;
				long endTime = 0;
				
				//TEST NEXT FIT
				startTime = System.nanoTime();;
				results = a.nextFit(shapes);
				endTime = System.nanoTime();
				NFcumulativeTime += endTime - startTime;
				NFcumulativeSheets += results.size();
				
				//TEST FIRST FIT
				endTime = System.nanoTime();;
				results = a.firstFit(shapes);
				endTime = System.nanoTime();;
				FFcumulativeTime += endTime - startTime;
				FFcumulativeSheets += results.size();
			}
			
			//WORKS OUT AVERAGES FOR TIME
			//IN MILLISECONDS
			double NFaverageTime =  ((double) NFcumulativeTime / (double) noOfRep) / 1000000;
			double FFaverageTime =  ((double) FFcumulativeTime / (double) noOfRep) / 1000000;
			
			//WORKS OUT AVERAGES FOR SHEETS, ROUNDS THE VALUE
			double NFaverageSheets =  Math.round(NFcumulativeSheets / noOfRep);
			double FFaverageSheets =  Math.round(FFcumulativeSheets / noOfRep);
			
			//PRINTS IN TABLE FORMAT
			String NF = "Next Fit";
			String FF = "First Fit";
			System.out.format("\n| %-13d | %-12d | %-12s | %-10.0f | %-10.3f", noOfShapes, noOfRep, NF, NFaverageSheets, NFaverageTime);
			System.out.format("\n|               |              | %-12s | %-10.0f | %-10.3f", FF, FFaverageSheets, FFaverageTime);
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
