package database.dataManager;

import database.dao.DirDao;
import database.dao.FileDao;
import database.dao.UserDao;
import database.dao.UserFileDao;
import database.dataHelper.DBHandler;
import database.dataHelper.tables.DirTable;
import database.dataHelper.tables.FileTable;
import database.dataHelper.tables.UserFileTable;
import database.dataHelper.tables.UserTable;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class DataManagerIMP implements DataManager {
    public static DataManager dataManager;
   private DirDao dirDao;
   private FileDao fileDao;
    private UserDao userDao;
    private UserFileDao userFileDao;
Connection connection=null;

    private DataManagerIMP() {
        DBHandler dbHandler=new DBHandler();
        connection=dbHandler.getDB();
        try {
            connection.setAutoCommit(false);
        connection.createStatement().execute(DirTable.getCreateTableStatement());
            connection.createStatement().execute(UserFileTable.getCreateTableStatement());
            connection.createStatement().execute(UserTable.getCreateTableStatement());
            connection.createStatement().execute(FileTable.getCreateTableStatement());

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initial();
    }

    private void initial(){
        dirDao=new DirDao(connection);
        fileDao=new FileDao(connection);
        userDao=new UserDao(connection);
        userFileDao=new UserFileDao(connection);

    }

    public static DataManager GetDataManger(){
        if (dataManager==null)
            dataManager = new DataManagerIMP();
        return dataManager;
    }

    @Override
    public DirDao getDirDao() {
        return dirDao;
    }

    @Override
    public FileDao getFileDao() {
        return fileDao;
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public UserFileDao getUserFileDao() {
        return userFileDao;
    }
}
