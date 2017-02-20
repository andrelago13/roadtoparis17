import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	static int rows;
	static int slotsPerRow;
	static int unvSlots;
	static int pools;
	static int serversNum;
	static int[][] servers;
	
	public static void readFile(String filename) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String info = reader.readLine();
		String[] details = info.split(" ");
		rows = Integer.parseInt(details[0]);
		slotsPerRow = Integer.parseInt(details[1]);
		unvSlots = Integer.parseInt(details[2]);
		pools = Integer.parseInt(details[3]);
		serversNum = Integer.parseInt(details[4]);
		
		servers = new int [serversNum+unvSlots][2];
		
		String line = null;
		int tempInd = 0;
		
		while ((line = reader.readLine()) != null) {			
				
			tempInd++;
		}
	}

	public static void main(String[] args) {
		
	}

}
