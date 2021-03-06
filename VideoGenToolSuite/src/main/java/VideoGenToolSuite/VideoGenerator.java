package main.java.VideoGenToolSuite;

import java.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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

import m2miage.VideoGenHelper;

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
					Filter filter = description.getFilter();
					if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
						location = this.negateFilter(location);	
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
						location = this.blackAndWhiteFilter(location);
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
						if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
							location = this.horizontalFilter(location);
						}
						else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
							location = this.verticalFilter(location);
						}
					}
					playlist.add(videoPath + location); 
				} else if (vseq instanceof OptionalVideoSeq) {
					int nombreAleatoire = rand.nextInt(2);
					if(nombreAleatoire == 1) {
						VideoDescription description = ((OptionalVideoSeq) vseq).getDescription();
						String location = description.getLocation();
						Text text = description.getText();
						if(text != null) {
							//location = this.addText(location, text);
						}
						Filter filter = description.getFilter();
						if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
							location = this.negateFilter(location);	
						}
						else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
							location = this.blackAndWhiteFilter(location);
						}
						else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
							if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
								location = this.horizontalFilter(location);
							}
							else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
								location = this.verticalFilter(location);
							}
						}
						playlist.add(videoPath + location);  
					}
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
					int nombreAleatoire = rand.nextInt(videodesc.size());
					VideoDescription description = videodesc.get(nombreAleatoire);
					String location = description.getLocation();
					Text text = videodesc.get(nombreAleatoire).getText();
					if(text != null) {
						location = this.addText(location, text);
					}
					Filter filter = description.getFilter();
					if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
						location = this.negateFilter(location);	
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
						location = this.blackAndWhiteFilter(location);
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
						if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
							location = this.horizontalFilter(location);
						}
						else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
							location = this.verticalFilter(location);
						}
					}
					playlist.add(videoPath + location);
				}
			}
		}
		return(playlist);
	}
	
	private void generateJpgImage(String location, String locationOut) {
		this.execCmd("ffmpeg -y -i \""+ Paths.get(location).toString() + "\" -vf scale=200x150 -r 1 -ss 00:00:00.435 -vframes 1 -f image2 \"" + Paths.get(locationOut).toString() + ".jpg\"");
	}
	
	/**
	 * 
	 * @return every video in the videogen file and their images in a base64 string format
	 */
	public ArrayList<HashMap<String, Object>> getAllVideoGenVideos() {
		ArrayList<HashMap<String, Object>> videoList = new ArrayList();		
		EList<Media> medias = videoGen.getMedias();
		for(Media media: medias) {
			if (media instanceof VideoSeq) {
				VideoSeq vseq = (VideoSeq) media;
				if (vseq instanceof MandatoryVideoSeq) {
					VideoDescription description = ((MandatoryVideoSeq) vseq).getDescription();
					String location = description.getLocation();
					//Filter and change file location
					Text text = description.getText();
					if(text != null) {
						location = this.addText(location, text);
					}
					Filter filter = description.getFilter();
					if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
						location = this.negateFilter(location);	
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
						location = this.blackAndWhiteFilter(location);
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
						if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
							location = this.horizontalFilter(location);
						}
						else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
							location = this.verticalFilter(location);
						}
					}
					//generate list of attributes
					HashMap<String, Object> m = new HashMap();
					m.put("id", description.getVideoid());
					m.put("type", "mandatory");
					m.put("location", location);
					this.generateJpgImage(this.videoPath + location, this.videoPath + location);
					try {
						File file = new File(this.videoPath + location + ".jpg");
						m.put("image", Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					videoList.add(m);
				} else if (vseq instanceof OptionalVideoSeq) {
					VideoDescription description = ((OptionalVideoSeq) vseq).getDescription();
					String location = description.getLocation();
					Text text = description.getText();
					if(text != null) {
						location = this.addText(location, text);
					}
					Filter filter = description.getFilter();
					if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
						location = this.negateFilter(location);	
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
						location = this.blackAndWhiteFilter(location);
					}
					else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
						if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
							location = this.horizontalFilter(location);
						}
						else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
							location = this.verticalFilter(location);
						}
					}
					HashMap<String, Object> m = new HashMap();
					m.put("id", description.getVideoid());
					m.put("type", "optional");
					m.put("location", location);
					this.generateJpgImage(this.videoPath + location, this.videoPath + location);
					try {
						File file =new File(this.videoPath + location + ".jpg");
						m.put("image", Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					videoList.add(m);
				} else if (vseq instanceof AlternativeVideoSeq) {
					EList<VideoDescription> videodesc= ((AlternativeVideoSeq) vseq).getVideodescs();
					ArrayList<HashMap<String, Object>> videos = new ArrayList();
					for(VideoDescription v: videodesc) {
						HashMap<String, Object> video = new HashMap();
						String location = v.getLocation();
						Text text = v.getText();
						if(text != null) {
							location = this.addText(location, text);
						}
						Filter filter = v.getFilter();
						if(filter instanceof org.xtext.example.mydsl.videoGen.impl.NegateFilterImpl) {
							location = this.negateFilter(location);	
						}
						else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.BlackWhiteFilterImpl) {
							location = this.blackAndWhiteFilter(location);
						}
						else if(filter instanceof org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) {
							if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("h")) {
								location = this.horizontalFilter(location);
							}
							else if(((org.xtext.example.mydsl.videoGen.impl.FlipFilterImpl) filter).getOrientation().equals("v")) {
								location = this.verticalFilter(location);
							}
						}
						video.put("id", v.getVideoid());
						video.put("location", location);
						video.put("parentId", ((AlternativeVideoSeq) vseq).getVideoid());
						this.generateJpgImage(this.videoPath + location, this.videoPath + location);
						try {
							File file =new File(this.videoPath + location+ ".jpg");
							video.put("image", Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						videos.add(video);
					}
					HashMap<String, Object> m = new HashMap();
					m.put("id", ((AlternativeVideoSeq) vseq).getVideoid());
					m.put("type", "alternative");
					m.put("videos", videos);
					videoList.add(m);
				}
			}
		}
		return(videoList);
	}
	
	/**
	 * Generate a video with FFmpeg
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void generateFFmpegVideo() throws FileNotFoundException, UnsupportedEncodingException {
		String input = videoPath + "playlist.txt";
		String output = videoPath + "output.mp4";
		String cmd = "ffmpeg -f concat -safe 0 -i \"" + Paths.get(input).toString() + "\" -fflags +genpts -c copy -y \"" + Paths.get(output).toString()+"\"";
		this.execCmd(cmd);
	}
	
	/**
	 * Generate a gif with ffmpeg
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException 
	 */
	public void generateFFmpegGif() throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
		String input = videoPath + "output.mp4";
		String output = videoPath + "output.gif";
		String outputPalette = videoPath+ "palette.png";
		String cmdPalette = "ffmpeg -t 3 -ss 2.6 -y -i \"" + Paths.get(input).toString() + "\" -vf fps=10,scale=480:-1:flags=lanczos,palettegen \"" + Paths.get(outputPalette).toString() + "\"";
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -i \"" +  Paths.get(outputPalette).toString() + "\" -filter_complex \"fps=10,scale=480:-1:flags=lanczos[x];[x][1:v]paletteuse\" \"" + Paths.get(output).toString()+"\"";
		this.execCmd(cmdPalette);
		this.execCmd(cmd);
	}
	
	/**
	 * Read the m3u playlist file
	 * @param playlist
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void playPlaylistWithVlc() throws FileNotFoundException, UnsupportedEncodingException {
		//TODO rewrite that to work with linux/macOS
		String cmd = "\"C:\\Program Files\\VideoLAN\\VLC\\vlc.exe\" \""+ Paths.get(videoPath + "playlist.m3u").toString() +"\"";
		this.execCmd(cmd);
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
		String output = path.substring(0, path.lastIndexOf('.')) +"Texted.mkv";
		String content = text.getContent();
		String textPosition = text.getPosition();
		String position = "0";
		if(textPosition.equals("CENTER")) {
			position = this.CENTER;
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
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -vf \"drawtext=text='" + content + "':fontcolor=" + color + ":fontsize=" + size + ":x=(w-text_w)/2:y=" + position + "\" \"" + videoPath + Paths.get(output).toString() + "\"";
		this.execCmd(cmd);
		return(output);
	}
	

	public String blackAndWhiteFilter(String path) {
		String input = videoPath + path;
		String output = path.substring(0, path.lastIndexOf('.')) +"Filtred.mkv";
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -vf hue=s=0 \"" + videoPath + Paths.get(output).toString() + "\"";
		this.execCmd(cmd);
		return(output);
	}
	
	public String negateFilter(String path) {
		String input = videoPath + path;
		String output = path.substring(0, path.lastIndexOf('.')) +"Filtred.mkv";
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -vf negate \"" + videoPath + Paths.get(output).toString() + "\"";
		this.execCmd(cmd);
		return(output);
	}
	
	public String horizontalFilter(String path) {
		String input = videoPath + path;
		String output = path.substring(0, path.lastIndexOf('.')) +"Filtred.mkv";
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -vf hflip \"" + videoPath + Paths.get(output).toString() + "\"";
		this.execCmd(cmd);
		return(output);
	}
	
	public String verticalFilter(String path) {
		String input = videoPath + path;
		String output = path.substring(0, path.lastIndexOf('.')) +"Filtred.mkv";
		String cmd = "ffmpeg -y -i \"" + Paths.get(input).toString() + "\" -vf vflip \"" + videoPath + Paths.get(output).toString() + "\"";
		this.execCmd(cmd);
		return(output);
	}
	
	/**
	 * Execute a command line process and wait for it to end
	 * @param cmd
	 */
	public void execCmd(String cmd) {
		System.out.println(cmd);
		try
        {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ( (line = br.readLine()) != null)
                System.out.println(line);
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t)
          {
            t.printStackTrace();
          }
	}
	
	public String getGeneratedVideoPath(){
		return this.videoPath + "output.mp4";
	}
	
	public String getGeneratedGifPath(){
		return this.videoPath + "output.gif";
	}
}

