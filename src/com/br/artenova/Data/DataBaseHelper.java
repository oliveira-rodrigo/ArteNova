package com.br.artenova.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	public DataBaseHelper(Context context) {
		// Databse: todos_db, Version: 1
		super(context, "Banco", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		executeSQLScript(database, "create.sql", null);
	}
	
	public void onCreate(SQLiteDatabase database, Context context) {
		executeSQLScript(database, "create.sql", context);
	}

	private void executeSQLScript(SQLiteDatabase database, String dbname, Context context) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		AssetManager assetManager = context.getAssets();
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
	}
	// TODO Auto-generated method stub

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
