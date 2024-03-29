package Controller;

import Model.account.Account;
import Model.account.player.Bot;
import Model.account.player.GameInterFace;
import Model.account.player.Player;
import network.client.BattleClient;
import network.client.Client;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Global {
    private static Account[] accounts = {Account.getDefaultAccount(), Account.getDefaultAccount()};
    public static boolean hasLoggedIn = false;
    public static Scanner scanner = new Scanner(System.in);

    private static Class userGI;
    private static Class botGI;
    private static Class defaultGI;

    private static Client client;
    private static BattleClient battleClient;
    private static Client chatRoomClient;

    public static void setFirstAccount(Account account) {
        accounts[0] = account;
    }
    public static void setSecondAccount(Account account) {
        accounts[1] = account;
    }

    public static Account getAccount(int i){
        return accounts[i];
    }

    public static Class getDefaultGI() {
        return defaultGI;
    }
    public static void setDefaultGI(Class defaultGI) {
        Global.defaultGI = defaultGI;
    }
    public static Class getUserGI() {
        return userGI;
    }
    public static void setUserGI(Class GI) {
        Global.userGI = GI;
    }
    public static Class getBotGI() {
        return botGI;
    }
    public static void setBotGI(Class botGI) {
        Global.botGI = botGI;
    }


    public static void setGI(Player player) {
        try {
            if (player instanceof Bot) {
                GameInterFace GI = (GameInterFace) botGI.getConstructor().newInstance();
                GI.setPlayer(player);
                player.setGI(GI);
            } else {
                GameInterFace GI = (GameInterFace) userGI.getConstructor().newInstance();
                GI.setPlayer(player);
                player.setGI(GI);
                player.setGI(GI);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void setDefaultGI(Player player) {
        try {
            GameInterFace GI = (GameInterFace) userGI.getConstructor().newInstance();
            GI.setPlayer(player);
            player.setGI(GI);
            player.setGI(GI);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public static Client getClient() {
        return client;
    }
    public static void setClient(Client client) {
        Global.client = client;
    }

    public static BattleClient getBattleClient() {
        if(battleClient==null)battleClient=new BattleClient(Global.getAccount(0));
        return battleClient;
    }

    public static void setBattleClient(BattleClient battleClient) {
        Global.battleClient = battleClient;
    }

    public static Client getChatRoomClient() {
        return chatRoomClient;
    }

    public static void setChatRoomClient(Client chatRoomClient) {
        Global.chatRoomClient = chatRoomClient;
    }
    public static void clearBattleClient() {
        Global.getBattleClient().close();
        Global.setBattleClient(null);
    }
}

