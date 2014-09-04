package com.br.artenova;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class HomeActivity extends Activity {

	private ImageButton imgBtn1;
	private ImageButton imgBtn2;
	private ImageButton imgBtn3;
	private ImageSwitcher imageSwitcher;
	private int position;

	private Timer myTimer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		imgBtn1 = (ImageButton) findViewById(R.id.imageButton1);
		imgBtn2 = (ImageButton) findViewById(R.id.imageButton2);
		imgBtn3 = (ImageButton) findViewById(R.id.imageButton3);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);

		imageSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView myView = new ImageView(getApplicationContext());
				myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				myView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				return myView;
			}
		});

		Drawable drawable = null;
		drawable = imgBtn1.getDrawable();
		setImageSwitcher(drawable);

		myTimer = new Timer();
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				TimerMethod();
			}

		}, 0, 3000);
	}

	public void changeImage(View view) {
		Drawable drawable = null;

		switch (view.getId()) {
		case R.id.imageButton1:
			drawable = imgBtn1.getDrawable();
			position = 1;
			break;
		case R.id.imageButton2:
			drawable = imgBtn2.getDrawable();
			position = 2;
			break;
		case R.id.imageButton3:
			drawable = imgBtn3.getDrawable();
			position = 0;
			break;
		}

		setImageSwitcher(drawable);
	}

	private void setImageSwitcher(Drawable drawable) {
		// animacoes
		Animation in = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right);
		Animation out = AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left);

		imageSwitcher.setInAnimation(out);
		imageSwitcher.setOutAnimation(in);
		imageSwitcher.setImageDrawable(drawable);
	}

	public void changeImageAuto() {
		Drawable drawable = null;

//		Toast.makeText(getApplicationContext(), Integer.toString(position),
//				Toast.LENGTH_SHORT).show();

		switch (position) {
		case 0:
			drawable = imgBtn1.getDrawable();
			break;
		case 1:
			drawable = imgBtn2.getDrawable();
			break;
		case 2:
			drawable = imgBtn3.getDrawable();
			break;
		}

		setImageSwitcher(drawable);

		position += 1;

		if (position == 3)
			position = 0;
	}

	private void TimerMethod() {
		// This method is called directly by the timer
		// and runs in the same thread as the timer.

		// We call the method that will work with the UI
		// through the runOnUiThread method.
		this.runOnUiThread(Timer_Tick);
	}

	private Runnable Timer_Tick = new Runnable() {
		public void run() {

			// This method runs in the same thread as the UI.

			// Do something to the UI thread here
			changeImageAuto();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
