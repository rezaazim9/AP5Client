package Model;

import java.util.ArrayList;

public class Account {

    public static ArrayList<Account> accounts = new ArrayList<>();
    public String name;
    public String password;
    public JWT jwt;
    public ArrayList<RFile> files;

    public Account(String name, String password, ArrayList<RFile> files, JWT jwt) {
        this.name = name;
        this.files = files;
        this.password = password;
        this.jwt = jwt;
    }
}
