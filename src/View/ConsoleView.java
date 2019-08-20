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
            MenuHandler.showMenu();
            MenuHandler.nextMove();
        }

    }

    @Override
    public void setGIs() {
        Global.setBotGI(CGI.class);
        Global.setUserGI(CGI.class);
        Global.setDefaultGI(CGI.class);
    }
}
