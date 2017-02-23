import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gustavo on 23/02/2017.
 */
public class Solver {

    public static long solve(List<Video> videos, List<Endpoint> endpoints, List<Cache> caches) {
        for (Cache cache : caches) {
            cache.favourites = getCacheConnectedVideos(cache);
            cache.favourites.sort(cache.localRequestComparator);
        }


        //


        return 0;
    }

    public static List<Video> getCacheConnectedVideos(Cache cache) {
        Set<Video> videos = new HashSet<Video>();
        List<Request> requests = getCacheConnectedRequests(cache);
        for (Request request : requests) {
            videos.add(request.video);
        }
        return new ArrayList<Video>(videos);
    }

    public static List<Request> getCacheConnectedRequests(Cache cache) {
        List<Request> requests = new ArrayList<Request>();
        for (Connection conn : cache.connections) {
            requests.addAll(conn.endpoint.requests);
        }
        return requests;
    }
}
