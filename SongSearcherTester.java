import java.util.ArrayList;

/**
 * Simple tester for the SongSearcher
 * @author Adrien, Dina
 *
 */
public class SongSearcherTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		SongSearcher ss = new SongSearcher("foo fighters pretender", retrievers);
		ArrayList<Song> results = ss.getSearchResults();
		for(Song s: results)
			System.out.println(s);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
