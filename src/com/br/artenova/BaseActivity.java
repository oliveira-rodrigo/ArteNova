package com.br.artenova;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore.Files;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends Activity {
	private Context _context;
	public String EMAIL_ADDRESS = "rodrigomagrello@gmail.com";
	private SharedPreferences sharedPref = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_context = getApplicationContext();
		
		sharedPref = this.getSharedPreferences(
				getString(R.string.Is_Autenticado), Context.MODE_PRIVATE);

	}

	public void backActivity(View view) {
		super.onBackPressed();
	}

	public void contatoActivity(View view) {
		Intent nextScreen = new Intent(getApplicationContext(),
				ContatoActivity.class);
		startActivity(nextScreen);
	}

	public void homeActivity(View view) {
		Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	public void showMessage(String texto) {
		android.widget.Toast.makeText(getApplicationContext(), texto,
				android.widget.Toast.LENGTH_LONG).show();
	}

	public void sendEmail(String nome, String emailAddress,
			String emailSubject, String emailBody) {
		final Intent emailIntent = new Intent(
				android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/html");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
				new String[] { emailAddress });
		emailIntent
				.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
		_context.startActivity(Intent.createChooser(emailIntent,
				"Sending email..."));
	}

	public StringBuilder inputStreamToString(InputStream is) {
		String rLine = "";
		StringBuilder answer = new StringBuilder();

		InputStreamReader isr = new InputStreamReader(is);

		BufferedReader rd = new BufferedReader(isr);

		try {
			while ((rLine = rd.readLine()) != null) {
				answer.append(rLine);
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	protected Boolean isAutenticado() {

		// verifica se o usuario ja foi autenticado
		long highScore = sharedPref.getLong(getString(R.string.Is_Autenticado),
				0);

		// showMessage(Long.toString(highScore));
		if (highScore == 1) {
			return true;
		} else {
			return false;
		}
	}

	protected void saveUsuarioAutenticado() {
		sharedPref.edit().putLong(getString(R.string.Is_Autenticado), 1)
				.apply();
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/*
	 * protected void criarBanco() {
	 * 
	 * String path = getApplicationContext().getDir("script_banco_dados",
	 * getApplicationContext().MODE_PRIVATE).getPath(); File file = new
	 * File(path + "/SQL_Database.sql"); int length = (int) file.length();
	 * 
	 * byte[] bytes = new byte[length];
	 * 
	 * try { FileInputStream in = new FileInputStream(file); try {
	 * in.read(bytes); } finally { in.close(); } } catch (FileNotFoundException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * }
	 * 
	 * String contents = new String(bytes); showMessage(contents); }
	 */

	/*private void criarBanco(SQLiteDatabase database, String dbname) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		AssetManager assetManager = _context.getAssets();
		InputStream inputStream = null;

		try {
			inputStream = assetManager.open(dbname);
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();

			String[] createScript = outputStream.toString().split(";");
			for (int i = 0; i < createScript.length; i++) {
				String sqlStatement = createScript[i].trim();
				// TODO You may want to parse out comments here
				if (sqlStatement.length() > 0) {
					database.execSQL(sqlStatement + ";");
				}
			}
		} catch (IOException e) {
			// TODO Handle Script Failed to Load
		} catch (SQLException e) {
			// TODO Handle Script Failed to Execute
		}
	}*/
}
