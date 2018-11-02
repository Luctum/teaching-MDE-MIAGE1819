import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.xtext.example.mydsl.videoGen.AlternativeVideoSeq;
import org.xtext.example.mydsl.videoGen.Image;
import org.xtext.example.mydsl.videoGen.MandatoryVideoSeq;
import org.xtext.example.mydsl.videoGen.Media;
import org.xtext.example.mydsl.videoGen.OptionalVideoSeq;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;
import org.xtext.example.mydsl.videoGen.VideoSeq;

public class VideoGenerator {
	
	private final String conf = "conf";
	
	private VideoGeneratorModel videoGen;
	
	private String videoPath;
	
	
	public VideoGenerator(String video) {
		this.videoGen = new VideoGenHelper().loadVideoGenerator(URI.createURI(video));
		this.videoPath = this.getVideoPath();
	}
	
	public String getVideoPath() {
		String video_path = "";
		try {
			String line;
            FileReader fileReader = new FileReader(conf);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                video_path = line;
            }   
            bufferedReader.close();  
        }
        catch(IOException ex) {
        }
		return video_path;
	}

	/**
	 * Generate videovariante playlist 
	 * @return ArrayList which contain video path
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public ArrayList<String> playListGenerator() throws FileNotFoundException, UnsupportedEncodingException {
		Random rand = new Random();
		ArrayList<String> playlist = new ArrayList();		
		EList<Media> medias = videoGen.getMedias();
		
		for(Media media: medias) {
			if(media instanceof Image) {
				
			} else if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof MandatoryVideoSeq) {
					String location = videoPath + ((MandatoryVideoSeq) vseq).getDescription().getLocation();
					playlist.add(location); 
				} else if (vseq instanceof OptionalVideoSeq) {
					int nombreAleatoire = rand.nextInt(2);
					if(nombreAleatoire == 1) {
						String location = videoPath + ((OptionalVideoSeq) vseq).getDescription().getLocation();
						playlist.add(location); 
					}
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
					int nombreAleatoire = rand.nextInt(videodesc.size());
					playlist.add(videoPath + videodesc.get(nombreAleatoire).getLocation());
				}
			}
		}
		return(playlist);
	}
	
	public void playWithFFmpeg(ArrayList<String> playlist) throws FileNotFoundException, UnsupportedEncodingException {
		StringBuilder playlistStr = new StringBuilder("");
		for (String s : playlist) {
			playlistStr.append("file" + " '" + s + "'" + "\n");
		}
		PrintWriter writer = new PrintWriter(videoPath + "playlist_ffmpeg.txt", "UTF-8");
		writer.println(playlistStr);
		writer.close();
		String input = videoPath + "playlist_ffmpeg.txt";
		String output = videoPath + "output.mp4";
		String cmd = "ffmpeg -f concat -safe 0 -i " + input + " -c copy " + output;
		System.out.println(cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playWithVLC(ArrayList<String> playlist) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(videoPath + "playlist.m3u", "UTF-8");
		StringBuilder playlistStr = new StringBuilder("");
		for (String s : playlist) {
			playlistStr.append("file" + " '" + s + "'" + "\n");
		}
		writer.println(playlistStr);
		writer.close();
		String cmd = "\"C:\\Program Files\\VideoLAN\\VLC\\vlc.exe\" \"..\\..\\videos\\playlist.m3u\"";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

