package com.br.artenova;

import java.io.File;
import java.util.ArrayList;

import com.br.artenova.Helpers.GridViewAdapter;
import com.br.artenova.Helpers.HorizontialListView;
import com.br.artenova.Helpers.ImageItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AcabamentosActivity extends Activity {

	private GridView gridView;
	private GridViewAdapter customGridAdapter;
	private File directory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acabamentos);
		
		// if there is no SD card, create new directory objects to make
		// directory on device
		if (Environment.getExternalStorageState() == null) {
			// create new file directory object
			directory = new File(Environment.getDataDirectory() + "/Imagens/");
			/*
			 * this checks to see if there are any previous test photo files if
			 * there are any photos, they are deleted for the sake of memory
			 */
			if (directory.exists()) {
				File[] dirFiles = directory.listFiles();
				if (dirFiles.length != 0) {
					for (int ii = 0; ii <= dirFiles.length; ii++) {
						// dirFiles[ii].delete();
					}
				}
			}
			// if no directory exists, create new directory
			if (!directory.exists()) {
				directory.mkdir();
			}

			// if phone DOES have sd card
		} else if (Environment.getExternalStorageState() != null) {
			// search for directory on SD card
			directory = new File(Environment.getExternalStorageDirectory()
					+ "/Imagens/");
			if (directory.exists()) {
				File[] dirFiles = directory.listFiles();
				if (dirFiles.length > 0) {
					for (int ii = 0; ii < dirFiles.length; ii++) {
						// dirFiles[ii].delete();
					}
					dirFiles = null;
				}
			}
			// if no directory exists, create new directory to store test
			// results
			if (!directory.exists()) {
				directory.mkdir();
			}
		}// end of SD card checking

		HorizontialListView listview = (HorizontialListView) findViewById(R.id.horListView);
		listview.setAdapter(mAdapter);

		gridView = (GridView) findViewById(R.id.gridView);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid_image,
				getData());
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(myOnItemClickListener);
	}
	
	private ArrayList getData() {
		final ArrayList imageItems = new ArrayList();

		int count = 1;
		for (File f : directory.listFiles()) { // do your stuff here
			Bitmap bitmap = BitmapFactory.decodeFile(f.getPath());
			imageItems.add(new ImageItem(bitmap, f.getName()));
			count++;
		}
		return imageItems;
	}

	private static String[] dataObjects = new String[] { "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #99" };

	private BaseAdapter mAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return dataObjects.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View retval = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.list_view_departamento_item_horizontal, null);
			TextView title = (TextView) retval.findViewById(R.id.textViewItem);
			title.setText(dataObjects[position]);
			title.setTag(dataObjects[position]);

			return retval;
		}

	};

	OnItemClickListener myOnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ImageView imgViewItem = ((ImageView) view
					.findViewById(R.id.imageGrid));

			// get the clicked item ID
			//String listItemId = imgViewItem.getTag().toString();

			// just toast it
			//showMessage("Item ID: " + listItemId);

			Intent nextScreen = new Intent(getApplicationContext(),
					AcabamentoPopupActivity.class);
			startActivity(nextScreen);

		}
	};
}
