package com.example.xinyi3_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


//the class for actions done inside the arrayList
public class ItemList implements Serializable {
/**
	 * ItemList serialization ID
	 */
	private static final long serialVersionUID = 1885050434788476946L;
	protected ArrayList<TodoItems> itemList=null;
	protected transient ArrayList<Listener> listeners=null;	

	//make arrayList of todoItems and new listeners for arraylist update
	public ItemList(){
		itemList=new ArrayList<TodoItems>();
		listeners = new ArrayList<Listener>();
	}
	
	//get listeners
	private ArrayList<Listener> getListeners(){
		if(listeners==null){
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	//return the itemList
	public Collection<TodoItems> getItems() {
		return itemList;
	}
	
	//will notify listeners each time an item is added
	public void addItem(TodoItems testItem) {
		itemList.add(testItem);
		notifyListeners();
	}
	
	//update listeners update varies depending on the task called
	private void notifyListeners() {
		for(Listener listener:getListeners()){
			listener.update();
		}	
	}

	//remove an item from itemList and update list
	public void removeItem(TodoItems testItem) {
		// TODO Auto-generated method stub
		itemList.remove(testItem);
		notifyListeners();
	}

	public int size() {
		// TODO Auto-generated method stub
		return itemList.size();
	}
	
	//iterate eachItem in the list, if item has a checked box, increase the count by 1
	public int CheckedSize(){
		int a=0;
		for (TodoItems itemLists:itemList){
			if (itemLists.checked == true ){
				a++;
			}
		}
		return a;
	}
	
	//iterate eachItem in the list, if item doesn't have a checked box, increase the count by 1
	public int UncheckedSize(){
		int a=0;
		for (TodoItems itemLists:itemList){
			if (itemLists.checked == false ){
				a++;
			}
		}
		return a;
	}

	public boolean contains(TodoItems testItem) {
		// TODO Auto-generated method stub
		return itemList.contains(testItem);
	}
	
	//add listener
	public void addListener(Listener l){
		getListeners().add(l);
	}
	
	//remove listener
	public void removeListener(Listener l){
		getListeners().remove(l);
	}
	
	//using the boolean value checked to modify the name of the Item and display a checked or unchecked box
	public void checkItem(TodoItems testItem){
		if (testItem.checked==false){
			testItem.editName("[x]"+testItem.getOgName());
			testItem.setCheck(true);
			notifyListeners();
		}else{
			testItem.editName("[]"+testItem.getOgName());
			testItem.setCheck(false);
			notifyListeners();
		}
	}
	
	//iterate through list and return a string consisting all items
	public String getAllItems(){
		String a = "";
		for (TodoItems itemLists:itemList){
			a = a +" " + itemLists.getName() + "\n";
		}
		return a;
	}
	
	//iterate through list and return a string consisting sleceted items based on a boolean value
	public String getSelectedItems(){
		String a="";
		for (TodoItems itemLists:itemList){
			if (itemLists.getSelect()==true){
				a = a +" " + itemLists.getName() + "\n";
			}
		}
		return a;
	}
	
	// change the boolean value of a item to indicate it is selected
	public void selectItem(TodoItems testItem){
		if (testItem.selected==false){
			testItem.setSelect(true);
		}else{
			testItem.setSelect(false);
		}
	}

	//reset the all selected items to unselected
	public void clear()
	{
		for (TodoItems itemLists:itemList){
			if (itemLists.getSelect()==true){
				itemLists.setSelect(false);
			}
		}
	}
}
