package programmingExercices;

import javax.swing.JFrame;

public class EmptyFrameViewer {

	public static void main(String[] args) {
	
		JFrame frame = new JFrame();
		frame.setSize(200, 400);
		frame.setVisible(true);
		frame.setTitle("An Empty Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
