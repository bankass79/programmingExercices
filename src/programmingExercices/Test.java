package programmingExercices;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Test {

	public static void main(String[] args) {

		try {
			URL imageLocation = new URL(
					"http://www.parisinfo.com/var" + "/otcp/sites/images/media/1.-photos/02.-sites-culturels-630-x-405/"
							+ "tour-eiffel-trocadero-630x405-c-thinkstock/37221-1-fre-FR/"
							+ "Tour-Eiffel-Trocadero-630x405-C-Thinkstock.jpg");

			JOptionPane.showMessageDialog(null, "Hello", "La Tour Eiffel", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon(imageLocation));

			System.exit(0);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
