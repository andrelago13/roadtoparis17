import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gustavo on 23/02/2017.
 */
public class Solver {

    public static List<Video> getCacheConnectedVideos(Cache cache, List<Connection> connections) {
        Set<Video> videos = new HashSet<Video>();
        List<Request> requests = getCacheConnectedRequests(cache, connections);
        for (Request request : requests) {
            //videos.add(request.video);
        }
        return new ArrayList<Video>(videos);
    }

    public static List<Request> getCacheConnectedRequests(Cache cache, List<Connection> connections) {
        List<Request> requests = new ArrayList<Request>();
        for (Connection conn : connections) {
            List<Endpoint> endpoints = null; // conn.endpoints;
            for (Endpoint endpoint : endpoints) {
                requests.addAll(endpoint.requests);
            }
        }
        return requests;
    }
}
