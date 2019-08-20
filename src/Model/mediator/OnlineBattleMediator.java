package Model.mediator;

import Controller.Global;
import Controller.menu.Battle;
import Model.account.Account;
import Model.account.player.OnlinePlayer;
import exeption.*;
import network.Message;

public class OnlineBattleMediator implements BattleMediator {

    @Override
    public void insert(int cardID, int x, int y){
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("insert " + cardID + " in " + x + " " + y);
    }
    @Override
    public void select(int ID) {
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("select "+ID);
    }
    @Override
    public void move(int x, int y){
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("move to "+x+" "+y);
    }
    @Override
    public void attack(int cardID) {
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("attack "+cardID);
    }
    @Override
    public void useSpecialPower(int x, int y){
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("Use special power "+x+" "+y);
    }
    @Override
    public void useItem(int x, int y){
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("use "+x+" "+y);
    }
    @Override
    public void endTurn() {
        if(Battle.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("end turn");
    }


    @Override
    public boolean init() {
        Message message=new Message("init");
        Global.getBattleClient().write(message);

        message = Global.getBattleClient().read();
        System.out.println("----------------------------------------------------------");
        System.out.println("message.getCarry().get(0) = " + message.getCarry().get(0));

        try {
            if (!NetworkMediator.isValid(message)) return false;
        } catch (Exception e) {
             return false;
        }

        /*
        * this message contains the map
        * */
//        Battle.getMenu().setMap((Map) message.getCarry().get(0));
        return true;
    }

    @Override
    public void handleBattleFinish() {
        try {
            Global.setFirstAccount(Account.getAccount(Global.getAccount(0).getUsername()));
            Global.setSecondAccount(Account.getAccount(Global.getAccount(1).getUsername()));
        } catch (InvalidAccountException e) {
            e.printStackTrace();
        }
        Global.clearBattleClient();
    }



    private void sendPlayerMove(String playerMove) {
        Message message = new Message("playerMove");
        message.addCarry(playerMove);
        System.out.println("message.getText() = " + message.getCarry().get(0));
        Global.getBattleClient().write(message);
    }
}
