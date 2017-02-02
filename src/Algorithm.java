import java.util.ArrayList;
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
		return null;
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

    /**
     * Assumes the number of cells of the slice does not exceed the maximum number of cells per slice.
     * @param minIngredients
     * @param matrix
     * @param slice
     * @return
     */
	private static boolean isValidSlice(int minIngredients, Pizza.Ingredients[][] matrix, Slice slice) {
        if (slice.x + slice.width >= matrix[0].length)
            return false;
        if (slice.y + slice.height >= matrix.length)
            return false;
        int tomatoes = 0;
        int mushrooms = 0;
		for (int y = slice.y; y < slice.y + slice.height; y++) {
            for (int x = slice.x; x < slice.x + slice.width; x++) {
                switch (matrix[y][x]) {
                    case NONE: return false;
                    case TOMATO: tomatoes++; break;
                    case MUSHROOM: mushrooms++; break;
                }
            }
        }
        if (tomatoes < minIngredients || mushrooms < minIngredients)
            return false;
		return true;
	}

	private static ArrayList<Integer> divisors(int num) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0) {
				res.add(i);
			}
		}
		return res;
	}
}
