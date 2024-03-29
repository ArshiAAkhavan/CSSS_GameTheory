package Model.account.player;

import Controller.Global;
import Model.account.Account;
import network.Message;

import java.util.Scanner;

public class OnlinePlayer extends Bot {

    private Scanner in;


    public OnlinePlayer(Account user, int maxMana, int mana, Scanner serverInput) {
        super(user, maxMana, mana);

        in=(serverInput);
    }

    public Scanner getInput() {
        return in;
    }

    @Override
    public void doYourMove() {
        super.doYourMove();
    }

    @Override
    protected void play() {

        Message message = Global.getBattleClient().read();
        this.output= (String) message.getCarry().get(0);
        System.out.println("---------------------------");
        System.out.println("output :"+output);
        System.out.println("---------------------------");
    }
}
