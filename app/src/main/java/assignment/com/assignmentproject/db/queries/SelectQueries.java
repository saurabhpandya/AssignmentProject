package assignment.com.assignmentproject.db.queries;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import assignment.com.assignmentproject.db.DBHelper;
import assignment.com.assignmentproject.db.tables.MOBILEDATAUSAGE_MASTER_TBL;
import assignment.com.assignmentproject.model.RecordsModel;
import io.reactivex.Observable;

public class SelectQueries {
    private Context context;
    private DBHelper manager;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public SelectQueries(Context context) {
        this.context = context;
        getDBInstance();
    }

    private void getDBInstance() {
        manager = DBHelper.getInstance(context);
    }

    public synchronized Observable<ArrayList<RecordsModel>> getMobileDataRecordsFromDb() {
        readWriteLock.readLock().lock();
        if (!manager.isDBOpen())
            manager.open();
        Cursor cursor = manager.getDB().query(MOBILEDATAUSAGE_MASTER_TBL.TABLE_NAME,
                null, null, null, null, null,
                MOBILEDATAUSAGE_MASTER_TBL.ID + " asc");

        int count = cursor.getCount();
        ArrayList<RecordsModel> retVal = new ArrayList<>();
        if (count > 0) {
            cursor.moveToFirst();
            RecordsModel data;
            for (int i = 0; i < count; i++) {
                data = new RecordsModel();
                data.setId(cursor.getInt(0));
                data.setQuarter(cursor.getString(1));
                data.setVolumeOfMobileData(cursor.getString(2));
                retVal.add(data);
                cursor.moveToNext();
            }
        }
        cursor.close();
        readWriteLock.readLock().unlock();
        return Observable.just(retVal);
    }
}
