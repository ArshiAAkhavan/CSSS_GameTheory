package Controller.menu.Graphics.FXMLController;

import Controller.Global;
import Model.account.player.OnlinePlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Controller.menu.NormalModeMenu;

public class NormalModeMenuFXMLC extends FXMLController {

    public TextField firstProbability;
    public TextField secondProbability;
    public Label payoff1;
    public Label payoff2;
    public Label payoff3;
    public Label payoff4;
    public Label firstTeam;
    public Label secondTeam;
    public Label firstScore;
    public Label secondScore;
    public Button playButton;
    public Label result1;
    public Label result2;
    public Label result3;
    public Label result4;

    @Override
    public void enterScene() {
        super.enterScene();

        int[][] table = NormalModeMenu.getMenu().getTable();
        payoff1.setText(Integer.toString(table[0][0]));
        payoff2.setText(Integer.toString(table[0][1]));
        payoff3.setText(Integer.toString(table[1][0]));
        payoff4.setText(Integer.toString(table[1][1]));

        firstTeam.setText(Global.getAccount(0).getUsername());
        secondTeam.setText(Global.getAccount(1).getUsername());

        firstScore.setText(Integer.toString(Global.getAccount(0).getPlayer().getScore()));
        secondScore.setText(Integer.toString(Global.getAccount(1).getPlayer().getScore()));

        playButton.setOnMousePressed(event -> {
            if(!(NormalModeMenu.getMenu().getPlayer(NormalModeMenu.getMenu().getTurn()) instanceof OnlinePlayer)){
                if(NormalModeMenu.getMenu().getTurn()%2 == 0) {
                    NormalModeMenu.getMenu().setProbability(Integer.parseInt(firstProbability.getText()));
                }
                else {
                    NormalModeMenu.getMenu().setProbability(Integer.parseInt(secondProbability.getText()));
                }
                NormalModeMenu.getMenu().play();
            }
        });
    }

    @Override
    public void updateScene() {
        super.updateScene();

        firstScore.setText(Integer.toString(Global.getAccount(0).getPlayer().getScore()));
        System.err.println(Global.getAccount(0).getPlayer().getScore());
        secondScore.setText(Integer.toString(Global.getAccount(1).getPlayer().getScore()));
        System.err.println(Global.getAccount(1).getPlayer().getScore());

        if(NormalModeMenu.getMenu().getTurn()%2 == 0){
            firstTeam.getStyleClass().clear();
            firstTeam.getStyleClass().addAll("labell", "scoreBoard", "teamTurn");
            secondTeam.getStyleClass().clear();
            secondTeam.getStyleClass().addAll("labell", "scoreBoard", "team");
        }
        else {
            secondTeam.getStyleClass().clear();
            secondTeam.getStyleClass().addAll("labell", "scoreBoard", "teamTurn");
            firstTeam.getStyleClass().clear();
            firstTeam.getStyleClass().addAll("labell", "scoreBoard", "team");
        }
    }

    public void showResults(float[][] results) {
        setResultStyle(result1, results[0][0]);
        setResultStyle(result2, results[0][1]);
        setResultStyle(result3, results[1][0]);
        setResultStyle(result4, results[1][1]);
    }

    private void setResultStyle(Label result , float number){
        float a = ((int) (number * 10))/10.0f;
        result.setStyle("-fx-background-radius: 20%;\n" +
                "-fx-font-size: 30;\n" +
                "-fx-text-fill: rgba(255, 255, 255, "+a+");\n" +
                "-fx-background-color: rgba(255, 0, 0, "+a+");"+
                "-fx-border-width: 4px;\n" +
                "-fx-border-color: rgba(255, 255, 255, "+a+");\n" +
                "-fx-border-radius: 20%;");
        result.setText(Float.toString(number));
    }
}
