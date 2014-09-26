package com.example.xinyi3_notes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//responsible for email layout and navigation between email selected items
public class EmailItemlist extends Activity
{

	private EditText recipient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_itemlist);
		//four buttons in the email layout
		recipient = (EditText) findViewById(R.id.emailEditText);
		Button sendAll= (Button) findViewById(R.id.emailAllButton);
		Button sendTodo = (Button) findViewById(R.id.emallTodoButton);
		Button sendSelectedTodo=(Button)findViewById(R.id.emailSelectedTodoButton);
		Button sendArchivedTodo=(Button)findViewById(R.id.emailSelectedArchiveButton);
		//click to navigate to email selected archived items
		sendArchivedTodo.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
		    	Intent intent = new Intent(EmailItemlist.this,SelectedArchiveActivity.class);
		    	startActivity(intent);				
			}
		});
		
		//click to navigate to email selected todo items
		sendSelectedTodo.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
		    	Intent intent = new Intent(EmailItemlist.this,SelectedTodoActivity.class);
		    	startActivity(intent);				
			}
		});
		
		//click to email all todo items
		sendTodo.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				emailAllTodo();
				recipient.setText("");			
			}
		});
		
		//click to email all the items
		sendAll.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				emailAllItems();				
				recipient.setText("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_itemlist, menu);
		return true;
	}
	
	//Code referenced from http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application September 25th, 2014
	
	public void emailAllItems(){

		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("plain/text");
		String[] recipients = {recipient.getText().toString()};
		i.putExtra(Intent.EXTRA_EMAIL  , recipients);
		i.putExtra(Intent.EXTRA_SUBJECT, "All Todo Items With Archived Items");

		String todoItems = ItemListController.getItemList().getAllItems();
		String archiveItems = ItemListController.getArchiveList().getAllItems();

		String allItems =  "Todo Items: " + "\n" + todoItems +  "Archived Items: " + "\n" + archiveItems;
		i.putExtra(Intent.EXTRA_TEXT  , allItems);
	//Code referenced from http://examples.javacodegeeks.com/android/core/email/android-sending-email-example/        September 25th, 2014
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(EmailItemlist.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void emailAllTodo(){

		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("plain/text");
		String[] recipients = {recipient.getText().toString()};
		i.putExtra(Intent.EXTRA_EMAIL  , recipients);
		i.putExtra(Intent.EXTRA_SUBJECT, "All Todo Items");

		String todoItems = ItemListController.getItemList().getAllItems();

		String allItems =  "Todo Items:" + "\n" + todoItems;
		i.putExtra(Intent.EXTRA_TEXT  , allItems);

		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(EmailItemlist.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}
	
	//Navigate between menus
	public void toArchive(MenuItem menu){
    	Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EmailItemlist.this,Archives.class);
    	startActivity(intent);
    }
    
	public void toMain(MenuItem menu){
    	Toast.makeText(this, "Todo List", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EmailItemlist.this,MainActivity.class);
    	startActivity(intent);
    }
    
	public void toSummary(MenuItem menu){
    	Toast.makeText(this, "Summary", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(EmailItemlist.this,SummaryActivity.class);
    	startActivity(intent);
    }
    
	
	

}
