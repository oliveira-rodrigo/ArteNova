package com.br.artenova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.br.artenova.Helpers.ArrayAdapterItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArteNovaActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arte_nova);

		final ListView listview = (ListView) findViewById(R.id.listView);

		/*
		 * String[] values = new String[] { "Android", "iPhone",
		 * "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
		 * "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X",
		 * "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
		 * "Android", "iPhone", "WindowsMobile" };
		 * 
		 * final ArrayList<String> list = new ArrayList<String>(); for (int i =
		 * 0; i < values.length; ++i) { list.add(values[i]); } final
		 * StableArrayAdapter adapter = new StableArrayAdapter(this,
		 * android.R.layout.simple_list_item_1, list);
		 * listview.setAdapter(adapter);
		 */

		// add your items, this can be done programatically
		// your items can be from a database
		ObjectItem[] ObjectItemData = new ObjectItem[20];

		ObjectItemData[0] = new ObjectItem(91, "Mercury");
		ObjectItemData[1] = new ObjectItem(92, "Watson");
		ObjectItemData[2] = new ObjectItem(93, "Nissan");
		ObjectItemData[3] = new ObjectItem(94, "Puregold");
		ObjectItemData[4] = new ObjectItem(95, "SM");
		ObjectItemData[5] = new ObjectItem(96, "7 Eleven");
		ObjectItemData[6] = new ObjectItem(97, "Ministop");
		ObjectItemData[7] = new ObjectItem(98, "Fat Chicken");
		ObjectItemData[8] = new ObjectItem(99, "Master Siomai");
		ObjectItemData[9] = new ObjectItem(100, "Mang Inasal");
		ObjectItemData[10] = new ObjectItem(101, "Mercury 2");
		ObjectItemData[11] = new ObjectItem(102, "Watson 2");
		ObjectItemData[12] = new ObjectItem(103, "Nissan 2");
		ObjectItemData[13] = new ObjectItem(104, "Puregold 2");
		ObjectItemData[14] = new ObjectItem(105, "SM 2");
		ObjectItemData[15] = new ObjectItem(106, "7 Eleven 2");
		ObjectItemData[16] = new ObjectItem(107, "Ministop 2");
		ObjectItemData[17] = new ObjectItem(108, "Fat Chicken 2");
		ObjectItemData[18] = new ObjectItem(109, "Master Siomai 2");
		ObjectItemData[19] = new ObjectItem(110, "Mang Inasal 2");

		// our adapter instance
		ArrayAdapterItem adapter = new ArrayAdapterItem(this,
				R.layout.list_view_departamento_item, ObjectItemData);

		// create a new ListView, set the adapter and item click listener
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListenerListViewItem());
	}

	/*
	 * private class StableArrayAdapter extends ArrayAdapter<String> {
	 * 
	 * HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
	 * 
	 * public StableArrayAdapter(Context context, int textViewResourceId,
	 * List<String> objects) { super(context, textViewResourceId, objects); for
	 * (int i = 0; i < objects.size(); ++i) { mIdMap.put(objects.get(i), i); } }
	 * }
	 */

	public class ObjectItem {
		public int itemId;
		public String itemName;

		// constructor
		public ObjectItem(int itemId, String itemName) {
			this.itemId = itemId;
			this.itemName = itemName;
		}
	}

	/*
	 * Here you can control what to do next when the user selects an item
	 */
	public class OnItemClickListenerListViewItem implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			Context context = view.getContext();

			TextView textViewItem = ((TextView) view
					.findViewById(R.id.textViewItem));

			// get the clicked item name
			String listItemText = textViewItem.getText().toString();

			// get the clicked item ID
			String listItemId = textViewItem.getTag().toString();

			// just toast it
			showMessage("Item: " + listItemText + ", Item ID: " + listItemId);
			
			Intent nextScreen = new Intent(getApplicationContext(),
					ProdutosActivity.class);
			startActivity(nextScreen);
		}
	}
}