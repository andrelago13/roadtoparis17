import java.util.Comparator;
import java.util.Vector;

public class DataCenter implements Comparator<Server>{
	public int grid[][];
	public Vector<Server> servers;
	
	@Override
	public int compare(Server server1, Server server2) {
		if(server1.capacity/server1.size > server2.capacity/server2.size){
			return -1;
		}
		else if(server1.capacity/server1.size > server2.capacity/server2.size){
			return 1;
		}
		return 0;
	}
}
