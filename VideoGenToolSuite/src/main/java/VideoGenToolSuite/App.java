package main.java.VideoGenToolSuite;
import static spark.Spark.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class App 
{
	
	public static void main(String[] args) {
		allowCors();
		VideoGenerator g = new VideoGenerator("example1.videogen");
		
		/*Get the video list before calling any API path 
		because it would be too long to generate it each time with filters/text (approx 20/30s to wait depending on the videogenfile) */
		String allVideos = new Gson().toJson(g.getAllVideoGenVideos()) ;
		
		get("/video/all", (request, response) -> {
			HttpServletResponse resp = response.raw();
			resp.setContentType("application/json");
			return allVideos;
		});
		get("/video/generate", (request, response) -> {
		    //genère la video
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
			/*g.generateFFmpegGif();
			Path p = Paths.get(g.getGeneratedGifPath());
			byte[] gif = Files.readAllBytes(p);
			response.status(200);
			HttpServletResponse resp = response.raw();
			resp.setContentType("image/gif");
			resp.getOutputStream().write(gif);
			resp.getOutputStream().close();*/
			return 200;
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
