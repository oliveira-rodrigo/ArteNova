package com.br.artenova;

import java.util.List;
import java.util.Map;

import com.br.artenova.Data.DataBaseHelper;
import com.br.artenova.Models.OGrupo;

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
	private DataBaseHelper dbHelper;
	private OGrupo ogrupo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ogrupo);
		
		List<OGrupo> grupo = OGrupo.listAll(OGrupo.class);
		
		imgViewBig = (ImageView) findViewById(R.id.imageViewBig);
		imgViewSmall1 = (ImageView) findViewById(R.id.imageViewSmall1);
		imgViewSmall2 = (ImageView) findViewById(R.id.imageViewSmall2);		
		imgViewSmall3 = (ImageView) findViewById(R.id.imageViewSmall3);
		textView = (TextView) findViewById(R.id.textView);
		
		String str ="<h2>Title</h2><br><p>Description here</p><div style='height: 1000px; background: Red'>teste</div>";
		for(int i=0; i<10;i++)
			str +="<br/><h2>Title</h2><br><p>Description here</p><div style='height: 1000px; background: Red'>teste</div>";
		Spanned sp = Html.fromHtml(str);
		textView.setText(grupo.get(0).getDescricao());
		
		imgViewBig.setImageDrawable(imgViewSmall1.getDrawable());
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
