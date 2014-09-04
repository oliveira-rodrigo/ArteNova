package com.br.artenova;

import com.br.artenova.Helpers.HorizontialListView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AcabamentoPopupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acabamento_popup);

		HorizontialListView listview = (HorizontialListView) findViewById(R.id.horListView);
		listview.setAdapter(mAdapter);

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
}
