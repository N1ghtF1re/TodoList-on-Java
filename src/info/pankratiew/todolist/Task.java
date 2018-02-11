/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */
package info.pankratiew.todolist;

import java.util.Date;
import java.sql.Timestamp;

public class Task {
	private String Title;
	private String Description;
	private int ID;
	private Timestamp data;
	private Date needDate;
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
		final int gran = 100;
		if (Description.length() < gran) {
			return Description;
		} else {
			java.lang.StringBuffer strBuf = new java.lang.StringBuffer(Description);
			
			for (int i = gran; i <= Description.length(); i += gran) {
				if(Description.contains(" ")) {
					while (strBuf.charAt(i) != ' ') {
						i--;
						if((i <= 0) || (i >= Description.length())) {
							break;
						}
					}
				}
				strBuf.insert(i, "<br>");
				//System.out.println(strBuf.toString());
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
	public Date getNeedDate() {
		return needDate;
	}
	public void setNeedDate(Date date) {
		this.needDate = date;
	}
	
	
}
