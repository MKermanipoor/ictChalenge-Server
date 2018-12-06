package database.dao;

import appModule.Dir;
import appModule.File;
import appModule.UserFile;
import database.dataHelper.tables.FileTable;
import database.dataManager.DataManager;
import database.dataManager.DataManagerIMP;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class FileDao implements DAO<File> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private static String getInsertQuery() {
        StringBuilder questionSymbols = new StringBuilder();
        for (int i = 0; i < FileTable.getAllColumnsWithoutID().length - 1; i++)
            questionSymbols.append("? ,");
        questionSymbols.append("?");
        return "INSERT INTO " + FileTable.TABLE_NAME +
                "(" +
                Arrays.toString(FileTable.getAllColumnsWithoutID()).replace("[", "").replace("]", "") +
                ") " +
                "VALUES (" + questionSymbols.toString() + ")";

    }
    private static String getDeleteQuery(){
        // DELETE FROM table_name WHERE condition;

        return "DELETE FROM "+ FileTable.TABLE_NAME+" Where"+FileTable.ID_COLUMN+"=";
    }


    public FileDao(Connection connection) {

        this.connection=connection;

        try {
            preparedStatement=connection.prepareStatement(getInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long add(File data,long UserID) {
        try {
            preparedStatement.setString(1,data.getRealLocation());
            preparedStatement.setString(2,data.getUserthingLocation());
            preparedStatement.setLong(3,data.getUserIdCreate());
            preparedStatement.setLong(4,data.getDownloadCount());
            preparedStatement.setByte(5,data.isDeleted());
            preparedStatement.setLong(6,data.getDirId());
            preparedStatement.setString(7,data.getHash());
            preparedStatement.setLong(8,data.getSize());
            preparedStatement.setString(9,data.getMineType());
            preparedStatement.setLong(10,new Date().getTime());
            int rowAffected=preparedStatement.executeUpdate();
            if(rowAffected==1) {
                resultSet=preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    data.setDbID(resultSet.getInt(1));
                }
               DataManager dataManager= DataManagerIMP.GetDataManger();
                String[] dirs=data.getUserthingLocation().split("/");
                long parent=-1;
                for (int i = 0; i <dirs.length-1 ; i++) {
                    String dirLocation=dirs[0];
                    for (int j = 0; j < i; j++) {
                        dirLocation+=("/"+dirs[j+1]);
                    }
                    //add in dir db
                    parent=dataManager.getDirDao().insertOrUpdate(new Dir(-1,dirLocation,parent,new Date()));
                }
                //add in user file
                dataManager.getUserFileDao().add(new UserFile(-1,UserID,data.getDbID(),1,data.getRealLocation(),new Date()));
                connection.commit();
            }
            else
                connection.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public long add(File data) {
        return 0;
    }

    @Override
    public File get(long id) {
        try {

            File file=search(FileTable.ID_COLUMN+" = "+id,null).get(0);
            return file;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(File object) {
        try {
            preparedStatement=connection.prepareStatement(getDeleteQuery()+object.getDbID());
            commit(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static String getSearchQuery(String selection,String order){
        order=order!=null?" ORDER BY "+order:"";
        String string="SELECT * FROM "+FileTable.TABLE_NAME+" WHERE "+selection+order;


        return string;

    }

    @Override
    public List<File> search(String selection, String order) {
        List<File> result = new ArrayList<>();
        ResultSet resultSet=null;
        if (connection==null){
            // TODO: 12/6/18 hmmmm
        }
        else {
            try {

                resultSet=connection.createStatement().executeQuery(getSearchQuery(selection,order));
                while (resultSet.next()){
                    File item =  buildFromResultSet(resultSet);
                    if(item!=null){
                        result.add(item);
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return result;
    }


    private void commit(int rowAffected){
        try {
            if(rowAffected==1) {
                connection.commit();
            }
            else
                connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static File buildFromResultSet(ResultSet resultSet) {
        File result=null;
        if(resultSet!=null){
            try {
                long dbID=resultSet.getLong(FileTable.ID_COLUMN);
                String reallocation=resultSet.getString(FileTable.REAL_LOCATION_COLUMN);
                String userThingLocation=resultSet.getString(FileTable.USER_THING_LOCATION_COLUMN);
                long userIdCreate=resultSet.getLong(FileTable.USER_ID_CREATE_COLUMN);
                long downloadCount=resultSet.getLong(FileTable.DOWNLOAD_COUNT_COLUMN);
                Byte isDeleted=resultSet.getByte(FileTable.IS_DELETED_COLUMN);
                Date createTime=new Date(resultSet.getLong(FileTable.CREATE_TIME));
                long dirID=resultSet.getLong(FileTable.DIR_ID_COLUMN);
                String hash=resultSet.getString(FileTable.HASH_COLUMN);
                long size=resultSet.getLong(FileTable.SIZE_COLUMN);
                String mineType=resultSet.getString(FileTable.MINE_TYPE_COLUMN);

                result=new File(dbID,reallocation,userThingLocation,userIdCreate,downloadCount,isDeleted,dirID,hash,size,mineType,createTime);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }


}
