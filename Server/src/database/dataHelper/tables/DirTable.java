package database.dataHelper.tables;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class DirTable implements BaseColumns {
    public final static String TABLE_NAME="dir";
    public final static String  DIR_LOCATION_COLUMN="dir_location";
    public final static String  PARENT_ID_COLUMN="parent_id";



    public static String getCreateTableStatement(){

        return       "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                DIR_LOCATION_COLUMN +" TEXT UNIQUE,"+
                PARENT_ID_COLUMN+ " INTEGER,"+
                CREATE_TIME +" INTEGER"+ ")";



    }
    public static String[] getAllColumns(){
        return new String[]{
                ID_COLUMN,
                DIR_LOCATION_COLUMN,
                PARENT_ID_COLUMN,
                CREATE_TIME

        };


    }
    public static String[] getAllColumnsWithoutID(){
        return new String[]{
                DIR_LOCATION_COLUMN,
                PARENT_ID_COLUMN,
                CREATE_TIME

        };


    }

    public static boolean isValidColumnName(String columnName){
        columnName = (columnName != null) ? columnName.trim() : null;
        for (String column : getAllColumns())
            if (column.equalsIgnoreCase(columnName))
                return true ;
        return false ;
    }
}
