package database.dao;

import appModule.UserFile;
import database.dataHelper.tables.UserFileTable;

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
public class UserFileDao implements DAO<UserFile> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;



    private static String getDeleteQuery(){
        // DELETE FROM table_name WHERE condition;

        return "DELETE FROM "+ UserFileTable.TABLE_NAME+" Where"+UserFileTable.ID_COLUMN+"=";
    }


    private static String getInsertQuery() {
        StringBuilder questionSymbols = new StringBuilder();
        for (int i = 0; i < UserFileTable.getAllColumnsWithoutID().length - 1; i++)
            questionSymbols.append("? ,");
        questionSymbols.append("?");
        return "INSERT INTO " + UserFileTable.TABLE_NAME +
                "(" +
                Arrays.toString(UserFileTable.getAllColumnsWithoutID()).replace("[", "").replace("]", "") +
                ") " +
                "VALUES (" + questionSymbols.toString() + ")";

    }

    public UserFileDao(Connection connection) {

        this.connection=connection;

        try {
            preparedStatement=connection.prepareStatement(getInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long add(UserFile data) {
        try {
            preparedStatement.setLong(1,data.getUserID());
            preparedStatement.setLong(2,data.getFileID());
            preparedStatement.setLong(3,data.getAccessLevel());
            preparedStatement.setString(4,data.getUrl());
            preparedStatement.setLong(5,new Date().getTime());
            int rowAffected=preparedStatement.executeUpdate();
            if(rowAffected==1) {
                resultSet=preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    data.setDbID(resultSet.getInt(1));
                }

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
    public UserFile get(long id) {
        try {

            UserFile user=search(UserFileTable.ID_COLUMN+" = "+id,null).get(0);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(UserFile object) {
        try {
            preparedStatement=connection.prepareStatement(getDeleteQuery()+object.getDbID());
            commit(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getSearchQuery(String selection,String order){
        order=order!=null?" ORDER BY "+order:"";
        String string="SELECT * FROM "+UserFileTable.TABLE_NAME+" WHERE "+selection+order;


        return string;

    }

    @Override
    public List<UserFile> search(String selection, String order) {
        List<UserFile> result = new ArrayList<>();
        ResultSet resultSet=null;
        if (connection==null){
            // TODO: 12/6/18 hmmmm
        }
        else {
            try {

                resultSet=connection.createStatement().executeQuery(getSearchQuery(selection,order));
                while (resultSet.next()){
                    UserFile item =  buildFromResultSet(resultSet);
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

    private static UserFile buildFromResultSet(ResultSet resultSet) {
        UserFile result=null;
        if(resultSet!=null){
            try {
                long dbID=resultSet.getLong(UserFileTable.ID_COLUMN);
                long userID=resultSet.getLong(UserFileTable.USER_ID_COLUMN);
                long fileID=resultSet.getLong(UserFileTable.FILE_ID_COLUMN);
                long accessLevel=resultSet.getLong(UserFileTable.ACCESS_LEVEL_COLUMN);
                String url=resultSet.getString(UserFileTable.URL_COLUMN);
                Date createTime=new Date(resultSet.getLong(UserFileTable.CREATE_TIME));
                result=new UserFile(dbID,userID,fileID,accessLevel,url,createTime);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
