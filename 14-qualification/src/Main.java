import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {
	
	static int rows;
	static int slotsPerRow;
	static int unvSlots;
	static int pools;
	static int serversNum;
	static DataCenter data;
	
	public static void readFile(String filename) throws IOException {

		data = new DataCenter();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String info = reader.readLine();
		String[] details = info.split(" ");
		rows = Integer.parseInt(details[0]);
		slotsPerRow = Integer.parseInt(details[1]);
		unvSlots = Integer.parseInt(details[2]);
		pools = Integer.parseInt(details[3]);
		serversNum = Integer.parseInt(details[4]);
		data.grid = new int[rows][slotsPerRow];
		data.servers = new Vector<Server>();
		
		String line = null;
		int tempUnvSlots = 0;
		int serverId = 0;
		
		while ((line = reader.readLine()) != null) {
			
			if(tempUnvSlots < unvSlots)	{
				String[] detailsLine = line.split(" ");
				data.grid[Integer.parseInt(detailsLine[0])][Integer.parseInt(detailsLine[1])] = -1;
			}
			else {
				
				String[] detailsLine = line.split(" ");
				
				Server server = new Server();
				server.id = serverId;
				server.size = Integer.parseInt(detailsLine[0]);
				server.capacity = Integer.parseInt(detailsLine[1]);
				
				data.servers.add(server);
				serverId++;
			}
			tempUnvSlots++;
		}
	}

	public static void main(String[] args) throws IOException {
		
		readFile("res/example.in");
		System.out.println(rows + " " + slotsPerRow + " " + unvSlots + " " + pools + " " + serversNum);
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < slotsPerRow; j++)
				System.out.print(data.grid[i][j] + " ");
			System.out.println("");
		}
		
		
		for(int k = 0; k < data.servers.size(); k++)
			System.out.println(data.servers.get(k).id + " " + data.servers.get(k).size + " " + data.servers.get(k).capacity);
	}

}
