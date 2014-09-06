package com.br.artenova;

import java.util.List;
import java.util.Map;

import com.br.artenova.Data.OGrupoRepository;
import com.br.artenova.Data.SQLiteDatabaseHelper;
import com.br.artenova.Helpers.VersionUtils;
import com.br.artenova.Models.OGrupo;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OGrupoActivity extends BaseActivity {

	private ImageView imgViewBig;
	private ImageView imgViewSmall1;
	private ImageView imgViewSmall2;
	private ImageView imgViewSmall3;
	private TextView textView;
	private OGrupo ogrupo = null;
	private OGrupoRepository ogrupoRep = null;
	private SQLiteDatabaseHelper helper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ogrupo);

		Context context = getApplicationContext();

		try {
			this.helper = new SQLiteDatabaseHelper(context, "Banco_ArteNova",
					null, VersionUtils.getVersionCode(context));
			this.helper.onCreate(this.helper.getWritableDatabase());
			this.ogrupoRep = new OGrupoRepository(helper);
			this.ogrupo = ogrupoRep.selectFirst();

			imgViewBig = (ImageView) findViewById(R.id.imageViewBig);
			imgViewSmall1 = (ImageView) findViewById(R.id.imageViewSmall1);
			imgViewSmall2 = (ImageView) findViewById(R.id.imageViewSmall2);
			imgViewSmall3 = (ImageView) findViewById(R.id.imageViewSmall3);
			textView = (TextView) findViewById(R.id.textView);

			textView.setText(ogrupo.getDescricao());
			imgViewBig.setImageDrawable(imgViewSmall1.getDrawable());
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeImage(View view) {
		Drawable drawable = null;

		switch (view.getId()) {
		case R.id.imageViewSmall1:
			drawable = imgViewSmall1.getDrawable();
			break;
		case R.id.imageViewSmall2:
			drawable = imgViewSmall2.getDrawable();
			break;
		case R.id.imageViewSmall3:
			drawable = imgViewSmall3.getDrawable();
			break;
		}

		imgViewBig.setImageDrawable(drawable);
	}
}