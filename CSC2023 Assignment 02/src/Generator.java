/**
 *  @Purpose: The Generator class generates a list of shapes of random width and height.
 * Do NOT modify the names and signatures of the generator method
 * 
 * @author  RYK
 * @since   30/10/2019
 * extended by @author 
 */
import java.util.ArrayList;
import java.util.List;

public class Generator {

	public static final int MAX_SIZE_HEIGHT = 250;
	public static final int MAX_SIZE_WIDTH = 300;

	/**
	 * This method will generate a list of shapes of random width and height
	 * (integers). The random width is between 1 and MAX_SIZE_WIDTH inclusive.
	 * The random height is between 1 and MAX_SIZE_HEIGHT inclusive.
	 * 
	 * @param numberOfShapes: the number of shapes to generate
	 * @return a list of shapes of random sizes
	 */

	public List<Shape> generateShapeList(int numberOfShapes) {
		/*
		 * THIS METHOD CURRENTLY RETURNS AN EMPTY LIST SO YOU WILL NEED TO ADD
		 * IN YOUR OWN CODE TO GENERATE A LIST OF SHAPES randomly
		 */

		List<Shape> shapes = new ArrayList<Shape>();
		
		for (int i = 0; i < numberOfShapes; i++) {
			int w = (int)(Math.random() * MAX_SIZE_WIDTH) + 1;
			int h = (int)(Math.random() * MAX_SIZE_HEIGHT) + 1;
			shapes.add(new Shape(w, h));
		}
		
		return shapes;
	}

}