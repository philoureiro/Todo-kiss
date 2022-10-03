package db;

import java.sql.Connection;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	private static Properties loadProperties() {
		 Properties loadedProps = null;
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			 loadedProps = new Properties();
			 loadedProps.load(fs);		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return loadedProps;
	}
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
