import java.util.List;

public class Algorithm {

	
	public static void solve(DataCenter center) {
		
	}
	
	public Server findFit(DataCenter center, List<Server> servers, int xStart, int yStart) {
		return null;
	}
	
	public void allocatePools(DataCenter center, int numPools) {
		List<Server> freeServers = (List<Server>)center.servers.clone();
		for (int i = 0; i < freeServers.size(); i++) {
			if (freeServers.get(i).posX == -1) {
				freeServers.remove(i);
				i--;
			}
		}
		while (!freeServers.isEmpty()) {
			int worstPool = 0;
			int worstPoolRow = 0;
			for (int i = 0; i < freeServers.size(); i++) {
				if (freeServers.get(i).posX != worstPoolRow)
					continue;
				freeServers.get(i).pool = worstPool;
				freeServers.remove(i);
				i--;
			}
		}
	}
}
