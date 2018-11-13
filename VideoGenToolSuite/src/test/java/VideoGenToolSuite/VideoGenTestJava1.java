package test.java.VideoGenToolSuite;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import main.java.VideoGenToolSuite.VideoGenerator;

public class VideoGenTestJava1 {
	
	@Test
	public void testInJava1() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGenerator g = new VideoGenerator("example1.videogen");
		g.generatePlaylistFile(g.generateVlcPlaylistString(g.generateVariation()), "m3u");
		g.playPlaylistWithVlc();
	}
	
	@Test
	public void testInJava2() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
		
		/*VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		assertEquals(4, videoGen.getMedias().size());	
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		for(Media media: medias) {
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof MandatoryVideoSeq) {
					String location = ((MandatoryVideoSeq) vseq).getDescription().getLocation();
					playlist += "file" + " '" + location + "'" + "\n"; 
				} else if (vseq instanceof OptionalVideoSeq) {
					int nombreAleatoire = rand.nextInt(2);
					if(nombreAleatoire == 1) {
						String location = ((OptionalVideoSeq) vseq).getDescription().getLocation();
						playlist += "file" + " '" + location + "'" + "\n"; 
					}
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
					int nombreAleatoire = rand.nextInt(videodesc.size());
					playlist += "file" + " '" + videodesc.get(nombreAleatoire).getLocation() + "'" + "\n";
				}
			}
		}*/
		
		//Using VideoGenerator functions
		VideoGenerator g = new VideoGenerator("example1.videogen");
		g.generatePlaylistFile(g.generateFFmpegPlaylistString(g.generateVariation()), "txt");
		g.generateFFmpegVideo();
		g.generateFFmpegGif();
		
	}

	

}
