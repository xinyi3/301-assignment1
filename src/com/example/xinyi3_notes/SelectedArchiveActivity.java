package com.example.xinyi3_notes;

import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

//one of the email activities that sends selected items through email app
public class SelectedArchiveActivity extends Activity
{
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_archive);
		
		//listView displaying archived items
		ListView listView = (ListView) findViewById(R.id.ArchiveSelectListView);
		Collection<TodoItems> todoItems = ItemListController.getArchiveList().getItems();
        final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
        final ArrayAdapter<TodoItems> itemsAdaptor = new ArrayAdapter<TodoItems>(this, android.R.layout.simple_list_item_1,list );
        listView.setAdapter(itemsAdaptor);
        
        //button to go to the email app with selected items
        Button EmailSelsectedArchive=(Button) findViewById(R.id.selsectedArchiveButton);
        EmailSelsectedArchive.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				archiveSelected();
				clearSelected();
			}
		});
        
        //when item is selected change its boolean value and make a toast to notify user
        listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{

				TodoItems todoItems = list.get(position);
				ItemListController.getArchiveList().selectItem(todoItems);
				if(todoItems.selected==true){
					Toast.makeText(SelectedArchiveActivity.this, todoItems.getName() + " Selected", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(SelectedArchiveActivity.this, todoItems.getName() + " Unselected", Toast.LENGTH_SHORT).show();
				}

			}
        	
		});
        
        
	}


	//clear the selected items's boolean value
	public void clearSelected(){
		ItemListController.getItemList().clear();
	}
	
	//function that sends the email
	public void archiveSelected(){
		Intent i = new Intent(Intent.ACTION_SEND);
		
		i.setType("plain/text");
		i.putExtra(Intent.EXTRA_SUBJECT, "Selected Archive Items");

		String todoItems = ItemListController.getArchiveList().getSelectedItems();

		i.putExtra(Intent.EXTRA_TEXT  , todoItems);

		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(SelectedArchiveActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

}
