import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.xtext.example.mydsl.videoGen.Text;
import org.xtext.example.mydsl.videoGen.Filter;

public class VideoGenerator {
	
	private final String conf = "conf";
	
	private VideoGeneratorModel videoGen;
	
	private String videoPath;
	
	private final String CENTER = "(h-text_h)/2";
	
	private final String TOP = "(h-text_h)/10";

	private final String BOTTOM = "(h-text_h)/1.1";

	
	
	
	
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
					VideoDescription description = ((MandatoryVideoSeq) vseq).getDescription();
					String location = description.getLocation();
					Text text = description.getText();
					if(text != null) {
						location = this.addText(location, text);
					}
					playlist.add(videoPath + location); 
				} else if (vseq instanceof OptionalVideoSeq) {
					int nombreAleatoire = rand.nextInt(2);
					if(nombreAleatoire == 1) {
						VideoDescription description = ((OptionalVideoSeq) vseq).getDescription();
						String location = description.getLocation();
						Text text = description.getText();
						if(text != null) {
							location = this.addText(location, text);
						}
						playlist.add(videoPath + location);  
					}
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
					int nombreAleatoire = rand.nextInt(videodesc.size());
					String location = videodesc.get(nombreAleatoire).getLocation();
					Text text = videodesc.get(nombreAleatoire).getText();
					if(text != null) {
						location = this.addText(location, text);
					}
					playlist.add(videoPath + location);
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
	
	/**
	 * Add text on video
	 * @param path path of the video
	 * @param text Text object to add
	 */
	public String addText(String path, Text text) {
		String input = videoPath + path;
		String output = path.substring(0, path.lastIndexOf('.')) +"Filtred.mp4";
		String content = text.getContent();
		String textPosition = text.getPosition();
		String position = "0";
		if(textPosition.equals("CENTER")) {
			position = this.CENTER;
			System.out.println("coucou");
		}
		else if(textPosition == "BOTTOM") {
			position = this.BOTTOM;
		}
		else if(textPosition == "TOP") {
			position = this.TOP;
		}
		String color = "white";
		if(text.getColor() != null) {
			color = text.getColor();
		}
		String size = "40";
		if(text.getSize() != null) {
			size = text.getSize();
		}
		String cmd = "ffmpeg -y -i " + Paths.get(input).toString() + " -vf \"drawtext=text='" + content + "':fontcolor=" + color + ":fontsize=" + size + ":x=(w-text_w)/2:y=" + position + "\" " + videoPath + Paths.get(output).toString() + "";
		System.out.println(cmd);
		try
        {            
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<ERROR>");
            while ( (line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("</ERROR>");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
		System.out.println("return");
		return(output);
	}
}

