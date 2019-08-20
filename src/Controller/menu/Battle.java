package Controller.menu;

import Controller.Global;
import Model.account.player.Player;
import Model.mediator.BattleMediator;
import Model.account.*;
import View.MenuHandler;
import Controller.menu.Graphics.FXMLController.*;
import exeption.*;

public class Battle extends Menu {

    private static Battle menu;

    public Player[] player = new Player[2];
    private Player ownPLayer;
    private Player opponentPlayer;
    public int turn = 0;
    public Account winner;

    private BattleMediator mediator;
    public Battle(String name) {
        super(name);
    }


    public static Battle getMenu() {
        if (Battle.menu == null) {
            Battle.menu = new Battle("Battle");
        }
        return menu;
    }


    @Override
    public boolean init(Menu parentMenu) {

        super.init(parentMenu);


//        this.match=new Match(Game.getAccount(0),Game.getAccount(1),this.gameMode);
        setPlayer(Global.getAccount(0).getPlayer(), Global.getAccount(1).getPlayer());
        this.setAccount(Global.getAccount(0));

        this.mediator.init();

        return true;
    }

    public void endTurn() {
        this.mediator.endTurn();




        this.account = this.getEnemy(this.account).getUser();
        swapPlayers();

        try {
            this.getGraphic().getController().updateScene();
        }catch (Exception ignored){}
        /*checkState*/
        if (false) {
            handleBattleFinish();
        } else {
            nextTurn();
        }

    }

    private void handleBattleFinish() {
        /*
         * giving the prize
         * */
        this.mediator.handleBattleFinish();


        /*
         * destroying the players
         * */
        Global.getAccount(0).setPlayer(null);
        Global.getAccount(1).setPlayer(null);

        /*
         * handling the account for getting input and stuff
         * */
        Global.setSecondAccount(Account.getDefaultAccount());
        this.account = SignInMenu.getMenu().getAccount();
        Global.setFirstAccount(SignInMenu.getMenu().getAccount());
        this.turn = 0;




        /*
         * getting out of battle
         * */
        try {
            SignInMenu.getMenu().setAccount(Account.getAccount(Battle.getMenu().getAccount().getUsername()));
            MainMenu.getMenu().setAccount(Account.getAccount(Battle.getMenu().getAccount().getUsername()));
        } catch (InvalidAccountException e) {
            e.printStackTrace();
        }
        MenuHandler.enterMenu(MainMenu.getMenu());
    }

    private void swapPlayers() {
        Player temp = this.player[0];
        this.player[0] = this.player[1];
        this.player[1] = temp;
    }


    private void nextTurn() {
        turn++;
    }
    private void setPlayer(Player firstPlayer, Player secondPlayer) {




        this.player[0] = firstPlayer;
        this.ownPLayer = firstPlayer;
        this.player[1] = secondPlayer;
        this.opponentPlayer = secondPlayer;
    }
    public Player getEnemy(Account me) {
        if (player[0].getUser().equals(me)) return player[1];
        return player[0];
    }

    public Player getMe(Account me) {
        if (player[0].getUser().equals(me)) return player[0];
        return player[1];

    }
    public Player getPlayer() {
        return player[0];
    }

    public Player getEnemyPlayer() {
        return player[1];
    }

    public int getTurn() {
        return turn % 2;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("4)Game info     5)Show my minions     6)Show opponent minions");
        System.out.println("7)Show card info [card id]     8)Select [card id]     9)Move to ([x], [y])");
        System.out.println("10)Attack [opponent card id]     11)Attack combo [opponent card id] [my card id] [my card id] [...]     12)Use special power (x, y)");
        System.out.println("13)Show hand     14)Insert [card name] in (x, y)     15)Show Next Card");
        System.out.println("16)End turn     17)Show collectables     18)Select [collectable id]");
        System.out.println("19)End Game   20)Use [location x, y]     21)Help");
    }

//    public GameMode getGameMode() {
//        return gameMode;
//    }
//
//    public void setGameMode(GameMode gameMode) {
//        this.gameMode = gameMode;
//    }

//
//    public static Battle newGame(GameMode gameMode) {
//        Battle.menu.setGameMode(gameMode);
//        Battle.menu.init(Battle.menu.getParentMenu());
//        return Battle.menu;
//    }

    public Player getOwnPLayer() {
        return ownPLayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }


    public void setMediator(BattleMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void exit() {
        super.exit();
    }
}
