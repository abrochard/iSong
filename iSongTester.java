import java.net.MalformedURLException;

import javax.swing.SwingUtilities;


public class iSongTester{
    public static void main(String[] args) {
    	try{
			iSongGUI test = new iSongGUI();
		}
		catch(IllegalAccessException e){
			e.printStackTrace();
		}
		catch(InstantiationException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
