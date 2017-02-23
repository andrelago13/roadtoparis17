import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gustavo on 23/02/2017.
 */
public class Solver {

    public static long solve(List<Video> videos, List<Endpoint> endpoints, List<Cache> caches) {
    	int index = 0;
    	int size = caches.size();
        for (Cache cache : caches) {
            cache.favourites = cache.getConnectedVideos();
            System.out.println(cache.favourites.size() + " " + size + "  ==  " + index + "/" + size);
            cache.favourites.sort(cache.localRequestComparator);
            System.out.println(cache.favourites.size() + " done" + "  ==  " + index + "/" + size);

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
        	++index;
        }
        
        return 0;
    }
}
