package net.satooro.sourcevincular.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.satooro.sourcevincular.utils.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Database {

    public final HikariDataSource hikari;

    public Database() {

        String host = Config.getString("mysql.host");
        String database = Config.getString("mysql.database");
        String user = Config.getString("mysql.username");
        int port = Config.get().getInt("mysql.port");
        String password = Config.getString("mysql.password");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?autoReconnect=true&characterEncoding=utf8", host, port, database));
        config.setUsername(user);
        config.setPassword(password);

        hikari = new HikariDataSource(config);

    }

    public <T> List<T> queryMany(String operation, Consumer<PreparedStatement> prepare, Adapter<T> adapter) {

        List<T> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = hikari.getConnection().prepareStatement(operation)) {

            prepare.accept(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                T valueAdapted = adapter.adapt(resultSet);

                list.add(valueAdapted);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T queryOne(String operation, Consumer<PreparedStatement> prepare, Adapter<T> adapter) {

        try (PreparedStatement preparedStatement = hikari.getConnection().prepareStatement(operation)) {

            prepare.accept(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ? adapter.adapt(resultSet) : null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> CompletableFuture<T> queryOneAsync(String operation, Consumer<PreparedStatement> prepare, Adapter<T> adapter) {
        return CompletableFuture.supplyAsync(() -> {
            try (PreparedStatement preparedStatement = hikari.getConnection().prepareStatement(operation)) {
                prepare.accept(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next() ? adapter.adapt(resultSet) : null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void writeOne(String operation, Consumer<PreparedStatement> prepare) {

        try (PreparedStatement preparedStatement = hikari.getConnection().prepareStatement(operation)) {

            prepare.accept(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
