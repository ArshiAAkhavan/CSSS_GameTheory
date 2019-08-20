package Model.mediator;

import Controller.Global;
import Controller.menu.NormalModeMenu;
import Model.account.player.OnlinePlayer;
import network.Message;

public class OnlineNormalModeMediator implements NormalModeMediator {
    @Override
    public void setProbability(int... probability) {
        if(NormalModeMenu.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("set probability",probability);
    }

    @Override
    public void endTurn() {
        if(NormalModeMenu.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("end turn");
    }

    @Override
    public void play() {
        if(NormalModeMenu.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("play");
    }
    private void sendPlayerMove(String playerMove, Object carry) {
        Message message = new Message("playerMove");
        message.addCarry(playerMove);
        message.addCarry(carry);
        System.out.println("message.getText() = " + message.getCarry().get(0));
        Global.getBattleClient().write(message);
    }
    private void sendPlayerMove(String playerMove) {
        sendPlayerMove(playerMove,null);
    }
}
