import java.util.List;

public class Video implements Comparable {
	public int id;
	public int numHits;
	public int size;
	public List<Request> requests;
	
	@Override
	public int compareTo(Object arg0) {
		if(arg0 instanceof Video && ((Video) arg0).id == id) {
			return 0;
		}
		
		return -1;
	}
	
	
}
