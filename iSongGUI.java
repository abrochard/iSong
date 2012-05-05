import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Interacts with the user
 * @author samaratrilling
 *
 */
public class iSongGUI {

	/**
	 * Instance variables for the songs
	 */
	String query;
	ArrayList<String> retrievers;
	ArrayList<String> initRetrievers;
	SongSearcher engine;
	ArrayList<Song> songs;
	
	/**
	 * Instance variables for creating
	 * the main window
	 */
	JFrame window;
	JPanel mainPanel;

	/**
	 * Instance variables for the searcher
	 * panel
	 */
	JPanel searcher;
	JLabel sites;
	JCheckBox retriever1;
	JCheckBox retriever2;
	JCheckBox retriever3;
	JTextField enterQuery;
	JButton search;
	JLabel info;
	
	/**
	 * Instance variables for the results panel
	 */
	JScrollPane results;
	TableModel songModel;
	JTable data;
	ListSelectionModel listSelectionModel;
	
	/**
	 * Constructor for the GUI
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws MalformedURLException
	 */
	public iSongGUI() throws IllegalAccessException, InstantiationException, ClassNotFoundException, MalformedURLException{
		 
		window = new JFrame("iSong");
		mainPanel = (JPanel) window.getContentPane();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				getSearcherPanel(), getResultsPanel());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		addSearcherListeners();
		
		window.setSize(700,500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setVisible(true);
	
	}
	
	/**
	 * Creates all the components for the searcher panel
	 * @return the searcher panel to the split pane creator
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws MalformedURLException
	 */
	public JPanel getSearcherPanel() throws IllegalAccessException, ClassNotFoundException, InstantiationException, MalformedURLException{
		searcher = new JPanel();
		searcher.setLayout(new BoxLayout(searcher, BoxLayout.PAGE_AXIS));
		
		sites = new JLabel("<html><br><br><b>Your Sources</b><br><html>");
		retriever1 = new JCheckBox("Amazon");
		retriever2 = new JCheckBox("ccMixter");
		retriever3 = new JCheckBox("mp3Skull");
		
		enterQuery = new JTextField(20);
		enterQuery.setMaximumSize(enterQuery.getPreferredSize());
		search = new JButton("Search");
		info = new JLabel("<html><br><b>Rihanna</b>'s our <br> default " +
				" girl <br> here. Tell her <br> what you're <br> looking " +
				"for <br> in the search <br> bar and she'll <br> bring you" +
				" <br> what you <br> asked for.</html>");
		
		searcher.add(sites);
		searcher.add(retriever1);
		searcher.add(retriever2);
		searcher.add(retriever3);
		searcher.add(enterQuery);
		searcher.add(search);
		searcher.add(info);
		return searcher;
		
	}
	
	/**
	 * Creates all the components for the results panel,
	 * including the table, and calls all methods needed
	 * to get the song data and display it
	 * @return the results panel to the split pane creator
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public JScrollPane getResultsPanel() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		retrievers = new ArrayList<String>();
		
		initRetrievers = new ArrayList<String>();
		initRetrievers.add("AmazonRetriever");
		initRetrievers.add("CcMixterRetriever");
		initRetrievers.add("Mp3SkullRetriever");
		engine = new SongSearcher("Rihanna", initRetrievers);
		songs = engine.getSearchResults();
		
		songModel = new SongTableModel(songs);
		data = new JTable(songModel);
		data.setFillsViewportHeight(true);
	
		TableRowSorter<TableModel> sorter 
			= new TableRowSorter<TableModel>(data.getModel());
		data.setRowSorter(sorter);
		sorter.setComparator(0, intComp);
		sorter.setComparator(1, stringComp);
		sorter.setComparator(2, stringComp);
		sorter.setComparator(3, stringComp);
		sorter.setComparator(4, intComp);
		sorter.setComparator(5, intComp);
		sorter.setComparator(6, doubleComp);
		sorter.setComparator(7, intComp);
		sorter.setComparator(8, stringComp);
		
		data.getSelectionModel().addListSelectionListener(new RowListener());
		
		results = new JScrollPane(data);
		results.setLayout(new ScrollPaneLayout());
		
		return results;	
	}
	
	/**
	 * Adds all the listeners to the checkboxes,
	 * buttons and search bar in the searcher panel
	 */
	public void addSearcherListeners(){
		retriever1.setMnemonic(KeyEvent.VK_1);
		retriever1.setSelected(false);
		retriever1.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					retrievers.add("AmazonRetriever");
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					if(retrievers.contains("amazonRetriever")){
						int index = retrievers.indexOf("AmazonRetriever");
						retrievers.remove(index);
					}//end contains	
				}//end deselected
			}//end method
		});
		
		retriever2.setMnemonic(KeyEvent.VK_2);
		retriever2.setSelected(false);
		retriever2.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					retrievers.add("CcMixterRetriever");
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					if(retrievers.contains("CcMixterRetriever")){
						int index = retrievers.indexOf("CcMixterRetriever");
						retrievers.remove(index);
					}//end if contains	
				}//end deselected	
			}//end method
		});
		
		retriever3.setMnemonic(KeyEvent.VK_3);
		retriever3.setSelected(false);
		retriever3.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					retrievers.add("Mp3SkullRetriever");
				}
				if(e.getStateChange() == ItemEvent.DESELECTED){
					if(retrievers.contains("Mp3SkullRetriever")){
						int index = retrievers.indexOf("Mp3SkullRetriever");
						retrievers.remove(index);
					}//end if contains
				}//end deselected
			}//end method
		});
		
		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String query;
				if(enterQuery.getText().isEmpty()){query = "Rihanna";}
				else{query = enterQuery.getText();}
				
				if(retrievers.isEmpty()){
					JOptionPane.showMessageDialog(window, "Gotta enter at least one"+
							"source. Songs don't grow on trees!");
				}
				
				SongSearcher engine = new SongSearcher(query, retrievers);
				try{
					ArrayList<Song> newSongs = engine.getSearchResults();
					for(Song tune : newSongs){
						data.setValueAt(tune, newSongs.indexOf(tune), 0);
					}
				}
				catch(IllegalAccessException e){
					e.printStackTrace();
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
				catch(InstantiationException e){
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Compares two doubles (for the table)
	 */
	public Comparator<Double> doubleComp = new Comparator<Double>(){
		public int compare(Double d1, Double d2){
			return d1.compareTo(d2);
		}
	};
	
	/**
	 * Compares two ints (for the table)
	 */
	public Comparator<Integer> intComp = new Comparator<Integer>(){
		public int compare(Integer i1, Integer i2){
			return i1.compareTo(i2);
		}
	};

	/**
	 * Compares two Strings (for the table)
	 */
	public Comparator<String> stringComp = new Comparator<String>(){
		public int compare(String s1, String s2){
			return s1.compareTo(s2);
		}
	};
	
	/**
	 * Inner class for the listener to open the song link in
	 * a new window
	 * @author samaratrilling
	 *
	 */
	private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (data.getSelectedRow() == -1) {
                return;
            }
            if(Desktop.isDesktopSupported()){
            	int selectedRow = data.getSelectedRow();
            	int converted = data.convertRowIndexToModel(selectedRow);
            	String source = (String) data.getValueAt(converted, 8);
            	
            	try{
            		URI uri = new URI(source);
            		Desktop.getDesktop().browse(uri);
            		data.clearSelection();
            	}
            	catch(IOException e){
            		e.printStackTrace();
            	}	
            	catch(URISyntaxException e){
            		e.printStackTrace();
            	}//end catch
            }//end if
        }//end valueChanged
    }//end rowListener
}
