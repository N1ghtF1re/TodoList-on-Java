/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */

package info.pankratiew.todolist;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class TodoFrame extends JFrame{
	public static int LastID;
	private static final long serialVersionUID = 1L;
	JTextField tf_addNewTodo = new JTextField(30);
	JTextArea ta_addDesc = new JTextArea("Описание", 10, 50); 
	JLabel label = new JLabel("Добавить новую задачу");
	JButton btn_add = new JButton("Добавить");
	DBConnect m = new DBConnect();
	
	public TodoFrame() {
		setTitle("Todo-List");
		setLayout(new FlowLayout());
		add(label);
		add(tf_addNewTodo);
		add(ta_addDesc);
		add(btn_add);
		btn_add.addActionListener(new ActionListenerBTN());
		setSize(600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//m.newQuery("INSERT INTO TODO values (2,\'Кеклол\', \'Кеклолвотэтоповорот\')");
		m.KekLol(this);
		System.out.println(LastID);
	}
	class ActionListenerBTN implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String ttl = tf_addNewTodo.getText();
				String dsc = ta_addDesc.getText();
				if ((ttl.replaceAll(" ", "").equals("")) || (dsc.replaceAll(" ", "").equals(""))) {
					throw new NoTextException();
				}
				m.newQuery("INSERT INTO TODO values (" + ++LastID + ",\'" + ttl + "\', \'" + dsc + "\')");
				ShowTodoFrame.addNewLabel(ttl + "\n" + dsc);
			} catch(NoTextException e1) {
				JOptionPane.showMessageDialog(null, "Заголовок/Описание не могут быть пустыми");
			}
		}
	}
	
}
