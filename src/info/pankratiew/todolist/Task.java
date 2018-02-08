/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */
package info.pankratiew.todolist;

import java.sql.Timestamp;

public class Task {
	private String Title;
	private String Description;
	private int ID;
	private Timestamp data;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		final int gran = 50;
		if (Description.length() < gran) {
			return Description;
		} else {
			java.lang.StringBuffer strBuf = new java.lang.StringBuffer(Description);
	
			for (int i = gran; i <= Description.length(); i += gran) {
				while (strBuf.charAt(i) != ' ') {
					i++;
				}
				strBuf.insert(i, "<br>");
				System.out.println(strBuf.toString());
			}
		return strBuf.toString();
			
		}
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	
}
