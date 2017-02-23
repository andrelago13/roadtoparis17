import java.util.*;

public class Cache {
	public int id;
	public int size;
	public List<Video> favourites;
	public Set<Video> assigned;
	public List<Connection> connections = new ArrayList<Connection>();

	public Comparator<Video> localRequestComparator = new Comparator<Video>() {
		@Override
		public int compare(Video v1, Video v2) {
			
			double v1_OriginalLatency = localVideoLatencyTimesRequests(v1);
			double v2_OriginalLatency = localVideoLatencyTimesRequests(v2);
			double v1_NewLatency = localVideoCacheLatencyTimesRequests(v1);
			double v2_NewLatency = localVideoCacheLatencyTimesRequests(v2);
			
			double v1_score = scoreCalculator(v1_OriginalLatency, v1_NewLatency, v1.size, size);
			double v2_score = scoreCalculator(v2_OriginalLatency, v2_NewLatency, v2.size, size);
			
			if(v1_score > v2_score) {
				return -1;
			} else if (v1_score < v2_score) {
				return 1;
			}
			
			return 0;
		}
		
	};

	private double scoreCalculator(double dbLatency, double cacheLatency, double videoSize, double cacheSize) {
		return (dbLatency/cacheLatency) * (cacheSize/videoSize);
	}

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

    public List<Video> getConnectedVideos() {
        Set<Video> videos = new HashSet<Video>();
        List<Request> requests = getConnectedRequests();
        for (Request request : requests) {
            videos.add(request.video);
        }
        return new ArrayList<Video>(videos);
    }

    public List<Request> getConnectedRequests() {
        List<Request> requests = new ArrayList<Request>();
        for (Connection conn : this.connections) {
            requests.addAll(conn.endpoint.requests);
        }
        return requests;
    }
}
