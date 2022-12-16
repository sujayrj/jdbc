package com.jeppu;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
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
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL("jdbc:h2:mem:");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("password");
        return jdbcDataSource;
    }

}
