package Controller.menu.Graphics.FXMLController;

import Controller.menu.SignInMenu;
import exeption.InvalidAccountException;
import exeption.WrongPassException;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class SignInMenuFXMLC extends FXMLController {

    public TextField userNameField;
    public PasswordField passwordField;
    public Button signInButton;
    public Button cancelButton;
    public Label errorLabel;

    @Override
    public void buildScene() {
        super.buildScene();
        userNameField.setText("uzumaki");
        passwordField.setText("1");
        signInButton.setOnMousePressed(event -> {
            System.err.println("hey");
            try {
                if(userNameField.getText().isEmpty()){
                    error("Please enter your username.");
                }
                else if(passwordField.getText().isEmpty()){
                    error("Please enter your password.");
                }
                else {
                    SignInMenu.getMenu().logIn(userNameField.getText(), passwordField.getText());
                }
            } catch (InvalidAccountException e) {
                error("Sorry! This account doesn't exist.");
            } catch (WrongPassException e) {
                error("Wrong Password!");
            }
        });
        cancelButton.setOnMousePressed(event -> {
            userNameField.clear();
            passwordField.clear();
        });
    }

    private void error(String error){
        errorLabel.setText(error);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), errorLabel);
        fadeTransition.play();
        fadeTransition.setOnFinished(event -> errorLabel.setText(null));
    }
}
