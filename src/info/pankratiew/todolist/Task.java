/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */
package info.pankratiew.todolist;

import java.util.Date;

public class Task {
	private String Title;
	private String Description;
	private int ID;
	private Date data;
	
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
