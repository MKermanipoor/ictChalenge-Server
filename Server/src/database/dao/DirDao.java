package database.dao;

import appModule.Dir;
import database.dataHelper.tables.DirTable;
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
public class DirDao implements DAO<Dir> {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;



    private static String getDeleteQuery(){
        // DELETE FROM table_name WHERE condition;

        return "DELETE FROM "+ DirTable.TABLE_NAME+" Where"+DirTable.ID_COLUMN+"=";
    }


    private static String getInsertQuery() {
        StringBuilder questionSymbols = new StringBuilder();
        for (int i = 0; i < DirTable.getAllColumnsWithoutID().length - 1; i++)
            questionSymbols.append("? ,");
        questionSymbols.append("?");
        return "INSERT INTO " + DirTable.TABLE_NAME +
                "(" +
                Arrays.toString(DirTable.getAllColumnsWithoutID()).replace("[", "").replace("]", "") +
                ") " +
                "VALUES (" + questionSymbols.toString() + ")";

    }

    public DirDao(Connection connection) {
        this.connection=connection;

        try {
            preparedStatement=connection.prepareStatement(getInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long add(Dir data) {
        try {
            preparedStatement.setString(1,data.getDirLocation());
            preparedStatement.setLong(2,data.getParentId());
            preparedStatement.setLong(3,new Date().getTime());

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
    public Dir get(long id) {
        try {

            Dir user=search(DirTable.ID_COLUMN+" = "+id,null).get(0);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(Dir object) {
        try {
            preparedStatement=connection.prepareStatement(getDeleteQuery()+object.getDbID());
            commit(preparedStatement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static String getSearchQuery(String selection,String order){
        order=order!=null?" ORDER BY "+order:"";
        String string="SELECT * FROM "+DirTable.TABLE_NAME+" WHERE "+selection+order;


        return string;

    }


    @Override
    public List<Dir> search(String selection, String order) {
        List<Dir> result = new ArrayList<>();
        ResultSet resultSet=null;
        if (connection==null){
            // TODO: 12/6/18 hmmmm
        }
        else {
            try {

                resultSet=connection.createStatement().executeQuery(getSearchQuery(selection,order));
                while (resultSet.next()){
                    Dir item =  buildFromResultSet(resultSet);
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

    private static Dir buildFromResultSet(ResultSet resultSet) {
        Dir result=null;
        if(resultSet!=null){
            try {
                long dbID=resultSet.getLong(DirTable.ID_COLUMN);
                String dirLocation=resultSet.getString(DirTable.DIR_LOCATION_COLUMN);
               long parentID=resultSet.getLong(DirTable.PARENT_ID_COLUMN);
                Date createTime=new Date(resultSet.getLong(DirTable.CREATE_TIME));
                result=new Dir(dbID,dirLocation,parentID,createTime);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
    public long insertOrUpdate(Dir dir){
        Dir work=getByDirLocation(dir.getDirLocation());
        if(work==null) {
            add(dir);
            work=dir;
        }
        return work.getDbID();

    }

    private Dir getByDirLocation(String dirLocation) {
        try {

            Dir user=search(DirTable.DIR_LOCATION_COLUMN+" = "+dirLocation,null).get(0);
            return user;
        }catch (Exception e){
            return null;
        }
    }


}
