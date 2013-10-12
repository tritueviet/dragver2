/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;


import com.nokia.lwuit.components.HeaderBar;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.geom.Rectangle;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.plaf.Style;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.microedition.pim.*;
import models.Var;

/**
 *
 * @author TRITUEVIET
 */
public class ViewSave extends BaseScreen implements ActionListener {

    private Label name, phone;
    private TextField tfname, tfphone;
    Button add, browser, save;
    Command back = new Command(Var.back);
    Command back1 = new Command(Var.back);
    private int vitri;
    private PIM  pim1 ;
    private String[] pimListNames1 = null;
    private ContactList contactList = null;

    public ViewSave(String sdt) {
        //removeAll();
//        Display.getInstance().setForceFullScreen(true);
//        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
//        
        if (controller.categoryBar.getVisibility() == true) {
            controller.categoryBar.setVisibility(false);
        }
        //refreshTheme();
       /* HeaderBar header = null;
        try {
            header = new HeaderBar("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        addComponent(header);

*/      
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Container north = new Container(new BorderLayout());
        Container north2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container north3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        name = new Label("Name: ");
        name.getUnselectedStyle().setMargin(0, 0, 10, 10);
        name.getPressedStyle().setMargin(0, 0, 10, 10);
        name.getSelectedStyle().setMargin(0, 0, 10, 10);
        name.getStyle().setMargin(0, 0, 10, 10);
       
        name.getUnselectedStyle().setPadding(0, 0, 1, 1);
        name.getPressedStyle().setPadding(0, 0, 1, 1);
        name.getSelectedStyle().setPadding(0, 0, 1, 1);
        name.getStyle().setPadding(0, 0, 1, 1);
       
        phone = new Label("Phone: ");
        phone.getUnselectedStyle().setMargin(0, 0, 10, 10);
        phone.getPressedStyle().setMargin(0, 0, 10, 10);
        phone.getSelectedStyle().setMargin(0, 0, 10, 10);
        phone.getStyle().setMargin(0, 0, 10, 10);
       
        phone.getUnselectedStyle().setPadding(0, 0, 1, 1);
        phone.getPressedStyle().setPadding(0, 0, 1, 1);
        phone.getSelectedStyle().setPadding(0, 0, 1, 1);
        phone.getStyle().setPadding(0, 0, 1, 1);
       
        tfname = new TextField("");
 
        tfname.getUnselectedStyle().setMargin(0, 0,10, 10);
        tfname.getPressedStyle().setMargin(0, 0,10, 10);
        tfname.getSelectedStyle().setMargin(0, 0,10, 10);
        tfname.getStyle().setMargin(0, 0,10, 10);
       
        tfname.getUnselectedStyle().setPadding(9, 0, 1, 1);
        tfname.getPressedStyle().setPadding(9, 0, 1, 1);
        tfname.getSelectedStyle().setPadding(9, 0, 1, 1);
        tfname.getStyle().setPadding(9, 0, 1, 1);

       
        tfphone = new TextField("" + sdt);
        tfphone.setConstraint(TextField.PHONENUMBER);
       
        tfphone.getUnselectedStyle().setMargin(0, 0, 10, 10);
        tfphone.getPressedStyle().setMargin(0, 0, 10, 10);
        tfphone.getSelectedStyle().setMargin(0, 0, 10, 10);
        tfphone.getStyle().setMargin(0, 0, 10, 10);
       
        tfphone.getUnselectedStyle().setPadding(9, 0, 1, 1);
        tfphone.getPressedStyle().setPadding(9, 0, 1, 1);
        tfphone.getSelectedStyle().setPadding(9, 0, 1, 1);
        tfphone.getStyle().setPadding(9, 0, 1, 1);
       
        Container c3 = new Container(new BorderLayout());
        add = new Button("Add");
        add.addActionListener(this);
        add.getUnselectedStyle().setMargin(5, 0, 10, 10);
        add.getPressedStyle().setMargin(5, 0, 10, 10);
        add.getSelectedStyle().setMargin(5, 0, 10, 10);
        add.getStyle().setMargin(5, 0, 10, 10);
       
        add.getUnselectedStyle().setPadding(0, 5, 0, 0);
        add.getPressedStyle().setPadding(0, 5,0, 0);
        add.getSelectedStyle().setPadding(0, 5, 0, 0);
        add.getStyle().setPadding(0, 5, 0, 0);
        
        
      
         c3.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0xDDDDDD);
                grphcs.fillRect(rctngl.getX(), rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
         c3.addComponent(BorderLayout.NORTH,add); 
       // c3.getStyle().setMargin(0, 0, 0, 0);
       // c3.getUnselectedStyle().setMargin(0, 0, 0, 0);
       // c3.getSelectedStyle().setMargin(0, 0, 0, 0);
       // c3.getPressedStyle().setMargin(0, 0, 0, 0);

        north2.addComponent(name);
        north2.addComponent(tfname);
        north.addComponent(BorderLayout.NORTH,north2);
       
        north3.addComponent(phone);
        north3.addComponent(tfphone);
        north.addComponent(BorderLayout.SOUTH,north3);
       
        addComponent(north);
       
        setBackCommand(back);
        addCommandListener(this);
        addCommand(back);
        
        Container center = new Container(new BorderLayout());
        center. setPreferredSize(new Dimension(235, 145));
        center.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0xf4f4f4);
                grphcs.fillRect(rctngl.getX(), rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        center.setScrollableY(false);
        north.setScrollableY(false);
        c3.setScrollableY(false);
        addComponent(center);
       
        addComponent(c3);
        setScrollableY(false);
        //setScrollableX(false);
       
       
       
 
    }

    public ViewSave(String sname, String sphone, final int vitri) {

        if (controller.categoryBar.getVisibility() == true) {
            controller.categoryBar.setVisibility(false);
        }
        //refreshTheme();
        repaint();
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        this.vitri = vitri;
        name = new Label("Name: ");
        name.getUnselectedStyle().setMargin(0, 0, 10, 10);
        name.getPressedStyle().setMargin(0, 0, 10, 10);
        name.getSelectedStyle().setMargin(0, 0, 10, 10);
        name.getStyle().setMargin(0, 0, 10, 10);
       
        name.getUnselectedStyle().setPadding(0, 0, 1, 1);
        name.getPressedStyle().setPadding(0, 0, 1, 1);
        name.getSelectedStyle().setPadding(0, 0, 1, 1);
        name.getStyle().setPadding(0, 0, 1, 1);
       
        phone = new Label("Phone: ");
        phone.getUnselectedStyle().setMargin(0, 0, 10, 10);
        phone.getPressedStyle().setMargin(0, 0, 10, 10);
        phone.getSelectedStyle().setMargin(0, 0, 10, 10);
        phone.getStyle().setMargin(0, 0, 10, 10);
       
        phone.getUnselectedStyle().setPadding(0, 0, 1, 1);
        phone.getPressedStyle().setPadding(0, 0, 1, 1);
        phone.getSelectedStyle().setPadding(0, 0, 1, 1);
        phone.getStyle().setPadding(0, 0, 1, 1);
       
        tfname = new TextField("" + sname);
        tfname.setMaxSize(20);
        tfname.getUnselectedStyle().setMargin(0, 0,10, 10);
        tfname.getPressedStyle().setMargin(0, 0, 10, 10);
        tfname.getSelectedStyle().setMargin(0, 0, 10, 10);
        tfname.getStyle().setMargin(0, 0, 10, 10);
       
        tfname.getUnselectedStyle().setPadding(9, 0, 1, 1);
        tfname.getPressedStyle().setPadding(9, 0, 1, 1);
        tfname.getSelectedStyle().setPadding(9, 0, 1, 1);
        tfname.getStyle().setPadding(9, 0, 1, 1);

        tfphone = new TextField("" + sphone);
        tfphone.setConstraint(TextField.PHONENUMBER);
        tfphone.setMaxSize(20);
        tfphone.getUnselectedStyle().setMargin(0, 0, 10, 10);
        tfphone.getPressedStyle().setMargin(0, 0, 10, 10);
        tfphone.getSelectedStyle().setMargin(0, 0, 10, 10);
        tfphone.getStyle().setMargin(0, 0,10,10);
       
        tfphone.getUnselectedStyle().setPadding(9, 0, 1, 1);
        tfphone.getPressedStyle().setPadding(9, 0, 1, 1);
        tfphone.getSelectedStyle().setPadding(9, 0, 1, 1);
        tfphone.getStyle().setPadding(9, 0, 1, 1);
       
        
        
        save = new Button("Save");
        save.addActionListener(this);
        save.getUnselectedStyle().setMargin(5, 0, 10, 10);
        save.getPressedStyle().setMargin(5, 0,10, 10);
        save.getSelectedStyle().setMargin(5, 0, 10, 10);
        save.getStyle().setMargin(5, 0,10, 10);
        save.getUnselectedStyle().setPadding(0, 7, 0, 0);
        save.getPressedStyle().setPadding(0, 7, 0, 0);
        save.getSelectedStyle().setPadding(0, 7, 0, 0);
        save.getStyle().setPadding(0, 7, 0, 0);
        try {
            browser = new Button();
            browser.setIcon(Image.createImage("/images/add.png"));
            
            browser.setPressedIcon(Image.createImage("/images/add-press.png"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
       // browser.setPreferredW(this.getComponentForm().getWidth() / 4 - 30);
        //browser.setPreferredH(this.getComponentForm().getHeight() / 4 - 40);
        browser.setUIID("DemoButton");
        browser.setPreferredSize(new Dimension(39, 39));
        browser.addActionListener(this);
        
        //Container cname = new Container(new BorderLayout());
        //cname.addComponent(BorderLayout.SOUTH, name);
             
  
        // container
        Container north = new Container(new BorderLayout());
        Container north1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container north2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        //Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       
        Container c = new Container(new BorderLayout());// chua textfield + browser
        Container c1 = new Container(new BorderLayout());// chua tfname
        Container c2 = new Container(new BorderLayout());// browser
       
        Container south = new Container(new BorderLayout());// save
       
        c1.addComponent(BorderLayout.WEST, tfname);
        
        c2.addComponent(BorderLayout.EAST, browser);
        c2.setPreferredSize(new Dimension(39, 39));
        //c2.setPreferredH(30);
        c2.getStyle().setMargin(0, 0, 0, 0);
        c.addComponent(BorderLayout.CENTER, c1);
        c.addComponent(BorderLayout.EAST, c2);
        c.getStyle().setMargin(0, 0, 0, 5);
        c.getStyle().setPadding(0, 0, 0, 0);
        north1.addComponent(name);
        north1.addComponent(c);
       
        north2.addComponent(phone);
        north2.addComponent(tfphone);
       
        north.addComponent(BorderLayout.CENTER,north1);
        north.addComponent(BorderLayout.SOUTH,north2);
       
        addComponent(north);
       
        south.addComponent(BorderLayout.NORTH,save);
        south.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0xDDDDDD);
                grphcs.fillRect(rctngl.getX(), rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        south.getStyle().setMargin(0, 0, 0, 0);
        south.getUnselectedStyle().setMargin(0, 0, 0, 0);
        south.getSelectedStyle().setMargin(0, 0, 0, 0);
        south.getPressedStyle().setMargin(0, 0, 0, 0);
       
        Container center2 = new Container(new BorderLayout());
        center2. setPreferredSize(new Dimension(235, 140));
        center2.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0xf4f4f4);
                grphcs.fillRect(rctngl.getX(), rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        addComponent(center2);
        addComponent(south);
       // addCommand(add);
        setScrollableY(false);
        setBackCommand(back1);
        addCommandListener(this);
        addCommand(back1);

    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() ==add){
            if (tfname.getText().length() != 0 && tfphone.getText().length() != 0) {
                
                  SaveContact(tfphone.getText().toString(), tfname.getText().toString());
                  controller.showAlert(Var.title[0], Var.title[1]);
                    
                    //controller.showNotification("add to contact");
                    
                    controller.shownormalcall();
                
                        
                    } else {
                        // refreshTheme();
                        controller.showAlert(Var.loitext[0], Var.loitext[1]);

                    }
        }
        //-----------------------------------------------------------------------------
        if (ae.getSource()==save) {
                    //Do Exit command code
                    if (tfname.getText().length() != 0 && tfphone.getText().length() != 0) {
                        int vi = -1;
                        System.out.println("" + vi);
                        for (int i = 0; i < Var.itemsID.size(); i++) {
                            if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == vitri) {
                                vi = i;
                                break;
                            }
                        }
                        if (vi != -1) {
                            Var.itemsName.setElementAt(tfname.getText(), vi);
                            Var.itemsPhone.setElementAt(tfphone.getText(), vi);
                        } else {
                            Var.itemsID.addElement(vitri + "");
                            Var.itemsName.addElement(tfname.getText());
                            Var.itemsPhone.addElement(tfphone.getText());

                        }
                        controller.showAlert(Var.title[0], Var.title[1]);
                        controller.showHome();
                        //  Dialog.show("Exception", Var.title[Var.language].toString(), "OK", null);

                    } else {
                        controller.showAlert(Var.loitext[0], Var.loitext[1]);

                    }


                }
        //-----------------------------------------------------------------------------
        if (ae.getSource() == browser) {
            
            Enumeration contacts = null;
             //hanh
             Dialog diag = new Dialog("Waiting...");
             diag.setHeight(320);
             com.sun.lwuit.Command cv = new com.sun.lwuit.Command("Back"); 
             diag.setBackCommand(cv);
             diag.addCommand(cv);
             if(controller.kiem==1)diag.setTimeout(4000);
             else diag.setTimeout(1000);
            pim1 = PIM.getInstance();
            pimListNames1 = pim1.listPIMLists(PIM.CONTACT_LIST);
            
            
            try {
                contactList = (ContactList) pim1.openPIMList(
                        PIM.CONTACT_LIST, PIM.READ_ONLY);
            } catch (PIMException ex) {
                ex.printStackTrace();
            }
            try {
                contacts = contactList.items();
            } catch (PIMException ex) {
                ex.printStackTrace();
            }
            diag.show();
            
            if (!contacts.hasMoreElements()) {
                controller.showAlert("Contact list is empty!", "");

            } else {          
                controller.lc.populateContactList(vitri,pim1, pimListNames1);
                controller.lc.show();
            }
        }

        if (ae.getCommand() == back) {
            controller.shownormalcall();
        } else if (ae.getCommand() == back1) {
            controller.showHome();
        }

    }

    /*public int SaveContact(String phoneNo, String nameContact) {
        //hanh
        Dialog diag = new Dialog("Waiting...");
        diag.setHeight(320);
        Container con1 = new Container();
        com.sun.lwuit.Command cv = new com.sun.lwuit.Command("Back"); 
        diag.setBackCommand(cv);
        diag.addCommand(cv);
        diag.setTimeout(1000);
        System.out.println("" + phoneNo + "..." + nameContact);
        int ok = 0;
        ContactList clist = null;
        try {
            clist = (ContactList) pim1.openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE);
        } catch (PIMException e) {
            e.printStackTrace();
            System.out.println("loi doc");
            ok = 1;
        }
        diag.show();
        Enumeration itemsEnum = null;
        InputStream is = null;

        Contact searchPattern = null;
        if (clist == null) {
            System.out.println("list trống");
            ok = 1;
        }
        Contact result = null;
        try {
            result = clist.createContact();
            searchPattern = clist.createContact();
            searchPattern.addString(Contact.TEL, Contact.ATTR_NONE, phoneNo);
            // tìm kiếm contact xem có trùng k
            itemsEnum = clist.items(searchPattern);
            boolean wasFound = false;
            if (itemsEnum != null && itemsEnum.hasMoreElements()) {
                //  controller.showAlert(Var.NULL[0],Var.NULL[1]);//
                wasFound = true;
                result = (Contact) itemsEnum.nextElement();
                ok = 2;
            }
            if (!wasFound) {
                // contact chưa tồn tại tạo mới nó
                String[] name = new String[clist.stringArraySize(Contact.NAME)];
                if (clist.isSupportedArrayElement(Contact.NAME, Contact.NAME_GIVEN)) {
                    name[Contact.NAME_GIVEN] = nameContact;
                    //name[Contact.NAME_FAMILY] = nameContact;
                }
                result.addStringArray(Contact.NAME, PIMItem.ATTR_NONE, name);

                if (clist.isSupportedField(Contact.TEL)) {
                    result.addString(Contact.TEL, Contact.ATTR_MOBILE, phoneNo);
                    // result.addString(Contact.TEL, Contact.ATTR_FAX, phoneNo);

                }

//                if (clist.isSupportedField(Contact.EMAIL)) {
//                    result.addString(Contact.EMAIL, Contact.ATTR_NONE, email);
//                }
//
//                if (clist.isSupportedField(Contact.ORG)) {
//                    result.addString(Contact.ORG, Contact.ATTR_NONE, TestCorporation);
//                }
//
//                if (clist.isSupportedField(Contact.BIRTHDAY)) {
//                    //result.addDate(Contact.BIRTHDAY, Contact.ATTR_NONE, Long.parseLong(date));
//                }
            }
            try {
                result.commit();
            } catch (PIMException f) {
                //     Dialog.show("Exception", Var.error[Var.language].toString(), "OK", null);
                ok = 1;
            }
        } catch (Exception e) {
            //  Dialog.show("Exception", Var.error[Var.language].toString(), "OK", null);
            e.printStackTrace();
        } finally {
            searchPattern = null;
            result = null;
            itemsEnum = null;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    //        Dialog.show("Exception", Var.error[Var.language].toString(), "OK", null);
                    ok = 1;
                }
                is = null;
            }
            if (clist != null) {
                try {
                    clist.close();
                } catch (PIMException e) {
                    //        Dialog.show("Exception", Var.error[Var.language].toString(), "OK", null);
                    ok = 1;
                }
                clist = null;
            }
        }
        return ok;

    }
    * */
    /**
     * Adds contact to PIM contact list.
     */
    private void SaveContact(String phoneNo, String nameContact) {
         //hanh
             Dialog diag = new Dialog("Waiting...");
             diag.setHeight(320);
             com.sun.lwuit.Command cv = new com.sun.lwuit.Command("Back"); 
             diag.setBackCommand(cv);
             diag.addCommand(cv);
             diag.setTimeout(2000);
        ContactList contacts = null;
        try {
            // Retrieve the contact list
            contacts = (ContactList) PIM.getInstance().openPIMList(
                    PIM.CONTACT_LIST, PIM.READ_WRITE);
            diag.show();
            Contact contact = contacts.createContact();
            String[] name = new String[contacts.stringArraySize(Contact.NAME)];
                if (contacts.isSupportedArrayElement(Contact.NAME, Contact.NAME_GIVEN)) {
                    name[Contact.NAME_GIVEN] = nameContact;
                    //name[Contact.NAME_FAMILY] = nameContact;
                }
                contact.addStringArray(Contact.NAME, PIMItem.ATTR_NONE, name);

                if (contacts.isSupportedField(Contact.TEL)) {
                    contact.addString(Contact.TEL, Contact.ATTR_MOBILE, phoneNo);
                    // result.addString(Contact.TEL, Contact.ATTR_FAX, phoneNo);

                }
                    
            contact.commit();
            contacts.close();
        } catch (PIMException e) {
            
        }
        
    }
    private void releaseContactList() {
        if (contactList != null) {
            try {

                contactList.close();
            } catch (PIMException ex) {
            }
        }
        contactList = null;
    }
}
