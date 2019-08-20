package Model.Table;

import java.util.ArrayList;

public class Game {
    private Board board;
    private int playerTurn;// zero -->first player     one -->second player
    private int turns;
    private ArrayList<Float>[]moves=new ArrayList[2];
    private ArrayList<Cell>results=new ArrayList<>();
    public Game(int[][] table) {
        board=new Board(table);
        for(int i=0;i<2;i++)moves[i]=new ArrayList<>();
    }
    public void play(){

        while(turns>0){
            if(playerTurn==0){
                this.board.setRowProbability(this.moves[0].get(0));
                this.moves[0].remove(0);
            }else{
                this.board.setColProbability(this.moves[1].get(0));
                this.moves[1].remove(0);
                this.results.add(this.board.play());
            }
            turns--;
            playerTurn=(playerTurn+1)%2;
        }
        for(int i=0;i<2;i++) moves[i]=new ArrayList<>();
    }
    public void setTurns(int turns) {
        /*
        * sets the rounds that can be played in this game
        * every time the method play is called the rounds will be reset to zero
        * */
        this.turns = turns;
    }
    public void addMoves(int player, float... moves){
        for (float move : moves) this.moves[player].add(move);
    }
    public ArrayList<Cell> getResults() {
        ArrayList<Cell> results = this.results;
        this.results=new ArrayList<>();
        return results;
    }
}
