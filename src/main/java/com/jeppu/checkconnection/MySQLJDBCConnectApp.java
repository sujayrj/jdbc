package com.jeppu.checkconnection;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLJDBCConnectApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {
            System.out.println("connection.isValid(1) = " + connection.isValid(1));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static DataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/helloworld");
        dataSource.setUsername("sujay");
        dataSource.setPassword("password");
        return dataSource;
    }
}
