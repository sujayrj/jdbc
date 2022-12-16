package com.jeppu;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class JdbcConnectApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {
            System.out.println("connection.isValid(1) = " + connection.isValid(1));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static DataSource createDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("password");
        return hikariDataSource;
    }

}
