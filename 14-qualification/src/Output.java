import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Vector;

public class Output {
	
	public static void write(Vector<Server> servers, String filename) throws IOException{
		
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println(servers.size());
		    for(int i=0; i<servers.size(); ++i){
		    	writeServer(servers.get(i), writer);
		    }
		    writer.close();
		} catch (IOException e) {
		   System.out.println("Error writing in file:" + filename);
		}
	}
	
	public static void writeServer (Server s, PrintWriter write){

		write.println(s.posY + " " + s.posX + " " + s.pool);
	}
	
	public static int guaranteedCapacity(int pool, DataCenter dataCenter){
		int capacity=0;
		Vector<Server> poolServers = getPoolServers(pool, dataCenter);
		Vector<Integer> rowCapacities = getRowCapacities(poolServers, dataCenter);
		capacity = getSecondGuaranteedCapacity(rowCapacities);
		return capacity;
	}
	
	public static Vector<Integer> getRowCapacities(Vector<Server> poolServers, DataCenter dataCenter){
		Vector<Integer> ret = new Vector<Integer>();
		for(int i=0;i<dataCenter.grid.length;++i){
			int rowCapacity=0;
			for(int j=0;j<poolServers.size();++j){
				if(poolServers.get(j).posY == i){
					rowCapacity+=poolServers.get(j).capacity;
				}
			}
			ret.add(rowCapacity);
		}
		return ret;
	}
	
	public static Vector<Server> getPoolServers(int pool, DataCenter dataCenter){
		Vector<Server> poolServers = new Vector<Server>();
		for(int i=0;i<dataCenter.servers.size();++i){
			if(dataCenter.servers.get(i).pool == pool){
				poolServers.add(dataCenter.servers.get(i));
			}
		}
		return poolServers;
	}
	
	public static int getSecondGuaranteedCapacity(Vector<Integer> capacities){
		Collections.sort(capacities);
		return capacities.get(capacities.size()-2);
	}
	
	public static int getMinimum(Vector<Integer> capacities){
		Collections.sort(capacities);
		return capacities.get(0);
	}
	
	public int evaluator(DataCenter dataCenter, int numPools){
		int ret = 0;
		Vector<Integer> capacities = new Vector<Integer>();
		for(int i=0;i<numPools;++i){
			capacities.add(guaranteedCapacity(i,dataCenter));
		}
		ret=getMinimum(capacities);
		return ret;
	}
}
