import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
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

}
