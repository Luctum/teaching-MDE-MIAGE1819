import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

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

public class VideoGenTestJava3 {
	
	String current = "";
	
	@Test
	public void testInJava1() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example1.videogen"));
		assertNotNull(videoGen);	
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	
	@Test
	public void testInJava2() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example2.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava3() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example3.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		//assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava4() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example4.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava5() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example5.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava6() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example6.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava7() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example7.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava8() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example8.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	

	@Test
	public void testInJava9() throws FileNotFoundException, UnsupportedEncodingException {
		
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example9.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	
	@Test
	public void testInJava10() throws FileNotFoundException, UnsupportedEncodingException {
		VideoGeneratorModel videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI("example10.videogen"));
		assertNotNull(videoGen);		
		System.out.println(videoGen.getInformation().getAuthorName());		
		Random rand = new Random();
		String playlist = "";
		EList<Media> medias = videoGen.getMedias();
		ArrayList<ArrayList<Object>> variante = this.generateVariante(medias);
		System.out.println(variante);
		int nbV = this.nbVariantes(medias);
		System.out.println(nbV);
		assertEquals(nbV, variante.size());
		assertEquals(variante.size(), this.csvSize());
	}
	
	public int nbVariantes(EList<Media> medias) {
		int res = 1;
		for(Media media: medias) {
			ArrayList<Object> etape = new ArrayList();
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof OptionalVideoSeq) {
					res = res*2;
				} else if (vseq instanceof AlternativeVideoSeq) {
					int nbAlternative= ((AlternativeVideoSeq) vseq).getVideodescs().size();
                    res = res*nbAlternative;
				}
			}
		}
		return res;
	}
	
	public ArrayList<ArrayList<Object>> generateVariante(EList<Media> medias) throws FileNotFoundException, UnsupportedEncodingException{
		StringBuilder sb = new StringBuilder();
		sb.append("ID");
		ArrayList<ArrayList<Object>> t = new ArrayList();
		ArrayList<VideoDescription> videoList = new ArrayList();
		
		for(Media media: medias) {
			ArrayList<Object> etape = new ArrayList();
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof MandatoryVideoSeq) {
					String location = ((MandatoryVideoSeq) vseq).getDescription().getVideoid();
					etape.add(location);
					videoList.add(((MandatoryVideoSeq) vseq).getDescription());
				} else if (vseq instanceof OptionalVideoSeq) {
					String location = ((OptionalVideoSeq) vseq).getDescription().getVideoid();
					videoList.add(((OptionalVideoSeq) vseq).getDescription());
					etape.add(location);
					etape.add("");
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
                    for (VideoDescription v : videodesc) {
                    	videoList.add(v);
                    	etape.add(v.getVideoid());
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
		return(t);
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
		
		int cpt = 1;
		for (ArrayList i: t) {
			builderCSV.append(cpt + ",");
			int taille = 0;
			for(VideoDescription video: videoList) {
					if(i.contains(video)) {
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
		
		PrintWriter writer = new PrintWriter("../../table.csv", "UTF-8");
		writer.println(builderCSV);
		writer.close();
		this.current = builderCSV.toString();
	}

	public int csvSize() {
        String[] table = this.current.split(Pattern.quote("\n"));
        System.out.println(table.length-1);
        return table.length-1;
	}
}
