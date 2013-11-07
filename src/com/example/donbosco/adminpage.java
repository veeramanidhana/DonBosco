package com.example.donbosco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class adminpage extends Activity {
	DBdata dbcon=new DBdata();
	List<String> items = new ArrayList<String>();
	List<String> ecode = new ArrayList<String>();
	//ArrayList items = new ArrayList();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_activity);
		if (android.os.Build.VERSION.SDK_INT > 9)
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	ListView ulist=(ListView)findViewById(R.id.eventdetails);
	try
	{
//	  Class.forName("net.sourceforge.jtds.jdbc.Driver");
//	  Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
		Connection con=dbcon.getConnection();
		Statement stmt = con.createStatement();
      ResultSet reset = stmt.executeQuery("select EventName,EventCode from EventNote  where Status ='Inprogress'  order by EventName ASC");
	 
	 //ResultSet len = stmt.executeQuery("select Count(EventName) As length from EventNote");
	
		//l=len.getInt("length");
	   for(int i=0;i<100;i++)
	   {
		   if(reset.next())
		   {
		   items.add(reset.getString("EventName")); 
		   ecode.add(reset.getString("EventCode"));
	       }
	   }
		
	}
	      catch(Exception ex)	
        {
	      Toast.makeText(getApplicationContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
		}
	      ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,items);
          ulist.setAdapter(adapter);
    
	      ulist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		
			Intent details1=new Intent(getBaseContext(),Update_event.class);
			details1.putExtra("name",items.get(arg2));
			details1.putExtra("code",ecode.get(arg2));
//			Toast.makeText(getBaseContext(),items.get(arg2), Toast.LENGTH_LONG).show();
			startActivity(details1);
			// TODO Auto-generated method stub
			
		}
	});
}
}

