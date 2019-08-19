import java.util.ArrayList;

public class Account {
    private static ArrayList<Account>accounts;

    private int username;
    private String password;

    private Account createAccount(int username,String password){
        if(Account.hasAccount(username))return null;

        Account newAccount=new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        accounts.add(newAccount);
        return newAccount;
    }

    public static Account getAccount(int username){
        for (Account account : accounts) {
            if(account.getUsername()==username)return account;
        }
        return null;
    }
    public static boolean hasAccount(int username){
        return Account.getAccount(username)!=null;
    }

    public int getUsername() {
        return username;
    }
    public void setUsername(int username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
