package Controller.menu;

import Controller.Global;
import Model.mediator.SignInMenuMediator;
import Model.account.Account;
import View.MenuHandler;
import exeption.AccountAlreadyExistsException;
import exeption.InvalidAccountException;
import exeption.WrongPassException;

public class SignInMenu extends Menu {

    private static SignInMenu menu;

    private Account temporaryAccount;
    private SignInMenuMediator signInMenuMediator;

    private SignInMenu(String name) {
        super(name);
    }


    public static SignInMenu getMenu(){
        if(SignInMenu.menu==null){
            SignInMenu.menu=new SignInMenu("SignInMenu");
        }
        return menu;
    }

    @Override
    public Menu enter(Menu subMenu) {
        if(this.account==null){
            System.out.println("no account has been signed in yet");
            return this;
        }
        return super.enter(subMenu);
    }

    public void creatAccount(String name, String username, String password) throws AccountAlreadyExistsException, InvalidAccountException, WrongPassException {

        if(!Account.addNewAccount(new Account(name, username, password)))return;
            logIn(username , password);
}

    public void logIn(String username, String password) throws InvalidAccountException, WrongPassException {
        try {
            signInMenuMediator.logIn(username,password);
        }catch (InvalidAccountException |WrongPassException e){
          throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        MenuHandler.enterMenu(SignInMenu.getMenu().enter(ChooseBattleModeMenu.getMenu()));
    }

    public void logOut() {
        Global.hasLoggedIn = false;
        this.account=null;
        MenuHandler.setAccount(null);
    }

    public void save() {
    }

    public void showLeaderBoard() {
        Account.sort();
    }
    @Override
    public void help() {
        super.help();
        System.out.println("4)create account [name] [user name] [password]     5)login [user name] [password]    6)show leaderboard");
        System.out.println("5)save     6)logout    ");
    }

    public void setSignInMenuMediator(SignInMenuMediator signInMenuMediator) {
        this.signInMenuMediator = signInMenuMediator;
    }
}
