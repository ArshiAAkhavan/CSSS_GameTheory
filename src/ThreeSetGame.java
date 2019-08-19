import java.util.ArrayList;

public class ThreeSetGame extends Menu {

    private int gamePlayed;
    private static int MAX_ROUND=4;
    private Game game;
    private int[][] table;
    public ThreeSetGame(Menu parentMenu, String name,int[][]table) {
        super(parentMenu, name);

        this.table=table;
        this.game=new Game(table);
    }
    public void setMoves(int player,float...moves){
        this.game.setMoves(player,moves);
    }
    public ArrayList<Cell> play(){
        gamePlayed++;
        this.game.setTurns(12);
        this.game.play();
        if(gamePlayed==MAX_ROUND){
            newGame();
        }
        return this.game.getResults();
    }

    private void newGame() {
        gamePlayed=0;
        /*
        * todo
        * */
    }
}
