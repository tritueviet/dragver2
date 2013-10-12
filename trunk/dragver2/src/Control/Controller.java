/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import com.nokia.lwuit.components.RefreshLoadBar;
import com.nokia.lwuit.*;
import com.nokia.lwuit.components.ContextMenu;
import com.nokia.mid.ui.CategoryBar;
import com.nokia.mid.ui.ElementListener;
import com.nokia.mid.ui.IconCommand;
import com.sun.lwuit.Container;
import com.sun.lwuit.Dialog;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import event.Event;
import java.util.Timer;
import com.nokia.lwuit.components.NotificationBar;
import com.nokia.lwuit.components.PopupChoiceGroup;
import com.sun.lwuit.Component;
import com.sun.lwuit.List;
import com.sun.lwuit.list.DefaultListModel;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import models.Var;
import views.*;

/**
 *
 * @author TRITUEVIET
 */
public class Controller {

    private static Controller instance = null;
    public CategoryBar categoryBar;
    private Image icon;
    public static Main main;
    private ViewHome view;
    private String nameRecord = "chaobanwall";
    private ViewAbout viewAbout = null;
    private ViewHelp viewHelp = null;
    ViewNormalCall normal = new ViewNormalCall();
   
    public List FullContactList = null; 
    public Vector FullPhoneList = null;
    public Vector FullNameList = null;
    public LoadContact lc;
    public int kiem = 1;
    public Controller() {
        
        FullContactList = new List(new DefaultListModel());
        FullPhoneList = new Vector();
        FullNameList = new Vector();
        loadTheme();
        init();
    }

    public void init() {
        IconCommand[] iconCommands = new IconCommand[2];

        for (int i = 0; i < iconCommands.length; i++) {
            try {
                icon = Image.createImage("/images/icon" + (i + 1) + ".png");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            iconCommands[i] = new IconCommand("", icon, null, Command.SCREEN, 1);

        }
        categoryBar = new CategoryBar(iconCommands, true);
        categoryBar.setElementListener(new CategoryElementListener());
        categoryBar.setMode(CategoryBar.ELEMENT_MODE_STAY_SELECTED);
       /* if (viewAbout == null) {
            viewAbout = new ViewAbout();
        }
        * */

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void chay(Main main) {
        this.main = main;

        if (1 == 0) {
            LoadScreen load = new LoadScreen(main);
            javax.microedition.lcdui.Display.getDisplay(main).setCurrent(load);
            load.start();
            load = null;
            categoryBar.setVisibility(false);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        categoryBar.setVisibility(true);
        categoryBar.setSelectedIndex(0);   
        lc = new LoadContact();
        
        showHome();
        
       // shownormalcall();
    }

    public void handleEvent(int eventType, Event evt) {
        switch (eventType) {

        }
    }
    public void showNotification(String title)
    {
       NotificationBar notificationBar = new NotificationBar(null, title);
        notificationBar.setTransitionSpeed(20);
        
        notificationBar.showNoitification();
        
             
    }
    public void showAlert(String tittle0, String tittle1) {
        boolean kt = false;
        if (categoryBar.getVisibility() == true) {
            categoryBar.setVisibility(false);
            
            kt = true;
        }
        Dialog diag = new Dialog(tittle0);
        diag.setHeight(320);
        Container con1 = new Container();
        TextArea ta = new TextArea(tittle1, 2, 10);
        ta.getStyle().setFgColor(0xffffff);
        con1.addComponent(ta);
        com.sun.lwuit.Command cv = new com.sun.lwuit.Command("Back"); 
        diag.setBackCommand(cv);
        diag.addCommand(cv);
        diag.setTimeout(2000);
        diag.show();
        if (kt == true) {
            categoryBar.setVisibility(true);
        }
//        Alert alert;
//        if (Var.language == 0) {
//            alert = new Alert("", tittle0, null, AlertType.CONFIRMATION);
//        } else {
//             alert = new Alert("", tittle1, null, AlertType.CONFIRMATION);
//        }
//        Display.getDisplay(main).setCurrent(alert);

    }
    public void waitScreen(){
         // Gauge gauge = new Gauge(null, false,  Gauge.INDEFINITE,Gauge.CONTINUOUS_RUNNING);
        
            //Alert alert = new Alert(Var.wait[Var.language]);
          Alert alert = new Alert("loading...");
            //alert.setIndicator(gauge);
            //alert.setTimeout(Alert.FOREVER);
           // Display.getDisplay(main).setCurrent(alert);
       // try {
        //    Thread.sleep(1000);
      //  } catch (InterruptedException ex) {
       //     ex.printStackTrace();
      //  }
           final Gauge gauge;
             gauge = new Gauge(null, false, Gauge.INDEFINITE,Gauge.CONTINUOUS_RUNNING);
             alert.setIndicator(gauge);
           // alert.setImage(ImageLoader.load(ImageLoader.DIALOG_DOWNLOAD));
           // alert.addCommand(Commands.ALERT_CANCEL);
            alert.setType(AlertType.INFO);
            alert.setTimeout(2000);
            Display.getDisplay(main).setCurrent(alert);
    }
    public void showHome() {
        if (categoryBar.getVisibility() == false) {
            categoryBar.setVisibility(true);
        }
        System.out.println("chay...");
        ViewHome views = new ViewHome();
        normal = null;
       // normal.setSdt("");
//        views.addCommand(about);
//        views.addCommand(help);
        views.showForm();
        views.refreshTheme();
    }

    public void shownormalcall() {
        if (categoryBar.getVisibility() == false) {
            categoryBar.setVisibility(true);
        }
        ViewNormal vs = new ViewNormal();
        vs.show();
//        if (normal == null) {
//            normal = new ViewNormalCall();   
//            normal.setSdt("");
//        }
//        normal.setFullScreenMode(false);
//        javax.microedition.lcdui.Display.getDisplay(main).setCurrent(normal);
        
        
    }

    public void showSetting() {
        if (categoryBar.getVisibility() == false) {
            categoryBar.setVisibility(true);
        }
        ViewSetting viewSetting = new ViewSetting();
        normal = null;
        viewSetting.refreshTheme();
        viewSetting.show();
    }

    public void exit() {
        //   saveConfig();
        main.destroyApp(true);
    }

    public void updateConfig() {
        try {
            //if()RecordStore.deleteRecordStore(nameRecord);
            RecordStore rs = RecordStore.openRecordStore(nameRecord, true);
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            int rid = 0;
            while (re.hasNextElement()) {
                rid = re.nextRecordId();
                rs.deleteRecord(rid);
            }
            byte[] data;
            data = (Var.language + "").getBytes();
            rs.addRecord(data, 0, data.length);

            rs.closeRecordStore();

            for (int j = 1; j < 4; j++) {
                rs = RecordStore.openRecordStore(nameRecord + j, true);
                re = rs.enumerateRecords(null, null, false);
                rid = 0;
                while (re.hasNextElement()) {
                    rid = re.nextRecordId();
                    rs.deleteRecord(rid);

                }
                for (int i = 0; i < Var.itemsID.size(); i++) {
                    if (j == 1) {
                        data = ((String) Var.itemsID.elementAt(i)).getBytes();
                        rs.addRecord(data, 0, data.length);
                    }
                    if (j == 2) {
                        data = ((String) Var.itemsName.elementAt(i)).getBytes();
                        rs.addRecord(data, 0, data.length);
                    }
                    if (j == 3) {
                        data = ((String) Var.itemsPhone.elementAt(i)).getBytes();
                        rs.addRecord(data, 0, data.length);
                    }
                }
                rs.closeRecordStore();

            }
            for (int i = 0; i < Var.itemsID.size(); i++) {
                System.out.println("  " + Var.itemsID.elementAt(i).toString() + "  " + Var.itemsName.elementAt(i).toString() + "  " + Var.itemsPhone.elementAt(i).toString());

            }
        } catch (RecordStoreException ex) {
            System.out.println("loi doc rms");
            ex.printStackTrace();
        }
    }

    public void rs1() {
        try {
            RecordStore rs = RecordStore.openRecordStore(nameRecord, true);
            if (rs.getNumRecords() == 0) {
                System.out.println("creat");
                return;
            }
            byte[] data;
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                String s = new String(re.nextRecord());
                Var.language = Integer.parseInt(s);
            }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            System.out.println("loi doc recod");
            ex.printStackTrace();
        }
    }

    public void rs2() {
        try {
            RecordStore rs = RecordStore.openRecordStore(nameRecord + 1, true);
            if (rs.getNumRecords() == 0) {
                System.out.println("creat1");
                return;
            }
            byte[] data;
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                String s = new String(re.nextRecord());
                Var.itemsID.addElement(s);
            }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            System.out.println("loi doc recod");
            ex.printStackTrace();
        }
    }

    public void rs3() {
        try {
            RecordStore rs = RecordStore.openRecordStore(nameRecord + 2, true);
            if (rs.getNumRecords() == 0) {
                System.out.println("creat2");
                return;
            }
            byte[] data;
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                String s = new String(re.nextRecord());
                Var.itemsName.addElement(s);
            }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            System.out.println("loi doc recod");
            ex.printStackTrace();
        }
    }

    public void rs4() {
        try {
            RecordStore rs = RecordStore.openRecordStore(nameRecord + 3, true);
            if (rs.getNumRecords() == 0) {
                System.out.println("creat3");
                return;
            }
            byte[] data;
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                String s = new String(re.nextRecord());
                Var.itemsPhone.addElement(s);
            }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            System.out.println("loi doc recod");
            ex.printStackTrace();
        }
    }

    public void loadConfig() {
        rs1();
        Var var = new Var();
        var.doiNgonNgu(Var.language);
        rs2();
        rs3();
        rs4();
    }

    public void loadTheme() {
        try {
            Resources theme = Resources.open("/themes/themecall4.res");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
        } catch (Throwable ex) {
            Dialog.show("Exception", ex.getMessage(), "OK", null);
        }
    }

    public void showAbout(int i) {
        if (categoryBar.getVisibility() == true) {
            categoryBar.setVisibility(false);
        }
        if (viewAbout == null) {
            viewAbout = new ViewAbout(i);
        }
        normal = null;
        //viewAbout.refreshTheme();
        viewAbout.show();
       
    }

    public void showHelp(int i) {
        if (categoryBar.getVisibility() == true) {
            categoryBar.setVisibility(false);
        }
        if (viewHelp == null) {
            viewHelp = new ViewHelp(i);
        }
        //viewHelp.refreshTheme();
        normal = null;
        viewHelp.show();
    }
    //hanh
   /* public static Component getComponentParent()
    {
        return 
         
    }
    * */
    public void showSave(String name, String phone, int vt) {
        ViewSave view = new ViewSave(name.toString(), phone.toString(), vt);
        view.show();
    }

    class CategoryElementListener implements ElementListener {

        public void notifyElementSelected(CategoryBar bar, int selectedIndex) {
            if (bar == categoryBar) {
                if (selectedIndex == 0) {
                   // shownormalcall();
                     showHome();
                } else if (selectedIndex == 1) {
                    //showHome();
                    shownormalcall();
                
                } else {
                    try {
                        exit();
                    } catch (Exception ex) {
                        System.out.println("k thoats ddc ");
                    }
                }

            }
        }
    }
}