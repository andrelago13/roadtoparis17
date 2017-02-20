import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Algorithm {

	
	public static void solve(DataCenter center, int numPools) {
		allocatePositions(center);
		allocatePools(center, numPools);
	}
	
	@SuppressWarnings("unchecked")
	public static void allocatePositions(DataCenter center) {
		ArrayList<Server> sortedServers = new ArrayList<>();
		sortedServers = (ArrayList<Server>) center.servers.clone();

		Collections.sort(sortedServers, DataCenter.RatioComparator);

		int rows = center.grid.length;
		int slots = center.grid[0].length;

		int sum = 0;
		for(int y = 0; y < rows; ++y) {
			for(int x = 0; x < slots; ++x) {
				if(center.grid[y][x] == 0) {
					Server fit = findFit(center, sortedServers, x, y);
					if(fit != null) {
						fit.posX = x;
						fit.posY = y;
						x += fit.size - 1;
						sum += fit.size;
					}
				}
			}
		}
		System.out.println("sum: " + sum);
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
			} else {
				break;
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

		int poolCapacity[] = new int[numPools];
		while (!freeServers.isEmpty()) {
			for (int i = 0; i < numPools; i++)
				poolCapacity[i] = Output.guaranteedCapacity(i, center);
			int worstPool = 0;
			for (int i = 0; i < numPools; i++)
				if (poolCapacity[i] < poolCapacity[worstPool])
					worstPool = i;
			Vector<Integer> rowCapacities = Output.getRowCapacities(Output.getPoolServers(worstPool, center), center);
			int secondMax = Output.getSecondGuaranteedCapacity(rowCapacities);
			int worstPoolRow = rowCapacities.indexOf(secondMax);

			boolean allocated = false;
			/*for (int i = 0; i < freeServers.size(); i++) {
				if (freeServers.get(i).posX != worstPoolRow)
					continue;
				freeServers.get(i).pool = worstPool;
				poolCapacity[worstPool] = Output.guaranteedCapacity(worstPool, center);
				freeServers.remove(i);
				i--;
				allocated = true;
				break;
			}*/
			List<Server> rowServers = serversByRow(freeServers, worstPool);
			rowServers.addAll(serversByRow(freeServers, (worstPool + 1) % numPools));
			for (int i = 0; i < rowServers.size(); i++) {
				rowServers.get(i).pool = worstPool;
				poolCapacity[worstPool] = Output.guaranteedCapacity(worstPool, center);
				freeServers.remove(rowServers.get(i));
				i--;
				allocated = true;
				break;
			}
			if (!allocated) {
				freeServers.get(0).pool = worstPool;
				poolCapacity[worstPool] = Output.guaranteedCapacity(worstPool, center);
				freeServers.remove(0);
			}
		}
	}

	public static List<Server> serversByRow(List<Server> servers, int row) {
		List<Server> res = new ArrayList<Server>();
		for (Server server : servers)
			if (server.posX == row)
				res.add(server);
		return res;
	}
}
