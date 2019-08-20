package Model.mediator;

import Controller.Global;
import Controller.menu.Battle;
import Controller.menu.Menu;
import Controller.menu.SignInMenu;
import View.MenuHandler;
import exeption.InvalidAccountException;
import exeption.WrongPassException;
import network.ChatMSG;
import network.Message;

import java.io.IOException;
import java.util.ArrayList;

public class OnlineMultiPlayerMenuMediator implements MultiPlayerMenuMediator {

    ArrayList<ChatMSG> chats=new ArrayList<>();

    Thread connectionThread;
    public OnlineMultiPlayerMenuMediator() {
        new Thread(() -> {
            while (true){
                Message message = Global.getChatRoomClient().read();
                ChatMSG chatMSG = (ChatMSG) message.getCarry().get(0);
                chats.add(chatMSG);
            }
        }).start();
    }

    @Override
    public void selectUser(String username, String password) throws InvalidAccountException, WrongPassException, IOException {
        /*username and password are ignored*/

        connectionThread=new Thread(() -> {
            try {
                Global.getBattleClient().connect();

                MenuHandler.enterMenu((Menu) MenuHandler.getGameMode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        connectionThread.start();

    }

    @Override
    public void sendMessage(String text) {
        ChatMSG chat=new ChatMSG(text, SignInMenu.getMenu().getAccount());
        Message message=new Message("Chat!");
        message.addCarry(chat);
        Global.getChatRoomClient().write(message);
    }

    @Override
    public ArrayList<ChatMSG> getChats() {
        return chats;
    }

    @Override
    public void cancel() {
        try {
            connectionThread.interrupt();
        }catch (Throwable arshia){}
        System.err.println("hey yo shit i canceled");
            Global.getBattleClient().close();

        Global.setBattleClient(null);
    }
}
