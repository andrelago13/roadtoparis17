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
					matrix = removeSlice(matrix, slice);
				}
			}
		}
		
		return slices;
	}
	
	private static Pizza.Ingredients[][] removeSlice(Pizza.Ingredients[][] matrix, Slice slice) {
		
		int x = slice.x;
		int y = slice.y;
		
		for(int xi = 0; xi < slice.width; ++xi) {
			for(int yi = 0; yi < slice.height; ++yi) {
				matrix[yi][xi] = Pizza.Ingredients.NONE;
			}
		}
		
		return matrix;
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
		// TODO
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
