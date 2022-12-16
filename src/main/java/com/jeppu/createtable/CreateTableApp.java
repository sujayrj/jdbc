package com.jeppu.createtable;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//Java Class which creates user table in H2
//create a file - schema.sql(any name) and place in main/resources so it is available in classpath
public class CreateTableApp {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("conn.isValid(1) = " + conn.isValid(1));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:/tmp/banking;INIT=RUNSCRIPT FROM 'classpath:schema.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
