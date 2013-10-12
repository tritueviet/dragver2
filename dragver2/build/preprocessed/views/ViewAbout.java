package views;

import Control.Controller;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.html.HTMLComponent;
import com.sun.lwuit.layouts.BoxLayout;
import models.Var;

public class ViewAbout extends BaseScreen implements ActionListener {

    Command back = new Command(Var.back);
    private static final String CONTENT_TEXT =""
            
            + "This application has been developed and updated by Institute of Information and Communications Technology CDiT.\n"
            + "\n"
            + "Contact:\n"
            + "Website:www.cdit.ptit.edu.vn\n"
            + "Email:support@cdit.com.vn\n"
            + "\n"
            + "Application: Drag and Call for Nokia Asha 501 Full Touch\n"
            + "Version: 1.0.2\n"
            + "Copyright @2013 CDiT. All rights reserved";
    private int vt;
    public ViewAbout(int vt) {
        if (controller.categoryBar.getVisibility() == true) {
            controller.categoryBar.setVisibility(false);
        }
        this.vt = vt;
        HeaderBar header = null;
        try {
            header = new HeaderBar("About");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //header.setHeaderTitleColor(0x00FFFF);
        header.getStyle().setBgColor(0xffffff);


        header.setScrollable(false);

        addComponent(header);
        TextArea about = new TextArea();
        about.setUIID("Label");
        about.setEditable(false);
        about.setFocusable(false);
        about.setEnabled(false);
        about.setRows(2);
        about.setGrowByContent(true);
        Font createSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        about.getStyle().setFont(createSystemFont);
        about.setText(CONTENT_TEXT);
        addComponent(about);
        setBackCommand(back);
        addCommandListener(this);
        addCommand(back);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand() == back) {
            if(vt==0){
                controller.showHome();
            }
            else {
            controller.shownormalcall();
            }
            
        }
    }
}
