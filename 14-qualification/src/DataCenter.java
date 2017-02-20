import java.util.Comparator;
import java.util.Vector;

public class DataCenter{
	public int grid[][];
	public Vector<Server> servers;
	
	public static Comparator<Server> RatioComparator = new Comparator<Server>(){
		public int compare(Server server1, Server server2){
			if(server1.capacity/server1.size > server2.capacity/server2.size){
				return -1;
			}
			else if(server1.capacity/server1.size > server2.capacity/server2.size){
				return 1;
			}
			return 0;
		}
	};
	
	public static Comparator<Server> IdComparator = new Comparator<Server>(){
		public int compare(Server server1, Server server2){
			if(server1.id > server2.id){
				return -1;
			}
			else if(server1.id > server2.id){
				return 1;
			}
			return 0;
		}
	};
}
