/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */

package info.pankratiew.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class DBConnect {
	/*public static void main(String[] args) {
		DBConnect m = new DBConnect();
        m.newQuery("INSERT INTO TODO values (1,\'Кеклол\', \'Кеклолвотэтоповорот\')");
		m.KekLol();
    }
 */
   void newQuery(String query) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            final String CONNECTION =
	    	         "jdbc:derby:TodoDatabase2;create=true";
            Connection con = DriverManager.getConnection(CONNECTION);
            try {
                Statement stmt = con.createStatement();
              /*  stmt.executeUpdate("create table TODO                 "
    		 			+   "(ID int NOT NULL PRIMARY KEY, 					"
    		 			+   "TITLE VARCHAR(32),                 		   "
    		 			+   "DESCRIPTION VARCHAR(255))                     ");*/
                stmt.executeUpdate(query);
                
                stmt.close();
            } finally {
            	System.out.println("eee");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void KekLol (TodoFrame TF) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            final String CONNECTION =
	    	         "jdbc:derby:TodoDatabase2;create=true";
            Connection con = DriverManager.getConnection(CONNECTION);
            try {
                Statement stmt = con.createStatement();
                ResultSet resultset = stmt.executeQuery("SELECT * FROM TODO ORDER by id DESC");
           
                while(resultset.next()) {
    				System.out.print(resultset.getInt("ID") + "; ");
    				TF.addLabels(resultset.getString("TITLE"), resultset.getString("DESCRIPTION"));;
    				TodoFrame.LastID = resultset.getInt("ID");
    			}
                //stmt.executeUpdate(query);
                
                stmt.close();
            } finally {
            	System.out.println("eee");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
