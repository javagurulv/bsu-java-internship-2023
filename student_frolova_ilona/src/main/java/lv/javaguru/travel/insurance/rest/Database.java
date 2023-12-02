package lv.javaguru.travel.insurance.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Database {

    @Autowired
    private TravelCalculatePremiumDatabaseLogger databaseLogger;

    private static final String url = "jdbc:mysql://localhost:3306/travel_insurance_application";
    private static final String user = "root";
    private static final String password = "Black*Sql00";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet queryResult;

    public Database() {

        databaseLogger.log("creating database connection");

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (
                SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            closeDatabaseConnection();
        }
    }

    public void executeQuery(String query) throws SQLException {
        queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            String content = queryResult.getString(1);
            System.out.println("Retrieved : " + content);
        }
    }

    public void closeDatabaseConnection() {

        databaseLogger.log("closing database connection");

        closeDbConnection();
        closeDbStatement();
        closeDbQueryResultTable();
    }

    private void closeDbConnection() {
        try {
            connection.close();
        }
        catch(SQLException se) {
            databaseLogger.log("Could not close the connection");
        }
    }

    private void closeDbStatement() {
        try {
            statement.close();
        } catch(SQLException se) {
            databaseLogger.log("Could not close the statement");
        }
    }

    private void closeDbQueryResultTable() {
        try {
            if (queryResult != null) queryResult.close();
        } catch(SQLException se) {
            databaseLogger.log("Could not close the result table");
        }
    }
}
