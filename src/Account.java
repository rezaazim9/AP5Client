import java.io.File;
import java.util.ArrayList;

public class Account {
    String name;
    String password;
    ArrayList<File> files;
    public  static ArrayList<Account> accounts = new ArrayList<>();
    public Account(String name, String password, ArrayList<File> files) {
        this.name = name;
        this.files= files;
        this.password = password;
    }
}
