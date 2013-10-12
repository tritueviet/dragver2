/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.ButtonGroup;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Label;
import com.sun.lwuit.RadioButton;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import models.Var;

/**
 *
 * @author TRITUEVIET
 */
public class ViewSetting extends BaseScreen implements ActionListener {

    public ButtonGroup radioButtonGroup;
    Command about = new Command(Var.about);
    Command help = new Command(Var.help);

    public ViewSetting() {
        if (controller.categoryBar.getVisibility() == false) {
            controller.categoryBar.setVisibility(true);
        }
        HeaderBar header = null;
        try {
            header = new HeaderBar(Var.setting);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setHeaderTitleColor(Var.hearder);
        header.getStyle().setBgColor(Var.colorWhite);
        header.setScrollable(false);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(header);
        Container con1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label lang = new Label(Var.lang);
        con1.addComponent(lang);

        radioButtonGroup = new ButtonGroup();
        RadioButton radioButton = new RadioButton("    English");

        radioButton.setUIID("ListItem");
        radioButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                Var.language = 0;
                Var.doiNgonNgu(0);
                controller.showSetting();
            }
        });

        radioButtonGroup.add(radioButton);
        //addComponent(radioButton);

        RadioButton radioButton1 = new RadioButton("    Tiếng Việt");
        radioButton1.setUIID("ListItem");

        radioButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                Var.doiNgonNgu(1);
                Var.language = 1;
                controller.showSetting();

            }
        });


        radioButtonGroup.add(radioButton1);
        radioButtonGroup.setSelected(Var.language);

        con1.addComponent(radioButton);
        con1.addComponent(radioButton1);

        con1.getStyle().setMargin(15, 10, 10, 10);


        addComponent(con1);

        addCommand(about);
        addCommand(help);
        addCommandListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
//        if (ae.getCommand() == about) {
//            controller.showAbout();
//        } else if (ae.getCommand() == help) {
//            controller.showHelp();
//        }
    }
}
