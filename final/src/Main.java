import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	static ArrayList<Cache> caches;
	
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
		
		for (int i = 0; i < detailsVid.length; ++i){
			Video video = new Video();
			video.id = videoId;
			video.size = Integer.parseInt(detailsVid[videoId]);
			videoId++;
		}
		int endpointId = 0;
		
		for(int j = 0; j < numEndpoints; ++j) {
			
			String infoEndpoint = reader.readLine();
			String[] detailsEndpoint = infoEndpoint.split(" ");
			
			Endpoint endpoint = new Endpoint();
			endpoint.id = endpointId;
			endpoint.latency = Integer.parseInt(detailsEndpoint[0]);
			endpointId++;
			
			for(int k = 0; k < Integer.parseInt(detailsEndpoint[1]); ++k){
				
				String infoEndpointCache = reader.readLine();
				String[] detailsEndpointCache = infoEndpointCache.split(" ");
				
				Connection connection = new Connection();
				connection.latency = Integer.parseInt(detailsEndpointCache[1]);
				connection.endpoint = endpoint;
				
				int cacheId = 0;
				Cache cache = new Cache();
				cache.id = cacheId;
				cache.size = cacheCapac;
				cacheId++;

				caches.add(cache);
				connection.cache = cache;
				endpoint.connections.add(connection);
			}	
		}
		
		for (int l = 0; l < requestDescrip; ++l){
			String infoRequests = reader.readLine();
			String[] detailsRequests = infoRequests.split(" ");
			
			Request request = new Request();
			request.video = videos.get(Integer.parseInt(detailsRequests[0]));
			request.endpoint = endpoints.get(Integer.parseInt(detailsRequests[1]));
			request.hits = Integer.parseInt(detailsRequests[2]);
			
			endpoints.get(Integer.parseInt(detailsRequests[1])).requests.add(request);
		}
	}
		

	public static void main(String[] args) throws IOException {
		solveFile("res/kittens.in");
	}

	public static void solveFile(String filename) throws IOException {
		System.out.println("Solving " + filename);
		readFile(filename);
		Solver.solve(videos, endpoints, caches);
		Output.write(caches, filename);
		System.out.println("Solved " + filename);
	}
}
