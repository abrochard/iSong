import java.util.ArrayList;

/**
 * Class that holds all of the different retrievers, making maintenance easy
 * @author Adrien
 *
 */
public class SongSearcher {
	
	private String query;
	private ArrayList<Song> results;
	// list of different retrievers, add or remove according to need
	private String[] retrievers = {"ccMixterRetriever", "mp3SkullRetriever", "amazonRetriever"};
	
	/**
	 * Constructor
	 * @param query
	 */
	public SongSearcher(String query) {
		this.query = query;
		results = new ArrayList<Song>();
	}
	
	/**
	 * Method to search and return the list of results
	 * @return
	 */
	public ArrayList<Song> getSearchResults() {
		//uses every class given in RETRIEVERS array
		for(int i=0; i<retrievers.length; i++) {
				try {
					//reflection + polymorphism
					Class<?> c = Class.forName(retrievers[i]);
					SongRetriever sr = (SongRetriever) c.newInstance();
					//uses the object normally via the interface
					results = sr.getResults(query, results);
//					System.out.println(sr.getSource());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return results;
	}

	/**
	 * Getter for the retrievers
	 * @return
	 */
	public String[] getRetrievers() {
		return retrievers;
	}

	/**
	 * Setter for the retrievers
	 * @param retrievers
	 */
	public void setRetrievers(String[] retrievers) {
		this.retrievers = retrievers;
	}
	
}
