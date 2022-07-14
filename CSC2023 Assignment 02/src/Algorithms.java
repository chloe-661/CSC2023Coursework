/**
 * @Purpose: The Algorithms class contains the two algorithms you have to implement  
 * Do NOT modify the names and signatures of the two algorithm methods
 * 
 * @author  RYK
 * @since   30/10/2019
 * extended by @author 
 *
 **/

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

	/**
	 * This method is used to implement the next fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests(shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e.
	 *         place all the shapes). e.g. if you pass a "shapes" list that has
	 *         two shapes {(w=200,h=200),(w=50,h=100)}, then the returned list
	 *         of sheets should show us all the information needed (e.g. that
	 *         one sheet is used, this sheet has one shelf and this shelf has
	 *         two shapes in it). In the test program, you can use the returned
	 *         list to retrieve the total number of sheets used.
	 **/

	public List<Sheet> nextFit(List<Shape> shapes) {
		List<Sheet> usedSheets = new ArrayList<Sheet>();
		
		if (shapes.size() > 0) {
			//Keeps track of current sheet and number of shapes in that sheet
			int currentSheet = 0; 	//Array index
			int currentShelf = 0; 	//Array index
			int shapesInSheet = 0;	//NOT array index
			
			//Creates initial sheet and first shelf 
			usedSheets.add(new Sheet());
			usedSheets.get(currentSheet).addShelf(new Shelf());
			
			
			for (int i = 0; i < shapes.size(); i++) {


				
				//If there is already the maximum amount of shapes in a sheet
				//Makes a new sheet and adds the first shelf
				if (shapesInSheet == Sheet.SHAPE_LIMIT) {
					usedSheets.add(new Sheet());
					currentSheet ++;
					usedSheets.get(currentSheet).addShelf(new Shelf());
					currentShelf = 0;
					shapesInSheet = 0;
				}
				
				//Variables to reduce repeated code...
				Sheet sheet = usedSheets.get(currentSheet);
				Shelf shelf = usedSheets.get(currentSheet).getShelves().get(currentShelf);
				
				//As long as the shape isn't to be the literal first to be put in a shelf 
				//AND
				//If shape is too tall or wide to fit in current shelf
				if (shelf.getHeight() != 0 && (shapes.get(i).getHeight() > shelf.getHeight() || shapes.get(i).getWidth() + shelf.getWidth() > Sheet.SHEET_WIDTH)) {
					
					
					//If shape is too tall or wide when rotated to fit in current shelf
					if ((shapes.get(i).getWidth() > shelf.getHeight() || shapes.get(i).getHeight() + shelf.getWidth() > Sheet.SHEET_WIDTH)) {

						//If sheet can fit a new shelf of shape's height
						if (sheet.allShelvesHeight() + shapes.get(i).getHeight() <= Sheet.SHEET_HEIGHT) {
							sheet.addShelf(new Shelf());
							currentShelf++;
						}
						//As long as rotated shape doesn't break dimension rules (e.g rotated shape has height of 300 when max is 250 = bad)
						//If sheet can fit a new shelf of shape's height when rotated
						else if (shapes.get(i).getWidth() < Sheet.SHEET_HEIGHT && shapes.get(i).getHeight() < Sheet.SHEET_WIDTH && sheet.allShelvesHeight() + shapes.get(i).getWidth() <= Sheet.SHEET_HEIGHT) {
							//Replace the shape with rotated shape
							shapes.set(i, new Shape(shapes.get(i).getHeight(), shapes.get(i).getWidth()));
							sheet.addShelf(new Shelf());
							currentShelf++;
						}
						//Create a new sheet and add first shelf
						else {
							usedSheets.add(new Sheet());
							currentSheet ++;
							shapesInSheet = 0;
							usedSheets.get(currentSheet).addShelf(new Shelf());
							currentShelf = 0;
						}
					}
					else {
						//Replace the shape with rotated shape
						shapes.set(i, new Shape(shapes.get(i).getHeight(), shapes.get(i).getWidth()));
					}
				}
				//Then adds the shape
				usedSheets.get(currentSheet).getShelves().get(currentShelf).place(shapes.get(i));
				shapesInSheet ++;
			}
		}
		return usedSheets;
	}

	/**
	 * This method is used to implement the first fit algorithm
	 * 
	 * @param shapes:a list of shapes representing customer requests (shapes to be
	 *            cut/placed)
	 * @return a list of the sheets used to fulfil all customer requests (i.e. place
	 *         all the shapes). e.g. if you pass a "shapes" list that has two
	 *         shapes {(w=200,h=200),(w=50,h=100)}, then the returned list of
	 *         sheets should show us all the information needed (e.g. that one
	 *         sheet is used, this sheet has one shelf and this shelf has two
	 *         shapes in it). In the test program, you can use the returned list
	 *         to retrieve the total number of sheets used
	 **/
	public List<Sheet> firstFit(List<Shape> shapes) {
		List<Sheet> usedSheets = new ArrayList<Sheet>();
		int x = 0;
		
		if (shapes.size() > 0) {
			//Creates initial sheet and first shelf 
			usedSheets.add(new Sheet());
			usedSheets.get(0).addShelf(new Shelf());
			//Adds the first shape regardless
			usedSheets.get(0).getShelves().get(0).place(shapes.get(0));
		
			for (int i = 1; i < shapes.size(); i++) {
				boolean shapeAdded = false;
				//Loops through each sheet
				for (int j = x; j < usedSheets.size(); j++) {
					
					//Keeps track of how many items on a sheet
					int totalShapesOnSheet = 0;
					for (int a = 0; a < usedSheets.get(j).getShelves().size(); a++) {
						totalShapesOnSheet += usedSheets.get(j).getShelves().get(a).getShapes().size();
					}
					
					//Loops through each shelf
					for (int k = 0; k < usedSheets.get(j).getShelves().size(); k ++) {
						
						//Variables to reduce repeated code...
						Shelf shelf = usedSheets.get(j).getShelves().get(k);
						
						//As long as there isn't too many shapes already on a sheet
						if (totalShapesOnSheet < Sheet.SHAPE_LIMIT) {
							//If it is the first shape on the sheet
							if (shelf.getHeight() == 0) {
								shelf.place(shapes.get(i));
								shapeAdded = true;
								break;
							}
							//If the shape will fit on the shelf
							else if (shapes.get(i).getHeight() <= shelf.getHeight() && shapes.get(i).getWidth() + shelf.getWidth() <= Sheet.SHEET_WIDTH) {
								shelf.place(shapes.get(i));
								shapeAdded = true;
								break;
							}
							//If the rotated shape will fit on the shelf
							else if (shapes.get(i).getWidth() <= shelf.getHeight() && shapes.get(i).getHeight() + shelf.getWidth() <= Sheet.SHEET_WIDTH) {
								//Replace the shape with rotated shape
								shapes.set(i, new Shape(shapes.get(i).getHeight(), shapes.get(i).getWidth()));
								shelf.place(shapes.get(i));
								shapeAdded = true;
								break;
							}
						}
						else {
							break;
						}
					}
					
					
					if (shapeAdded == true) {
						break;
					}
					//Checks if it can add a new shelf onto the current shelf
					if (shapeAdded == false) {
						if (totalShapesOnSheet < Sheet.SHAPE_LIMIT) {
							//If it can add new shelf with original shape
							if (shapes.get(i).getHeight() <= Sheet.SHEET_HEIGHT - usedSheets.get(j).allShelvesHeight() && shapes.get(i).getWidth() < Sheet.SHEET_WIDTH) {
								usedSheets.get(j).addShelf(new Shelf());
								int lastShelf = usedSheets.get(j).getShelves().size() -1;
								usedSheets.get(j).getShelves().get(lastShelf).place(shapes.get(i));
								break;
							}
							//If it can add new shelf with rotated shape
							else if (shapes.get(i).getWidth() <= Sheet.SHEET_HEIGHT - usedSheets.get(j).allShelvesHeight() && shapes.get(i).getHeight() < Sheet.SHEET_WIDTH) {
								usedSheets.get(j).addShelf(new Shelf());
								int lastShelf = usedSheets.get(j).getShelves().size() -1;
								//Replace the shape with rotated shape
								shapes.set(i, new Shape(shapes.get(i).getHeight(), shapes.get(i).getWidth()));
								usedSheets.get(j).getShelves().get(lastShelf).place(shapes.get(i));
								break;
							}
							//If there are no new sheets to search through
							//Need to add another
							else if(j == usedSheets.size() - 1) {
								usedSheets.add(new Sheet());
								usedSheets.get(j+1).addShelf(new Shelf());
							}
						}
						//If too many shapes on a sheet already, create new sheet and stop algorithm from looking at that sheet ever aga
						else {
							usedSheets.add(new Sheet());
							usedSheets.get(j+1).addShelf(new Shelf());
							x ++;
						}
					}
				}
			}
		}
		return usedSheets;
	}
	
	public void print(List<Sheet> sheets) {
		
		/*  EXAMPLE OUTPUT (NOT CORRECT BY EITHER ALGORITHM)
		 	  
		    Sheet 1
		    ======================================== Number of shelves = 3
            | H:   2 W: 288 |						<----- | Shape 03 |
		  	| H: 153 W: 254 |						<----- | Shape 02 |
			| H:  67 W:  17 |						<----- | Shape 01 |
			========================================
			
			Sheet 2
			======================================== Number of shelves = 2
			| H:  46 W: 245 |						<----- | Shape 06 |
			| H: 184 W: 258 || H:  49 W:   7 |		<----- | Shape 04 | | Shape 05 |
			========================================
			
			Sheet 3
			======================================== Number of shelves = 1
			| H:  65 W:  33 || H:  26 W: 209 |		<----- | Shape 07 || Shape 08 |
			========================================
			
			Sheet 4
			======================================== Number of shelves = 1
			| H: 195 W: 240 |						<----- | Shape 09 |
			========================================
			
			Sheet 5
			======================================== Number of shelves = 1
			| H:  81 W:  84 |						<----- | Shape 10 |
			========================================
		 
		 */
		
		for (int i = 0; i < sheets.size(); i++) {
			System.out.println("Sheet " + (i+1));
			System.out.println("======================================================================");
			for (int j = sheets.get(i).getShelves().size()-1; j >=0; j--) {
				for (int k = 0; k < sheets.get(i).getShelves().get(j).getShapes().size(); k++) {
					int w = sheets.get(i).getShelves().get(j).getShapes().get(k).getWidth();
					int h = sheets.get(i).getShelves().get(j).getShapes().get(k).getHeight();
					System.out.format("| H: %3d W: %3d |", h, w);
				}
				System.out.println();
			}
			System.out.println("======================================================================");
			System.out.println();
		}
	}

}
