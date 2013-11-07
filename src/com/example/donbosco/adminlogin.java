package com.example.donbosco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminlogin extends Activity {
	Button login,cancel;
	EditText uname,pass;
	
	
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminlogin_activity);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
login=(Button)findViewById(R.id.button1);
cancel=(Button)findViewById(R.id.button2);
uname=(EditText)findViewById(R.id.editText1);
pass=(EditText)findViewById(R.id.editText2);
login.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String username=uname.getText().toString();
		String password=pass.getText().toString();
		if(username.equals("")||password.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
		}
		
		else
		{
			
			try
				{
			     
				  Class.forName("net.sourceforge.jtds.jdbc.Driver");
				  Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
			      Statement stmt = con.createStatement();
				 ResultSet reset = stmt.executeQuery("select * from Login where UserName='"+username+"' and Password='"+password+"'");
			if(reset.next())
				{
				String u1=reset.getString("UserName");
				Toast.makeText(getApplicationContext(), "Welcome "+u1, Toast.LENGTH_SHORT).show();
					String p1=reset.getString("Password");
					if(u1.equals(username)&&p1.equals(password))
						{
							
							Intent login=new Intent(getBaseContext(),adminpage.class);
							startActivity(login);
						}
					else
					{
						Toast.makeText(getApplicationContext(), " Username and password does not match", Toast.LENGTH_LONG).show();
					}
			
				}
				else
						{
							Toast.makeText(getApplicationContext(), "Incorrect Username or password", Toast.LENGTH_LONG).show();	
						}
		}
				
		catch(Exception ex )
		{
			Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
			
		}
		


		// TODO Auto-generated method stub
		
	}
}
});

cancel.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		finish();
		// TODO Auto-generated method stub
		
	}
});
}
}
