package edu.sdccd.cisc191.util;

import java.sql.Connection;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DatabaseConfig.getConnection();
             var stmt = conn.createStatement()) {
        try(Connection conn = DatabaseConfig.getConnection();
            var stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS students (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    gpa DOUBLE NOT NULL CHECK (gpa >= 0.0 AND gpa <= 4.0)
                    gpa DOUBLE NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS courses (
                    id INT PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    student_id INT NOT NULL,
                    FOREIGN KEY (student_id) REFERENCES students(id)
                    ON DELETE CASCADE
                )
            """);
                    student_id INT,
                    FOREIGN KEY (student_id) REFERENCES students(id)
                )
            """);
        } catch(Exception e){
            throw new RuntimeException("cannot run database initializer", e);

        }
        // TODO create students table if it does not exist
        // students: id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, gpa DOUBLE NOT NULL

        } catch (Exception e) {
            throw new RuntimeException("Cannot run database initializer", e);
        }
    }
}