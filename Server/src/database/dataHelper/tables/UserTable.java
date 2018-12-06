package database.dataHelper.tables;

/**
 * Created by saeidbahmani on 12/5/18.
 */
public class UserTable implements BaseColumns {
    public final static String TABLE_NAME="user";
    public final static String  USER_NAME_COLUMN="user_name";
    public final static String  LAST_ACCESS_COLUMN="last_access_user";
    public final static String  USER_PASSWORD_COLUMN="user_password";
    public final static String  USER_TOKEN_COLUMN="token_user";


    public static String getCreateTableStatement(){

        return       "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+
                ID_COLUMN +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                USER_NAME_COLUMN +" TEXT UNIQUE,"+
                USER_PASSWORD_COLUMN+ " TEXT,"+
                USER_TOKEN_COLUMN+" TEXT UNIQUE,"+
                LAST_ACCESS_COLUMN+" INTEGER,"+
                CREATE_TIME +" INTEGER"+ ")";



    }
    public static String[] getAllColumns(){
        return new String[]{
                ID_COLUMN,
                USER_NAME_COLUMN,
                USER_PASSWORD_COLUMN,
                USER_TOKEN_COLUMN,
                LAST_ACCESS_COLUMN,
                CREATE_TIME

        };


    }
    public static String[] getAllColumnsWithoutID(){
        return new String[]{
                USER_NAME_COLUMN,
                USER_PASSWORD_COLUMN,
                USER_TOKEN_COLUMN,
                LAST_ACCESS_COLUMN,
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
