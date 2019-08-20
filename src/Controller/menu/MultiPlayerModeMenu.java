package Controller.menu;

import Model.account.Account;
import Model.mediator.MultiPlayerMenuMediator;
import View.MenuHandler;
import exeption.InvalidAccountException;
import exeption.WrongPassException;
import network.ChatMSG;

import java.io.IOException;
import java.util.ArrayList;

public class MultiPlayerModeMenu extends Menu{
    private static MultiPlayerModeMenu menu;
    private MultiPlayerMenuMediator mediator;

    private MultiPlayerModeMenu(String name) {
        super(name);
    }


    public static MultiPlayerModeMenu getMenu(){
        if(MultiPlayerModeMenu.menu==null){
            MultiPlayerModeMenu.menu=new MultiPlayerModeMenu("MultiPlayer");
        }
        return menu;
    }

    public void selectUser(String username,String password) throws InvalidAccountException, WrongPassException {
        try {
            System.err.println();
            this.mediator.selectUser(username,password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel(){
        this.mediator.cancel();
    }

    public void sendMessage(String text){
        this.mediator.sendMessage(text);
    }

    public ArrayList<ChatMSG> getChats(){
        return this.mediator.getChats();
    }

    @Override
    public void help() {
        super.help();
        System.out.println("4)select user [username] [password]");
    }

    public void setMediator(MultiPlayerMenuMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void exit() {
        this.mediator.cancel();
        super.exit();
    }
}