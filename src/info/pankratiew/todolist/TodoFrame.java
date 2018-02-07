/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */

package info.pankratiew.todolist;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class TodoFrame extends JFrame{
	public static int LastID = 0;
	private static final long serialVersionUID = 1L;
	
	JTextField tf_addNewTodo = new JTextField(15);
	JTextArea ta_addDesc = new JTextArea("Описание", 10, 30); 
	JLabel label = new JLabel("Добавить новую задачу");
	JButton btn_add = new JButton("Добавить");
	DBConnect connect = new DBConnect();
	JPanel mainPanel = new JPanel();
	JPanel listPanel = new JPanel();
	
	/**
	 * Window Constructor
	 */
	public TodoFrame() {
		setTitle("Todo-List");
		setLayout(new GridLayout(1, 2));
		
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(label);
		mainPanel.add(tf_addNewTodo);
		mainPanel.add(ta_addDesc);
		mainPanel.add(btn_add);
		
		add(mainPanel);
		//add(listPanel);
		JScrollPane scroll = new JScrollPane(listPanel); // Add scrollbar 
		scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(80,100));
        add(scroll, BorderLayout.CENTER);
		btn_add.addActionListener(new ActionListenerBTN());
		
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		connect.addLabelFromDB (this);
		System.out.println(LastID);
	}

	/**
	 * Add tasks to frame
	 * @param Title
	 * @param Description
	 */
	void addLabels(String Title, String Description) {
       	JLabel lbl1 = new JLabel();
    	JLabel lbl2 = new JLabel();
    	
		JPanel tmpPanel = new JPanel();
		
		tmpPanel.setLayout(new MigLayout("wrap"));
		lbl1.setText(Title);
		lbl2.setText(Description);
		tmpPanel.add(lbl1);
		tmpPanel.add(lbl2);
		
		tmpPanel.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),BorderFactory.createLineBorder(Color.black)));
		
		listPanel.add(tmpPanel,0);
	}
	
	/**
	 * onClick button - add new label
	 * @author Win10
	 *
	 */
	class ActionListenerBTN implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String ttl = tf_addNewTodo.getText();
				String dsc = ta_addDesc.getText();
				if ((ttl.replaceAll(" ", "").equals("")) || (dsc.replaceAll(" ", "").equals(""))) {
					throw new NoTextException();
				}
				connect.newQuery("INSERT INTO TODO values (" + ++LastID + ",\'" + ttl + "\', \'" + dsc + "\')");
				addLabels(ttl, dsc);
				
				tf_addNewTodo.setText("");
				ta_addDesc.setText("");
			} catch(NoTextException e1) {
				JOptionPane.showMessageDialog(null, "Заголовок/Описание не могут быть пустыми"); // If title/description empty - show error
			}
		}
	}
	
}
