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
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JScrollPane;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoFrame extends JFrame implements  MouseListener{
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
	
		
		ta_addDesc.setBorder(BorderFactory.createLineBorder(Color.black));
		tf_addNewTodo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(label);
		mainPanel.add(tf_addNewTodo);
		mainPanel.add(ta_addDesc);
		mainPanel.add(btn_add);
		
		add(mainPanel);
		
		//add(listPanel);
		JScrollPane scroll = new JScrollPane(listPanel); // Add scroll-bar 
		scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(80,100));
        add(scroll, BorderLayout.CENTER);
        
        mainPanel.setBackground(new Color(235,240,244));
		listPanel.setBackground(new Color(235,240,244));
		tf_addNewTodo.setBorder(BorderFactory.createLineBorder(new Color(194,194,194),1));
		ta_addDesc.setLineWrap(true);
		ta_addDesc.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(194,194,194),1), 
												BorderFactory.createEmptyBorder(8, 12, 8, 8)));
		
		btn_add.addActionListener(new ActionListenerBTN());
		
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		connect.addLabelFromDB (this);
		//connect.newQuery("ALTER TABLE TODO ADD tdate TIMESTAMP");
		connect.newQuery("ALTER TABLE TODO ALTER COLUMN DESCRIPTION LONGVARCHAR");
		System.out.println(LastID);
	}

	/**
	 * Add tasks to frame
	 * @param Title
	 * @param Description
	 */
	void addLabels(Task task) {
		Date date = new Date();
		task.setData(date);
		
		SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy H:m:s");
		String strDate = formatDate.format(date);
       	JLabel lbl1 = new JLabel();
    	JLabel lbl2 = new JLabel();
    	JLabel lbl3 = new JLabel();
    	
		JPanel tmpPanel = new JPanel();
		
		tmpPanel.addMouseListener(this);
		tmpPanel.setLayout(new MigLayout("wrap"));
		lbl1.setText(task.getTitle());
		lbl2.setText("<html>" + task.getDescription() + "</html>");
		
		lbl3.setText(strDate);
		lbl3.setText(String.format("%-70s%10s%n","", strDate));
		lbl1.setFont(new Font("Arial", Font.BOLD, 16));
		lbl2.setFont(new Font("Arial", Font.ITALIC, 12));
		
		
		
		lbl1.setName("TaskName");
		tmpPanel.add(lbl1);
		tmpPanel.add(lbl2);
		tmpPanel.add(lbl3);
		tmpPanel.setName(""+task.getID());
		tmpPanel.setBackground(new Color(235,240,244));
		tmpPanel.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5),BorderFactory.createLineBorder(Color.black)));
		
		listPanel.add(tmpPanel,0);
		revalidate();
        repaint();
	}
	
	/**
	 * onClick button - add new label
	 *
	 */
	class ActionListenerBTN implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String ttl = tf_addNewTodo.getText();
				String dsc = ta_addDesc.getText();
				if ((ttl.replaceAll(" ", "").equals("")) || (dsc.replaceAll(" ", "").equals(""))) {
					throw new NoTextException(); // Generate Exception
				}
				connect.newQuery("INSERT INTO TODO values (" + ++LastID + ",\'" + ttl + "\', \'" + dsc + "\')");
				Task task = new Task();
				task.setTitle(ttl);
				task.setDescription(dsc);
				task.setID(LastID);
				
				addLabels(task);
				
				tf_addNewTodo.setText("");
				ta_addDesc.setText("");
			} catch(NoTextException e1) {
				JOptionPane.showMessageDialog(null, "Заголовок/Описание не могут быть пустыми"); // If title/description empty - show error
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(arg0.getComponent().getName());
		System.out.println(arg0.getID());
		String lblTitle = "";
		for(Component c : ((Container) arg0.getComponent()).getComponents()) {
			try {
				if (c.getName().equals("TaskName")) {
					lblTitle = ((JLabel) c).getText();
				}
			} catch (Exception e1) {
			}
		}
		int isDelete = JOptionPane.showConfirmDialog(this, "Точно удаляем задачу \"" + lblTitle + "\"?", "Удалить \'" + lblTitle + "\'?", JOptionPane.YES_NO_OPTION);
		if(isDelete == JOptionPane.YES_OPTION) {
			System.out.println("Удаляем");
			listPanel.remove(arg0.getComponent());
			connect.newQuery("DELETE FROM TODO WHERE ID = " + arg0.getComponent().getName());
			revalidate();
            repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}



	
}
