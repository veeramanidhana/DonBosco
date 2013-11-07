package com.example.donbosco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import android.widget.TextView;
import android.widget.Toast;

public class Update_event extends Activity {
	String getname,status,getcode;
	Button update,cancel;
	TextView Title,fdate,tdate,venue,desc;
	RadioGroup radiogroup;
	RadioButton progress,completed;
	DBdata dbcon=new DBdata();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_activity);
		update=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		 radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
	     progress=(RadioButton)findViewById(R.id.radioprogress);
	     completed=(RadioButton)findViewById(R.id.radiocompleted);
	     Title=(TextView)findViewById(R.id.textView3);
	     fdate=(TextView)findViewById(R.id.fdate1);
	     tdate=(TextView)findViewById(R.id.tdate1);
	     venue=(TextView)findViewById(R.id.venue1);
	     desc=(TextView)findViewById(R.id.desc1);
		getname=getIntent().getStringExtra("name");
		getcode=getIntent().getStringExtra("code");
		Title.setText(getname);
		try{

//			  Class.forName("net.sourceforge.jtds.jdbc.Driver");
//			  Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
			 Connection con=dbcon.getConnection();  
			Statement stmt = con.createStatement();
		      ResultSet reset = stmt.executeQuery("select (Convert(nvarchar,FromTime,104)) As FromDate,(Convert(nvarchar,FromTime,104)) As ToDate,Venue,Description from EventNote where EventCode='"+getcode+"'");
		      while(reset.next())
		      {
		    	  String f_date=reset.getString("Fromdate");
		    	  String t_date=reset.getString("ToDate");
		    	  String venue2=reset.getString("Venue");
		    	  String desc2=reset.getString("Description");
		    	  fdate.setText(f_date);
		    	  tdate.setText(t_date);
		    	  venue.setText(venue2);
		    	  desc.setText(desc2);
		      }
		}
		catch(Exception ex)
		{
			
		}
		 radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
			 
				@Override
				public void onCheckedChanged(RadioGroup arg0, int arg1) 
				{
					
					if(progress.isChecked()==true)
					{
					status="Inprogress";
					}
					if(completed.isChecked()==true)
					{
						status="Completed";
					}
					
				}
			});
		 update.setOnClickListener(new OnClickListener() 
		 {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try
				{

					  Class.forName("net.sourceforge.jtds.jdbc.Driver");
					  Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
					  Statement stmt = con.createStatement();
				     
					  stmt.executeUpdate("Update EventNote set Status='"+status+"' where EventCode='"+getcode+"'");
					 // Toast.makeText(getBaseContext(),getcode,Toast.LENGTH_LONG).show();
					  
					 Toast.makeText(getBaseContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                       finish();				
				}
				catch(Exception ex)
				{
					Toast.makeText(getBaseContext(),ex.getMessage(), Toast.LENGTH_LONG).show();
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
