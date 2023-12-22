package net.satooro.stragnarok.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Adapter<T> {

    T adapt(ResultSet resultSet) throws SQLException;
}
