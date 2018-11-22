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
		get("/video/all", (request, response) -> {
			HttpServletResponse resp = response.raw();
			resp.setContentType("application/json");
			return new Gson().toJson(g.getAllVideoGenVideos()) ;
		});
		get("/video/generate", (request, response) -> {
		    //genère et stream la vidéo
			g.generateFFmpegVideo();
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
			Path p = Paths.get(g.getGeneratedGifPath());
			byte[] gif = Files.readAllBytes(p);
			response.status(200);
			HttpServletResponse resp = response.raw();
			resp.setContentType("image/gif");
			resp.getOutputStream().write(gif);
			resp.getOutputStream().close();
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
