import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Output {
	
	public static void write(List<Cache> caches, String filename) throws IOException{
		
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println(countUsedCaches(caches));
		    for(int i=0; i<caches.size(); ++i){
		    	writeCache(caches.get(i), writer);
		    }
		    writer.close();
		} catch (IOException e) {
		   System.out.println("Error writing in file:" + filename);
		}
	}
	
	public static int countUsedCaches(List<Cache> caches){
		int ret=0;
		for(int i=0;i<caches.size();++i){
			if(!caches.get(i).assigned.isEmpty()){
				ret++;
			}
		}
		return ret;
	}
	
	public static void writeCache(Cache cache, PrintWriter write){
		
		String ret = "";
		ret+=cache.id;
		System.out.println("Size:" + cache.assigned.size());
		for(Video v : cache.assigned){
			ret+=" " + v.id;
		}
		ret+="\n";
		write.print(ret);
	}
}
