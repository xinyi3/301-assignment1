package com.example.xinyi3_notes;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//One of the core activities that let you access the Archives 
public class Archives extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_archive);
		// save/load Archive arrayList 
        ItemListManager.initManager(this.getApplicationContext());
        ArchiveListManager.initManager2(this.getApplicationContext());

		//display Archive arrayList in listView and set an adapter
		ListView listView = (ListView) findViewById(R.id.archiveListView);
		Collection<TodoItems> todoItems = ItemListController.getArchiveList().getItems();
		final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
		final ArrayAdapter<TodoItems> itemsAdaptor = new ArrayAdapter<TodoItems>(this, android.R.layout.simple_list_item_1,list );
	    listView.setAdapter(itemsAdaptor);
	  //contextMenu
        registerForContextMenu(listView);
	  //added a change observer
	    ItemListController.getArchiveList().addListener(new Listener(){
        	public void update(){
        		list.clear();
        		Collection<TodoItems> todoItems = ItemListController.getArchiveList().getItems();
        		list.addAll(todoItems);
        		itemsAdaptor.notifyDataSetChanged();
        	}
        });

    }

	//long click context menu
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
    	
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Todo List");
		menu.add("Delete");
    }

	//code for deleting and moving an archived item when long clicked
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onContextItemSelected(item);
        Collection<TodoItems> todoItems = ItemListController.getArchiveList().getItems();
        final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		
		if (item.getTitle()=="Todo List"){
			TodoItems todoItem = list.get(position);
			ItemListController.getItemList().addItem(todoItem);
			ItemListController.getArchiveList().removeItem(todoItem);
			Toast.makeText(this, "Moved Item to Todo List", Toast.LENGTH_SHORT).show();
		}
		
		if (item.getTitle()=="Delete"){
			TodoItems todoItem = list.get(position);
			ItemListController.getArchiveList().removeItem(todoItem);
			Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();

		}
		return true;
	}
	
	// Navigate between menus
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}
	
	 public void todos(MenuItem menu){
	    	Toast.makeText(this, "Todo Items", Toast.LENGTH_SHORT).show();
	    	Intent intent = new Intent(Archives.this,MainActivity.class);
	    	startActivity(intent);
	    }
	 
	 public void summary(MenuItem menu){
	    	Toast.makeText(this, "Summary", Toast.LENGTH_SHORT).show();
	    	Intent intent = new Intent(Archives.this,SummaryActivity.class);
	    	startActivity(intent);
	    }
	 
	 public void email(MenuItem menu){
	    	Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
	    	Intent intent = new Intent(Archives.this,EmailItemlist.class);
	    	startActivity(intent);
	    }

}
