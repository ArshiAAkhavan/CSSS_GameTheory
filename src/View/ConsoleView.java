package View;



import Controller.Global;
import Model.account.player.CGI;

public class ConsoleView implements View{
    @Deprecated
    protected CommandHandler commandHandler;
    protected ConsoleOutput consoleOutput=ConsoleOutput.getInstance();
    @Override
    public void play(String... args) {
        setGIs();
        MenuHandler.startMenus();
        while(true){
            System.err.println("give me");
 //           MenuHandler.showMenu();
            MenuHandler.nextMove();
            System.err.println("debug");
        }

    }

    @Override
    public void setGIs() {
        Global.setBotGI(CGI.class);
        Global.setUserGI(CGI.class);
        Global.setDefaultGI(CGI.class);
    }
}
