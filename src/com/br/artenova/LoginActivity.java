package com.br.artenova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.br.artenova.Helpers.VersionUtils;
import com.br.artenova.Data.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	private EditText username = null;
	private EditText password = null;
	// private Button login;
	private SQLiteDatabaseHelper helper = null;
	private JsonHelper jsonHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Context context = getApplicationContext();

		// criar o banco
		try {
			this.helper = new SQLiteDatabaseHelper(context, "Banco_ArteNova",
					null, VersionUtils.getVersionCode(context));

			this.helper.onCreate(this.helper.getWritableDatabase());
			//showMessage("Banco Criado");

		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isAutenticado()) {
			// openHome();
		}

		setContentView(R.layout.activity_login);
		username = (EditText) findViewById(R.id.editTextLogin);
		password = (EditText) findViewById(R.id.editTextSenha);
		// login = (Button) findViewById(R.id.buttonLogin);
	}

	public void login(View view) throws JSONException {

		if (username.getText().toString().equals("")
				|| password.getText().toString().equals("")) {
			showMessage("Informe usuário e senha para efetuar o login!");
			return;
		}

		int retorno = 0;

		if (username.getText().toString().equals("admin")
				&& password.getText().toString().equals("admin")) {

			retorno = checkLogin();

			if (retorno == 200) {

				// salvar no app que usuario ja foi autenticado
				saveUsuarioAutenticado();

				requestUpdateDB();

				// verificar versao, se for zero, request update, senao
				// verificar se tem update passando a versao do servidor na url
				// de update
				// se tiver update atualiza o banco
				// ocultar frame do login e mostrar frame de progress

				openHome();

			} else {
				if (retorno != 500)
					showMessage("Usuário ou senha incorretos!");
			}
		} else {
			showMessage("Usuário ou senha incorretos!");
		}
	}

	private void openHome() {
		Intent nextScreen = new Intent(getApplicationContext(),
				HomeActivity.class);
		startActivity(nextScreen);
	}

	public int checkLogin() throws JSONException {

		int statusCode = 0;

		if (isOnline()) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://texas10.com.br/api/application/login");

			try {
				// json
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Email", "oliveira.rodrigoaugusto@hotmail.com");
				jsonobj.put("Senha", "123456");
				jsonobj.put("Device", "android-teste");

				StringEntity se = new StringEntity(jsonobj.toString());
				se.setContentType("application/json;charset=UTF-8");
				httppost.setEntity(se);

				HttpResponse httpresponse = httpclient.execute(httppost);

				statusCode = httpresponse.getStatusLine().getStatusCode();

				if (statusCode != 200) {
					String responseText = null;
					responseText = EntityUtils.toString(httpresponse
							.getEntity());

					JSONObject json = new JSONObject(responseText);
					showMessage(json.getString("Mensagem"));
				}

			} catch (ClientProtocolException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
				return statusCode;
			} catch (IOException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
				return statusCode;
			} catch (JSONException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
			}

			return statusCode;
		} else {
			showMessage("Sem conexão com a Internet. Tente novamente mais tarde. ");
			return 500; // sem conexão
		}
	}

	private void requestUpdateDB() {
		if (isOnline()) {
			int statusCode = 0;

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://texas10.com.br/api/application/update");

			HttpResponse httpresponse = null;
			String responseText = null;
			JSONObject json = null;

			try {
				// json
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Versao", "0");
				jsonobj.put("Device", "android-teste");

				StringEntity se = new StringEntity(jsonobj.toString());
				se.setContentType("application/json;charset=UTF-8");
				httppost.setEntity(se);

				httpresponse = httpclient.execute(httppost);
				statusCode = httpresponse.getStatusLine().getStatusCode();

				responseText = EntityUtils.toString(httpresponse.getEntity());

				if (statusCode != 200) {
					json = new JSONObject(responseText);
					showMessage(json.getString("Mensagem"));
				} else {
					json = new JSONObject(responseText);

					//Log.d("json", responseText);
					
					this.jsonHelper = new JsonHelper(json, helper);					
				}

			} catch (ClientProtocolException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
				// return statusCode;
			} catch (IOException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
				// return statusCode;
			} catch (JSONException e) {
				showMessage(e.getMessage());
				e.printStackTrace();
			}
		} else
			showMessage("Sem conexão com a Internet. Tente novamente mais tarde. ");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
