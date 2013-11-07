package com.example.donbosco;

import java.sql.Connection;
import java.sql.DriverManager;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;

public class DBdata extends Activity {
	private static final Context Context = null;
	 private static String driver=null;
	 private Connection      _conn;
	 public DBdata()
     {

 	 	 driver="net.sourceforge.jtds.jdbc.Driver";
 	 	 String dbURL = "jdbc:jtds:sqlserver://192.168.1.7/DBVData";
 	 	 String username ="sa";
 	 	 String password = "123";
 	 	 Connection dbCon = null;
     }
	 public  Connection  getConnection()
		        throws SQLException
		    {
		        if ( _conn != null ) { return _conn; }
		        
		        try {
		            Class.forName(driver);
		        }
		        catch (ClassNotFoundException ex)
		        {
		            throw new SQLException
		                ( "Could not locate " + ex.getMessage() );
		        }
		        String dbURL = "jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123";
		        try {
					_conn = DriverManager.getConnection(dbURL);
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        try {
					_conn.setAutoCommit( false );
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        return _conn;
		    }

}



