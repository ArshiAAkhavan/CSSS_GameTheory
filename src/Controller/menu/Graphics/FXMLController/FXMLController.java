package Controller.menu.Graphics.FXMLController;

import Controller.menu.Menu;


public class FXMLController {
    protected Menu menu;
    public void init(){
        /*
        *only called once the FXML is being loaded
        *
        * very few configuration that only concerns the FXMLController and its attribute
        * note that no field is set yet so you may not use them
        * you can only set them
        *
        * not that scenes objects (such as button and labels and....) should not be used in this method cuz they are not
        * yet set
        *
        * if you wish to work with them use the method build scene
        * */

    }
    public void buildScene(){
        /*
        * only called once after the FXML is fully loaded
        *
        * use this method to work with the scenes objects (such as button ,label and ....)
        * */
//        this.menu.getGraphic().getScene().setOnMouseEntered(e -> this.menu.getGraphic().getScene().setCursor(new ImageCursor(new Image(this.menu.getGraphic().getMousePath()))));
//        this.menu.getGraphic().getScene().setOnMouseMoved(e -> this.menu.getGraphic().getScene().setCursor(new ImageCursor(new Image(this.menu.getGraphic().getMousePath()))));
//        this.menu.getGraphic().getScene().setOnMouseClicked(e -> this.menu.getGraphic().getScene().setCursor(new ImageCursor(new Image(this.menu.getGraphic().getMousePath()))));
//
//        menu.getGraphic().getScene().setOnKeyPressed(e -> {
//            if (e.getCode() == KeyCode.ESCAPE){
//                menu.exit();
//            }
//        });
    }

    public void enterScene(){

    }

    public void updateScene(){
        /*
        * called when the current menu is changed to this (every time we enter the menu)
        *
        * use this method for the update you wish to make
        *
        * note that due to your code this method can be used in other functions as well
        * */
    }

    public void initialize(){
        init();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
