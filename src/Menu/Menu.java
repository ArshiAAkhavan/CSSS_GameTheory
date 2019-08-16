package Menu;

import java.util.ArrayList;

public class Menu {
    private Menu parentMenu;
    private String name;
    private ArrayList<Menu>subMenu;

    public Menu(Menu parentMenu, String name) {
        this.parentMenu = parentMenu;
        this.name = name;
        subMenu=new ArrayList<>();
    }
    public Menu enter(Menu subMenu){
        subMenu.setParentMenu(this);
        return subMenu;
    }
    public Menu exit(){
        return this.parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }
}
