package uni.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {
    public E mapRow(ResultSet resultSet) throws SQLException;
}
