package Model.mediator;

import Controller.Global;
import Controller.menu.NormalModeMenu;
import Model.account.player.OnlinePlayer;
import network.Message;

public class OnlineNormalModeMediator implements NormalModeMediator {
    @Override
    public void setProbability(int... probability) {
        if(NormalModeMenu.getMenu().getAccount().getPlayer() instanceof OnlinePlayer) return;
        sendPlayerMove("set probability"+ " "+string(probability));
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

    private void sendPlayerMove(String playerMove) {
        Message message = new Message("playerMove");
        message.addCarry(playerMove);
        System.out.println("message.getText() = " + message.getCarry().get(0));
        Global.getBattleClient().write(message);
    }

    private String string(int[] probability) {
        StringBuilder retVal=new StringBuilder();
        for(int i=0;i<probability.length;i++){
            retVal.append(probability[i]+" ");
        }
        return retVal.toString();
    }
}
