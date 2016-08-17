package SQL;

import java.io.*;
import java.sql.*;

public class ConnectionProvider implements Provider {
	
	static Connection connection=null;
	
	public static Connection getCon() {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionURL, username, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return connection;
	}
	
	public static void saveQueryToFile(PreparedStatement pst) {
	//This method saves the queries to file for further reference and backup
		try(FileWriter fw = new FileWriter("queryBackup.sql", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(pst + ";\n");
			} catch (IOException e) {
			    e.printStackTrace();
			}
	}
}
