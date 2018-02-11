/**
 * @author Pankratiew Alexandr
 *
 * @site pankratiew.info
 *
 */

package info.pankratiew.todolist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {

/** 
 * 
 * Make a query in the database
 * @param query
 * 
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
                System.out.println("Запрос выполнен. Текст запроса: ");
                stmt.close();
            } finally {
            	System.out.println(query);
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
    @SuppressWarnings("deprecation")
	void addLabelFromDB (TodoFrame TF) throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            final String CONNECTION =
	    	         "jdbc:derby:TodoDatabase2;create=true";
            Connection con = DriverManager.getConnection(CONNECTION);
            try {
                Statement stmt = con.createStatement();
                
                String addQuery = "";
                switch(TF.status) {
                	case ALL:
                	break;
                	
                	case TODAY:
                	Date currDate = new Date(System.currentTimeMillis());
                    addQuery = " WHERE need_date = \'" + currDate + "\'";	
                    break;
                    
                	case TOMOROW: 
                	Date nextDate = new Date(System.currentTimeMillis() + 24*60*60*1000);
                    addQuery = " WHERE need_date = \'" + nextDate + "\'";	
                    break;
                }
               
                
                System.out.println("SELECT * FROM TODO" + addQuery);
                ResultSet resultset = stmt.executeQuery("SELECT * FROM TODO" + addQuery); // Извлекаем из БД все записи"
                
                while(resultset.next()) {
                	// Сортируем полученные данные

    				//System.out.print(resultset.getInt("ID") + "; ");
    				Task task = new Task();
    				task.setTitle(resultset.getString("TITLE"));
    				task.setDescription(resultset.getString("DESCRIPTION"));
    				task.setID(resultset.getInt("ID"));
    				task.setData(resultset.getTimestamp("TDATE"));
    				task.setNeedDate(resultset.getDate("need_date"));
    				TF.addLabels(task); // Добавляем задачу на фрейм
    			

    			}
                resultset  = stmt.executeQuery("SELECT id FROM TODO ORDER BY id"); 
                while(resultset.next()) {
                	TodoFrame.LastID = resultset.getInt("ID");	
                	System.out.println(TodoFrame.LastID);
    			}
                stmt.close();
            } finally {
            	System.out.println("\nДанные извлечены");
                con.close();
            }
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
    }
}
