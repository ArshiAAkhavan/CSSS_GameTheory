package Model.mediator;

public interface PlayableMediator {
    void setProbability(int... probability);
    void endTurn();
    void play();

}
