package database.dataHelper;

import database.dataHelper.tables.UserTable;

import java.sql.*;


/**
 * Created by saeidbahmani on 12/5/18.
 */
public class DBHandler {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_PATH="jdbc:mysql://31.184.135.160:3306/databas";
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement=null;
    public DBHandler(){
        createNewDatabase();
    //initial();
        try {
            connection = DriverManager.getConnection(DATABASE_PATH,"root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDB(){

        return connection;
    }



//    private void initial(){
//
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//         //   connection = DriverManager.getConnection(DATABASE_PATH);
////        } catch (SQLException e) {
////            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        createNewDatabase();
//
//
//
//
//    }

//    public static void createTable(){
//        try {
//            connection=DriverManager.getConnection(DATABASE_PATH);
//
//            connection.setAutoCommit(false);
//
//            preparedStatement=connection.prepareStatement(UserTable.getCreateTableStatement());
//
//
//
//
//            preparedStatement.executeUpdate();
//            connection.commit();
//
//          //  statement=connection.createStatement();
//            //statement.execute(UserTable.getCreateTableStatement());
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
    public static void createNewDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // url = "jdbc:sqlite:/Users/saeidbahmani/Desktop/nazarie/dbtest/" + fileName;

        try (Connection conn = DriverManager.getConnection(DATABASE_PATH,"root","")) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");



            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    }

