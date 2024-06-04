package Model;

import java.io.File;

public class ClientThreadUpload extends Thread {
    File file;
    Account account;

    public ClientThreadUpload(File file, Account account) {
        this.file = file;
        this.account = account;

    }

    @Override
    public void run() {
        account.files.add(file);
    }
}
