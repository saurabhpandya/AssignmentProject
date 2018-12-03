package assignment.com.assignmentproject.db.queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import assignment.com.assignmentproject.db.DBHelper;
import assignment.com.assignmentproject.db.DatabaseBean;
import io.reactivex.Observable;

import static assignment.com.assignmentproject.db.DatabaseBean.CONSTRAINT_NONE;


public class DBQueries {
    static final String TAG = "DBQueries";
    private Context context;
    private DBHelper manager;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public DBQueries(Context context) {
        this.context = context;
        getDBInstance();
    }

    private void getDBInstance() {
        manager = DBHelper.getInstance(context);
    }

    public synchronized void createTable(String tableName, ArrayList<DatabaseBean> columnDetails) {
        StringBuffer createQuery = new StringBuffer();
        createQuery.append("create table ").append(tableName).append(" ( ");
        if (0 < columnDetails.size()) {
            for (int i = 0; i < columnDetails.size(); i++) {
                createQuery.append(columnDetails.get(i).getColumnName());
                if ("".equals(columnDetails.get(i).getDataType()) || null == columnDetails.get(i).getDataType())
                    columnDetails.get(i).setDataType(DatabaseBean.TYPE_TEXT);

                if ("".equals(columnDetails.get(i).getConstraint()) || null == columnDetails.get(i).getConstraint())
                    columnDetails.get(i).setConstraint(CONSTRAINT_NONE);

                switch (columnDetails.get(i).getDataType()) {
                    case DatabaseBean.TYPE_INTEGER:
                        createQuery.append(" integer ");
                        break;
                    case DatabaseBean.TYPE_TEXT:
                        createQuery.append(" text ");
                        break;
                    default:
                        createQuery.append(" text ");
                        break;
                }

                switch (columnDetails.get(i).getConstraint()) {
                    case DatabaseBean.CONSTRAINT_NOT_NULL:
                        createQuery.append(" not null");
                        break;
                    case DatabaseBean.CONSTRAINT_PRIMARY_KEY:
                        createQuery.append(" primary key");
                        break;
                    default:
                        createQuery.append("");
                        break;
                }
                if (i == columnDetails.size() - 1) {
                    createQuery.append(");");
                } else {
                    createQuery.append(", ");
                }
               /* Log.d(TAG, "columnDetails: "+columnDetails.get(i).getColumnName()
                        +" "+columnDetails.get(i).getConstraint()
                        +" "+columnDetails.get(i).getDataType());*/
            }
            if (!isTableExists(tableName)) {
                manager.incrementCount();
                manager.getDB().execSQL(createQuery.toString());
                Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(context, "Table already exists", Toast.LENGTH_SHORT).show();
        }
    }

    public synchronized boolean isTableExists(String tableName) {
        if (tableName == null || manager == null || !manager.getDB().isOpen()) {
            return false;
        }
        if (!manager.isDBOpen())
            manager.open();
        Cursor cursor = manager.getDB().rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?",
                new String[]{"table", tableName});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();

        return count > 0;
    }

    public synchronized long insertValues(String tableName, ArrayList<Map<String, Object>> columnDetails) {
        long retVal = 0;
        ContentValues initialValues;
        if (!manager.isDBOpen())
            manager.open();
        readWriteLock.writeLock().lock();
        for (int i = 0; i < columnDetails.size(); i++) {
            initialValues = new ContentValues();
            for (Map.Entry<String, Object> entry : columnDetails.get(i).entrySet()) {
                if (entry.getValue() instanceof String)
                    initialValues.put(entry.getKey(), (String) entry.getValue());
                else if (entry.getValue() instanceof Integer)
                    initialValues.put(entry.getKey(), (int) entry.getValue());
                else if (entry.getValue() instanceof Long)
                    initialValues.put(entry.getKey(), (long) entry.getValue());
                else if (entry.getValue() instanceof Byte)
                    initialValues.put(entry.getKey(), (byte) entry.getValue());
                else if (entry.getValue() instanceof Short)
                    initialValues.put(entry.getKey(), (short) entry.getValue());
                else if (entry.getValue() instanceof Float)
                    initialValues.put(entry.getKey(), (float) entry.getValue());
                else if (entry.getValue() instanceof Double)
                    initialValues.put(entry.getKey(), (double) entry.getValue());
                else if (entry.getValue() instanceof Boolean)
                    initialValues.put(entry.getKey(), (boolean) entry.getValue());
            }
            retVal = manager.getDB().insert(tableName, null, initialValues);
            if (retVal > 0)
                Log.d(TAG, "insertedValue: " + columnDetails.get(i));
            //Toast.makeText(context, "retVal: "+retVal, Toast.LENGTH_SHORT).show();
        }

        readWriteLock.writeLock().unlock();
        return retVal;
    }

    public synchronized long insertSingleValue(String tableName, ContentValues values) {
        Log.d(TAG, "insertSingleValue: ");
        long retVal = 0;
        if (!manager.isDBOpen())
            manager.open();
        readWriteLock.writeLock().lock();
        retVal = manager.getDB().insert(tableName, null, values);

        readWriteLock.writeLock().unlock();
        return retVal;
    }

    public synchronized long getRowCount(String tableName, String columnName) {
        long count = 0;
        if (!manager.isDBOpen())
            manager.open();
        Cursor cursor = manager.getDB().query(tableName,
                new String[]{"count(" + columnName + ")"},
                null, null, null, null, null);
        cursor.moveToFirst();
        count = cursor.getLong(0);
        cursor.close();

        Log.d(TAG, "rowCount: " + count);
        return count;
    }

    public synchronized Observable<Long> getRowCount1(String tableName, String columnName) {
        long count = 0;
        if (!manager.isDBOpen())
            manager.open();
        Cursor cursor = manager.getDB().query(tableName,
                new String[]{"count(" + columnName + ")"},
                null, null, null, null, null);
        cursor.moveToFirst();
        count = cursor.getLong(0);
        cursor.close();

        Log.d(TAG, "rowCount: " + count);
        return Observable.just(count);
    }
}
