import java.util.ArrayList;

/**
 * Class that holds all of the different retrievers, making maintenance easy
 * @author Adrien, Dina
 *
 */
public class SongSearcher {

	private String query;
	private ArrayList<Song> results;
	private ArrayList<String> retrievers; 

	/**
	 * Constructor
	 * @param query
	 */
	public SongSearcher(String query, ArrayList<String> inRetrievers) {
		this.query = query;
		results = new ArrayList<Song>();
		retrievers = inRetrievers;
	}

	/**
	 * Method to search and return the list of results
	 * @return
	 */
	public ArrayList<Song> getSearchResults() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		for(int i=0; i<retrievers.size(); i++) {
					Class<?> c = Class.forName(retrievers.get(i));
					SongRetriever sr = (SongRetriever) c.newInstance();
					results = sr.getResults(query, results);
		}
		return results;
	}


}