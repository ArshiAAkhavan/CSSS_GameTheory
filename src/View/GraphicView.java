package View;

//import SignInMenu;
import Controller.Global;
import Controller.menu.*;
import Controller.menu.Battle;
import Controller.menu.MainMenu;
import Controller.menu.SignInMenu;
import Model.account.player.CGI;
import Model.account.player.GGI;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
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
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
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
        //initializing graphics for each menu
        SignInMenu.getMenu().getGraphic().init();
        MainMenu.getMenu().getGraphic().init();
        GameModeMenu.getMenu().getGraphic().init();
        ChooseBattleModeMenu.getMenu().getGraphic().init() ;
        MultiPlayerModeMenu.getMenu().getGraphic().init();
        Battle.getMenu().getGraphic().init();
    }


    private static void setRootPaths() {
        //setting root Path for each menu
        SignInMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/SignInMenu.fxml");
        MainMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/MainMenu.fxml");
        GameModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/GameModeMenu.fxml");
        MultiPlayerModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/MultiPlayerModeMenu.fxml");
        ChooseBattleModeMenu.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/ChooseBattleModeMenu.fxml");
        Battle.getMenu().getGraphic().setRootPath("Controller/menu/Graphics/FXMLs/Battle.fxml");
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
