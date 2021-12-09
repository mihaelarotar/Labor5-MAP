package uni.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcRepository<E> implements ICrudRepository<E> {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost::3306/university";
        String user = "user1";
        String pass = "pass";

        return DriverManager.getConnection(url, user, pass);
    }

    public List<E> getRows(String sql, RowMapper<E> mapper) throws SQLException {
        List<E> rows = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
        }

        while (resultSet.next()) {
            rows.add(mapper.mapRow(resultSet));
        }
        connection.close();
        return rows;
    }

    public E getEntity(String sql, RowMapper<E> mapper) throws SQLException {
        E row = null;
        Connection connection = getConnection();
        ResultSet resultSet;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            resultSet = statement.executeQuery();
        }
        if(resultSet.next()) {
            row = mapper.mapRow(resultSet);
        }
        connection.close();
        return row;
    }


    public void updateTable(String sql) throws SQLException {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        }
        connection.close();
    }
}
