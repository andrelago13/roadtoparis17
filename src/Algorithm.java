import java.util.Vector;

public class Algorithm {
	
	public static Vector<Slice> solve(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix) {
		
		int sizeY = matrix.length;
		int sizeX = matrix[0].length;
		
		Vector<Slice> slices = new Vector<>();
		
		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				if(matrix[y][x] == Pizza.Ingredients.NONE) {
					break;
				}
				
				Slice slice = findMaxSlice(minIngredients, maxCells, matrix, x, y);
				if(slice != null) {
					slices.add(slice);
				}
				
				
			}
		}
		
		return slices;
	}
	
	private static Pizza.Ingredients[][] removeSlice(Pizza.Ingredients[][] matrix, Slice slice) {
		
	}

	/**
	 *
	 * @param minIngredients
	 * @param maxCells
	 * @param matrix
	 * @param x
	 * @param y
	 * @return the slice or null if impossible
	 */
	private static Slice findMaxSlice(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix, int x, int y) {
		// TODO
		return null;
	}

	private static boolean isValidSlice(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix, Slice slice) {
		return true;
	}
}
