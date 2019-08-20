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

    @Override
    public void setProbability(int... probability) {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public int[][] play() {
        return new int[0][];
    }

    @Override
    public int getTurn() {
        return 0;
    }
}
