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
 * Class that searches for song on dig.ccmixter.org
 * A website for music under creative content license
 * @author Adrien
 *
 */
public class ccMixterRetriever implements SongRetriever {
	
	public static String SOURCE="dig.ccmixter.org";
	public static String BASE="http://dig.ccmixter.org/dig?dig-query=";

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
			URL url = new URL(BASE+query);
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
		//splits at every song found
		String[] rawSongs = content.split("upload_id");
		//rawSongs[0] is just some html code
		for(int i=1; i<rawSongs.length; i++) {
			Song s = new Song();
			//find the name
			Pattern p = Pattern.compile("\"upload_name\" : \"(.*)\", \"upload_extra\" :");
			Matcher m = p.matcher(rawSongs[i]);
			if(m.find()) 
				s.setTitle(m.group(1));
			//find the artist
			p = Pattern.compile("\"user_real_name\" : \"(.*)\", \"artist_page_url\"");
			m = p.matcher(rawSongs[i]);
			if(m.find())
				s.setArtist(m.group(1));
			//find the duration
			p = Pattern.compile("\"ps\" : \"(\\d+):(\\d+)\", \"br\" : \"CBR\"");
			m = p.matcher(rawSongs[i]);
			if(m.find()) {
				//do a little of parsing to convert into seconds
				int duration = Integer.parseInt(m.group(1))*60 + Integer.parseInt(m.group(2));
				s.setDuration(duration);
			}
			//find the size
			p = Pattern.compile("((\\d+\\.*\\d*)MB)");
			m = p.matcher(rawSongs[i]);
			if(m.find())
				s.setSize(Double.parseDouble(m.group(1).replaceAll("MB", "")));
			//TODO find the BPM
			p = Pattern.compile("\"bpm\" : (\\d+),");
			m = p.matcher(rawSongs[i]);
			if(m.find())
				s.setBPM(Integer.parseInt(m.group(1)));
			//find the download link
			p = Pattern.compile("\"download_url\" : \"((.*).mp3)\", \"file_rawsize\"");
			m = p.matcher(rawSongs[i]);
			if(m.find())
				s.setSource(m.group(1).split(",")[0]); //cut the 'file_rawsize" part
			//mark the retriever of the song
			s.setRetriever(SOURCE);
			//add the song to the previous results
			results.add(s);
		}
		return results;
	}

}
