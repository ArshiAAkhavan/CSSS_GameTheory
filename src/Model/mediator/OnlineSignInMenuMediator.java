package Model.mediator;

import Controller.Global;
import Controller.menu.SignInMenu;
import Model.account.Account;
import View.MenuHandler;
import network.Auth;
import network.Message;

public class OnlineSignInMenuMediator implements SignInMenuMediator {
    @Override
    public void logIn(String username, String password) throws Exception {
        Message message=new Message("Log in");
        message.addCarry(username);
        message.addCarry(password);

        Global.getClient().write(message);

        message= Global.getClient().read();

        if(!NetworkMediator.isValid(message))throw new Exception();

        System.out.println("message.getCarry().get(0) = " + message.getCarry().get(0));
        System.out.println("message.getAuthToken() = " + message.getAuthToken());

        Global.getClient().setAuth((Auth) message.getCarry().get(1));
        Account account= (Account) message.getCarry().get(0);
        Global.setFirstAccount(account);
        Global.hasLoggedIn = true;
        SignInMenu.getMenu().setAccount(account);
        MenuHandler.setAccount(account);

    }
}
