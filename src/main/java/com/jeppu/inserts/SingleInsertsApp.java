package com.jeppu.inserts;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

public class SingleInsertsApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {

            try (PreparedStatement pStat = connection.prepareStatement("INSERT INTO users (first_name, last_name, registration_date) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                pStat.setString(1, "Syngene");
                pStat.setString(2, "Rao");
                pStat.setObject(3, LocalDate.of(2010, 2, 10));
                int insertedRows = pStat.executeUpdate();

                final ResultSet resultSet = pStat.getGeneratedKeys();
                resultSet.next();
                long insKey = resultSet.getLong(1);

                System.out.println("Inserted " + insertedRows + " row into users table with id = " + insKey);
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
