import java.util.ArrayList;
import java.util.Vector;

public class Algorithm {
	
	public static Vector<Slice> solve(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix) {
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
