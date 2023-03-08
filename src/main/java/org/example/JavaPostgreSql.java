package org.example;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa odpowiadajaca za placzenie z baza danych
 */
public class JavaPostgreSql {
    String url = "jdbc:postgresql://localhost:5432/javafx";
    String user = "postgres";
    String pass = "123456";
    public Connection databaseLink;

    /**
     *
     *Metoda lacza z baza
     * @return databaseLink
     */
        public Connection getConnection() {
        try {
            databaseLink = DriverManager.getConnection(url, user, pass);
        }catch (Exception e){
            e.printStackTrace();
        }return databaseLink;
    }

    /**
     * Dodanie wartosci do bazy danych
     * @param userName nazwa uzytkownika
     * @param userPassword hasło
     */
    public static void writeToDatabase(String userName, String userPassword) {
        String url = "jdbc:postgresql://localhost:5432/javafx";
        String user = "postgres";
        String pass = "123456";
        String username = userName;
        String password = userPassword;

        String query = "INSERT INTO users(username, password) VALUES(?,?)";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement pst = con.prepareStatement(query)){
            pst.setString(1, username);
            pst.setString(2, password);
            pst.executeUpdate();
            System.out.println("Sucessfully created");
        }catch (SQLException ex){
            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
