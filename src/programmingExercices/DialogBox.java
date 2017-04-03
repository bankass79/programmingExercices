package programmingExercices;

import javax.swing.JOptionPane;

public class DialogBox {

	public static void main(String[] args) {

		
		String doSomeThing= JOptionPane.showInputDialog("What would you like me to do?");
		
		String  doMessage= JOptionPane.showInputDialog(doSomeThing);
		
		System.out.println("I'm sorry, your name. I'm afraid I can't do that");
		
		//String answerMessage= JOptionPane.showInputDialog(doMessage, message);
	
		
		System.exit(0);


	}

}
