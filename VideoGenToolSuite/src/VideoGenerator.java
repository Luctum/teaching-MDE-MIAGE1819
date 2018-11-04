import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

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
	public ArrayList<String> generateVariation() throws FileNotFoundException, UnsupportedEncodingException {
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
	
	
	/**
	 * Generate a video with FFmpeg
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generateFFmpegVideo() throws FileNotFoundException, UnsupportedEncodingException {
		String input = videoPath + "playlist.txt";
		String output = videoPath + "output.mp4";
		String cmd = "ffmpeg -f concat -safe 0 -i \"" + Paths.get(input).toString() + "\" -c copy -y \"" + Paths.get(output).toString()+"\"";
		System.out.println(cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a gif with ffmpeg
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generateFFmpegGif() throws FileNotFoundException, UnsupportedEncodingException {
		String input = videoPath + "playlist.txt";
		String output = videoPath + "output.gif";
		String cmd = "ffmpeg -f concat -safe 0 -i \"" + Paths.get(input).toString() + "\" -c copy -y \"" + Paths.get(output).toString()+"\"";
		System.out.println(cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the m3u playlist file
	 * @param playlist
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void playPlaylistWithVlc() throws FileNotFoundException, UnsupportedEncodingException {
		//TODO rewrite that to work with linux/macOS
		String cmd = "\"C:\\Program Files\\VideoLAN\\VLC\\vlc.exe\" \"..\\..\\videos\\playlist.m3u\"";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Generate a ffmpeg playlist string to allow ffmpeg manipulation of multiple videos
	 * @param playlist
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public String generateFFmpegPlaylistString(ArrayList<String> playlist) throws FileNotFoundException, UnsupportedEncodingException {
		StringBuilder playlistStr = new StringBuilder("");
		for (String s : playlist) {
			playlistStr.append("file '" + Paths.get(s).toString() + "'" + System.lineSeparator());
		}
		return playlistStr.toString();
	}
	
	/**
	 * Generate a m3u playlist style String
	 * @param playlist
	 * @return the playlist string
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public String generateVlcPlaylistString(ArrayList<String> playlist) throws FileNotFoundException, UnsupportedEncodingException {
		StringBuilder playlistStr = new StringBuilder("");
		for (String s : playlist) {
			playlistStr.append(Paths.get(s).toUri().toString() + System.lineSeparator());
		}
		return playlistStr.toString();
	}
	
	/**
	 * Generate a playlist file from String
	 * @param playlist
	 * @param extension the file's extension as of txt or m3u...
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generatePlaylistFile(String playlist, String extension) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(Paths.get(videoPath + "playlist." + extension).toString(), "UTF-8");
		writer.println(playlist);
		writer.close();
	}
}

