import java.util.ArrayList;
import java.util.List;

/**
 * @Purpose: The CorrectnessTest class is used to validate the correctness of
 *           the implemented algorithms. You can add additional methods if you
 *           need
 * 
 * @author RYK
 * @since 30/10/2019 extended by @author
 */

public class CorrectnessTest {
	static Algorithms a = new Algorithms();
	
	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("*********** Correctness testing *************");
		System.out.println("*********************************************");
		System.out.println();
		
		BOTHtestRuleAPart1();
		BOTHtestRuleAPart2();
		BOTHtestRuleB();
		BOTHtestRuleCPart1();
		BOTHtestRuleCPart2();
		BOTHtestRuleCPart3();
		BOTHtestRuleE();
		BOTHtestRuleF();
		exampleNFTrace();
		exampleFFTrace();

	}
	
	public static void BOTHtestRuleAPart1() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Shape(100,100));
		
		/*
		 * EXPECTED
		Sheet 1
		=================
		| H: 100 W: 100 |
		=================
		*/
		
		int expectedSheets = 1; 
		int expectedShelves = 1; 
		int expectedShapesOnShelf1 = 1; 
		
		System.out.println("RULE A PART 1:\nA shape is placed at the bottom left corner of a\nsheet (starting the first shelf on the sheet)");
		System.out.println();
		
		//NextFit
		List<Sheet> results = a.nextFit(shapes);
		
		boolean x = results.size() == expectedSheets;
		boolean y = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		boolean z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		
		if (x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("Next Fit Visual Output:\n");
		a.print(results);
		
		//FirstFit
		results = a.firstFit(shapes);
		
		x = results.size() == expectedSheets;
		y = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		
		if (x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("First Fit Visual Output:\n");
		a.print(results);

		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleAPart2() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		
		/*
		 * EXPECTED
		Sheet 1
		==================================
		| H: 100 W: 100 || H: 100 W: 150 |
		==================================
		*/
		
		int expectedSheets = 1; 
		int expectedShelves = 1; 
		int expectedShapesOnShelf1 = 2; 
		
		System.out.println("RULE A PART 2:\nA shape is placed to the right of another shape, if\nthere is sufficient space in the shelf");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean x = results.size() == expectedSheets;
		boolean y = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		boolean z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		
		if (x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		
		x = results.size() == expectedSheets;
		y = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		
		if (x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleB() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		shapes.add(new Shape(25, 75));
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 100 W: 100 || H: 100 W: 150 || H:  75 W:  25 | <---- Last entry has been rotated
		===================================================
		*/
		
		int expectedSheets = 1; 
		int expectedShelves = 1; 
		int expectedShapesOnShelf1 = 3;
		int expectedHeightOfLastShape = 75;
		
		System.out.println("RULE B:\nIf a shape does not fit in a shelf, one rotates the\nshape and tries to fit it in the shelf");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		boolean w = results.size() == expectedSheets;
		boolean x = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		boolean y = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		boolean z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().get(2).getHeight() == expectedHeightOfLastShape;
		
		if (w && x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		w = results.size() == expectedSheets;
		x = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		y = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf1;
		z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().get(2).getHeight() == expectedHeightOfLastShape;
		
		if (w && x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleCPart1() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		shapes.add(new Shape(150,100));
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 100 W: 150 |
		| H: 100 W: 100 || H: 100 W: 150 |
		===================================================
		*/
		
		int expectedSheets = 1; 
		int expectedShelves = 2; 
		int expectedShapesOnShelf1 = 2;
		int expectedShapesOnShelf2 = 1;
		int expectedHeightOfLastShape = 100;
		
		System.out.println("RULE C PART 1:\nIf a shape still does not fit in a shelf, one can start a new shelf\ndirectly on top of the current shelf against the left side of the sheet,\nif there is enough space in the sheet. First, try to create a new shelf with the\nshape in its original orientation");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean v = results.size() == expectedSheets;
		boolean w = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		boolean x = results.get(expectedSheets - 1).getShelves().get(expectedShelves-2).getShapes().size() == expectedShapesOnShelf1;
		boolean y = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf2;
		boolean z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().get(expectedShapesOnShelf2-1).getHeight() == expectedHeightOfLastShape;
		
		if (v && w && x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		v = results.size() == expectedSheets;
		w = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		x = results.get(expectedSheets - 1).getShelves().get(expectedShelves-2).getShapes().size() == expectedShapesOnShelf1;
		y = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf2;
		z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().get(expectedShapesOnShelf2-1).getHeight() == expectedHeightOfLastShape;
		
		if (v && w && x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleCPart2() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		shapes.add(new Shape(100,200));
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 100 W: 200 |
		| H: 100 W: 100 || H: 100 W: 150 |
		===================================================
		*/
		
		int expectedSheets = 1; 
		int expectedShelves = 2; 
		int expectedShapesOnShelf1 = 2;
		int expectedShapesOnShelf2 = 1;
		int expectedHeightOfLastShape = 100;
		
		System.out.println("RULE C PART 2:\nIf shape in original orientation is too big to create new shelf, rotate it and try again");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean v = results.size() == expectedSheets;
		boolean w = results.get(expectedSheets - 1).getShelves().size() == expectedShelves;
		boolean x = results.get(expectedSheets - 1).getShelves().get(expectedShelves-2).getShapes().size() == expectedShapesOnShelf1;
		boolean y = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf2;
		boolean z = results.get(expectedSheets - 1).getShelves().get(expectedShelves-1).getShapes().get(expectedShapesOnShelf2-1).getHeight() == expectedHeightOfLastShape;
		
		if (v && w && x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		v = results.size() == expectedSheets;
		w = results.get(0).getShelves().size() == expectedShelves;
		x = results.get(0).getShelves().get(expectedShelves-2).getShapes().size() == expectedShapesOnShelf1;
		y = results.get(0).getShelves().get(expectedShelves-1).getShapes().size() == expectedShapesOnShelf2;
		z = results.get(0).getShelves().get(expectedShelves-1).getShapes().get(expectedShapesOnShelf2-1).getHeight() == expectedHeightOfLastShape;
		
		if (v && w && x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleCPart3() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		shapes.add(new Shape(250,200));
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 100 W: 100 || H: 100 W: 150 |
		===================================================
		
		Sheet 2
		===================================================
		| H: 200 W: 250 |
		===================================================
		*/
		
		int expectedSheets = 2; 
		int expectedShelvesSheet1 = 1;
		int expectedShelvesSheet2 = 1;
		int expectedShapesOnlastShelf = 1;
		
		System.out.println("RULE C PART 3:\nIf both original and rotated version of the shape are too big to create new shelf, create new sheet");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean v = results.size() == expectedSheets;
		boolean w = results.get(0).getShelves().size() == expectedShelvesSheet1;
		boolean x = results.get(1).getShelves().size() == expectedShelvesSheet2;
		boolean y = results.get(1).getShelves().get(0).getShapes().size() == expectedShapesOnlastShelf;
		
		if (v && w && x && y) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		v = results.size() == expectedSheets;
		w = results.get(0).getShelves().size() == expectedShelvesSheet1;
		x = results.get(1).getShelves().size() == expectedShelvesSheet2;
		y = results.get(1).getShelves().get(0).getShapes().size() == expectedShapesOnlastShelf;
		
		if (v && w && x && y) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleE() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(100,100));
		shapes.add(new Shape(100,150));
		shapes.add(new Shape(250,200));
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 100 W: 100 || H: 100 W: 150 |
		===================================================
		
		Sheet 2
		===================================================
		| H: 200 W: 250 |
		===================================================
		*/
		
		int maxHeight = 250;
		
		System.out.println("RULE E:\nTotal height of all shelves in a sheet must be <= to the max (250 in this case)");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean w = results.get(0).allShelvesHeight() <= maxHeight;
		boolean x = results.get(1).allShelvesHeight() <= maxHeight;
		
		if (w && x) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		w = results.get(0).allShelvesHeight() <= maxHeight;
		x = results.get(1).allShelvesHeight() <= maxHeight;
		
		if (w && x) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void BOTHtestRuleF() {
		boolean NextFitpass = false;
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(30,10)); //1
		shapes.add(new Shape(30,10)); //2
		shapes.add(new Shape(30,10)); //3
		shapes.add(new Shape(30,10)); //4
		shapes.add(new Shape(30,10)); //5
		shapes.add(new Shape(30,10)); //6
		shapes.add(new Shape(30,10)); //7
		shapes.add(new Shape(30,10)); //8
		shapes.add(new Shape(30,10)); //9
		shapes.add(new Shape(30,10)); //10
		shapes.add(new Shape(30,10)); //11
		shapes.add(new Shape(30,10)); //12
		shapes.add(new Shape(30,10)); //13
		shapes.add(new Shape(30,10)); //14
		shapes.add(new Shape(30,10)); //15
		shapes.add(new Shape(30,10)); //16
		shapes.add(new Shape(30,10)); //17
		shapes.add(new Shape(30,10)); //18
		shapes.add(new Shape(30,10)); //19
		shapes.add(new Shape(30,10)); //20
		shapes.add(new Shape(20,20)); //21
		
		/*
		 * EXPECTED
		Sheet 1
		===================================================
		| H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 |x2
		| H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 || H: 10 W: 30 |x2
		===================================================
		
		Sheet 2
		===================================================
		| H: 10 W: 30 |
		===================================================
		*/
		
		int expectedShapesInSheet1 = 20;
		int expectedShapesInSheet2 = 1;
		
		System.out.println("RULE F:\nTotal number of shapes in a sheet can't exceed the max (20 in this case)");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		int total = 0;
		for (int i = 0; i < results.get(0).getShelves().size(); i++){
			total += results.get(0).getShelves().get(i).getShapes().size();
		}
		
		boolean w = total == expectedShapesInSheet1;
		
		total = 0;
		for (int i = 0; i < results.get(1).getShelves().size(); i++){
			total += results.get(1).getShelves().get(i).getShapes().size();
		}
		
		
		boolean x = total == expectedShapesInSheet2; 
		
		if (w && x) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		results = a.firstFit(shapes);
		total = 0;
		for (int i = 0; i < results.get(0).getShelves().size(); i++){
			total += results.get(0).getShelves().get(i).getShapes().size();
		}
		
		w = total == expectedShapesInSheet1;
		
		total = 0;
		for (int i = 0; i < results.get(1).getShelves().size(); i++){
			total += results.get(1).getShelves().get(i).getShapes().size();
		}
		
		x = total == expectedShapesInSheet2; 
		
		if (w && x) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass + "\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void exampleNFTrace() {
		boolean NextFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(161,222));
		shapes.add(new Shape(63,137));
		shapes.add(new Shape(99,132));
		shapes.add(new Shape(161,100));
		shapes.add(new Shape(20,173));
		shapes.add(new Shape(67,88));
		shapes.add(new Shape(25,187));
		shapes.add(new Shape(217,162));
		shapes.add(new Shape(90,59));
		shapes.add(new Shape(27,118));
		
		/*
		 * EXPECTED
		Sheet 1
		====================================
		| H: 222 W: 161 || H: 137 W:  63 |
		====================================
		
		Sheet 2
		====================================
		| H:  88 W:  67 || H:  25 W: 187 |
		| H:  20 W: 173 |
		| H: 132 W:  99 || H: 100 W: 161 |
		====================================
		
		Sheet 3
		====================================
		| H:  27 W: 118 |
		| H: 162 W: 217 || H:  90 W:  59 |
		====================================
		*/
		
		int expectedSheets = 3;
		int expectedShelvesSheet1 = 1;
		int expectedShelvesSheet2 = 3;
		int expectedShelvesSheet3 = 2;
		
		System.out.println("EXAMPLE TRACE:");
		System.out.println();
		
		List<Sheet> results = a.nextFit(shapes);
		
		boolean w = results.size() == expectedSheets;
		boolean x = results.get(0).getShelves().size() == expectedShelvesSheet1;
		boolean y = results.get(1).getShelves().size() == expectedShelvesSheet2;
		boolean z = results.get(2).getShelves().size() == expectedShelvesSheet3;
		
		if (w && x && y && z) {
			NextFitpass = true;
		}
		
		System.out.println("NextFit Visual Output:\n");
		a.print(results);
		
		
		
		System.out.println("Using hand traces and comparing the results:\nNextFit Passed = " + NextFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
	
	public static void exampleFFTrace() {
		boolean FirstFitpass = false;
		List<Shape> shapes = new ArrayList<Shape>();
		//width, height
		shapes.add(new Shape(161,222));
		shapes.add(new Shape(63,137));
		shapes.add(new Shape(99,132));
		shapes.add(new Shape(161,100));
		shapes.add(new Shape(20,173));
		shapes.add(new Shape(67,88));
		shapes.add(new Shape(25,187));
		shapes.add(new Shape(217,162));
		shapes.add(new Shape(90,59));
		shapes.add(new Shape(27,118));
		
		/*
		 * EXPECTED
		Sheet 1
		======================================================================
		| H: 222 W: 161 || H: 137 W:  63 || H: 173 W:  20 || H: 187 W:  25 || H: 118 W:  27 |
		======================================================================
		
		Sheet 2
		======================================================================
		| H:  88 W:  67 || H:  59 W:  90 |
		| H: 132 W:  99 || H: 100 W: 161 |
		======================================================================
		
		Sheet 3
		======================================================================
		| H: 162 W: 217 |
		======================================================================
		*/
		
		int expectedSheets = 3;
		int expectedShelvesSheet1 = 1;
		int expectedShelvesSheet2 = 2;
		int expectedShelvesSheet3 = 1;
		
		System.out.println("EXAMPLE TRACE:");
		System.out.println();
		
		List<Sheet> results = a.firstFit(shapes);
		
		boolean w = results.size() == expectedSheets;
		boolean x = results.get(0).getShelves().size() == expectedShelvesSheet1;
		boolean y = results.get(1).getShelves().size() == expectedShelvesSheet2;
		boolean z = results.get(2).getShelves().size() == expectedShelvesSheet3;
		
		if (w && x && y && z) {
			FirstFitpass = true;
		}
		
		System.out.println("FirstFit Visual Output:\n");
		a.print(results);
		
		
		
		System.out.println("Using hand traces and comparing the results:\nFirstFit Passed = " + FirstFitpass);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	}
}
