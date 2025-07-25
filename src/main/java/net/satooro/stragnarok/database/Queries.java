package net.satooro.stragnarok.database;


import net.satooro.stragnarok.discord.events.UserInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import static net.satooro.stragnarok.STRagnarok.database;
import static net.satooro.stragnarok.database.Database.getConnect;

public class Queries {

    public Queries() {
        createTablePlayersLinked();
    }


    private void createTablePlayersLinked(){
        String sql = """
                CREATE TABLE IF NOT EXISTS `players_linked`
                                (`id` INT NOT NULL AUTO_INCREMENT,
                                `player_uuid` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `player_nick` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `userid` VARCHAR(32) NULL DEFAULT NULL,
                                `code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `status` BOOLEAN NOT NULL DEFAULT FALSE,
                                `rewards` BOOLEAN NOT NULL DEFAULT FALSE,
                                `server` VARCHAR(32) NULL DEFAULT NULL,
                                `dscoins` VARCHAR(50) NULL DEFAULT 0,
                                `ticket_status` BOOLEAN NOT NULL DEFAULT FALSE,
                                PRIMARY KEY (`id`)) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;""";

        try (Statement stmt = getConnect().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createVerificationUUID(String UUID, String code, String nickname){
        String sql = "INSERT INTO players_linked (player_uuid, code, player_nick) VALUES (?, ?, ?)";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, UUID);
            ps.setString(2, code);
            ps.setString(3, nickname);
            ps.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void createVerificationUser(String userID, String code) {
        String sql = "INSERT INTO players_linked (userid, code) VALUES (?, ?)";

        try {
            PreparedStatement ps = getConnect().prepareStatement(sql);
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
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                hasCode = rs.getBoolean(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hasCode;
    }

    public static UserInfo getNickAndUUIDFromCode(String codigo){
        UserInfo userInfo = null;

        String sql = "SELECT player_nick, player_uuid FROM players_linked WHERE code = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String player_nick = rs.getString("player_nick");
                String player_uuid = rs.getString("player_uuid");

                userInfo = new UserInfo(player_nick, player_uuid);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userInfo;
    }

    public static String getCodeFromUserID(String userID) {
        String code = null;
        String sql = "SELECT code FROM players_linked WHERE userid = ?";

        try {
            PreparedStatement ps = getConnect().prepareStatement(sql);
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

    public static String getCodeFromUUID(String UUID){
        String code = null;
        String sql = "SELECT code FROM players_linked WHERE player_uuid = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return code;
    }

    public static String getUUIDFromCode(String code){
        String player_uuid = null;
        String sql = "SELECT player_uuid FROM players_linked where code = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                player_uuid = rs.getString(1);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return player_uuid;
    }

    public static String getNickAndStatus(String userid){
        String username = null;
        String status = null;
        String sql = "SELECT player_nick, status FROM players_linked WHERE userid = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                username = rs.getString("player_nick");
                status = rs.getString("status");
                return username + "|" + status;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getNameFromCode(String code){
        String name = null;
        String sql = "SELECT player_nick FROM players_linked WHERE code = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
    }

    public static String getUserIdFromUUID(String UUID) {
        String userID = null;
        String sql = "SELECT userid FROM players_linked WHERE player_uuid = ?";

        try {
            PreparedStatement ps = getConnect().prepareStatement(sql);
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


    public static Boolean isUserinDB(String userid){
        boolean userindb = false;
        String sql = "SELECT player_nick FROM players_linked WHERE userid = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userindb = rs.getInt(1) > 0;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userindb;
    }

    public static Boolean hasDiscordID(String discordID){
        boolean hasID = false;
        String sql = "SELECT COUNT(userid) FROM players_linked WHERE userid = ?";

        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
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

    public static Boolean playerIsVerified(String UUID){
        boolean userid = false;
        String sql = "SELECT COUNT(userid) FROM players_linked WHERE player_uuid = ? AND status = 1";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userid = rs.getInt(1) > 0;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userid;


    }

    public static void setVerificationPlayer(String userID, Boolean status, String nickname){
        String sql = "UPDATE players_linked SET userid = ?, status = ?, code = null, rewards = true WHERE player_nick = ?";
        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
            ps.setString(1, userID);
            ps.setBoolean(2, status);
            ps.setString(3, nickname);
//            ps.setString(3, code);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Boolean hasPlayer(String UUID) {
        boolean hasUUID = false;
        String sql = "SELECT COUNT(player_uuid) FROM players_linked WHERE player_uuid = ?";

        try {
            PreparedStatement ps = getConnect().prepareStatement(sql);
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

    public static Boolean hasRewards(String UUID){
        boolean hasRewards = false;
        String sql = "SELECT rewards FROM players_linked WHERE player_uuid = ?";

        try{
            PreparedStatement ps = getConnect().prepareStatement(sql);
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

    /*
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

    /*
    private void createTablePlayersLinked() {

        database.writeOne("""
                CREATE TABLE IF NOT EXISTS `players_linked`
                (`id` INT NOT NULL AUTO_INCREMENT,
                `player_uuid` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                `player_nick` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                `userid` VARCHAR(32) NULL DEFAULT NULL,
                `code` VARCHAR(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                `status` BOOLEAN NOT NULL DEFAULT FALSE,
                `rewards` BOOLEAN NOT NULL DEFAULT FALSE,
                `server` VARCHAR(32) NULL DEFAULT NULL,
                `dscoins` VARCHAR(50) NULL DEFAULT 0,
                PRIMARY KEY (`id`)) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;""", preparedStatement -> {});
    }


    public static void createVerificationUUID(String UUID, String code, String nickname){
        String sql = "INSERT INTO players_linked (player_uuid, code, player_nick) VALUES (?, ?, ?)";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ps.setString(2, code);
            ps.setString(3, nickname);
            ps.execute();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
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

    public static String getCodeFromUUID(String UUID){
        String code = null;
        String sql = "SELECT code FROM players_linked WHERE player_uuid = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return code;
    }

    public static UserInfo getNickAndUUIDFromCode(String codigo){
        UserInfo userInfo = null;

        String sql = "SELECT player_nick, player_uuid FROM players_linked WHERE code = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String player_nick = rs.getString("player_nick");
                String player_uuid = rs.getString("player_uuid");

                userInfo = new UserInfo(player_nick, player_uuid);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userInfo;
    }

    public static String getUUIDFromCode(String code){
        String player_uuid = null;
        String sql = "SELECT player_uuid FROM players_linked where code = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                player_uuid = rs.getString(1);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return player_uuid;
    }

    public static String getNickAndStatus(String userid){
        String username = null;
        String status = null;
        String sql = "SELECT player_nick, status FROM players_linked WHERE userid = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                username = rs.getString("player_nick");
                status = rs.getString("status");
                return username + "|" + status;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getNameFromCode(String code){
        String name = null;
        String sql = "SELECT player_nick FROM players_linked WHERE code = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return name;
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


    /*
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
     */


    /*
    public static Boolean isUserinDB(String userid){
        boolean userindb = false;
        String sql = "SELECT player_nick FROM players_linked WHERE userid = ?";
        try{
                PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
                ps.setString(1, userid);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    userindb = rs.getInt(1) > 0;
                }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userindb;
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

    public static Boolean playerIsVerified(String UUID){
        boolean userid = false;
        String sql = "SELECT COUNT(userid) FROM players_linked WHERE player_uuid = ? AND status = 1";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, UUID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                userid = rs.getInt(1) > 0;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userid;


    }

    public static void setVerificationPlayer(String userID, Boolean status, String nickname){
        String sql = "UPDATE players_linked SET userid = ?, status = ?, code = null, rewards = true WHERE player_nick = ?";
        try{
            PreparedStatement ps = database.hikari.getConnection().prepareStatement(sql);
            ps.setString(1, userID);
            ps.setBoolean(2, status);
            ps.setString(3, nickname);
//            ps.setString(3, code);
            ps.executeUpdate();
        } catch (SQLException e){
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
    */
}
