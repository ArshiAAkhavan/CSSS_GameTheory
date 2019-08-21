package Controller.menu.Graphics.FXMLController;

import Controller.Global;
import Controller.menu.FourSetModeMenu;
import Model.account.player.OnlinePlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import Controller.menu.FourSetModeMenu;

public class FourSetModeMenuFXMLC extends FXMLController {

    public Label firstTeam;
    public Label secondTeam;
    public Label firstScore;
    public Label secondScore;
    public Button playButton;
    public Button firstProbButton;
    public Button secondProbButton;
    public Label payoff1;
    public Label payoff2;
    public Label payoff3;
    public Label payoff4;
    public VBox firstProb;
    public VBox secondProb;
    public Label result1;
    public Label result2;
    public Label result3;
    public Label result4;


    @Override
    public void enterScene() {
        super.enterScene();

        firstTeam.setText(Global.getAccount(0).getUsername());
        secondTeam.setText(Global.getAccount(1).getUsername());

        firstScore.setText(Integer.toString(Global.getAccount(0).getPlayer().getScore()));
        secondScore.setText(Integer.toString(Global.getAccount(1).getPlayer().getScore()));

        playButton.setOnMousePressed(event -> {
            setProbability(FourSetModeMenu.getMenu().getRound());
            FourSetModeMenu.getMenu().play();

        });
    }

    public void showResults(float[][] results) {
        setResultStyle(result1, results[0][0]);
        setResultStyle(result2, results[0][1]);
        setResultStyle(result3, results[1][0]);
        setResultStyle(result4, results[1][1]);
    }

    private void setResultStyle(Label result, float number) {
        float a = ((int) (number * 10)) / 10.0f;
        result.setStyle("-fx-background-radius: 20%;\n" +
                "-fx-font-size: 30;\n" +
                "-fx-text-fill: rgba(255, 255, 255, " + a + ");\n" +
                "-fx-background-color: rgba(255, 0, 0, " + a + ");" +
                "-fx-border-width: 4px;\n" +
                "-fx-border-color: rgba(255, 255, 255, " + a + ");\n" +
                "-fx-border-radius: 20%;");
        result.setText(Float.toString(number));
    }

    @Override
    public void updateScene() {
        super.updateScene();
        firstScore.setText(Integer.toString(Global.getAccount(0).getPlayer().getScore()));
        secondScore.setText(Integer.toString(Global.getAccount(1).getPlayer().getScore()));
        if (FourSetModeMenu.getMenu().getTurn() % 2 == 0) {
            firstTeam.getStyleClass().add("teamTurn");
            secondTeam.getStyleClass().remove("teamTurn");
        } else {
            secondTeam.getStyleClass().add("teamTurn");
            firstTeam.getStyleClass().remove("teamTurn");
        }
    }

    private void setProbability(int round) {
        if (!(FourSetModeMenu.getMenu().getPlayer(0) instanceof OnlinePlayer)) {
            setProb((GridPane) firstProb.getChildren().get(round));
        } else {
            setProb((GridPane) secondProb.getChildren().get(round));
        }
    }

    private void setProb(GridPane gridPane) {
        int[] probs = new int[6];
        for (int i = 0; i < 6; i++) {
            Label label = (Label) gridPane.getChildren().get(i);
            if (label.getText().equals("F")) probs[i] = 100;
            else if (label.getText().equals("S")) probs[i] = 0;
            else probs[i] = 100;
        }
        FourSetModeMenu.getMenu().setProbability(probs);
    }

//    private void getProbability() {
//        if (!(FourSetModeMenu.getMenu().getPlayer(0) instanceof OnlinePlayer)) {
//            fillProb(firstProb);
//        } else {
//            fillProb(secondProb);
//        }
//    }

    private boolean isEmpty(GridPane gridPane) {
        for (int i = 0; i < 6; i++) {
            Label label = (Label) gridPane.getChildren().get(i);
            if (label.getText().isEmpty()) return true;
        }
        return false;
    }

    private void fillProb() {
        firstProbButton.setOnMousePressed(event -> {

            VBox box;
            int turn = FourSetModeMenu.getMenu().getTurn();
            if (FourSetModeMenu.getMenu().getPlayer(turn) instanceof OnlinePlayer) {
                if (turn == 1) box = firstProb;
                else box = secondProb;
            } else {
                if (turn == 0) box = firstProb;
                else box = secondProb;
            }
            int i = FourSetModeMenu.getMenu().getRound();
            GridPane gridPane = (GridPane) box.getChildren().get(i);
            for (int j = 0; j < 6; j++) {
                Label label = (Label) gridPane.getChildren().get(i);
                if (label.getText().isEmpty()) label.setText("F");
            }
        });
        secondProbButton.setOnMousePressed(event -> {
            VBox box;
            int turn = FourSetModeMenu.getMenu().getTurn();
            if (FourSetModeMenu.getMenu().getPlayer(turn) instanceof OnlinePlayer) {
                if (turn == 1) box = firstProb;
                else box = secondProb;
            } else {
                if (turn == 0) box = firstProb;
                else box = secondProb;
            }
            int i = FourSetModeMenu.getMenu().getRound();
            GridPane gridPane = (GridPane) box.getChildren().get(i);
            for (int j = 0; j < 6; j++) {
                Label label = (Label) gridPane.getChildren().get(i);
                if (label.getText().isEmpty()) label.setText("S");
            }
        });
    }

    @Override
    public void buildScene() {
        super.buildScene();
        fillProb();
    }
}
