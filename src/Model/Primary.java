package Model;
import Model.account.Account;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.JsonElement;
import com.gilecode.yagson.com.google.gson.JsonStreamParser;
import exeption.*;

import java.io.*;
import java.util.ArrayList;

public class Primary {

    public static ArrayList<String> defaultNames = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();

    public static void getAccounts() throws IOException {
        YaGson gson = new YaGson();
        BufferedReader reader = new BufferedReader(new FileReader("Account.json"));
        JsonStreamParser jsonStreamParser = new JsonStreamParser(reader);
        if(jsonStreamParser.hasNext()) {
            while (jsonStreamParser.hasNext()) {
                JsonElement jsonElement = jsonStreamParser.next();
                if (jsonElement.isJsonObject()) {
                    accounts.add(gson.fromJson(jsonElement, Account.class));
                }
            }
        }
        reader.close();
    }
    public static void configAccounts() throws IOException{
        getAccounts();
    }

    public static void saveAccounts(){
        YaGson gson = new YaGson();
        File file = new File("Account.json");
        file.delete();
        for (Account account:
                accounts) {
            try{
                FileWriter fileWriter = new FileWriter("Account.json", true);
                account.setPlayer(null);
                gson.toJson(account, fileWriter);
                fileWriter.write("\n");
                fileWriter.close();
            } catch (IOException ignored) {}
        }
    }

    private static <E> void writeJson(ArrayList<E> arrays, String path) throws IOException {
        YaGson gson = new YaGson();
        FileWriter fileWriter = new FileWriter(path, false);
        for (E e:
                arrays) {
            gson.toJson(e, fileWriter);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
    private static void writeSingle(Object obj, String path) throws IOException {
        YaGson gson = new YaGson();
        FileWriter fileWriter = new FileWriter(path, false);
        gson.toJson(obj, fileWriter);
        fileWriter.write("\n");
        fileWriter.close();
    }


}