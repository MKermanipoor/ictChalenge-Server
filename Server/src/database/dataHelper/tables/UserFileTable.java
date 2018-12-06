package database.dataHelper.tables;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class UserFileTable implements BaseColumns {
    public final static String TABLE_NAME="user_file";
    public final static String  USER_ID_COLUMN="user_id";
    public final static String  FILE_ID_COLUMN="file_id";
    public final static String  ACCESS_LEVEL_COLUMN="access_level";
    public final static String  URL_COLUMN="url";


    public static String getCreateTableStatement(){

        return       "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                USER_ID_COLUMN +" INTEGER,"+
                FILE_ID_COLUMN+ " INTEGER,"+
                ACCESS_LEVEL_COLUMN+" INTEGER,"+
                URL_COLUMN+" TEXT,"+
                CREATE_TIME +" INTEGER"+ ")";



    }
    public static String[] getAllColumns(){
        return new String[]{
                ID_COLUMN,
                USER_ID_COLUMN,
                FILE_ID_COLUMN,
                ACCESS_LEVEL_COLUMN,
                URL_COLUMN,
                CREATE_TIME

        };


    }
    public static String[] getAllColumnsWithoutID(){
        return new String[]{
                USER_ID_COLUMN,
                FILE_ID_COLUMN,
                ACCESS_LEVEL_COLUMN,
                URL_COLUMN,
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
