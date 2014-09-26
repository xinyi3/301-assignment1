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

//one of the email activities that sends selected archive items through email app
public class SelectedTodoActivity extends Activity
{
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_todo);
		
		//listView displaying Todo items
		ListView listView = (ListView) findViewById(R.id.TodoSelectListView);
		Collection<TodoItems> todoItems = ItemListController.getItemList().getItems();
        final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
        final ArrayAdapter<TodoItems> itemsAdaptor = new ArrayAdapter<TodoItems>(this, android.R.layout.simple_list_item_1,list );
        listView.setAdapter(itemsAdaptor);
        
        //button to go to the email app with selected items
        Button emailSelsectedTodo=(Button) findViewById(R.id.selsectedArchiveButton);
        emailSelsectedTodo.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
				emailSelected();
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
				ItemListController.getItemList().selectItem(todoItems);
				if(todoItems.selected==true){
					Toast.makeText(SelectedTodoActivity.this, todoItems.getName() + " Selected", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(SelectedTodoActivity.this, todoItems.getName() + " Unselected", Toast.LENGTH_SHORT).show();
				}

			}
        	
		});
        
        
	}


	//clear the selected items's boolean value
	public void clearSelected(){
		ItemListController.getItemList().clear();
	}
	
	//function that sends the email
	public void emailSelected(){
		Intent i = new Intent(Intent.ACTION_SEND);
		
		i.setType("plain/text");
		i.putExtra(Intent.EXTRA_SUBJECT, "Selected Todo Items");

		String todoItems = ItemListController.getItemList().getSelectedItems();

		i.putExtra(Intent.EXTRA_TEXT  , todoItems);

		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(SelectedTodoActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	}

}
