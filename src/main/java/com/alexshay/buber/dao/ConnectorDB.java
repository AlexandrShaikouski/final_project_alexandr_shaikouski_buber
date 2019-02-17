package com.alexshay.buber.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ConnectorDB {
    private ConnectorDB() {
    }

    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("url")+ "?" +
                "useUnicode=" + resource.getString("useUnicode") + "&" +
                "characterEncoding=" + resource.getString("characterEncoding") + "&" +
                "autoReconnect=" + resource.getString("autoReconnect") + "&" +
                "useSSL=" + resource.getString("useSSL") ;
        String user = resource.getString("user");
        String pass = resource.getString("password");
        return DriverManager.getConnection(url, user, pass);
    }
}
