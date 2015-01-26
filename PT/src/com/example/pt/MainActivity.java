package com.example.pt;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create a new WebView for HTML to run
		WebView webview = new WebView(this);

		// Enable JavaScriptin WebView
		webview.getSettings().setJavaScriptEnabled(true);

		// Add the WebAppInterface class to the JavaScript so the HTML button
		// can access the methods from the WebAppInterface class.
		// JavaScriptInterface is called "ID".
		webview.addJavascriptInterface(new WebAppInterface(this), "ID");

		setContentView(webview);

		// Read & display elements from a HTML file in the WebView
		try {
			InputStream stream = this.getAssets().open("elements.html");
			int streamSize = stream.available();
			byte[] buffer = new byte[streamSize];
			stream.read(buffer);
			stream.close();
			String html = new String(buffer);
			webview.loadData(html, "text/html", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public class image {

		 public void change() {
		
		 ImageView image = (ImageView) (findViewById(R.id.imageView1));
		 image.setImageResource(R.raw.a1);
		 System.out.println("Image should have changed");
		
		 }
	}

	public class WebAppInterface {
		Context mContext;

		/** Instantiate the interface and set the context */
		WebAppInterface(Context c) {
			mContext = c;
		}

//		@JavascriptInterface
		public void getID(String toast) {
			// Show toast of the atomic symbol - ID
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

			// Switch to another screen - start second activity

			Intent intent = new Intent(MainActivity.this, Element.class);

			 startActivity(intent.putExtra("image", R.raw.a1));

			 startActivity(intent);

			// Using the ID, show a specific image on the second screen.

			// IF statement not working :D
			if (toast == "H") {
				System.out.println("TEST!!!!!!!");
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		 getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		 int id = item.getItemId();
		 if (id == R.id.action_settings) {
		 return true;
		 }
		return super.onOptionsItemSelected(item);
	}

}
