import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static int rows;
	static int columns;
	static int min_ingredients;
	static int max_cells;
	static Pizza.Ingredients[][] matrix;

	public static void readFile(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));

		String info = reader.readLine();
		String[] slice_details = info.split(" ");
		rows = Integer.parseInt(slice_details[0]);
		columns = Integer.parseInt(slice_details[1]);
		min_ingredients = Integer.parseInt(slice_details[2]);
		max_cells = Integer.parseInt(slice_details[3]);
		matrix = new Pizza.Ingredients[rows][columns];

		String line = null;
		
		int temp_j = 0;

		while ((line = reader.readLine()) != null) {

			for (int i = 0; i < line.length(); i++) {
				//System.out.println(line.charAt(i));
				//System.out.println(line.length());
				
				if (line.charAt(i) == 'T')
					matrix[temp_j][i] = Pizza.Ingredients.TOMATO;
				else
					matrix[temp_j][i] = Pizza.Ingredients.MUSHROOM;
				
			}

			temp_j++;
		}
	}

	public static void main(String[] args) throws IOException {
		int score = 0;
		score += tryFile("example");
		score += tryFile("small");
		score += tryFile("medium");
		score += tryFile("big");
		
		System.out.println("TOTAL SCORE: " + score);
	}
	
	public static int tryFile(String file) throws IOException {
		readFile("res/" + file + ".in");
		
		Vector<Slice> res = Algorithm.solve(min_ingredients, max_cells, matrix, true);
		
		int score = 0;
		for(Slice s : res) {
			score += s.getArea();
		}
		
		System.out.println("Slices (" + file + "): " + res.size());
		System.out.println("   Score: " + score);
		Output.write(res, "res/" + file + ".out");
		
		return score;
	}

}
