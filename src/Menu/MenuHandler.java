package Menu;

import Menu.Menu;

public class MenuHandler {
    private static Menu currentMenu;

    public static void main(String[] args) {

    }

    private static void enterMenu(Menu menu){
        currentMenu=currentMenu.enter(menu);
    }
    private static void exit(){
        currentMenu=currentMenu.exit();
    }

}
