package Controller.menu;

//it is finished


import View.MenuHandler;
import exeption.InvalidAccountException;
import exeption.WrongPassException;

public class ChooseBattleModeMenu extends Menu {
    private static ChooseBattleModeMenu menu;
    private ChooseBattleModeMenu( String name) {
        super(name);
    }


    public static ChooseBattleModeMenu getMenu() {
        if(ChooseBattleModeMenu.menu==null){
            ChooseBattleModeMenu.menu=new ChooseBattleModeMenu("BattleMode");
        }
        return menu;
    }


    public void setMode(int mode) {

        switch (mode){
            case 1:
                MenuHandler.setGameMode(NormalModeMenu.getMenu());
                break;
            case 2:

                MenuHandler.setGameMode(FourSetModeMenu.getMenu());
                break;
            default:
                System.out.println("please Enter a Number between 1 and 2");
        }
        try {

            MultiPlayerModeMenu.getMenu().selectUser("chert","chert");
        } catch (InvalidAccountException | WrongPassException ignored) {
        }
    }

    @Override
    public void help() {
        super.help();
        System.out.println("4)mode [mode number]");
    }
}
