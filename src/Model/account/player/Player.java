package Model.account.player;

import Controller.Global;
import Model.account.Account;
import com.gilecode.yagson.YaGson;
import java.util.Scanner;


class ScannerWrapper{
    Scanner scanner;

}

public class Player {

    private Account user;
    private GameInterFace GI;
    protected ScannerWrapper inputStream =new ScannerWrapper();


    private int score;

    public Player(Account user, int maxMana, int mana) {
        this.user = user;
        YaGson gson = new YaGson();
        //In Order To Secure Objects In Account We Made A HardCopy Of MainDeck

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return player.user.equals(this.user);
    }

    public Account getUser() {
        return user;
    }

    public GameInterFace getGI() {
        return GI;
    }

    public void setGI(GameInterFace GI) {
        this.GI = GI;
    }

    public void doYourMove(){
    }





    public Scanner getInputStream() {
        if(this.inputStream ==null || this.inputStream.scanner==null){
            this.inputStream = new ScannerWrapper();
            this.inputStream.scanner = Global.scanner;
        }
        return inputStream.scanner;

    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
    public void clearScore(){
        this.score=0;
    }
}
