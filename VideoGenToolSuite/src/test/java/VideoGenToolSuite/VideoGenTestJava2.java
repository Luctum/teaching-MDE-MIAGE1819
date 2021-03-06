package test.java.VideoGenToolSuite;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

import m2miage.VideoGenHelper;

public class VideoGenTestJava2 {
	
	@Test
	public void testInJava1() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
		assertNotNull(videoGen);		
		assertEquals(5, videoGen.getMedias().size());
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> t = new ArrayList();
		ArrayList<VideoDescription> videoList = new ArrayList();
		
		for(Media media: medias) {
			ArrayList<Object> etape = new ArrayList();
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				
				if (vseq instanceof MandatoryVideoSeq) {
					VideoDescription location = ((MandatoryVideoSeq) vseq).getDescription();
					videoList.add(location);
					etape.add(location);
				} else if (vseq instanceof OptionalVideoSeq) {
					VideoDescription location = ((OptionalVideoSeq) vseq).getDescription();
					videoList.add(location);
					etape.add(location);
					etape.add("");
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
                    for (VideoDescription v : videodesc) {
                    	etape.add(v.getVideoid());
                    	videoList.add(v);
                    }
				}
				
				ArrayList<ArrayList<Object>> tmp = new ArrayList();
				if(t.isEmpty()) {
					for (Object j: etape) {
						ArrayList<Object> list = new ArrayList();
						list.add(j);
						tmp.add(list);
					}					
				}
				else {
					for (Object j: etape) {
						for (ArrayList i: t) {
							ArrayList i_tmp = (ArrayList) i.clone();
							i_tmp.add(j);
							tmp.add(i_tmp);
						}					
					}
				}
				t = (ArrayList<ArrayList<Object>>)tmp.clone();
			}
		}
		
		this.generateCSV(t, videoList);
		
	}
	
	public double getFileSize(String path){
		File file =new File(path);
		if(file.exists()){
			double bytes = file.length();
			double kilobytes = (bytes / 1024);
			return (kilobytes / 1024);
		}
		return 0;
	}
	
	public void generateCSV(ArrayList<ArrayList<Object>> t, ArrayList<VideoDescription> videoList) throws FileNotFoundException, UnsupportedEncodingException {
		String video_path = new String();
		try {
			String line;
            FileReader fileReader = new FileReader("conf");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                video_path = line;
            }   
            bufferedReader.close();  
        }
        catch(IOException ex) { 
        }
		
		StringBuilder builderCSV = new StringBuilder();
		builderCSV.append("ID,");
		for(Object video: videoList) {
			builderCSV.append(((VideoDescription)video).getVideoid() + ",");
		}
		builderCSV.append("taille");
		builderCSV.append("\n");
		
		System.out.println(t.toString());
		System.out.println(videoList.toString());
		
		int cpt = 1;
		for (ArrayList i: t) {
			builderCSV.append(cpt + ",");
			int taille = 0;
			for(VideoDescription video: videoList) {
					if(i.contains(video.getVideoid()) || i.contains(video)) {
						taille += this.getFileSize(video_path + video.getLocation());
						builderCSV.append("TRUE,");
					} else {
						builderCSV.append("FALSE,");
					}
			}
			builderCSV.append(taille);
			builderCSV.append("\n");
			cpt++;
		}
		
		PrintWriter writer = new PrintWriter("../../taille.csv", "UTF-8");
		writer.println(builderCSV);
		writer.close();
	}

}
