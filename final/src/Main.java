import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	static int numVideos;
	static int numEndpoints;
	static int requestDescrip;
	static int numCaches;
	static int cacheCapac;
	static ArrayList<Video> videos;
	static ArrayList<Endpoint> endpoints;
	
	
	public static void readFile(String filename) throws IOException{
		
		videos = new ArrayList<Video>();
		endpoints = new ArrayList<Endpoint>();
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String info = reader.readLine();
		String[] details = info.split(" ");
		numVideos = Integer.parseInt(details[0]);
		numEndpoints = Integer.parseInt(details[1]);
		requestDescrip = Integer.parseInt(details[2]);
		numCaches = Integer.parseInt(details[3]);
		cacheCapac = Integer.parseInt(details[4]);
		
		String infoVideos = reader.readLine();
		String[] detailsVid = infoVideos.split(" ");
		int videoId = 0;
		
		for (int i = 0; i < detailsVid.length; i++){
			Video video = new Video();
			video.id = videoId;
			video.size = Integer.parseInt(detailsVid[videoId]);
			videoId++;
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
