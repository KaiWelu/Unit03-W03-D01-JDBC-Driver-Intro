package org.dci;

import java.sql.*;

public class Main {
    private static String jbcdUrl = "jdbc:postgresql://localhost:5432/library_db";
    private static String username = "postgres";
    private static String password = "your_secure_password";


    public static void main(String[] args) throws SQLException {

        try(Connection connection = DriverManager.getConnection(jbcdUrl, username, password)) {
            System.out.println("Connection successful!");

            // insert books
            //insertBook(connection, "Hallo Welt!", "Kai Weluda", 2005, true);
            printAllBooks(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public static void insertBook(Connection con, String title, String author, int publicationYear,
                                  boolean isAvailable) {
        String query = "INSERT INTO books(title, author, publication_year, is_available) VALUES (?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = con.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, publicationYear);
            preparedStatement.setBoolean(4, isAvailable);

            int rowsAdded = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAdded);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printAllBooks(Connection con) throws SQLException {
        String query = "SELECT * FROM books";

        try(Statement statement = con.createStatement()) {

            try(ResultSet queryResult = statement.executeQuery(query)) {
                while (queryResult.next()) {
                    System.out.println("ID: " + queryResult.getInt("book_id"));
                    System.out.println("Title: " + queryResult.getString("title"));
                    System.out.println("Author: " + queryResult.getString("author"));
                    System.out.println("Publication Year: " + queryResult.getInt("publication_year") + "\n");
                }
            }
        } catch  (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}