package Controller.menu.Graphics.FXMLController;

import Controller.menu.Battle;
import Controller.menu.*;
import Controller.menu.Graphics.GraphicsControls;
import Controller.menu.SignInMenu;
import Model.account.player.GGI;
import View.MenuHandler;
import exeption.*;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.nio.file.Paths;

public class BattleFXMLC extends FXMLController {

    @FXML
    public Button endTurn;
    public Button graveYard;
    public Button menuButton;
    public Button showCollectableButton;
    public GridPane playerMana;
    public GridPane enemyMana;
    public GridPane handInfo;
    public GridPane showCollectable;
    public ImageView firstPlayer;
    public ImageView secondPlayer;
    public ImageView nextCardOnHand;
    public ImageView ownSP;
    public ImageView opponentSP;
    public Label ownPlayerInfo;
    public Label opponentPlayerInfo;
    public Label errorLable;
    public TextField nextCardOnHandInfo;
    public ImageView firstPlayerTurn;
    public ImageView secondPlayerTurn;
    public HBox mapBox;
    public AnchorPane frame;
    public GridPane map;
    public GridPane handFrame;
    public GridPane handManaFrame;
    private MediaPlayer mediaPlayer ;

    @Override
    public void init() {//setStyles
        super.init();
        for(int i = 0 ; i < 9 ; i++){
            for (int j = 0 ; j < 5 ; j++){
                Rectangle rectangle = getRectangle(i, j);
                ImageView imageView = getCell(i , j);
                GraphicsControls.setCellStyle("cell", rectangle, imageView);
            }
        }
        GraphicsControls.setButtonStyle("endTurnButton", endTurn);
        GraphicsControls.setButtonStyle(".battleMenuButton", menuButton);
        GraphicsControls.setButtonStyle(".graveYardButton", graveYard);
        GraphicsControls.setButtonStyle("showCollectableButton", showCollectableButton);
    }

    @Override
    public void buildScene() {

        super.buildScene();
        endTurn.setOnAction(actionEvent -> {
            if(Battle.getMenu().getPlayer().getGI() instanceof GGI) {
                    Battle.getMenu().endTurn();
                    updateScene();

            }
        });
        endTurn.setDisable(false);
        menuButton.setOnAction(e -> {
            if(Battle.getMenu().getPlayer().getGI() instanceof GGI) {
                menu.enter(MainMenu.getMenu());
            }
        });


        showCollectableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Battle.getMenu().getPlayer().getGI() instanceof GGI) {
                    showCollectable.getStyleClass().add("showCollectableEntered");
                    updateScene();
                }
            }
        });

        showCollectable.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCollectable.getStyleClass().remove("showCollectableEntered");
                showCollectable.getChildren().clear();
                updateScene();
            }
        });

        try {
            Media music = new Media(Paths.get("resources/music/music_battlemap_risensun2.m4a").toUri().toString());
            mediaPlayer = new MediaPlayer(music);
            mediaPlayer.setCycleCount(-1);
            MediaView mediaView = new MediaView(mediaPlayer);
            frame.getChildren().add(mediaView);
        }catch (Exception ignored){
            System.err.println("couldn't load the music file");
        }

    }

    @Override
    public void enterScene() {
        super.enterScene();
        try {
            ((SignInMenuFXMLC)SignInMenu.getMenu().getGraphic().getController()).playMusic(false);
            mediaPlayer.play();
        }catch (Exception e){
            System.err.println("couldnt load the music");
        }
        try {
            //
            //
        }catch (Exception e){
            System.err.println("couldn't load the images for players !");
        }
        updateScene();
    }

    @Override
    public void updateScene() {
        super.updateScene();
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                updateTurn();
            }
        });
    }

    public void finish(){
        ImageView black = new ImageView("resources/play/play_background.jpg");
        black.setFitWidth(frame.getWidth());
        black.setFitHeight(frame.getHeight());
        addToScene(black);
        ImageView endGameBack = new ImageView("resources/play/play_mode_rift@2x.jpg");
        Label endGame = new Label();
        addToScene(endGameBack);
        addToScene(endGame);
        endGame.setFont(Font.font("Didot", 40.0));
        endGame.setTextAlignment(TextAlignment.CENTER);
        endGameBack.setFitHeight(frame.getMinHeight());
        endGameBack.setX(frame.getWidth()/ 4);
        endGame.setMinWidth(frame.getMinWidth());
        endGame.setMinHeight(frame.getMinHeight());
        endGame.setLayoutX(frame.getWidth()/3);
        endGame.setLayoutY(frame.getHeight()/1.5);
        if(Battle.getMenu().getAccount().equals(Battle.getMenu().winner)){
            endGame.setTextFill(Color.rgb(255, 255, 255));
            endGame.setText("Congrats! YOU WON!");
        }
        else {
            endGame.setTextFill(Color.rgb(0, 0, 0));
            endGame.setText("Ooops! YOU LOST!");
        }
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(5));
        pauseTransition.play();
        pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuHandler.enterMenu(MainMenu.getMenu());
            }
        });
    }

    private void updateTurn() {
        if (Battle.getMenu().getTurn() % 2 == 0) {
            firstPlayerTurn.setImage(new Image("resources/profile_icons/borders/gold@2x.png"));
            secondPlayerTurn.setImage(new Image("resources/profile_icons/borders/silver@2x.png"));
        } else {
            secondPlayerTurn.setImage(new Image("resources/profile_icons/borders/gold@2x.png"));
            firstPlayerTurn.setImage(new Image("resources/profile_icons/borders/silver@2x.png"));
        }
    }
    private void error(String error){
        errorLable.setText(error);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), errorLable);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLable.setText(null);
            }
        });
    }

    public ImageView getCell(int x , int y){
        for (Node node : map.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                if(node instanceof ImageView)
                    return (ImageView) node;
            }
        }
        return null;
    }
    public Rectangle getRectangle(int x, int y){
        for(Node node : map.getChildren()){
            if(GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y){
                if(node instanceof Rectangle){
                    return (Rectangle) node;
                }
            }
        }
        return null;
    }
    public void addToScene(Node... nodes) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                frame.getChildren().addAll(nodes);
            }
        });
    }
    public void removeFromScene(Node... nodes) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                frame.getChildren().removeAll(nodes);
            }
        });
    }
    public void playMusic(boolean f){
        if (f){
            try{
                mediaPlayer.play();
            }catch(Exception ignored){}
        } else {
            try{
                mediaPlayer.pause();
            }catch(Exception ignored){}
        }
    }

    private double getMapX(){
        return mapBox.getLayoutX() + 70;
    }
    private double getMapY(){
        return mapBox.getLayoutY();
    }
    private double getMapWidth() {
        return map.getWidth();
    }
    public double getCellWidth(){
        return getMapWidth()/9;
    }
    private double getMapHeight(){
        return map.getHeight();
    }
    public double getCellHeight(){
        return getMapHeight()/5;
    }
    public double getX(int x){
        return getMapX() + ( x + .5 ) * getCellWidth();
    }
    public double getY(int y){
        return getMapY() + (y + .5) * getCellHeight();
    }
}