package fr.in2p3.cc.tms.tsmhelper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DisplayVid extends Activity {

	private String file_path = null;
	private List<TmsSlot> tms_slots = new ArrayList<TmsSlot>();
		
	// UI
	private ListView list_v = null;
	private EditText input_search = null;
	
	// UI helpers
	private ArrayAdapter<TmsSlot> list_adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_vid);
		
		list_v = (ListView) this.findViewById(R.id.listView1);
		input_search = (EditText) this.findViewById(R.id.editText1);
		
		// Get the file_path from the intent
		Intent intent = getIntent();
		file_path = intent.getStringExtra(MainActivity.FILE_PATH);
		
		try {
			parseCSVFile();
			
		} catch (IOException e) {
			Log.e("CSV_PARSER", "Parsing failed" + e.getMessage());
			return;
		}
				
		list_adapter = new ArrayAdapter<TmsSlot>(this, R.layout.volume_list_item, tms_slots);
		
		addTextWatcher();
		
		list_v.setAdapter(list_adapter);
	}

	private void addTextWatcher() {
		
		input_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
				DisplayVid.this.list_adapter.getFilter().filter("LT" + arg0);				
			}
			
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_vid, menu);
		return true;
	}
	
	private void parseCSVFile() throws IOException {
		
		File f = new File(file_path);
		FileReader fr = new FileReader(f);
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = null;
		String volname = null;
		String slotname = null;
		
		while (true) {
			line = br.readLine();
			
			if (line == null) {
				break;
			}
			
			String[] values = line.split(",");
			
			volname = values[1];
			slotname = values[0];
			
			tms_slots.add(new TmsSlot(volname, slotname));
		}
		
		br.close();
	}

}
