package Controller.menu;

import Controller.Global;
import Model.Table.Cell;
import Model.Table.Game;
import Model.account.player.Player;

import java.util.ArrayList;

public class NormalModeMenu extends Menu implements Playable {

    private static NormalModeMenu menu;
    public static NormalModeMenu getMenu() {
        if(NormalModeMenu.menu==null){
            NormalModeMenu.menu=new NormalModeMenu("NormalMode");
        }
        return menu;
    }


    private static final int ROUNDS = 1000;

    private Game game;
    private int turn;
    private int[]move;
    private int[][] table={{2,1},{0,-1}};
    private Player[] player;


    private NormalModeMenu(String name) {
        super(name);
    }

    @Override
    public boolean init(Menu parentMenu) {
        this.game=new Game(table);
        move=new int[2];

        player=new Player[2];
        setPlayer(Global.getAccount(0).getPlayer(), Global.getAccount(1).getPlayer());
        this.setAccount(Global.getAccount(0));


        return super.init(parentMenu);
    }

    @Override
    public void setProbability(int... probability){
        this.move[turn]=probability[0]%100;
    }

    @Override
    public void endTurn(){
        turn=(turn+1)%2;
    }

    @Override
    public int[][] play(){
        if(turn==1) return null;

        /*
        * initializing the game
        * */
        for(int i=0;i<2;i++){
            for(int j=0;j<ROUNDS;j++){
                this.game.addMoves(i,this.move[i]/100.0f);
            }
        }
        this.game.setTurns(2*ROUNDS);
        this.game.play();

        /*
        * preparing the return table
        * */
        int[][] retVal=new int[2][2];
        ArrayList<Cell> results = this.game.getResults();
        for (Cell result : results) {
            retVal[result.getX()][result.getY()]++;
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                retVal[i][j]/=(ROUNDS*1.0f);
            }
        }
        this.setScore(retVal);
        return retVal;
    }

    private void setScore(int[][] retVal) {
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                this.player[0].addScore(retVal[i][j]*table[i][j]);
                this.player[1].addScore(-retVal[i][j]*table[i][j]);
            }
        }
    }


    private void setPlayer(Player player1, Player player2) {
        this.player[0]=player1;
        this.player[1]=player2;

    }

}
