package Controller.menu;

import Controller.Global;
import Model.Table.Cell;
import Model.Table.Game;
import Model.account.player.Player;
import Model.mediator.PlayableMediator;

import java.util.ArrayList;

public class FourSetModeMenu extends Menu implements Playable {

    private static FourSetModeMenu menu;

    public static FourSetModeMenu getMenu() {
        if(FourSetModeMenu.menu==null){
            FourSetModeMenu.menu=new FourSetModeMenu("FourSetMode");
        }
        return menu;
    }

    private FourSetModeMenu(String name) {
        super(name);
    }

    private static final int MAX_SETS = 4;
    private static final int MAX_ROUNDS=6;// 4 set of 6 rounded game :D
    private int round;

    private PlayableMediator mediator;

    private Game game;
    private int turn;
    private int[][]move;
    private int[][] table={{2,1},{0,-1}};
    private Player[] player;


    @Override
    public boolean init(Menu parentMenu) {
        this.game=new Game(table);
        move=new int[2][6];

        player=new Player[2];

        setPlayer(Global.getAccount(0).getPlayer(), Global.getAccount(1).getPlayer());

        boolean isOk=super.init(parentMenu);

        this.setAccount(Global.getAccount(0));
        return isOk;
    }

    @Override
    public void setProbability(int... probability){
        this.mediator.setProbability(probability);
        this.move[turn]=probability;
    }

    @Override
    public void endTurn(){
        this.mediator.endTurn();
        turn=(turn+1)%2;
        this.account=this.player[this.turn].getUser();
        this.getGraphic().getController().updateScene();
    }

    @Override
    public float[][] play(){
        this.mediator.play();
        if(turn==0) {
            endTurn();
            return null;
        }

        /*
         * initializing the game
         * */
        for(int i=0;i<2;i++){
            for(int j = 0; j< MAX_SETS; j++){
                this.game.addMoves(i,(this.move[i][j]%100)/100.0f);
            }
        }
        this.game.setTurns(2* MAX_SETS);
        this.game.play();

        this.round++;
        /*
         * preparing the return table
         * */
        float[][] retVal=new float[2][2];
        ArrayList<Cell> results = this.game.getResults();
        for (Cell result : results) {
            retVal[result.getX()][result.getY()]++;
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                System.out.println("i,j,value:"+i+" "+j+" "+retVal[i][j]);
                retVal[i][j]/=(MAX_SETS *1.0f);
                System.out.println("i,j,value:"+i+" "+j+" "+retVal[i][j]);
            }
        }
        this.setScore(retVal);
        try {
            this.getGraphic().getController().updateScene();
        }catch (Exception ignored){
            System.err.println("graphic error___________________________________");
            ignored.printStackTrace();
        }
        return retVal;
    }

    private void setScore(float[][] retVal) {
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                this.player[0].addScore((int) (retVal[i][j]*table[i][j]));
                this.player[1].addScore((int) (-retVal[i][j]*table[i][j]));
            }
        }
    }


    private void setPlayer(Player firstPlayer, Player secondPlayer) {
        Global.setGI(firstPlayer);
        Global.setGI(secondPlayer);

        this.player[0]=firstPlayer;
        this.player[1]=secondPlayer;

    }

    public void setMediator(PlayableMediator mediator) {
        this.mediator=mediator;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    public int[][] getTable() {
        return table;
    }

    public Player getPlayer(int i) {
        return player[i%2];
    }
}
