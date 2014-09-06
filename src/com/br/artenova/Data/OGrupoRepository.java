package com.br.artenova.Data;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.br.artenova.Models.OGrupo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OGrupoRepository {

	private String TABLE_NAME = "Grupos";
	private String COLUMN_ID = "ID";
	private String COLUMN_DESCRICAO = "DESCRICAO";
	private String COLUMN_IMAGEM_1 = "IMAGEM_1";
	private String COLUMN_IMAGEM_2 = "IMAGEM_2";
	private String COLUMN_IMAGEM_3 = "IMAGEM_3";

	private JSONObject json = null;
	private SQLiteDatabase db = null;

	public OGrupoRepository(SQLiteDatabaseHelper dbHelper) {
		this.db = dbHelper.getWritableDatabase();		
	}

	protected void saveOrUpdate(JSONObject jsonData, List<String> imagens) {
		try {
			this.json = jsonData;
			
			String id = this.json.getString("ID");
			String descricao = this.json.getString("Descricao");
			String img_1 = this.json.getString("Imagem_1");
			String img_2 = this.json.getString("Imagem_2");
			String img_3 = this.json.getString("Imagem_3");

			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_ID, id);
			contentValues.put(COLUMN_DESCRICAO, descricao);
			contentValues.put(COLUMN_IMAGEM_1, img_1);
			contentValues.put(COLUMN_IMAGEM_2, img_2);
			contentValues.put(COLUMN_IMAGEM_3, img_3);
			
			imagens.add(img_1);
			imagens.add(img_2);
			imagens.add(img_3);

			OGrupo ogrupo = selectByID(id);

			if (ogrupo == null)
				this.save(contentValues);
			else
				this.update(contentValues, id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void save(ContentValues contentValues) {
		try {
			this.db.insert(TABLE_NAME, null, contentValues);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void update(ContentValues contentValues, String ID) {
		try {
			this.db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
					new String[] { ID });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected OGrupo selectByID(String id) {
		OGrupo ogrupo = null;
		try {
			Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
					COLUMN_DESCRICAO, COLUMN_IMAGEM_1, COLUMN_IMAGEM_2,
					COLUMN_IMAGEM_3 }, COLUMN_ID + "=?", new String[] { id },
					null, null, null, null);

			if (cursor != null) {
				cursor.moveToFirst();

				ogrupo = new OGrupo(Integer.valueOf(cursor.getString(0)), // ID
						cursor.getString(1), // Descricao
						cursor.getString(2), // Imagem_1
						cursor.getString(3), // Imagem_2
						cursor.getString(4)); // Imagem_3
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ogrupo;
	}

	public OGrupo selectFirst() {
		OGrupo ogrupo = null;
		try {
			Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
					COLUMN_DESCRICAO, COLUMN_IMAGEM_1, COLUMN_IMAGEM_2,
					COLUMN_IMAGEM_3 }, null, null, null, null, null);

			if (cursor != null) {
				cursor.moveToFirst();

				ogrupo = new OGrupo(Integer.valueOf(cursor.getString(0)), // ID
						cursor.getString(1), // Descricao
						cursor.getString(2), // Imagem_1
						cursor.getString(3), // Imagem_2
						cursor.getString(4)); // Imagem_3
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ogrupo;
	}

}
