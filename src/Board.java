import java.util.Random;

public class Board {
    private static final int HEIGHT = 2;
    private static final int WIDTH  = 2;

    private int[][] table;

    private float rowProbability;
    private float colProbability;

    public Board(int[][] table) {
        this.table = table;
    }

    public int play(){
        Random random=new Random();
        int row=(random.nextFloat()<=rowProbability)? 0 : 1;
        int col=(random.nextFloat()<= colProbability)? 0 : 1;

        return table[row][col];
    }

    public void setRowProbability(float rowProbability) {
        this.rowProbability = rowProbability;
    }
    public void setColProbability(float colProbability) {
        this.colProbability = colProbability;
    }
    public void setRowChoose(int decision){
        this.rowProbability=(decision==0)? -1 : 1;
    }
    public void setColChoose(int decision){
        this.colProbability=(decision==0)? -1 : 1;
    }
}
