package com.br.artenova;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class ProdutoOptionsFragment extends Fragment {

	ImageView imageViewInfo;
	Activity mActivity;

	String backgroundColor;
	String textColor;

	int padLeft, padRight, padTop, padBottom;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		backgroundColor = "#8C8989";
		textColor = "#FFFFFF";

		padRight = padLeft = 20;
		padTop = padBottom = 5;

		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.produto_options_fragment,
				container, false);

		mActivity = getActivity();
		
		imageViewInfo = (ImageView) view.findViewById(R.id.imageViewInfo);
		imageViewInfo.setOnClickListener(new OnClickListener() {
		 public void onClick(View v) {
		 infoActivity(v);
		 }
		 });
		//
		// mTextView2 = (TextView) view.findViewById(R.id.menu2);
		// mTextView2.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// changeView(v);
		// }
		// });
		//
		// mTextView3 = (TextView) view.findViewById(R.id.menu3);
		// mTextView3.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// changeView(v);
		// }
		// });
		//
		// mTextView4 = (TextView) view.findViewById(R.id.menu4);
		// mTextView4.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// changeView(v);
		// }
		// });
		//
		// mTextView5 = (TextView) view.findViewById(R.id.menu5);
		// mTextView5.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// changeView(v);
		// }
		// });
		//
		// configMenu();

		return view;
	}

	public void changeView(View view) {

		// Intent nextScreen;
		//
		// switch (view.getId()) {
		// case R.id.menu1:
		// nextScreen = new Intent(mActivity, OGrupoActivity.class);
		// startActivity(nextScreen);
		// break;
		// case R.id.menu2:
		// nextScreen = new Intent(mActivity, ArteNovaActivity.class);
		// startActivity(nextScreen);
		// break;
		// case R.id.menu3:
		// nextScreen = new Intent(mActivity, OGrupoActivity.class);
		// startActivity(nextScreen);
		// break;
		// case R.id.menu4:
		// nextScreen = new Intent(mActivity, OGrupoActivity.class);
		// startActivity(nextScreen);
		// break;
		// case R.id.menu5:
		// nextScreen = new Intent(mActivity, ContatoActivity.class);
		// startActivity(nextScreen);
		// break;
		// }
	}

	private void configMenu() {
		// String nome = mActivity.getClass().getSimpleName();
		// android.widget.Toast.makeText(getActivity(), nome,
		// android.widget.Toast.LENGTH_SHORT).show();

		// switch (mActivity.getClass().getSimpleName()) {
		// case "OGrupoActivity":
		// mTextView1.setClickable(false);
		// mTextView1.setBackgroundResource(R.drawable.menu_background);
		// mTextView1.setTextColor(Color.parseColor(textColor));
		// break;
		// case "ArteNovaActivity":
		// case "ProdutosActivity":
		// mTextView2.setClickable(false);
		// mTextView2.setBackgroundResource(R.drawable.menu_background);
		// mTextView2.setTextColor(Color.parseColor(textColor));
		// break;
		// case "ContatoActivity":
		// mTextView5.setClickable(false);
		// mTextView5.setBackgroundResource(R.drawable.menu_background);
		// mTextView5.setTextColor(Color.parseColor(textColor));
		// break;
		// }
	}

	public void infoActivity(View view) {
		Intent nextScreen = new Intent(mActivity, InfoProdutoActivity.class);
		startActivity(nextScreen);
	}
}
