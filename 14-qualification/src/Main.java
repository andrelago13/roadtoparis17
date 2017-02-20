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
		c.servers = new ArrayList<>();
		
		
		Algorithm.allocatePositions(c);
		for(Server s : c.servers) {
			System.out.println(s.id + ": [" + s.posX + ", " + s.posY + "]");
		}

		System.out.println("End");
	}

}
