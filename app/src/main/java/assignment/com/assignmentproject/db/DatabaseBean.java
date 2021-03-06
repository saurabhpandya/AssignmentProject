package assignment.com.assignmentproject.db;

public class DatabaseBean {
    private String dataType, constraint, columnName, columnValue;
    public static final String TYPE_INTEGER = "1";
    public static final String TYPE_TEXT = "0";
    public static final String CONSTRAINT_NOT_NULL = "1";
    public static final String CONSTRAINT_PRIMARY_KEY = "0";
    public static final String CONSTRAINT_NONE = "-1";

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
