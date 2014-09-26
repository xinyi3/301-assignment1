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

//This class is used to save and load an archive list using serializable

public class ArchiveListManager {
	static final String prefFile="ArchiveList";
	static final String ilkey ="archiveList";
	Context context;
	
	static private ArchiveListManager archiveListManager=null;
	
	public ArchiveListManager(Context context) {
		this.context = context;
	}

	// use this call to initiate manager
	public static void initManager2(Context context){
		if (archiveListManager == null){
			if(context==null){
				throw new RuntimeException("missing context forIarchiveListManager");
			}
			archiveListManager = new ArchiveListManager(context);
		}
	}
	
	public static ArchiveListManager getManager(){
		if (archiveListManager==null){
			throw new RuntimeException("Did not initialize manager");		
		}
		return archiveListManager;
	}
	
	//load archive arrayList 
	public ItemList loadArchiveList() throws IOException, ClassNotFoundException{
		SharedPreferences settings= context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String archiveListData = settings.getString(ilkey, "");
		if(archiveListData.equals("")){
			return new ItemList();
		}else{
			return archiveListFromString(archiveListData);
		}
	}
	
	//encode the array and save into byteArray
	static public ItemList archiveListFromString(String archiveListData) throws  IOException, ClassNotFoundException {
		ByteArrayInputStream bi= new ByteArrayInputStream(Base64.decode(archiveListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ItemList)oi.readObject();
	}
	
	//read the byteArray and change it back
	static public String archiveListToString(ItemList il) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bo= new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(il);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes,Base64.DEFAULT);
	}

	//save archive arrayList using serializable
	public void saveArchiveList(ItemList il) throws IOException{
		SharedPreferences settings= context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(ilkey, archiveListToString(il));
		editor.commit();
	}



}
