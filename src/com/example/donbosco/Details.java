package com.example.donbosco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Details extends Activity {
	String getstring;
	Button exit;
	TextView event_name,f_time,t_time,f_date,t_date,venue1,description;
	DBdata dbcon=new DBdata();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_activity);
		event_name=(TextView)findViewById(R.id.ename);
		f_time=(TextView)findViewById(R.id.ftime);
		t_time=(TextView)findViewById(R.id.ttime);
		f_date=(TextView)findViewById(R.id.fdate);
		t_date=(TextView)findViewById(R.id.tdate);
		venue1=(TextView)findViewById(R.id.venue2);
		description=(TextView)findViewById(R.id.desc2);
		exit=(Button)findViewById(R.id.exit);
		getstring=getIntent().getStringExtra("keyname");
		try
		{

//			  Class.forName("net.sourceforge.jtds.jdbc.Driver");
//			  Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
			Connection con=dbcon.getConnection();
			  Statement stmt = con.createStatement();
		      ResultSet edetails = stmt.executeQuery("select EventName,(Convert(nvarchar,FromTime,104)) As FromDate,(Convert(nvarchar,FromTime,104)) As ToDate,(LTRIM(RIGHT(CONVERT(VARCHAR(20), FromTime, 100), 7))) As fromtime,(LTRIM(RIGHT(CONVERT(VARCHAR(20), Totime, 100), 7))) As totime,Venue,Description from EventNote where EventName='"+getstring+"'");
		      while(edetails.next())
		      {
		    	  String event_name1=edetails.getString("EventName");
		    	  String from_date=edetails.getString("FromDate");
		    	  String to_date=edetails.getString("ToDate");
		    	  String from_time=edetails.getString("fromtime");
		    	  String to_time=edetails.getString("totime");
		    	  String venue=edetails.getString("Venue");
		    	  String desc=edetails.getString("Description");
		    	  
      		 	event_name.setText(event_name1);
      		 	f_date.setText(from_date);
      		 	f_time.setText(from_time);
      		 	t_date.setText(to_date);
      		 	t_time.setText(to_time);
      		 	venue1.setText(venue);
      		 	description.setText(desc);
		      }
		}
		catch(Exception ex)
		{
			
		}
		event_name.setText(getstring);
exit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		finish();
		// TODO Auto-generated method stub
		
	}
});

}
}
