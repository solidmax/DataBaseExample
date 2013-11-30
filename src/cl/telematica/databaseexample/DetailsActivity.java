package cl.telematica.databaseexample;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.databaseexample.adapters.RssAdapter;
import cl.telematica.databaseexample.adapters.RssAdapter_details;
import cl.telematica.databaseexample.database.DataBaseClass;
import cl.telematica.databaseexample.models.EarthQuakeDataModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class DetailsActivity extends Activity {
	
	private String url = null;
	private ListView listView;
	private List <EarthQuakeDataModel> list = new ArrayList<EarthQuakeDataModel>(); 
	
//	private ProgressBar mProgressBar;
//	private RelativeLayout hPBarLayout;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		listView = (ListView) findViewById(R.id.listView2);
//		hPBarLayout = (RelativeLayout) findViewById(R.id.hPBarLayout);
//		mProgressBar = (ProgressBar) findViewById(R.id.legacy_navigation_progressBar);
		final String[] datos=new String[]{"elem1","elem2","elem3"}; 
		//datos[1]=c.getstring(1);
	
		DataBaseClass dbInstance = new DataBaseClass(this);
		SQLiteDatabase db = dbInstance.getReadableDatabase();
		
		Cursor c = db.rawQuery("SELECT * FROM alumnos LIMIT 7", null);
		if(c.moveToFirst()){
			do{
			 EarthQuakeDataModel model = new EarthQuakeDataModel();
			 model.title  = c.getString(1);
			 model.magnitude = c.getString(2);
			 model.location = c.getString(3);
			 model.depth = c.getString(4);
			 model.latitude = c.getString(5);
			 model.longitude = c.getString(5);
			 model.dateTime = c.getString(6);
			 list.add(model);
			} while (c.moveToNext());
			}
			c.close();

			RssAdapter_details adapter = new RssAdapter_details(getApplicationContext(), R.string.app_name, list);
			listView.setAdapter(adapter);
		

	}
}
