/**
 * Class to represent a song
 * @author Adrien
 *
 */
public class Song implements Comparable<Song>{

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
	 * Constructor
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getRetriever() {
		return retriever;
	}

	public void setRetriever(String retriever) {
		this.retriever = retriever;
	}

	@Override
	public String toString() {
		return "Song [title=" + title + ", artist=" + artist + ", album="
				+ album + ", bitrate=" + bitrate + ", duration=" + duration
				+ ", size=" + size + ", bpm=" + bpm + ", source=" + source
				+ ", retriever=" + retriever + "]";
	}

	@Override
	public int compareTo(Song arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
