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
 * A class that searches for mp3s from amazon.com
 * @author Adrien
 *
 */
public class AmazonRetriever implements SongRetriever {
	
	public static String SOURCE="amazon.com";
	public static String BASE="http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Ddigital-music&field-keywords=";

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
	 * Method to get the content of the result page
	 * @param query
	 * @return
	 */
	private String getResultPage(String query) {
		String content = "";
		try {
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
	 * Parses content of a page
	 * @param content of page
	 * @param results arraylist of songs
	 * @return Arraylist of song results
	 */
	private ArrayList<Song> parseContent(String content, ArrayList<Song> results) {
		//splits at every song found
		String[] rawSongs = content.split("<td class=\"titleColOdd\"><table border=\"0\"");
		for(int i=1; i<rawSongs.length; i++) {
			Song s = new Song();
			//find the source, title, artist, album and duration
			Pattern p = Pattern.compile("<a href=\"(.*)\">(.*)</a></td></tr></table></td><td class=\"titleColOdd\"><a href=\"(.*)\">(.*)</a></td><td class=\"titleColOdd\"><a href=\"(.*)\">(.*)</a></td><td class=\"priceColOdd\">(\\d+):(\\d+)</td>");
			Matcher m = p.matcher(rawSongs[i]);
			if(m.find()) {
				s.setSource(m.group(1));
				s.setTitle(m.group(2));
				s.setArtist(m.group(4));
				s.setAlbum(m.group(6));
				s.setDuration(Integer.parseInt(m.group(7))*60 + Integer.parseInt(m.group(8)));
			}
			s.setRetriever(SOURCE);
			results.add(s);
		}
		return results;
	}

	/**
	 * Returns source of a retriever
	 */
	@Override
	public String getSource() {
		return SOURCE;
	}

}
