import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Cache {
	public int id;
	public int size;
	public List<Video> favourites;
	public Set<Video> assigned;
	public static List<Connection> connections;
	
	public Comparator<Video> localRequestComparator = new Comparator<Video>() {
		@Override
		public int compare(Video v1, Video v2) {
			
			double v1_OriginalLatency = localVideoLatencyTimesRequests(v1);
			double v2_OriginalLatency = localVideoLatencyTimesRequests(v2);
			double v1_NewLatency = localVideoCacheLatencyTimesRequests(v1);
			double v2_NewLatency = localVideoCacheLatencyTimesRequests(v2);
			
			double v1_score = (v1_OriginalLatency/v1_NewLatency) * (size/v1.size);
			double v2_score = (v2_OriginalLatency/v2_NewLatency) * (size/v2.size);
			
			if(v1_score > v2_score) {
				return -1;
			} else if (v1_score < v2_score) {
				return 1;
			}
			
			return 0;
		}
		
	};
	
	public double localVideoLatencyTimesRequests(Video video) {
		double sumLatencyTimesRequests = 0;
		for(Connection c : connections) {
			sumLatencyTimesRequests += c.endpoint.latency*c.endpoint.getVideoRequests(video);
		}
		return sumLatencyTimesRequests;
	}
	
	public double localVideoCacheLatencyTimesRequests(Video video) {
		double sumLatencyTimesRequests = 0;
		for(Connection c : connections) {
			sumLatencyTimesRequests += c.latency*c.endpoint.getVideoRequests(video);
		}
		return sumLatencyTimesRequests;
	}
	
	
	
	
	
	
	
}
