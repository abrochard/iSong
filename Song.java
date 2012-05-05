import java.util.Comparator;

/**
 * Class to represent a song
 * @author Adrien, Dina
 *
 */
public class Song {

	private String title;
	private String artist;
	private String album;
	private int bitrate; //in kbps
	private int duration; //in seconds
	private double size; //in mb
	private int bpm; //Beat Per Minute
	private String source; //the website's URL where the song can be found
	private String retriever; //the retriever or service that found the song

	/**
	 * Default constructor
	 */
	public Song() {
		this.title = null;
		this.artist = null;
		this.album = null;
		this.bitrate = 0;
		this.duration = 0;
		this.size = 0;
		this.source = null;
	}

	/**
	 * Alternate constructor
	 * @param title
	 * @param artist
	 * @param album
	 * @param bitrate
	 * @param length
	 * @param size
	 * @param source
	 */
	public Song(String title, String artist, String album, int bitrate,
			int duration, double size, int bpm, String source, String retriever) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.bitrate = bitrate;
		this.duration = duration;
		this.size = size;
		this.bpm = bpm;
		this.source = source;
		this.retriever = retriever;
	}

	/**
	 * Gets title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets artist
	 * @return artst
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets artist
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Gets album
	 * @return album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Sets album
	 * @param album
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * Gets bitrate
	 * @return bitrate
	 */
	public int getBitrate() {
		return bitrate;
	}

	/**
	 * Sets bitrate
	 * @param bitrate
	 */
	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	/**
	 * Gets song duration
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets song duration
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets song size
	 * @return size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Sets size of song
	 * @param size
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * Gets beats per minute
	 * @return BPM
	 */
	public int getBPM() {
		return bpm;
	}

	/**
	 * Sets beats per minute
	 * @param bpm
	 */
	public void setBPM(int bpm) {
		this.bpm = bpm;
	}

	/**
	 * Gets song URL 
	 * @return url
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets song url
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Gets retriever used for song
	 * @return retriever
	 */
	public String getRetriever() {
		return retriever;
	}

	/**
	 * Sets retriever used for song
	 * @param retriever
	 */
	public void setRetriever(String retriever) {
		this.retriever = retriever;
	}

}