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
		caches = new ArrayList<Cache>();
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

		for (int i = 0; i < numCaches; ++i) {
			Cache cache = new Cache();
			cache.size = cacheCapac;
			cache.id = i;
			caches.add(cache);
		}
		
		String infoVideos = reader.readLine();
		String[] detailsVid = infoVideos.split(" ");
		int videoId = 0;
		
		for (int i = 0; i < detailsVid.length; ++i){
			Video video = new Video();
			video.id = videoId;
			video.size = Integer.parseInt(detailsVid[videoId]);
			videos.add(video);
			videoId++;
		}
		int endpointId = 0;

		for(int j = 0; j < numEndpoints; ++j) {
			
			String infoEndpoint = reader.readLine();
			String[] detailsEndpoint = infoEndpoint.split(" ");
			
			Endpoint endpoint = new Endpoint();
			endpoint.id = endpointId;
			endpoint.latency = Integer.parseInt(detailsEndpoint[0]);
			endpoint.connections = new ArrayList<Connection>();
			endpoint.requests = new ArrayList<Request>();
			endpointId++;

			for(int k = 0; k < Integer.parseInt(detailsEndpoint[1]); ++k){
				
				String infoEndpointCache = reader.readLine();
				String[] detailsEndpointCache = infoEndpointCache.split(" ");
				
				Connection connection = new Connection();
				connection.cache = caches.get(Integer.parseInt(detailsEndpointCache[0]));
				connection.latency = Integer.parseInt(detailsEndpointCache[1]);
				connection.endpoint = endpoint;

				endpoint.connections.add(connection);
				connection.cache.connections.add(connection);
			}
			endpoints.add(endpoint);
		}
		
		for (int l = 0; l < requestDescrip; ++l){
			String infoRequests = reader.readLine();
			String[] detailsRequests = infoRequests.split(" ");
			
			Request request = new Request();
			request.video = videos.get(Integer.parseInt(detailsRequests[0]));
			request.endpoint = endpoints.get(Integer.parseInt(detailsRequests[1]));
			request.hits = Integer.parseInt(detailsRequests[2]);

			int endpointId2 = Integer.parseInt(detailsRequests[1]);
			endpoints.get(endpointId2).requests.add(request);

			if (endpoints.get(endpointId2).videoRequests.get(request.video.id) == null) {
				endpoints.get(endpointId2).videoRequests.put(request.video.id, 0);
			} else {
				endpoints.get(endpointId2).videoRequests.put(request.video.id, endpoints.get(endpointId2).videoRequests.put(request.video.id, 0));
			}
		}
	}
		

	public static void main(String[] args) throws IOException {
		solveFile("res/kittens");
		solveFile("res/me_at_the_zoo");
		solveFile("res/trending_today");
		solveFile("res/videos_worth_spreading");
	}

	public static void solveFile(String filename) throws IOException {
		System.out.println("Solving " + filename);
		readFile(filename + ".in");
		Solver.solve(videos, endpoints, caches);
		Output.write(caches, filename + ".out");
		System.out.println("Solved " + filename);
	}
}