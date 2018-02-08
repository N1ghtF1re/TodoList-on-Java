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
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnect {

/** 
 * Make a query in the database
 * @param query
 */
   void newQuery(String query) throws SQLException {
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
                con.close();
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
    }
    
   	/** 
   	 * Add tasks from the DB
   	 * @param TF
   	 */
    void addLabelFromDB (TodoFrame TF) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            final String CONNECTION =
	    	         "jdbc:derby:TodoDatabase2;create=true";
            Connection con = DriverManager.getConnection(CONNECTION);
            try {
                Statement stmt = con.createStatement();
                ResultSet resultset = stmt.executeQuery("SELECT * FROM TODO"); // Извлекаем из БД все записи
           
                while(resultset.next()) {
                	// Сортируем полученные данные

    				System.out.print(resultset.getInt("ID") + "; ");
    				Task task = new Task();
    				task.setTitle(resultset.getString("TITLE"));
    				task.setDescription(resultset.getString("DESCRIPTION"));
    				task.setID(resultset.getInt("ID"));
    				task.setData(resultset.getTimestamp("TDATE"));
    				TF.addLabels(task); // Добавляем задачу на фрейм
    				TodoFrame.LastID = resultset.getInt("ID");
    				
    			}
                //stmt.executeUpdate(query);
                
                stmt.close();
            } finally {
            	System.out.println("\nЗапрос выполнен");
                con.close();
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
    }
}
