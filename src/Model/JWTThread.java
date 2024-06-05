package Model;

import javax.swing.*;
import java.awt.*;

public class JWTThread extends Thread {
    private String token;
    private Frame frame;
    private boolean access = false;

    public JWTThread(String token, Frame frame) {
        this.token = token;
        this.frame = frame;
    }

    @Override
    public void run() {
        if (JOptionPane.showConfirmDialog(frame, token, "Access", JOptionPane.YES_NO_OPTION) == 0) {
            access = true;
        }
    }
    public boolean getAccess(){
        return access;
    }
    public void setAccess(boolean access){
        this.access = access;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    public Frame getFrame(){
        return frame;
    }
    public void setFrame(Frame frame){
        this.frame = frame;
    }
}
