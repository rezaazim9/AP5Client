package Model;

import java.util.ArrayList;

public class Account {

    public static ArrayList<Account> accounts = new ArrayList<>();
    public String name;
    public String password;
   public ArrayList<RFile> files;
    public Account(String name, String password, ArrayList<RFile> files) {
        this.name = name;
        this.files= files;
        this.password = password;
    }
}
