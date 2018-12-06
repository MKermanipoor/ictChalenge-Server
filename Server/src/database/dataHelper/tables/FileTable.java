package database.dataHelper.tables;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class FileTable implements BaseColumns {
    public final static String TABLE_NAME="FILE";
    public final static String REAL_LOCATION_COLUMN="real_location";
    public final static String  USER_THING_LOCATION_COLUMN="user_thing_location";
    public final static String  USER_ID_CREATE_COLUMN="user_id_create";
    public final static String  DOWNLOAD_COUNT_COLUMN="download_cunt";
    public final static String  IS_DELETED_COLUMN="is_deleted";
    public final static String  DIR_ID_COLUMN="dir_id";
    public final static String  HASH_COLUMN="hash";
    public final static String  SIZE_COLUMN="size";
    public final static String  MINE_TYPE_COLUMN="mine_type";




    public static String getCreateTableStatement(){

        return       "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                REAL_LOCATION_COLUMN +" TEXT UNIQUE,"+
                USER_THING_LOCATION_COLUMN+ " TEXT,"+
                USER_ID_CREATE_COLUMN+" TEXT,"+
                DOWNLOAD_COUNT_COLUMN+" INTEGER,"+
                IS_DELETED_COLUMN+" Byte,"+
                DIR_ID_COLUMN+" TEXT,"+
                HASH_COLUMN+" TEXT,"+
                SIZE_COLUMN+" INTEGER,"+
                MINE_TYPE_COLUMN+" TEXT,"+

                CREATE_TIME +" INTEGER"+ ")";



    }
    public static String[] getAllColumns(){
        return new String[]{
                ID_COLUMN,
                REAL_LOCATION_COLUMN,
                USER_THING_LOCATION_COLUMN,
                USER_ID_CREATE_COLUMN,
                DOWNLOAD_COUNT_COLUMN,
                IS_DELETED_COLUMN,
                DIR_ID_COLUMN,
                HASH_COLUMN,
                SIZE_COLUMN,
                MINE_TYPE_COLUMN,

                CREATE_TIME

        };


    }
    public static String[] getAllColumnsWithoutID(){
        return new String[]{
                REAL_LOCATION_COLUMN,
                USER_THING_LOCATION_COLUMN,
                USER_ID_CREATE_COLUMN,
                DOWNLOAD_COUNT_COLUMN,
                IS_DELETED_COLUMN,
                DIR_ID_COLUMN,
                HASH_COLUMN,
                SIZE_COLUMN,
                MINE_TYPE_COLUMN,

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
