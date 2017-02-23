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
            cache.favourites = cache.getConnectedVideos();
            cache.favourites.sort(cache.localRequestComparator);
        }


        //


        return 0;
    }
}
