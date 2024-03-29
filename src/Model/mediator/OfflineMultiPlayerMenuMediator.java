package Model.mediator;

import Controller.Global;
import Controller.menu.Battle;
import Model.account.Account;
import View.MenuHandler;
import exeption.InvalidAccountException;
import exeption.WrongPassException;
import network.ChatMSG;

import java.util.ArrayList;

public class OfflineMultiPlayerMenuMediator implements MultiPlayerMenuMediator {

    @Override
    public void selectUser(String username, String password) throws InvalidAccountException, WrongPassException {

        Account account = Account.getAccount(username);
        if (!account.getPassword().equals(password)) {
            throw new WrongPassException();
        }
        Global.setSecondAccount(account);
        MenuHandler.enterMenu(Battle.getMenu());
    }

    @Override
    public void sendMessage(String text) {

    }

    @Override
    public ArrayList<ChatMSG> getChats() {
        return null;
    }

    @Override
    public void cancel() {

    }
}
