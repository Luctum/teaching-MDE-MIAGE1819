import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.xtext.example.mydsl.videoGen.AlternativeVideoSeq;
import org.xtext.example.mydsl.videoGen.Image;
import org.xtext.example.mydsl.videoGen.MandatoryVideoSeq;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.OptionalVideoSeq;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoSeq;

public class VideoGenTestJava2 {
	
	@Test
	public void testInJava1() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		assertEquals(4, videoGen.getMedias().size());
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		
		StringBuilder sb = new StringBuilder();
		sb.append("ID");
		ArrayList<ArrayList<Object>> t;
		
		for(Media media: medias) {
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof MandatoryVideoSeq) {
					//1scenario possible
				} else if (vseq instanceof OptionalVideoSeq) {
					int nombreAleatoire = rand.nextInt(2);
					if(nombreAleatoire == 1) {
						String location = ((OptionalVideoSeq) vseq).getDescription().getLocation();
						playlist += location + "\n";
						//2 scenario possible
					}
				} else if (vseq instanceof AlternativeVideoSeq) {
					//nbAlternative scenarios possibles
				}
			}
		}
	}
	
	public double getFileSize(String path){
		File file =new File("c:\\java_xml_logo.jpg");
		
		if(file.exists()){
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			return (kilobytes / 1024);
		}
		return 0;
	}

}
