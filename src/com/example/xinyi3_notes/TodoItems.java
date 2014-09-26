package com.example.xinyi3_notes;

import java.io.Serializable;
//the item class for every item stored in arryList
public class TodoItems implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3769627290757962979L;
	protected String todoItemsName;
	public String ogItemName;
	public boolean checked = false;
	public boolean selected = false;
	

	//when the user inputs a name add an empty(unchecked) box in front of it
	public TodoItems(String todoItemsName) {
		this.ogItemName=todoItemsName;
		this.todoItemsName = "[]" + todoItemsName;
		// TODO Auto-generated constructor stub
	}
 
	//return current string
	public String getName() {
		// TODO Auto-generated method stub
		return this.todoItemsName;
	}
	
	//return boolean value== true if item is selected
	public boolean getSelect(){
		return this.selected;
	}
	
	//get the original name without the check box
	public String getOgName(){
		return this.ogItemName;
	}
	
	//return a string with the name of item
	public String toString(){
		return getName();
	}
	
	//change the item name
	public void editName(String name){
		this.todoItemsName=name;
	}
	
	//boolean value for item being checked or unchecked
	public void setCheck(boolean checkX){
		this.checked=checkX;
	}
	
	//change the boolean value for item being selected or unselected 
	public void setSelect(boolean selected){
		this.selected=selected;
	}

}
