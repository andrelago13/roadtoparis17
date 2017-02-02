import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Output {
	
	public static void write(Vector<Slice> slices, String filename) throws IOException{
		
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println(slices.size());
		    for(int i=0; i<slices.size(); ++i){
		    	writeSlice(slices.get(i), writer);
		    }
		    writer.close();
		} catch (IOException e) {
		   System.out.println("Error writing in file:" + filename);
		}
	}
	
	public static void writeSlice (Slice s, PrintWriter write){
		
		write.println(s.x + " " + s.y + " " + (s.x + s.width) + " " + (s.y + s.height));
	}
}
