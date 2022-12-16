package com.jeppu.updates;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//Class to demo how to update a row and then query to verify
public class UpdateApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET registration_date = ? WHERE id = ?;")) {
                preparedStatement.setObject(1, LocalDate.of(2011, 8, 7));
                preparedStatement.setInt(2, 4);

                int updatedRows = preparedStatement.executeUpdate();
                System.out.println("Updated " + updatedRows + " rows");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;")) {
                preparedStatement.setInt(1, 4);

                final ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    LocalDate registrationDate = resultSet.getObject("registration_date", LocalDate.class);

                    System.out.println("Found user : " + id + " | " + firstName + " | " + lastName + " | " + registrationDate);
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
