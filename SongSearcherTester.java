import java.util.ArrayList;

/**
 * Simple tester for the SongSearcher
 * @author Adrien
 *
 */
public class SongSearcherTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SongSearcher ss = new SongSearcher("foo fighters pretender");
		ArrayList<Song> results = ss.getSearchResults();
		for(Song s: results)
			System.out.println(s);
	}

}
