package Model.mediator;

import Controller.menu.Battle;
public class OfflineBattleMediator implements BattleMediator {


    @Override
    public boolean init() {
        return true;
    }
    @Override
    public void insert(int cardID, int x, int y){ }
    @Override
    public void select(int ID){}
    @Override
    public void move(int x, int y){}
    @Override
    public void attack(int cardID){}
    @Override
    public void useSpecialPower(int x, int y){}
    @Override
    public void useItem(int x, int y){}
    @Override
    public void endTurn(){}

    @Override
    public void handleBattleFinish() {

    }
}
