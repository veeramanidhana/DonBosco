package com.example.donbosco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class userpage extends Activity {
	
	DBdata dbcon=new DBdata();
	int l=0;
	List<String> items = new ArrayList<String>();
	//ArrayList items = new ArrayList();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_activity);
		if (android.os.Build.VERSION.SDK_INT > 9)
		{
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	ListView ulist=(ListView)findViewById(R.id.listView1);
	try
	{
	  //Class.forName("net.sourceforge.jtds.jdbc.Driver");
	  //Connection con=DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7/DBVData;user=sa;password=123"); //Server
	  Connection con=dbcon.getConnection();
	  Statement stmt1=con.createStatement();
	   ResultSet len = stmt1.executeQuery("select Count(EventName)As clen from EventNote");
	if(len.next())
	{
		 int l=len.getInt("clen");
		 Statement stmt = con.createStatement();
	      ResultSet reset = stmt.executeQuery("select EventName from EventNote  where Status ='Inprogress'  order by EventName ASC");
		  for(int i=0;i<l;i++)
		   {
			   if(reset.next())
			   {
			   items.add(reset.getString("EventName")); 
		       }
		   }
			
		}
		//Toast.makeText(getBaseContext(),l, Toast.LENGTH_LONG).show();
		
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
		
			Intent details=new Intent(getBaseContext(),Details.class);
			details.putExtra("keyname",items.get(arg2));
			Toast.makeText(getBaseContext(),items.get(arg2), Toast.LENGTH_LONG).show();
			startActivity(details);
			// TODO Auto-generated method stub
			
		}
	});
}
}