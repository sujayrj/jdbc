package com.jeppu.queries;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//Class which demonstrates how to query a table
public class SelectQueryApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;")) {
                final ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    LocalDate registrationDate = resultSet.getObject("registration_date", LocalDate.class);

                    System.out.println("Found User : " + id + " | " + firstName + " | " + lastName + " | " + registrationDate);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:/tmp/banking");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
