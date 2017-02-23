import java.util.List;

public class Endpoint {
	public int id;
	public int latency;
	public List<Connection> connections;
	public List<Request> requests;
	
	public int getVideoRequests(Video v) {
		for(Request r : requests) {
			if(r.video == v) {
				return r.hits;
			}
		}
		return 0;
	}
}
