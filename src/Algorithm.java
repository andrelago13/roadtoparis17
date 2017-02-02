import java.util.ArrayList;
import java.util.Vector;

public class Algorithm {
	
	public static Vector<Slice> solve(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix, boolean recursive) {
		
		int sizeY = matrix.length;
		int sizeX = matrix[0].length;
		
		Vector<Slice> slices = new Vector<>();
		
		for(int x = 0; x < sizeX; ++x) {
			for(int y = 0; y < sizeY; ++y) {
				if(matrix[y][x] == Pizza.Ingredients.NONE) {
					break;
				}

				Slice slice;
				if(recursive) {
					slice = findMaxSliceRec(minIngredients, maxCells, matrix, x, y);
				} else {
					slice = findMaxSlice(minIngredients, maxCells, matrix, x, y);
				}
				
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
				matrix[y + yi][x + xi] = Pizza.Ingredients.NONE;
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
		
		int size = maxCells;
		Slice slice = new Slice();
		slice.x = x;
		slice.y = y;

		while(size > 0) {
			ArrayList<Integer> divisors = divisors(size);
			int divs = divisors.size();
			int l = 0;
			int r = divs - 1;
			
			while(l < divs) {
				slice.width = divisors.get(l);
				slice.height = divisors.get(r);
				
				if(isValidSlice(minIngredients, matrix, slice)) {
					return slice;
				}
				
				++l;
				--r;
			}
			
			size -= 2;
		}
		
		return null;
	}

	private static Slice findMaxSliceRec(int minIngredients, int maxCells, Pizza.Ingredients[][] matrix, int x, int y) {
		int width = matrix[0].length;
		int height = matrix.length;
		
		int size = maxCells;
		Slice slice = new Slice();
		slice.x = x;
		slice.y = y;
		
		Slice bestSlice = null;
		int bestSliceScore = 0;

		while(size > 0) {
			ArrayList<Integer> divisors = divisors(size);
			int divs = divisors.size();
			int l = 0;
			int r = divs - 1;
			
			while(l < divs) {
				slice.width = divisors.get(l);
				slice.height = divisors.get(r);
				
				if(isValidSlice(minIngredients, matrix, slice)) {
					int childX = slice.x + slice.width;
					int childY = slice.y + slice.height;
					
					if(childX >= width || childY >= height) {
						return slice;
					}
					
					Slice child = findMaxSlice(minIngredients, maxCells, matrix, childX, childY);
					if(child == null) {
						++l;
						--r;
						continue;
					}
					
					int score = child.getArea() + slice.getArea();
					if(score > bestSliceScore) {
						bestSliceScore = score;
						bestSlice = slice;
					}					
					
					return slice;
				}
				
				++l;
				--r;
			}
			
			size -= 2;
		}
		
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
        if (slice.x + slice.width > matrix[0].length)
            return false;
        if (slice.y + slice.height > matrix.length)
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
		res.add(num);
		return res;
	}
}
