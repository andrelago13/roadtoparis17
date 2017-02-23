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
			System.out.println(cache.id);
            cache.favourites = cache.getConnectedVideos();
            System.out.println(cache.favourites.size() + " " + caches.size());
            cache.favourites.sort(cache.localRequestComparator);
            System.out.println(cache.favourites.size() + " done");

            HashSet<Video> assigned = new HashSet<>();
        	int accumulatedSize = 0;
        	for(Video v : cache.favourites) {
        		if(v.size + accumulatedSize > cache.size) {
        			break;
        		}
        		accumulatedSize += v.size;
        		assigned.add(v);
        	}
        	cache.assigned = assigned;
        }
        
        return 0;
    }
}
