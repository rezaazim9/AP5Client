package Model;

import java.io.File;
import java.util.ArrayList;

public class Account {

    public static ArrayList<Account> accounts = new ArrayList<>();
    public String name;
    public String password;
   public ArrayList<File> files;
    public Account(String name, String password, ArrayList<File> files) {
        this.name = name;
        this.files= files;
        this.password = password;
    }
}
