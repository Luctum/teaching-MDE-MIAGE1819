package main.java.VideoGenToolSuite;
import static spark.Spark.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

public class App 
{
	
	public static void main(String[] args) {
		VideoGenerator g = new VideoGenerator("example1.videogen");
		get("/video/all", (request, response) -> {
			//Génère la liste des vignettes (before ?)
		    //Récupère la liste des vidéos, leur type et leur image
			return 200;
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
		    //génère le gif
			return 200;
		});
        
    }
}
