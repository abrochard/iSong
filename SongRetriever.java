import java.util.ArrayList;

/**
 * Interface for the retrievers searching from different websites
 * @author Adrien
 *
 */
public interface SongRetriever {

	/**
	 * This method returns an ArrayList of songs corresponding to the 
	 * results of the query on the particular website tested 
	 * The method is given the query and the results so far,
	 * it will add to to the results and return a more complete list
	 * @param query 
	 * @param results
	 * @return 
	 */
	ArrayList<Song> getResults(String query, ArrayList<Song> results);

	/**
	 * This method just return the website's URL from which the song are searched
	 * @return
	 */
	String getSource();

}