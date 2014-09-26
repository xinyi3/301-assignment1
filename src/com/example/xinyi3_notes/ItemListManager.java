package com.example.xinyi3_notes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

////This class is used to save and load an Item list using serializable

public class ItemListManager {
	static final String prefFile="ItemList";
	static final String ilkey ="itemList";
	Context context;
	
	static private ItemListManager itemListManager=null;
	
	public ItemListManager(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	// use this call to initiate manager
	public static void initManager(Context context){
		if (itemListManager == null){
			if(context==null){
				throw new RuntimeException("missing context forIitemListManager");
			}
			itemListManager = new ItemListManager(context);
		}
	}
	
	public static ItemListManager getManager(){
		if (itemListManager==null){
			throw new RuntimeException("Did not initialize manager");		
		}
		return itemListManager;
	}
	
	//load archive arrayList 
	public ItemList loadItemList() throws IOException, ClassNotFoundException{
		SharedPreferences settings= context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String itemListData = settings.getString(ilkey, "");
		if(itemListData.equals("")){
			return new ItemList();
		}else{
			return itemListFromString(itemListData);
		}
	}
	
	//encode the array and save into byteArray
	static public ItemList itemListFromString(String itemListData) throws  IOException, ClassNotFoundException {
		ByteArrayInputStream bi= new ByteArrayInputStream(Base64.decode(itemListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ItemList)oi.readObject();
	}
	
	//read the byteArray and change it back
	static public String itemListToString(ItemList il) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bo= new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(il);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}

	//save archive arrayList using serializable
	public void saveItemList(ItemList il) throws IOException{
		SharedPreferences settings= context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(ilkey, itemListToString(il));
		editor.commit();
	}



}
