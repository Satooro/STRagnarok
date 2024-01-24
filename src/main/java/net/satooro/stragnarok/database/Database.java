package net.satooro.stragnarok.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.satooro.stragnarok.utils.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.satooro.stragnarok.STRagnarok.database;

public class Database {
    /*
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
     */

    private static Connection conn;

    public Database() {
        if(Config.getBoolean("mysql.enabled")){
            String host = Config.getString("mysql.host");
            String database = Config.getString("mysql.database");
            String username = Config.getString("mysql.username");
            int port = Config.get().getInt("mysql.port");
            String password = Config.getString("mysql.password");

            try {
                if (getConnect() != null && !getConnect().isClosed()) {
                    return;
                }
                Class.forName("java.sql.Driver");
                setConnect(DriverManager.getConnection("jdbc:mysql://" +
                        host + ":" + port + "/" + database +
                        "?autoReconnect=true&useSSL=false", username, password));
                System.out.println("O Mysql ligou na porta " + port);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public static Connection getConnect() {
        return conn;
    }

    public static void setConnect(Connection connect) {
        conn = connect;
    }

    public static void closeConnect(Connection connect) throws SQLException {
        connect.close();
    }

    /*
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

     */
}
