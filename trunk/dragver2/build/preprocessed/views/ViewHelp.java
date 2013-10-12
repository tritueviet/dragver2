package views;

import Control.Controller;
import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Font;
import com.sun.lwuit.Form;
import com.sun.lwuit.TextArea;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.html.HTMLComponent;
import com.sun.lwuit.layouts.BoxLayout;
import models.Var;

public class ViewHelp extends BaseScreen implements ActionListener {

    //private String title = "Giúp đỡ";
    Command back = new Command(Var.back);
    private int vt;
    private static String text = "Drag one of 14 favourite contacts (added by users)and drop it into Phone icon to start calling or SMS icon to send SMS. Users can also make call by tapping numbers of contacts in smart dialer tab.\n"
            + "\n" + "Functions: \n"
            + " • Drag and drop to call or send SMS\n"
            + " • Drag and drop to sort contacts\n"
            + " • Share contact name and phone by SMS and Email\n" + "\n";
    public ViewHelp(int vt) {
        

       // Display.getInstance().setForceFullScreen(true);
        //Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        if (controller.categoryBar.getVisibility() == true) {
            controller.categoryBar.setVisibility(false);
        }
        HeaderBar header = null;
        try {
            header = new HeaderBar(Var.help);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        header.setHeaderTitleColor(Var.hearder);
        header.getStyle().setBgColor(Var.colorWhite);
         header.setSize(new Dimension(240, 40));
       // header.setVisible(true);
        //header.setScrollableY(false);
        //header.setScrollableX(false);
        
        header.setScrollable(false);
        addComponent(header);
//        setTitle(Var.help);
        this.vt = vt;
        
        TextArea help = new TextArea();
        help.setUIID("Label");
        help.setEditable(false);
        help.setFocusable(false);
        help.setEnabled(false);
        help.setRows(2);
        help.setGrowByContent(true);
        Font createSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        help.getStyle().setFont(createSystemFont);
        help.setText(text);
        addComponent(help);
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

    