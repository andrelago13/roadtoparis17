import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Cache {
	public int id;
	public List<Video> favourites;
	public Set<Video> assigned;
	
	public Comparator<Video> localRequestComparator = new Comparator<Video>() {

		@Override
		public int compare(Video v1, Video v2) {
			
			
			
			return 0;
		}
		
	};
}
