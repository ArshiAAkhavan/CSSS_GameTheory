
public class MenuHandler {
    private static Menu currentMenu;
    private static Account enemy;

    public static void main(String[] args) {

    }

    private static void enterMenu(Menu menu){
        currentMenu=currentMenu.enter(menu);
    }
    private static void exit(){
        currentMenu=currentMenu.exit();
    }

}
