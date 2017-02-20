import java.util.ArrayList;
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
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//test();
		readFile("res/example.in");
	}
	
	public static void test() {
		System.out.println("Start");
		
		DataCenter c = new DataCenter();
		c.grid = new int[2][];
		c.grid[0] = new int[5];
		c.grid[1] = new int[5];
		c.grid[0][0] = -1;
		c.servers = new ArrayList<>();
		
		Server s = new Server();
		s.capacity = 10;
		s.size = 3;
		s.posX = -1;
		s.posY = -1;
		s.id = 0;
		c.servers.add(s);
		
		s = new Server();
		s.capacity = 10;
		s.size = 3;
		s.posX = -1;
		s.posY = -1;
		s.id = 1;
		c.servers.add(s);
		
		s = new Server();
		s.capacity = 5;
		s.size = 2;
		s.posX = -1;
		s.posY = -1;
		s.id = 2;
		c.servers.add(s);
		
		s = new Server();
		s.capacity = 5;
		s.size = 1;
		s.posX = -1;
		s.posY = -1;
		s.id = 3;
		c.servers.add(s);
		
		s = new Server();
		s.capacity = 1;
		s.size = 1;
		s.posX = -1;
		s.posY = -1;
		s.id = 4;
		c.servers.add(s);
		
		
		Algorithm.allocatePositions(c);
		for(Server serv : c.servers) {
			System.out.println(serv.id + "(" + serv.size + "," + serv.capacity + "): [" + serv.posX + ", " + serv.posY + "]");
		}

		System.out.println("End");
	}
	
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
		data.servers = new ArrayList<Server>();
		
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
}
