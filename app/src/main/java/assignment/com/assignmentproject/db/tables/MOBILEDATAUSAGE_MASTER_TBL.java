package assignment.com.assignmentproject.db.tables;

import android.util.Log;

public class MOBILEDATAUSAGE_MASTER_TBL {

    public static String TABLE_NAME = "MOBILEDATAUSAGE_MASTER_TBL";
    private static final String CATEGORY_TBL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static String MOBILEDATA_VOLUME = "MOBILEDATA_VOLUME";
    public static String QUARTER = "QUARTER";
    public static String ID = "ID";
    private static final String MOBILEDATAUSAGE_TBL_CREATE = (new StringBuffer())
            .append("create table ").append(TABLE_NAME).append(" ( ")
            .append(ID).append(" integer, ")
            .append(QUARTER).append(" text), ")
            .append(MOBILEDATA_VOLUME).append(" text) ")
            .append(";").toString();

    public MOBILEDATAUSAGE_MASTER_TBL() {
    }

    public static String createTable() {
        Log.d("", TABLE_NAME + " TABLE CREATED");
        return MOBILEDATAUSAGE_TBL_CREATE;
    }

    public static String dropTable() {
        return CATEGORY_TBL_DROP;
    }
}