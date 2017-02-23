import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 23/02/2017.
 */
public class Solver {

    public static List<Request> getCacheConnectedRequests(Cache cache, List<Connection> connections) {
        List<Request> requests = new ArrayList<Request>();
        for (Connection conn : connections) {
            List<Endpoint> endpoints = null; // conn.endpoints;
            for (Endpoint endpoint : endpoints) {
                requests.addAll(endpoint.requests);
            }
        }
        return null;
    }
}
