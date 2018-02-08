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
		return Description;
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
