import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that searches from mp3skull.com
 * A website that uses its own algorithm to find mp3s on the web
 * @author Adrien
 *
 */
public class mp3SkullRetriever implements SongRetriever {
	
	public static String SOURCE="mp3skull.com";
	public static String BASE="http://mp3skull.com/mp3/";

	/**
	 * Method which adds the songs found to the previous results
	 * @param query
	 * @param results
	 * @return results
	 */
	@Override
	public ArrayList<Song> getResults(String query, ArrayList<Song> results) {
		String content = getResultPage(query);
		results = parseContent(content, results);
		return results;
	}

	/**
	 * Simple getter for the source
	 * @return
	 */
	@Override
	public String getSource() {
		return SOURCE;
	}
	
	/**
	 * Method to get the content of the result page
	 * @param query
	 * @return
	 */
	private String getResultPage(String query) {
		String content = "";
		try {
			//get the result page
			URL url = new URL(BASE+query+".html");
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			if(httpConnection.getResponseMessage().equals("OK")) {
				Scanner in = new Scanner(httpConnection.getInputStream());
				while(in.hasNextLine()) 
					content += in.nextLine() +"\n";
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * Method that parses the content into different song objects and append them to the list
	 * @param content
	 * @param results
	 * @return
	 */
	private ArrayList<Song> parseContent(String content, ArrayList<Song> results) {
		// splits at every song found
		String[] rawSongs = content.split("<div id=\"song_html\"");
		//rawSongs[0] is just some html code
		for(int i=1; i<rawSongs.length; i++) {
			Song s = new Song();
			// find the name and artist
			//depends heavily on the name of the file
			Pattern p = Pattern.compile("<div style=\"font-size:15px;\"><b>(.*)-(.*) mp3</b></div>");
			Matcher m = p.matcher(rawSongs[i]);
			if(m.find()) {
				s.setArtist(m.group(1));
				s.setTitle(m.group(2));
			}
			//set the bitrate, duration and size
			p = Pattern.compile("(\\d+) kbps<br />(\\d+):(\\d+)<br />(\\d+\\.\\d+) mb			</div>");
			m = p.matcher(rawSongs[i]);
			if(m.find()) {
				s.setBitrate(Integer.parseInt((m.group(1))));
				s.setDuration(Integer.parseInt(m.group(2))*60+Integer.parseInt(m.group(3)));
				s.setSize((Double.parseDouble(m.group(4))));
			}
			//set the download source
			p = Pattern.compile("<a href=\"(.*)\" rel=\"nofollow\" target=\"_blank\" style=\"color:green;\">Download</a>");
			m = p.matcher(rawSongs[i]);
			if(m.find())
				s.setSource((m.group(1)));
			//mark the retriever of the song
			s.setRetriever(SOURCE);
			//add the results to the list
			results.add(s);
		}
		return results;
	}

}
