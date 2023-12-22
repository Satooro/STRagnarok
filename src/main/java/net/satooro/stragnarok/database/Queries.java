package net.satooro.sourcevincular.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static net.satooro.sourcevincular.SourceVincular.database;

public class Queries {

    public Queries() {
        createTablePlayersLinked();
    }

    private void createTablePlayersLinked() {

        database.writeOne("""
                CREATE TABLE IF NOT EXISTS `players_linked`
                (`id` INT NOT NULL AUTO_INCREMENT,
                `player_uuid` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                `userid` VARCHAR(32) NULL DEFAULT NULL,
                `code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                `status` BOOLEAN NOT NULL DEFAULT FALSE,
                `rewards` BOOLEAN NOT NULL DEFAULT FALSE,
                `server` VARCHAR(32) NULL DEFAULT NULL,
                `dscoins` VARCHAR(50) NULL DEFAULT 0,
                PRIMARY KEY (`id`)) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;""", preparedStatement -> {});
    }

    public static void createVerificationUser(String userID, String code) {
        String sql = "INSERT INTO players_linked (userid, code) VALUES (?, ?)";

        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, userID);
            ps.setString(2, code);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean hasCode(String code) {
        boolean hasCode = false;
        String sql = "SELECT COUNT(code) FROM players_linked WHERE code = ?";
        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hasCode = rs.getBoolean(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hasCode;
    }

    public static String getCodeFromUserID(String userID) {
        String code = null;
        String sql = "SELECT code FROM players_linked WHERE userid = ?";

        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return code;
    }

    public static String getUserIdFromUUID(String UUID) {
        String userID = null;
        String sql = "SELECT userid FROM players_linked WHERE player_uuid = ?";

        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userID = rs.getString(1);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userID;
    }

    public static void setVerificationPlayer(String UUID, Boolean status, String code, String server) {
        String sql = "UPDATE players_linked SET player_uuid = ?, status = ?, code = null, rewards = true, server = ? WHERE code = ?";
        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ps.setBoolean(2, status);
            ps.setString(3, server);
            ps.setString(4, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Boolean hasPlayer(String UUID) {
        boolean hasUUID = false;
        String sql = "SELECT COUNT(player_uuid) FROM players_linked WHERE player_uuid = ?";

        try {
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hasUUID = rs.getInt(1) > 0;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hasUUID;
    }

    public static Boolean hasDiscordID(String discordID){
        boolean hasID = false;
        String sql = "SELECT COUNT(userid) FROM players_linked WHERE userid = ?";

        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, discordID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                hasID = rs.getInt(1) > 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hasID;
    }

    public static Boolean hasRewards(String UUID){
        boolean hasRewards = false;
        String sql = "SELECT rewards FROM players_linked WHERE player_uuid = ?";

        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                hasRewards = rs.getInt(1) > 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return hasRewards;
    }
}
