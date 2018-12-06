package database.dao;

import appModule.User;
import database.dataHelper.tables.UserTable;

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
public class UserDao implements DAO<User> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;



    private static String getDeleteQuery(){
       // DELETE FROM table_name WHERE condition;

        return "DELETE FROM "+UserTable.TABLE_NAME+" Where"+UserTable.ID_COLUMN+"=";
    }


    private static String getInsertQuery() {
        StringBuilder questionSymbols = new StringBuilder();
        for (int i = 0; i < UserTable.getAllColumnsWithoutID().length - 1; i++)
            questionSymbols.append("? ,");
        questionSymbols.append("?");
        return "INSERT INTO " + UserTable.TABLE_NAME +
                "(" +
                Arrays.toString(UserTable.getAllColumnsWithoutID()).replace("[", "").replace("]", "") +
                ") " +
                "VALUES (" + questionSymbols.toString() + ")";

    }

    public UserDao(Connection connection) {

        this.connection=connection;

        try {
            preparedStatement=connection.prepareStatement(getInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long add(User data) {
        try {
            preparedStatement.setString(1,data.getUserName());
            preparedStatement.setString(2,data.getUserPassWord());
            preparedStatement.setString(3,data.getToken());
            preparedStatement.setLong(4,new Date().getTime());
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
    public User get(long id) {
        try {

        User user=search(UserTable.ID_COLUMN+" = "+id,null).get(0);
        return user;
    }catch (Exception e){
        return null;
    }
    }

    @Override
    public void delete(User object) {
        try {
            preparedStatement=connection.prepareStatement(getDeleteQuery()+object.getDbID());
            commit(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int update(User object) {


        String getUpdateQuery="UPDATE "+UserTable.TABLE_NAME+
                " SET "+UserTable.LAST_ACCESS_COLUMN+"="
                +object.getLastAccessTime()+" WHERE "+
                UserTable.ID_COLUMN+"="
                +object.getDbID();
        try {
            preparedStatement=connection.prepareStatement(getUpdateQuery);
            commit(preparedStatement.executeUpdate());
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


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


    private static String getSearchQuery(String selection,String order){
        order=order!=null?" ORDER BY "+order:"";
        String string="SELECT * FROM "+UserTable.TABLE_NAME+" WHERE "+selection+order;


        return string;

    }
    @Override
    public List<User> search(String selection, String order) {
        List<User> result = new ArrayList<>();
        ResultSet resultSet=null;
        if (connection==null){
            // TODO: 12/6/18 hmmmm
        }
        else {
            try {

                resultSet=connection.createStatement().executeQuery(getSearchQuery(selection,order));
                while (resultSet.next()){
                    User item =  buildFromResultSet(resultSet);
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





    private static User buildFromResultSet(ResultSet resultSet) {
    User result=null;
    if(resultSet!=null){
        try {
            long dbID=resultSet.getLong(UserTable.ID_COLUMN);
            String userName=resultSet.getString(UserTable.USER_NAME_COLUMN);
            String userPassword=resultSet.getString(UserTable.USER_PASSWORD_COLUMN);
            String token=resultSet.getString(UserTable.USER_TOKEN_COLUMN);
            Date lastAccess=new Date(resultSet.getLong(UserTable.LAST_ACCESS_COLUMN));
            Date createTime=new Date(resultSet.getLong(UserTable.CREATE_TIME));
            result=new User(dbID,userName,userPassword,token,lastAccess,createTime);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
        return result;
    }

}
