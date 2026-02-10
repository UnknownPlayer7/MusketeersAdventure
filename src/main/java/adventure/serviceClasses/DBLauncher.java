package adventure.serviceClasses;

import java.sql.*;
import java.util.*;

public class DBLauncher {

    private final String url = "jdbc:postgresql://localhost:5432/musketeersdb";
    private final Connection connection;

    public DBLauncher() {
        connection = connect();
    }

    public List<List<String>> getListOfRows() {
        List<List<String>> data = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        List<String> rows;
        try(ResultSet table = getTable(); connection){

            for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                headers.add(table.getMetaData().getColumnName(i));
            }
            data.add(headers);

            while(table.next()) {
                rows = new ArrayList<>();
                for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                    rows.add(table.getString(i));
                }
                data.add(rows);
            }
            return data;
        }
        catch (SQLException e) {
            throw new RuntimeException(e + " Couldn't read the table: ");
        }
    }

    public void savePlayer(String name, String trait) {
        String query = ("""
                                         UPDATE musketeers
                                            SET name = ?, trait = ?
                                            WHERE id = 1;
                                         """);
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, trait);
            int rowsAffected = statement.executeUpdate();

            System.out.print("\nData saved!\nRows updated: " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e + " Couldn't update the table: ");
        }

    }

    private Connection connect() {
        try{
            Properties authorization = new PropertyLoader("/jdbc.properties").getProperties();

            return DriverManager.getConnection(url, authorization);
        }
        catch (SQLException e) {
            throw new RuntimeException(e + " Couldn't connect to " + url);
        }


    }

    private ResultSet  getTable() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM public.musketeers");
    }



}
