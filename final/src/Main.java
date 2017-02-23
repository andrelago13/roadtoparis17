import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {
	
	static int numVideos;
	static int numEndpoints;
	static int requestDescrip;
	static int numCaches;
	static int cacheCapac;
	static ArrayList<Video> videos;
	static ArrayList<Endpoint> endpoints;
	
	
	public static void readFile(String filename) throws FileNotFoundException{
		
		videos = new ArrayList<Video>();
		endpoints = new ArrayList<Endpoint>();
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
