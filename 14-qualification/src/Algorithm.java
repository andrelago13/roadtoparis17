import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithm {

	
	public static void solve(DataCenter center) {
		allocatePositions(center);
		allocatePools(center);
	}
	
	@SuppressWarnings("unchecked")
	public static void allocatePositions(DataCenter center) {
		ArrayList<Server> sortedServers = new ArrayList<>();
		sortedServers = (ArrayList<Server>) center.servers.clone();

		Collections.sort(sortedServers, DataCenter.RatioComparator);

		int rows = center.grid.length;
		int slots = center.grid[0].length;

		for(int y = 0; y < rows; ++y) {
			for(int x = 0; x < slots; ++x) {
				if(center.grid[y][x] == 0) {
					Server fit = findFit(center, sortedServers, x, y);
					if(fit != null) {
						fit.posX = x;
						fit.posY = y;
						x += fit.size - 1;
					}
				}/* else {
					boolean found = false;
					for(int xi = x; xi < slots; ++xi) {
						if(center.grid[y][x] == 0) {
							x = xi - 1;
							found = true;
							break;
						}
					}
					if(!found) {
						x = slots;
					}
				}*/
			}
		}
	}
	
	public static Server findFit(DataCenter center, List<Server> servers, int xStart, int yStart) {
		if(center.grid[yStart][xStart] != 0) {
			return null;
		}

		int maxSize = 1;
		int slots = center.grid[0].length;

		for(int x = xStart + 1; x < slots; ++x) {
			if(center.grid[yStart][x] == 0) {
				++maxSize;
			}
		}

		for(int i = 0; i < servers.size(); ++i) {
			Server s = servers.get(i);
			if(s.size <= maxSize) {
				servers.remove(s);
				return s;
			}
		}

		return null;
	}
	
	public static void allocatePools(DataCenter center, int numPools) {
		List<Server> freeServers = (List<Server>)center.servers.clone();
		for (int i = 0; i < freeServers.size(); i++) {
			if (freeServers.get(i).posX < 0) { // Server not allocated in the grid
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
