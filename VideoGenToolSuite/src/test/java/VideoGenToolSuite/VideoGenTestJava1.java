package test.java.VideoGenToolSuite;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import main.java.VideoGenToolSuite.VideoGenerator;

public class VideoGenTestJava1 {
	
	@Test
	public void testInJava1() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGenerator g = new VideoGenerator("example2.videogen");
		g.generatePlaylistFile(g.generateVlcPlaylistString(g.generateVariation()), "m3u");
		g.playPlaylistWithVlc();
	}
	
	@Test
	public void testInJava2() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
		//Using VideoGenerator functions
		VideoGenerator g = new VideoGenerator("example1.videogen");
		g.generatePlaylistFile(g.generateFFmpegPlaylistString(g.generateVariation()), "txt");
		g.generateFFmpegVideo();
		g.generateFFmpegGif();
		
	}

	

}
