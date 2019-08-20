package Model.mediator;

import exeption.*;


public interface BattleMediator {

    boolean init() ;

    void insert(int cardID, int x, int y) throws Exception;
    void select(int ID) throws Exception;
    void move(int x, int y) throws Exception;
    void attack(int cardID) throws Exception;
    void useSpecialPower(int x, int y) throws Exception;
    void useItem(int x, int y) throws Exception;
    void endTurn();

    void handleBattleFinish();

}
