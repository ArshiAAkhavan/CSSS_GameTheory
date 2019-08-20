package Controller.menu;

public interface Playable {
    void setProbability(int... probability);
    void endTurn();
    float[][] play();
    int getTurn();

}
