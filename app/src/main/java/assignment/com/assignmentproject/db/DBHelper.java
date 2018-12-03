package assignment.com.assignmentproject.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import assignment.com.assignmentproject.AssignmentApp;
import assignment.com.assignmentproject.db.tables.MOBILEDATAUSAGE_MASTER_TBL;


public class DBHelper {
    private static final String TAG = "DBHelper";
    private static final String DB_NAME = "MOBILEDATA";
    private static int dbVersion = 1;

    private static DBHelper adapter;

    //    private final Context context;
    private final DatabaseHelper dbHelper;
    private SQLiteDatabase dbObject;

    private DBHelper(final Context ctx) {
        dbHelper = new DatabaseHelper(ctx);
    }

    public static DBHelper getInstance(final Context context) {
        try {
            synchronized (AssignmentApp.getContext()) {
                if (adapter == null) {
                    adapter = new DBHelper(context);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adapter;
    }

    public SQLiteDatabase getDB() {
        return dbObject;
    }

    //---opens the database---
    public DBHelper open() throws SQLException {
        dbObject = dbHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        dbHelper.close();
    }

    public void incrementCount() {
        dbVersion++;
    }

    public boolean isDBOpen() {
        if (null != dbObject) {
            if (dbObject.isOpen()) {
                return true;
            } else
                return false;
        } else {
            return false;
        }

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(final Context context) {
            super(context, DB_NAME, null, dbVersion);
        }

        @Override
        public void onCreate(final SQLiteDatabase dbObj) {
            Log.d(TAG, "Inside onCreate");
            dbObj.execSQL(MOBILEDATAUSAGE_MASTER_TBL.createTable());
        }

        @Override
        public void onUpgrade(final SQLiteDatabase dbObj, final int oldVersion, final int newVersion) {
            dbVersion = newVersion;
            dbObj.execSQL(MOBILEDATAUSAGE_MASTER_TBL.dropTable());
            onCreate(dbObj);
        }
    }
}

