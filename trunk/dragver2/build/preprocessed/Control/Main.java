package Control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.lwuit.Dialog;
import com.sun.lwuit.Display;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import javax.microedition.midlet.MIDlet;
import models.Var;
import views.ViewHome;

/**
 * @author TRITUEVIET
 */
public class Main extends MIDlet {

    public Main() {
        Display.init(this);
        Controller.getInstance().loadConfig();
    }
    
    public void startApp() {
        Controller.getInstance().chay(this);
    }

    public void pauseApp() {
        System.out.println("gfhjk");
    }

    public void destroyApp(boolean unconditional) {
        Controller.getInstance().updateConfig();
        System.out.println("update");
        notifyDestroyed();
    }
}
