package com.br.artenova.Data;

import java.io.IOException;
import java.util.logging.Logger;

import com.br.artenova.Helpers.AssetUtils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    //private static final Logger log = LoggerFactory.getLogger(SQLiteDatabaseHelper.class);
    
    private static final String SQL_DIR = "sql" ;
    
    private static final String CREATEFILE = "SQL_Database.sql";
    
    private static final String UPGRADEFILE_PREFIX = "upgrade-";
    
    private static final String UPGRADEFILE_SUFFIX = ".sql";
    
    private Context context ;
    
	public SQLiteDatabaseHelper(Context context, String name,
            CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //log.info("create database");
            execSqlFile( CREATEFILE, db );
        } catch( IOException exception ) {
            throw new RuntimeException("Database creation failed", exception );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //log.info("upgrade database from {} to {}", oldVersion, newVersion );
            for( String sqlFile : AssetUtils.list(SQL_DIR, this.context.getAssets())) {
                if ( sqlFile.startsWith(UPGRADEFILE_PREFIX)) {
                    int fileVersion = Integer.parseInt(sqlFile.substring( UPGRADEFILE_PREFIX.length(),  sqlFile.length() - UPGRADEFILE_SUFFIX.length())); 
                    if ( fileVersion > oldVersion && fileVersion <= newVersion ) {
                        execSqlFile( sqlFile, db );
                    }
                }
            }
        } catch( IOException exception ) {
            throw new RuntimeException("Database upgrade failed", exception );
        }
    }
    
    protected void execSqlFile(String sqlFile, SQLiteDatabase db ) throws SQLException, IOException {
        //log.info("  exec sql file: {}", sqlFile );
        for( String sqlInstruction : SqlParser.parseSqlFile( SQL_DIR + "/" + sqlFile, this.context.getAssets())) {
            //log.trace("    sql: {}", sqlInstruction );
            db.execSQL(sqlInstruction);
        }
    }
}
