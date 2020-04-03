package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AbstractDAO {

    private Properties properties;
    public void setDbConnection(Connection dbConnection) {
        dbConnection = dbConnection;
    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    protected Connection dbConnection = null;

    public AbstractDAO() {
        properties = new Properties();
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbConnection = getDBConnection();
    }

    private Connection getDBConnection() {
        if (dbConnection != null) return dbConnection;
        String driver = properties.getProperty("db.driver");
        String dbuser = properties.getProperty("db.login");
        String dbpassword = properties.getProperty("db.password");
        String dburl = properties.getProperty("db.url");



        try {

            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(dburl, dbuser,
                    dbpassword);
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}
