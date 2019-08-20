package View;
import Controller.menu.*;
import exeption.*;

public class CommandHandler {

//    static {
//        setPatterns();
//    }

    public CommandHandler() {

    }



    //commandHandler
    public  void handleCommand(String command) {
        command=command.toLowerCase();
        System.out.println("command = " + command);
        try {
            String[] word = command.toLowerCase().split(" ");
            if (commonCommandHandler(word)) {

            } else if (MenuHandler.getCurrentMenu() instanceof SignInMenu) {
                SignInMenuCommandHandler(word);
            }else if(MenuHandler.getCurrentMenu() instanceof Battle){
                BattleCommandHandler(word,command);
            }else if(MenuHandler.getCurrentMenu() instanceof ChooseBattleModeMenu){
                ChooseBattleModeMenuCommandHandler(word);
            }else if(MenuHandler.getCurrentMenu() instanceof MultiPlayerModeMenu){
                MultiPlayerMenuCommandHandler(word);
            }else if(MenuHandler.getCurrentMenu() instanceof NormalModeMenu){
                NormalModeMenu menu= (NormalModeMenu) MenuHandler.getCurrentMenu();
                if(word[0].equals("set") && word[1].equals("moves")){
                    menu.setProbability(Integer.parseInt(word[2]));
                }else if(word[0].equals("end") && word[1].equals("turn")){
                    menu.endTurn();
                }else if(word[0].equals("play")){
                    int[][] results = menu.play();
                    for(int i=0;i<2;i++){
                        for(int j=0;j<2;j++){
                            System.out.print(results[i][j]+" ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            }
        }
        catch (Exception e){
            System.err.println("exceptions accrued while running");
            e.printStackTrace();
        }
    }

    private static boolean commonCommandHandler(String[] word) {
        /*common commands*/
        if(word[0].equals("help")){
            MenuHandler.getCurrentMenu().help();
            return true;
        }
        else if(word.length >= 2 && word[0].equals("show") && word[1].equals("menu")){
            MenuHandler.getCurrentMenu().showMenu();
            return true;
        }else if(word[0].equals("enter")){
            try {

                try {
                    MenuHandler.enterMenu(MenuHandler.getCurrentMenu().enter(MenuHandler.getCurrentMenu().getMenuFromSubMenus(word[1])));
                } catch (InvalidSubMenuException e) {
                    System.out.println("the requested menu doesnt exist");
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("please choose a number between 1 and " + MenuHandler.getCurrentMenu().getSubMenus().size());
            }
            return true;
        }else if(word[0].equals("exit")){
            if(MenuHandler.getCurrentMenu().getParentMenu()==null) System.out.println("This is the root menu!");
            else {
                MenuHandler.getCurrentMenu().exit();
                return true;
            }
        }
        return false;
    }


    private static void MultiPlayerMenuCommandHandler(String[] word) {
        MultiPlayerModeMenu menu= (MultiPlayerModeMenu) MenuHandler.getCurrentMenu();
        System.err.println("debug");
        if(word[0].equals("select") && word[1].equals("user")){
            try {
                System.err.println("debug");
                menu.selectUser(word[2],word[3]);
            } catch (InvalidAccountException e) {
                System.out.println("this account doesnt exist");
            } catch (WrongPassException e) {
                System.out.println("username and pass word dont match try again");
            }
        }
    }
    private static void SignInMenuCommandHandler(String[] word) {
        SignInMenu menu= (SignInMenu) MenuHandler.getCurrentMenu();
        if(word[0].equals("create") && word[1].equals("account")){
            try {
                menu.creatAccount(word[2],word[3],word[4]);
            } catch (AccountAlreadyExistsException e) {
                System.out.println("this userName is already taken");
            } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("please enter in the fallowing order");
                System.out.println("1)name     2)username      3)password");
            } catch (WrongPassException e) {
                System.out.println("Wrong Password");
            } catch (InvalidAccountException e) {
                System.out.println("seems like your account doesnt exist");
            }
        }else if(word[0].equals("login")){
            try {
                menu.logIn(word[1],word[2]);
            } catch (InvalidAccountException e) {
                System.out.println("Account doesnt exist");
            } catch (WrongPassException e) {
                System.out.println("username and password doesnt match try again");
            } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("please enter in the fallowing order");
                System.out.println("1)username      2)password");
            }
        }else if(word[0].equals("show") && word[1].equals("leaderboard")){
            menu.showLeaderBoard();

        }else if(word[0].equals("save")){
            menu.save();
        }else if(word[0].equals("logout")){
            menu.logOut();
        }
    }
    private static void ChooseBattleModeMenuCommandHandler(String[] word) {
        ChooseBattleModeMenu menu= ChooseBattleModeMenu.getMenu();
        if(word[0].equals("mode")){
            menu.setMode(Integer.parseInt(word[1]));
        }
    }
    private static void BattleCommandHandler(String[] word,String command) {
        Battle menu = (Battle) MenuHandler.getCurrentMenu();

//        menu.getMatch().addCommand(command,menu.getTurn());

        System.err.println("word:");
        for (int i = 0; i < word.length; i++) {
            System.err.print(word[i]);
        }

        if(word[0].equals("end") && word[1].equals("turn")){
             System.err.println("end turn");
             menu.endTurn();
        }
    }

//    public static void setPatterns(){
//        setChooseBattleModePattern();
//        setSignInPatterns();
//        setBattlePatterns();
//        setMainMenuPattern();
//        setGameModeMenuPatterns();
//        setMultiPlayerModeMenuPattern();
//    }
//    private static void setMultiPlayerModeMenuPattern() {
//        MultiPlayerModeMenu.getMenu().addPattern("enter [\\w]+");
//        MultiPlayerModeMenu.getMenu().addPattern("[\\d]+");
//        MultiPlayerModeMenu.getMenu().addPattern("help");
//        MultiPlayerModeMenu.getMenu().addPattern("show");
//        MultiPlayerModeMenu.getMenu().addPattern("exit");
//        MultiPlayerModeMenu.getMenu().addPattern("select user [\\w]+ [\\w]+");
//    }
//    public static void setSignInPatterns() {
//        SignInMenu.getMenu().addPattern("enter [\\w]+");
//        SignInMenu.getMenu().addPattern("[\\d]+");
//        SignInMenu.getMenu().addPattern("help");
//        SignInMenu.getMenu().addPattern("show");
//        SignInMenu.getMenu().addPattern("exit");
//        SignInMenu.getMenu().addPattern("create account [\\w]+ [\\w]+ [\\w]+");
//        SignInMenu.getMenu().addPattern("login [\\w]+ [\\w]+");
//        SignInMenu.getMenu().addPattern("show leaderboard");
//        SignInMenu.getMenu().addPattern("save");
//        SignInMenu.getMenu().addPattern("logout");
//    }
//    public static void setBattlePatterns(){
//        Battle.getMenu().addPattern("enter [\\w]+");
//        Battle.getMenu().addPattern("[\\d]+");
//        Battle.getMenu().addPattern("help");
//        Battle.getMenu().addPattern("exit");
//        Battle.getMenu().addPattern("show");
//        Battle.getMenu().addPattern("game info");
//        Battle.getMenu().addPattern("show hand");
//        Battle.getMenu().addPattern("show my minions");
//        Battle.getMenu().addPattern("show opponent minions");
//        Battle.getMenu().addPattern("show card info [\\d]+");
//        Battle.getMenu().addPattern("show next card");
//        Battle.getMenu().addPattern("show collectables");
//        Battle.getMenu().addPattern("show info");
//        Battle.getMenu().addPattern("select [\\d]+");
//        Battle.getMenu().addPattern("move to [\\d]+ [\\d]+");
//        Battle.getMenu().addPattern("attack [\\d]+");
//        Battle.getMenu().addPattern("attack combo [\\d]+ [\\d]+[ \\d+]+");
//        Battle.getMenu().addPattern("use special power [\\d]+ [\\d]+");
//        Battle.getMenu().addPattern("insert [\\w]+ in [\\d]+ [\\d]+");
//        Battle.getMenu().addPattern("end turn");
//        Battle.getMenu().addPattern("use \\[[\\d+] [\\d]+\\]");
//        Battle.getMenu().addPattern("select [\\d]+");
//        Battle.getMenu().addPattern("enter graveyard");
//        Battle.getMenu().addPattern("help");
//        Battle.getMenu().addPattern("end game");
//    }
//    public static void setMainMenuPattern(){
//        MainMenu.getMenu().addPattern("[\\d]+");
//        MainMenu.getMenu().addPattern("enter [\\w]+");
//        MainMenu.getMenu().addPattern("help");
//        MainMenu.getMenu().addPattern("show");
//        MainMenu.getMenu().addPattern("exit");
//    }
//    public static void setChooseBattleModePattern(){
//        ChooseBattleModeMenu.getMenu().addPattern("[\\d]+");
//        ChooseBattleModeMenu.getMenu().addPattern("enter [\\w]+");
//        ChooseBattleModeMenu.getMenu().addPattern("help");
//        ChooseBattleModeMenu.getMenu().addPattern("show");
//        ChooseBattleModeMenu.getMenu().addPattern("exit");
//        ChooseBattleModeMenu.getMenu().addPattern("mode [\\d]+");
//    }
//    public static void setGameModeMenuPatterns(){
//        GameModeMenu.getMenu().addPattern("[\\d]+");
//        GameModeMenu.getMenu().addPattern("enter [\\w]+");
//        GameModeMenu.getMenu().addPattern("help");
//        GameModeMenu.getMenu().addPattern("show");
//        GameModeMenu.getMenu().addPattern("exit");
//
//    }

    private static String getName(String[] word , int startPoint) {
        StringBuilder name = new StringBuilder();
        for (int i = startPoint ; i < word.length ; i++){
            name.append(word[i]).append(" ");
        }
        return name.substring(0, name.length() - 1);
    }
}
