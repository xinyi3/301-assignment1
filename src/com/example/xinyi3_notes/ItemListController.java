package com.example.xinyi3_notes;

import java.io.IOException;
//controller that controls the itemList and archiveList
public class ItemListController {
	private static ItemList itemList = null;
	private static ItemList archiveList=null;
	
	//return the ItemList and save the list
	static public ItemList getItemList(){
		if ( itemList==null){
			try {
				itemList= ItemListManager.getManager().loadItemList();
				itemList.addListener(new Listener(){
					
					@Override
					public void update(){
						saveItemList();
					}
				});
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList form ItemListManager");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ItemList form ItemListManager");
			}
		}
		return itemList;
	}
	
	//save the list using serializable
	static public void saveItemList(){
		try{
			ItemListManager.getManager().saveItemList(getItemList());
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ItemList form ItemListManager");
		}
	}
	
	
	//return the ArchiveList and save the list
	static public ItemList getArchiveList(){
		if ( archiveList==null){
			try {
				archiveList= ArchiveListManager.getManager().loadArchiveList();
				archiveList.addListener(new Listener(){
					
					@Override
					public void update(){
						saveArchiveList();
					}
				});
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ArchiveList form ArchiveListManager");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Could not deserialize ArchiveList form ArchiveListManager");
			}
		}
		return archiveList;
	}
	
	//add an item into ItemList
	public void addItem(TodoItems todoItems) {
		getItemList().addItem(todoItems);
	}
	
	//save Archivelist using serializable
	static public void saveArchiveList(){
		try{
			ArchiveListManager.getManager().saveArchiveList(getArchiveList());
		}catch (IOException e){
			e.printStackTrace();
			throw new RuntimeException("Could not deserialize ArchiveList form ArchiveListManager");
		}
	}

}
