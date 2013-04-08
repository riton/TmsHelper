package fr.in2p3.cc.tms.tsmhelper;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	public static final String FILE_PATH = "fr.in2p3.cc.tms.tmshelper.FILE_PATH";

	final int ACTIVITY_CHOOSE_FILE = 1;
	
	/* UI */
	private EditText file_path = null;
	private Button file_chooser = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		file_path = (EditText) this.findViewById(R.id.editText1);
		file_chooser = (Button) this.findViewById(R.id.button1);
		
		/*
		Intent intent = new Intent(this, DisplayVid.class);
		intent.putExtra(FILE_PATH, "/sdcard/slots.list");
		startActivity(intent);
		*/
		
		setupChooseFileHandler(file_chooser);		
	}
	
	private void setupChooseFileHandler(Button btn) {
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent chooseFile = null;
				Intent intent = null;
				chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
				chooseFile.setType("file/*");
				intent = Intent.createChooser(chooseFile, "Choose a file");
				
				startActivityForResult(intent, ACTIVITY_CHOOSE_FILE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
			case ACTIVITY_CHOOSE_FILE:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					String path = uri.getPath();
					Intent intent = new Intent(this, DisplayVid.class);
					intent.putExtra(FILE_PATH, path);
					
					startActivity(intent);
				}
		}
		
	}

}
