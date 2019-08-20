package Controller.menu.Graphics.FXMLController;

import javafx.scene.control.Label;
import Controller.menu.ChooseBattleModeMenu;


public class ChooseBattleModeMenuFXMLC extends FXMLController {

    public Label normalButton;
    public Label fourSetButton;

    @Override
    public void buildScene() {
        super.buildScene();
        normalButton.setOnMousePressed(event -> ChooseBattleModeMenu.getMenu().setMode(1));
        fourSetButton.setOnMousePressed(event -> ChooseBattleModeMenu.getMenu().setMode(2));
        
    }
}
