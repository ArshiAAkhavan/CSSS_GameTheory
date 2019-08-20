package Model.account;

import Controller.Global;
import Model.account.player.Player;
import Model.mediator.AccountMediator;
import exeption.AccountAlreadyExistsException;
import exeption.InvalidAccountException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Account {

    private static final Account defaultAccount = new Account("Duelyst","SAF","Pass the fucking word");
    private static int unique = 0;
    private static final int INITIAL_MONEY = 99999999;

    private static AccountMediator mediator;


    protected Player player;
    protected String name;
    protected String username;
    protected String password;
    protected int ID;
    private int money;//unit :derik


    public static boolean addNewAccount(Account account) throws AccountAlreadyExistsException {
        try {
            return mediator.addNewAccount(account);
        } catch (AccountAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void save() {
        try {
            mediator.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Account getDefaultAccount(){
        return Account.defaultAccount;
    }
    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.ID = Account.unique++;
        this.money = Account.INITIAL_MONEY;
    }

    public static Account getAccount(String username) throws InvalidAccountException {
        try {
            return mediator.getAccount(username);
        } catch (InvalidAccountException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Account getAccount(int ID) throws InvalidAccountException {
        try {
            return mediator.getAccount(ID);
        } catch (InvalidAccountException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasAccount(String username) {
        try {
            Account.getAccount(username);
            return true;
        } catch (InvalidAccountException e) {
            return false;
        }
    }

    public static ArrayList<Account> getAccounts() {
        System.err.println();
        try {
            return mediator.getAccounts();
        } catch (Exception e) {
            e.printStackTrace();
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
//            return null;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account account = (Account) obj;

        return this.ID == account.ID;
    }


    public Player getPlayer() {
        if (this.player == null){
            this.player = new Player(this, 2, 2);
            Global.setDefaultGI(player);
        }
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static ArrayList<Account> sort() {
        Account.getAccounts().sort(Comparator.comparingInt(o -> o.money));
        Collections.reverse(Account.getAccounts());
        return Account.getAccounts();
    }

    public static void setMediator(AccountMediator mediator) {
        Account.mediator = mediator;
    }
}
