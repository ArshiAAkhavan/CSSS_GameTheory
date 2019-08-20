package Controller.menu;

import Model.Table.Cell;

import java.util.ArrayList;

public interface Playable {
    void setProbability(int... probability);
    void endTurn();
    int[][] play();

}
