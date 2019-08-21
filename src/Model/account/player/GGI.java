package Model.account.player;


import Controller.menu.FourSetModeMenu;

public class GGI implements GameInterFace {
    private Player player;
    @Override
    public void intervene() {
        try {
            System.out.println("turn: "+FourSetModeMenu.getMenu().getTurn());
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPlayer(Player player) {
        this.player=player;
    }
}
