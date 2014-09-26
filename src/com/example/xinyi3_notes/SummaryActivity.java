package com.example.xinyi3_notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//displays a summary of todo items and archived items 
public class SummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		//textViews that contain integers
		final TextView tIUnchecked = (TextView) findViewById(R.id.todoUnchecked);
		final TextView tIChecked = (TextView) findViewById(R.id.todoChecked);
		final TextView archiveSize = (TextView) findViewById(R.id.archiveTodo);
		final TextView aChecked = (TextView) findViewById(R.id.archiveChecked);
		final TextView aUnchecked = (TextView) findViewById(R.id.archiveUnchecked);
	
		Button refresh = (Button) findViewById(R.id.refreshButton);
		refresh.setOnClickListener(new OnClickListener() {
			
			//press the button to get the summary 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tIUnchecked.setText(Integer.toString(ItemListController.getItemList().UncheckedSize()));
				tIChecked.setText(Integer.toString(ItemListController.getItemList().CheckedSize()));
				archiveSize.setText(Integer.toString(ItemListController.getArchiveList().size()));
				aChecked.setText(Integer.toString(ItemListController.getArchiveList().CheckedSize()));
				aUnchecked.setText(Integer.toString(ItemListController.getArchiveList().UncheckedSize()));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.summary, menu);
		return true;
	}
	
	//Navigate between the menus
	public void archives(MenuItem menu){
	    Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(SummaryActivity.this,Archives.class);
	    startActivity(intent);
	}
	
	public void todos(MenuItem menu){
	    Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(SummaryActivity.this,MainActivity.class);
	    startActivity(intent);
	}
	
	public void email(MenuItem menu){
	    Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(SummaryActivity.this,EmailItemlist.class);
	    startActivity(intent);
	}
	
	//Functions that gets the updated summary and displays it in the textviews
	public void updateSummary(){
		
		TextView tIUnchecked = (TextView) findViewById(R.id.todoUnchecked);
		tIUnchecked.setText(Integer.toString(ItemListController.getItemList().UncheckedSize()));
		
		TextView tIChecked = (TextView) findViewById(R.id.todoChecked);
		tIChecked.setText(Integer.toString(ItemListController.getItemList().CheckedSize()));
		
		TextView archiveSize = (TextView) findViewById(R.id.archiveTodo);
		archiveSize.setText(Integer.toString(ItemListController.getArchiveList().size()));
		
		TextView aChecked = (TextView) findViewById(R.id.archiveChecked);
		aChecked.setText(Integer.toString(ItemListController.getArchiveList().CheckedSize()));
		
		TextView aUnchecked = (TextView) findViewById(R.id.archiveUnchecked);
		aUnchecked.setText(Integer.toString(ItemListController.getArchiveList().UncheckedSize()));
	}

}
