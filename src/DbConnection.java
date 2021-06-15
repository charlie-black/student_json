
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnection {

	      Connection conn = null;
	      public static Connection dbConnector() {
	      try {
	         Class.forName("org.postgresql.Driver");
	         Connection conn = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/StudentData",
	            "postgres", "baraza");
	      } catch (Exception e) {
	         JOptionPane.showMessageDialog(null, e);
	         
	        
	         
	      }
		return null;
	      
	   
	

}
}
