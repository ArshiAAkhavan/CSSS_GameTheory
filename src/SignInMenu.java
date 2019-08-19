public class SignInMenu extends Menu {
    public SignInMenu(Menu parentMenu, String name) {
        super(parentMenu, name);
    }

    public void logIn(int username,String password){
        Account account = Account.getAccount(username);
        this.account=account;
    }

}
