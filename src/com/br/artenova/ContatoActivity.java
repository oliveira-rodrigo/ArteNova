package com.br.artenova;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ContatoActivity extends BaseActivity {

	private TextView textViewArteNova;
	private TextView textViewUnue;
	private TextView textViewSteel;
	private PopupWindow pwindo;
	private Button btnClosePopup;

	private EditText editTextNome;
	private EditText editTextEmail;
	private EditText editTextSubject;
	private EditText editTextBody;

	private View layout;
	private LayoutInflater inflater;
	
	private Integer Popup_Width = 320;
	private Integer Popup_Height = 480;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contato);

		textViewArteNova = (TextView) findViewById(R.id.textViewArteNova);
		textViewUnue = (TextView) findViewById(R.id.textViewUnue);
		textViewSteel = (TextView) findViewById(R.id.textViewSteel);

		String strArteNova = "Rua Travessa Dalva de Oliveira, 300 <br/>"
				+ "Parque das Industrias Leves <br/>"
				+ "Londrina - PR <br/><br/>" + "comercial@artenova.ind.br";
		Spanned spArteNova = Html.fromHtml(strArteNova);
		textViewArteNova.setText(spArteNova);

		String strUnue = "Rua Lupércio Pozatto, 675 <br/>"
				+ "Parque Industrial José Belinati <br/>"
				+ "Londrina - PR <br/><br/>" + "comercial@unue.com.br";
		Spanned spUnue = Html.fromHtml(strUnue);
		textViewUnue.setText(spUnue);

		String strSteel = "Rua Jaburu, 175 <br/>"
				+ "Parque das Industrias Leves <br/>"
				+ "Londrina - PR <br/><br/>" + "comercial@steelforma.com.br";
		Spanned spSteel = Html.fromHtml(strSteel);
		textViewSteel.setText(spSteel);

		// We need to get the instance of the LayoutInflater
		inflater = (LayoutInflater) ContatoActivity.this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		layout = inflater.inflate(R.layout.popup_contato,
				(ViewGroup) findViewById(R.id.popupContato));
	}

	public void openPopup(View view) {
		try {

			pwindo = new PopupWindow(layout, Popup_Width, Popup_Height, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

			btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
			btnClosePopup.setOnClickListener(cancel_button_click_listener);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private OnClickListener cancel_button_click_listener = new OnClickListener() {
		public void onClick(View v) {
			pwindo.dismiss();
		}
	};

	public void enviarEmail(View view) {
		try {
			editTextNome = (EditText) layout.findViewById(R.id.editTextNome);
			editTextEmail = (EditText) layout.findViewById(R.id.editTextEmail);
			editTextSubject = (EditText) layout
					.findViewById(R.id.editTextAssunto);
			editTextBody = (EditText) layout
					.findViewById(R.id.editTextMensagem);

			String emailBody = "<b>Nome:</b> "
					+ editTextNome.getText().toString()
					+ "<br/><b>E-mail:</b> "
					+ editTextEmail.getText().toString()
					+ "<br/><br/><b>Mensagem:</b> "
					+ editTextBody.getText().toString();

			sendEmail(editTextNome.getText().toString(), this.EMAIL_ADDRESS,
					editTextSubject.getText().toString(), emailBody);

			showMessage("E-mail enviado com sucesso!");
		} catch (Exception e) {
			showMessage("Falha ao enviar o e-mail! Descreição do erro: " + e.getMessage() +" - " + e.getLocalizedMessage() +" - " + e.getCause());
			e.printStackTrace();

		}
	}

}
