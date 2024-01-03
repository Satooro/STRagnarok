package net.satooro.stragnarok.discord.events;

public class UserInfo {

    private final String nickname;
    private final String playerUuid;

    public UserInfo(String nickname, String playerUuid){
        this.nickname = nickname;
        this.playerUuid = playerUuid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPlayerUuid(){
        return playerUuid;
    }

}
