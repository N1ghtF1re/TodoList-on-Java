package info.pankratiew.todolist;

import javax.swing.JLabel;

/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */

public class ShowTodoFrame {
	static TodoFrame TF = new TodoFrame();
	
	public static void main(String args[]) {
		TF.setVisible(true);
	}
	static void addNewLabel(String txt) {
		JLabel Newlbl = new JLabel();
		Newlbl.setText(txt);
		System.out.println(Newlbl.getText());
		TF.add(Newlbl);
		TF.update(g);
	}
	
}
