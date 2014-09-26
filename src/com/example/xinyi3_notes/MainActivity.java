
package com.example.xinyi3_notes;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

//the activity responsible for the layout displayed when program starts(todo ItemList)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		// save/load Archive arrayList 
        ItemListManager.initManager(this.getApplicationContext());
        ArchiveListManager.initManager2(this.getApplicationContext());
        
		//display Item arrayList in listView and set an adapter
        ListView listView = (ListView) findViewById(R.id.todoListView);
        Collection<TodoItems> todoItems = ItemListController.getItemList().getItems();
        final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
        final ArrayAdapter<TodoItems> itemsAdaptor = new ArrayAdapter<TodoItems>(this, android.R.layout.simple_list_item_1,list );
        listView.setAdapter(itemsAdaptor);
        //contextMenu
        registerForContextMenu(listView);
        //added a change observer
        ItemListController.getItemList().addListener(new Listener(){
        	public void update(){
        		list.clear();
        		Collection<TodoItems> todoItems = ItemListController.getItemList().getItems();
        		list.addAll(todoItems);
        		itemsAdaptor.notifyDataSetChanged();
        	}
        });

        
        //when item is clicked check/uncheck the item
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TodoItems todoItems = list.get(position);
				ItemListController.getItemList().checkItem(todoItems);
				
			}
		});

    }

	//long click context menu
    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
    	
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Archive");
		menu.add("Delete");
    }

	//code for deleting and moving an archived item when long clicked
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onContextItemSelected(item);
        Collection<TodoItems> todoItems = ItemListController.getItemList().getItems();
        final ArrayList<TodoItems> list = new ArrayList<TodoItems>(todoItems);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		
		if (item.getTitle()=="Archive"){
			TodoItems todoItem = list.get(position);
			ItemListController.getArchiveList().addItem(todoItem);
			ItemListController.getItemList().removeItem(todoItem);
			Toast.makeText(this, "Moved Item to Archive", Toast.LENGTH_SHORT).show();
		}
		
		if (item.getTitle()=="Delete"){
			TodoItems todoItem = list.get(position);
			ItemListController.getItemList().removeItem(todoItem);
			Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
		}
		
		return true;
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	//Navigate between the menus
    public void archives(MenuItem menu){
    	Toast.makeText(this, "Archives", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this,Archives.class);
    	startActivity(intent);
    }
    
    public void summary(MenuItem menu){
    	Toast.makeText(this, "Summary", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
    	startActivity(intent);
    }
    
    public void email(MenuItem menu){
    	Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent(MainActivity.this,EmailItemlist.class);
    	startActivity(intent);
    }

    //add an item into the itemList when add button is clicked
    public void addItemAction(View v){
    	Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    	ItemListController it = new ItemListController();
    	EditText textView = (EditText) findViewById(R.id.todoEditText);
    	it.addItem(new TodoItems(textView.getText().toString()));
    	textView.setText("");
    }
    
}
