package com.example.donbosco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
//mageView im=(ImageView)findViewById(R.id.imageView1);
//.setBackgroundResource(R.drawable.ic_launcher);

		final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            	
                Intent mInHome = new Intent(MainActivity.this, Home.class);
                MainActivity.this.startActivity(mInHome);
                MainActivity.this.finish();
            }
        }, 1000);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}