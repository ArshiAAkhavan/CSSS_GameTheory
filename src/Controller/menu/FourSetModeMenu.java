package Controller.menu;

public class FourSetModeMenu extends Menu implements Playable {

    private static FourSetModeMenu menu;

    public static FourSetModeMenu getMenu() {
        if(FourSetModeMenu.menu==null){
            FourSetModeMenu.menu=new FourSetModeMenu("FourSetMode");
        }
        return menu;
    }

    private FourSetModeMenu(String name) {
        super(name);
    }

}
