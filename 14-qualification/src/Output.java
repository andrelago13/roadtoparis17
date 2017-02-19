import java.io.IOException;
import java.io.PrintWriter;
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
}
