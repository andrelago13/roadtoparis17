import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		Video v1 = new Video();
		v1.numHits=10;
		v1.size=20;
		
		Video v2 = new Video();
		v2.numHits=1;
		v2.size=2;
		
		Cache cache = new Cache();
		cache.assigned = new HashSet<Video>();
		cache.assigned.add(v1);
		cache.assigned.add(v2);
		
		List<Cache> caches = new ArrayList<Cache>();
		caches.add(cache);
		
		Output output=new Output();
		output.write(caches, "test");
	}

}
