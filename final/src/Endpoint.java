import java.util.HashMap;
import java.util.List;

public class Endpoint {
	public int id;
	public int latency;
	public List<Connection> connections;
	public List<Request> requests;
	public HashMap<Integer, Integer> videoRequests = new HashMap<Integer, Integer>();
	
	public int getVideoRequests(Video v) {
		/*for(Request r : requests) {
			if(r.video == v) {
				return r.hits;
			}
		}
		return 0;*/
		if (videoRequests.get(v.id) != null) {
			return videoRequests.get(v.id);
		}
		return 0;
	}
}
