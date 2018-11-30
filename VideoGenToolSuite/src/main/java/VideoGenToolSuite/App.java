package main.java.VideoGenToolSuite;

import static spark.Spark.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class App 
{
	
	public static void main(String[] args) {
		allowCors();
		VideoGenerator g = new VideoGenerator("questionchampion.videogen");
		
		/*Get the video list before calling any API path 
		because it would be too long to generate it each time with filters/text (approx 20/30s to wait depending on the videogenfile) */
		String allVideos = new Gson().toJson(g.getAllVideoGenVideos()) ;
		
		get("/video/all", (request, response) -> {
			HttpServletResponse resp = response.raw();
			resp.setContentType("application/json");
			return allVideos;
		});
		post("/video/generate", (request, response) -> {
		    //genère la video
			ArrayList<String> a = new Gson().fromJson(request.body(), ArrayList.class);
			StringBuilder cleanString = new StringBuilder();
			for(String s: a) {
				cleanString.append("file '" + g.getVideoPath() + s + "'");
				cleanString.append(System.getProperty("line.separator"));
			}
			g.generatePlaylistFile(cleanString.toString(), "txt");
			g.generateFFmpegVideo();
			return 200;
		});
		get("/video/generate/random", (request, response) -> {
		    //genère la video aléatoire
			g.generatePlaylistFile(g.generateFFmpegPlaylistString(g.generateVariation()), "txt");
			g.generateFFmpegVideo();
			return 200;
		});
		get("/video", (request, response) -> {
			//stream la vidéo
			Path p = Paths.get(g.getGeneratedVideoPath());
			byte[] video = Files.readAllBytes(p);
			response.status(200);
			HttpServletResponse resp = response.raw();
			resp.setContentType("video/mp4");
			resp.getOutputStream().write(video);
			resp.getOutputStream().close();
			return 200;
		});
		get("/gif", (request, response) -> {
			g.generateFFmpegGif();
			File file =new File(g.getGeneratedGifPath());
			return new Gson().toJson(Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
		});
    }
	
	public static void allowCors() {
		options("/*",
		        (request, response) -> {

		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		        });

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
	}
}
