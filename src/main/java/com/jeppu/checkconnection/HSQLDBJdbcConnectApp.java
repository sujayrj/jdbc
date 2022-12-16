package com.jeppu.checkconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//[TODO]
public class HSQLDBJdbcConnectApp {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
            System.out.println("connection.isValid(1) = " + connection.isValid(1));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static DataSource createDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig());
        //hikariDataSource.setJdbcUrl("jdbc:hsqldb:file:/tmp/banking");
        //hikariDataSource.setUsername("sa");
        //hikariDataSource.setPassword("");
        return hikariDataSource;
    }

    private static HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:hsqldb:mem:");
        config.setUsername("sa");
        config.setPassword("");
        return config;
    }


}
