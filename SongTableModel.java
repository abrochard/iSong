import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 * The table model that forms the template for the song table
 * @author samaratrilling
 *
 */
public class SongTableModel extends AbstractTableModel{
	
	private Object[][] data;
	
	/**
	 * Turns the arraylist of results into a 2-D array
	 * @param songs
	 */
	public SongTableModel(ArrayList<Song> songs){
		data = new Object[songs.size()][9];
		
		int i = 0;
		for(Song s : songs){
			data[i][0] = songs.indexOf(s) + 1;
			data[i][1] = s.getTitle();
			data[i][2] = s.getArtist();
			data[i][3] = s.getAlbum();
			data[i][4] = s.getBitrate();
			data[i][5] = s.getDuration();
			data[i][6] = s.getSize();
			data[i][7] = s.getBPM();
			data[i][8] = s.getSource();
			i++;
		}
	}
	
	/**
	 * Returns number of columns
	 */
	public int getColumnCount(){
		return 9;
	}
	
	/**
	 * Returns number of rows
	 */
	public int getRowCount(){
		return data.length;
	}
	
	/**
	 * Returns the column name
	 */
	public String getColumnName(int columnIndex){
		switch (columnIndex) {
		case 0: return "#";
		case 1: return "Name";
		case 2: return "Artist";
		case 3: return "Album";
		case 4: return "Bitrate";
		case 5: return "Time";
		case 6: return "Size";
		case 7: return "BPM";
		case 8: return "Source";
		}
		return "error getColumnName";
	}
	
	/**
	 * returns the object at the specified row and col
	 * in the 2-D array list
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	/**
	 * Allows new user search results to be displayed
	 * by changing the contents of the displayed array
	 */
	public void setValueAt(Object value, int row, int col){
			Song newSong = (Song) value;
			
			int i = row;
			data[i][0] = row + 1;
			data[i][1] = newSong.getTitle();
			data[i][2] = newSong.getArtist();
			data[i][3] = newSong.getAlbum();
			data[i][4] = newSong.getBitrate();
			data[i][5] = newSong.getDuration();
			data[i][6] = newSong.getSize();
			data[i][7] = newSong.getBPM();
			data[i][8] = newSong.getSource();
			
			for(int j=0; j<9; j++){
				fireTableCellUpdated(row, j);
			}
	}
}