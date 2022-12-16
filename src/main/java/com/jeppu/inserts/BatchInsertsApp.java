package com.jeppu.inserts;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;

public class BatchInsertsApp {
    public static void main(String[] args) {
        try (Connection connection = createDataSource().getConnection()) {

            try (PreparedStatement pStat = connection.prepareStatement("INSERT INTO users (first_name, last_name, registration_date) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                pStat.setString(1, "Hikal");
                pStat.setString(2, "Kumar");
                pStat.setObject(3, LocalDate.of(2010, 2, 10));

                pStat.addBatch();
                pStat.clearParameters();

                pStat.setString(1, "Piramal");
                pStat.setString(2, "Sharma");
                pStat.setObject(3, LocalDate.of(2008, 2, 5));

                pStat.addBatch();
                pStat.clearParameters();

                pStat.setString(1, "Syngene");
                pStat.setString(2, "Rao");
                pStat.setObject(3, LocalDate.of(2010, 2, 10));

                pStat.addBatch();

                final int[] insertedRows = pStat.executeBatch();
                System.out.println("Inserted " + Arrays.toString(insertedRows) + " rows ");
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
