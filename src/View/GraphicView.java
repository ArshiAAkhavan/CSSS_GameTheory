package View;


import Controller.Global;
import Controller.menu.*;
import Model.account.player.CGI;
import Model.account.player.GGI;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class GraphicView extends Application implements View{

    private static Stage stage;
    private static Rectangle2D primaryScreenBounds ;


    public static void setScene(Scene scene) {
        GraphicView.stage.setScene(scene);
        stage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        setGIs();
        configStage(primaryStage);
        //TODO -> handle exit button
        stage.setOnCloseRequest(e -> {
            try {
                stop();
            } catch (Exception ignored) {}
        });
        initializeGraphicMenu();
        MenuHandler.startMenus();
        new Thread(() -> {
            while(true){
                MenuHandler.showMenu();
                MenuHandler.nextMove();
            }
        }).start();
    }

    private void configStage(Stage primaryStage) {
        stage = primaryStage;
        primaryScreenBounds = new Rectangle2D(10,10,800,600);
        stage.setFullScreen(false); //TODO make it true in the end !
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setResizable(false);
        stage.setFullScreenExitHint("");
        stage.setOnHiding(event -> System.exit(0));
    }

    private static void initializeGraphicMenu() {
        setRootPaths();
        initGraphics();
        setListeners();
    }

    private static void setListeners() {
    }

    private static void initGraphics() {
        SignInMenu.getMenu().getGraphic().init();
        ChooseBattleModeMenu.getMenu().getGraphic().init();
        NormalModeMenu.getMenu().getGraphic().init();
        FourSetModeMenu.getMenu().getGraphic().init();
    }


    private static void setRootPaths() {
        SignInMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/SignInMenu.fxml");
        ChooseBattleModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/ChooseBattleModeMenu.fxml");
        NormalModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/NormalModeMenu.fxml");
        FourSetModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/FourSetModeMenu.fxml");
    }

    public void play(String...args) {
        launch(args);
    }

    @Override
    public void setGIs() {
        Global.setUserGI(GGI.class);
        Global.setBotGI(CGI.class);
        Global.setDefaultGI(CGI.class);
    }


    public static Stage getStage() {
        return stage ;
    }

    public static Rectangle2D getPrimaryScreenBounds() {
        return primaryScreenBounds;
    }
}
