package assignment.com.assignmentproject.db;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import assignment.com.assignmentproject.Utils.ArrayUtil;
import assignment.com.assignmentproject.db.queries.DBQueries;
import assignment.com.assignmentproject.db.queries.SelectQueries;
import assignment.com.assignmentproject.db.tables.MOBILEDATAUSAGE_MASTER_TBL;
import assignment.com.assignmentproject.model.RecordsModel;

public class DbManager {

    DBQueries dbQueries;
    SelectQueries sQueries;

    public DbManager(Context context) {
        dbQueries = new DBQueries(context);
        sQueries = new SelectQueries(context);
    }

    public void insertMobileDataUsageData(final ArrayList<RecordsModel> mobileDataUsageModel) {
        if (ArrayUtil.isEmpty(mobileDataUsageModel)) return;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ArrayList<Map<String, Object>> columnDetails = new ArrayList<>();
                for (RecordsModel bean : mobileDataUsageModel) {
                    Map<String, Object> valuesMap = new HashMap<>();
                    valuesMap.put(MOBILEDATAUSAGE_MASTER_TBL.ID, bean.getId());
                    valuesMap.put(MOBILEDATAUSAGE_MASTER_TBL.QUARTER, bean.getQuarter());
                    valuesMap.put(MOBILEDATAUSAGE_MASTER_TBL.MOBILEDATA_VOLUME, bean.getVolumeOfMobileData());
                    columnDetails.add(valuesMap);
                }
                dbQueries.insertValues(MOBILEDATAUSAGE_MASTER_TBL.TABLE_NAME, columnDetails);
            }
        };
        new Thread(runnable).start();
    }
}
