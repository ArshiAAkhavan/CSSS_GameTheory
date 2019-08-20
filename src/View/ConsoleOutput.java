package View;

import Controller.menu.*;
import Model.account.Account;
import View.Listeners.OnMenuClickedListener;


class ShowMenu implements OnMenuClickedListener {
    @Override

    public void show(Menu menu) {
        System.out.println(menu.getName()+" :");
        int i=0;
        for (Menu subMenu : menu.getSubMenus()) {
            i++;
            System.out.println(i+") "+subMenu.getName());
        }
    }
}

public class ConsoleOutput {

    private static ConsoleOutput instance;

    public static ConsoleOutput getInstance(){
        if(instance==null)instance=new ConsoleOutput();
        return instance;
    }

    private ConsoleOutput() {
        setListener();
    }

    private static void setListener(){
        //show menu
        {
            SignInMenu.getMenu().addMenuClickListener(new ShowMenu());
            Battle.getMenu().addMenuClickListener(new ShowMenu());
            ChooseBattleModeMenu.getMenu().addMenuClickListener(menu -> {
                System.out.println("Modes:");
                System.out.println("1)normal mode     2)4 set game mode ");
            });
            GameModeMenu.getMenu().addMenuClickListener(new ShowMenu());
            MainMenu.getMenu().addMenuClickListener(new ShowMenu());
            MultiPlayerModeMenu.getMenu().addMenuClickListener(menu -> {
                System.out.println("Accounts: ");
                int i=0;
                for (Account account : Account.getAccounts()) {
                    i++;
                    System.out.println("\t"+i+") UserName:"+account.getUsername()+"\tName: "+account.getName());
                }
            });

        }
    }
}
