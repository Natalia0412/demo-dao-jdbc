package db;

import java.sql.*;

public class DB {
    private static Connection conn;
    private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String serverip = "localhost";
    private static final String serverport = "1433";
    private static final String dbName = "COURSEJDBC";
    private static final String url = "jdbc:sqlserver://" + serverip + "\\SQLEXPRESS:" + serverport + ";databaseName=" + dbName;
    private static final String dataBaseName = "sa";
    private static final String dataBasePassword = "14n2016";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, dataBaseName, dataBasePassword);
            } catch (SQLException | ClassNotFoundException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                System.out.println("Conectei");
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }


}
