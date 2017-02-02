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
				
				
			}
		}
		
		return slices;
	}

}
