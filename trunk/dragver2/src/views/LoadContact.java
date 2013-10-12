package views;

import com.nokia.lwuit.components.HeaderBar;
import com.nokia.lwuit.components.SearchBar;
import com.nokia.lwuit.components.SearchBarListener;
import com.sun.lwuit.Button;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Image;
import com.sun.lwuit.List;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.events.DataChangedListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.list.DefaultListModel;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Font;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import models.Var;

/**
 *
 * @author FOX
 */
public class LoadContact extends BaseScreen implements ActionListener, SearchBarListener //    private ContactList contList = null;
{

    int vitri;
    int isearch = 0;
    private List contactList = null;
    private Vector phoneList = null;
    private Vector nameList = null;
    private PIM pim = null;
    private Alert alert = null;
    private String[] pimListNames = null;
    HeaderBar header = null;
    Command back = new Command(Var.back);
    TextField searchField;
    SearchBar searchbar = null;
    Button searchButton;
    Image img = loadImage("/images/contactAva.png");

    public LoadContact() {
        contactList = new List(new DefaultListModel());

        phoneList = new Vector();
        nameList = new Vector();


        try {
            header = new HeaderBar("All contact");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        header.setSize(new Dimension(240, 40));
        //header.setHeaderTitleColor(0x00FFFF);
        header.getStyle().setBgColor(0xffffff);
        Font font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
        header.setScrollable(false);
        addComponent(header);


        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //refreshTheme();

        Container contain = new Container(new BorderLayout());


        contactList.addActionListener(this);
        Image img;
        /*  try {
         img = Image.createImage("/images/search.png");
         searchbar = new SearchBar("Search", img);
         } catch (IOException ex) {
         ex.printStackTrace();
         }
        
         searchbar.setActionListener(this);
        
         // Container search  = new Container(new BorderLayout());
         // search.addComponent(BorderLayout.CENTER,searchbar);
        
         contain.addComponent(BorderLayout.NORTH, searchbar);
        
         */
        try {
            searchButton = new Button(Image.createImage("/images/search.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        searchButton.setUIID("DemoButton");

        //searchButton.setPreferredW(getWidth() / 2 - 30);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (searchField.getText().length() > 0) {
                    searchField.setText("");
                    searchField.repaint();

                    try {
                        searchButton.setIcon(Image.createImage("/images/search.png"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    SearchContact("");

                }

            }
        });

        searchField = new TextField();
        // searchField.setMaxSize(20);
        searchField.getUnselectedStyle().setMargin(3, 0, 5, 5);
        searchField.getPressedStyle().setMargin(3, 0, 5, 5);
        searchField.getSelectedStyle().setMargin(3, 0, 5, 5);
        searchField.getStyle().setMargin(3, 0, 5, 5);

        searchField.getUnselectedStyle().setPadding(3, 0, 1, 1);
        searchField.getPressedStyle().setPadding(3, 0, 1, 1);
        searchField.getSelectedStyle().setPadding(3, 0, 1, 1);
        searchField.getStyle().setPadding(3, 0, 1, 1);
        searchField.setHint("Enter name");// anh o cho nay
        //searchField.setHintIcon(Image.createImage("/images/anh2.png"));

        searchField.addDataChangeListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
                if (searchField.getText().length() > 0) {

                    try {
                        searchButton.setIcon(Image.createImage("/images/delete.png"));

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else {

                    try {
                        searchButton.setIcon(Image.createImage("/images/search.png"));

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

                SearchContact(searchField.getText());
            }
        });

        Container c1 = new Container(new BorderLayout());
        c1.setPreferredW(200);
        c1.addComponent(BorderLayout.WEST, searchField);

        Container c2 = new Container(new BorderLayout());
        c2.setPreferredSize(new Dimension(25, 25));
        c2.getStyle().setMargin(0, 0, 5, 5);
        c2.addComponent(BorderLayout.EAST, searchButton);
        c2.setPreferredSize(new Dimension(25, 25));
        Container c = new Container(new BorderLayout());
        c.addComponent(BorderLayout.WEST, c1);
        c.addComponent(BorderLayout.EAST, c2);

        //contain.addComponent(BorderLayout.NORTH, searchField);   
        contain.addComponent(BorderLayout.NORTH, c);



//        Button clearButton = new Button("Clear");
//        clearButton.setPreferredW(getWidth() / 2);
//        clearButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent evt) {
//                searchField.setText("");
//                populateContactList("");
//                show();
//
//            }
//        });
        //       Container buttonContainer = new Container();
//        Container all = new Container();
//        all.setLayout(new BorderLayout());
//        buttonContainer.setLayout(new BorderLayout());

        //all.addComponent(BorderLayout.CENTER, searchField);

        //       buttonContainer.addComponent(BorderLayout.WEST, searchButton);
        //       buttonContainer.addComponent(BorderLayout.CENTER, clearButton);
        //       all.addComponent(BorderLayout.SOUTH, buttonContainer);

        //addComponent(search);
        contain.addComponent(BorderLayout.CENTER, contactList);
        contain.getUnselectedStyle().setMargin(0, 0, 5, 5);
        contain.getPressedStyle().setMargin(0, 0, 5, 5);
        contain.getSelectedStyle().setMargin(0, 0, 5, 5);
        contain.getStyle().setMargin(0, 0, 5, 5);
        //contain.getUnselectedStyle().setPadding(0, 5, 0, 0);
        //contain.getPressedStyle().setPadding(0, 5, 0, 0);
        //contain.getSelectedStyle().setPadding(0, 5, 0, 0);
        //contain.getStyle().setPadding(0, 5, 0, 0);
        addComponent(contain);
        //addComponent(all);
        //search.setScrollableY(true);
        contain.setScrollableY(true);
        contactList.setScrollToSelected(true);
        //all.setScrollable(true);
        setScrollableY(false);
        setScrollableX(false);



        setBackCommand(back);
        addCommand(back);
        addCommandListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == back) {
                    controller.showSave("", "", vitri);

                }
            }
        });

        // show();

    }

    public void populateContactList(final int vitri, PIM pim1, String[] pimListNames1) {
        //show();
        this.repaint();
        this.vitri = vitri;
        if (controller.FullContactList.size() > 0) {
            Calendar cal = Calendar.getInstance();
            System.out.println("  " + cal.getTime());
            SearchContact("");

            Calendar cal1 = Calendar.getInstance();
            System.out.println("  " + cal1.getTime());
            return;
        } else {
            controller.kiem = 0; 
            Calendar cal = Calendar.getInstance();
            System.out.println("  " + cal.getTime());
            pim = pim1;
            pimListNames = pimListNames1;
            String searchTerm = "";
            contactList.setModel(new DefaultListModel());
            System.out.println("load lai");

            try {
                /*
                 * String[] pimListNames = pim.listPIMLists(PIM.CONTACT_LIST);
                 * if(pimListNames.length == 0) { controller.showAlert("Empty
                 * Contacts",""); return; // alert.addCommand(Command.);
                 *
                 * }
                 *
                 */
                for (int i = 0; i < pimListNames.length; ++i) {
                    ContactList cl = (ContactList) pim.openPIMList(
                            PIM.CONTACT_LIST, PIM.READ_ONLY, pimListNames[i]);
                    Enumeration items = cl.items(searchTerm);
                    while (items.hasMoreElements()) {
                        Contact c = (Contact) items.nextElement();
//                 

                        if (c.countValues(Contact.NAME) > 0 && c.countValues(Contact.TEL) > 0) {
                            String[] nameArray = c.getStringArray(Contact.NAME, 0);
                            String phone = null;
                            phone = c.getString(Contact.TEL, 0);
                            if (nameArray != null && phone != null) {
                                for (int j = 0; j < nameArray.length; j++) {
                                    if (nameArray[j] != null) {
                                        // contactList.addItem(nameArray[j] + "   " + phone);
                                        // contactList.addItem(nameArray[j]);
                                        phoneList.addElement(phone);
                                        nameList.addElement(nameArray[j]);
                                        Command item = new Command(nameArray[j], img);
                                        contactList.addItem(item);

                                        controller.FullPhoneList.addElement(phone);
                                        controller.FullNameList.addElement(nameArray[j]);
                                        controller.FullContactList.addItem(item);



                                        break;
                                    }
                                }

                            } else {
                                controller.showAlert("Error", "Lá»—i");
                            }




                        }
                    }
                }

            } catch (PIMException ex) {
                ex.printStackTrace();
            }
            if (contactList.getModel().getSize() == 0) {
            }
            Calendar cal1 = Calendar.getInstance();
            System.out.println("  " + cal1.getTime());
        }

    }

    public static Image loadImage(String path) {
        try {
            // load image
            return Image.createImage(path);
        } catch (IOException ioe) {
        }
        return null;
    }

    public void actionPerformed(ActionEvent ae) {

        int t = contactList.getSelectedIndex();
        controller.showSave(nameList.elementAt(t).toString(),
                phoneList.elementAt(t).toString(),
                vitri);
    }

    public void notifySearchBarListener(SearchBar sb, String string) {
    }

    public void SearchContact(String name) {

        this.repaint();
        contactList.setModel(new DefaultListModel());
        phoneList = new Vector();
        nameList = new Vector();
        if (name.length() == 0) {
              Calendar cal = Calendar.getInstance();
            System.out.println("  " + cal.getTime());

            for (int i = 0; i < controller.FullContactList.size(); i++) {
                String ct = controller.FullNameList.elementAt(i).toString();

                phoneList.addElement(controller.FullPhoneList.elementAt(i).toString());
                nameList.addElement(ct);
                //Command item = new Command(ct, loadImage("/images/contactAva.png"));
                //contactList.addItem(item);     
                controller.FullContactList.setSelectedIndex(i);
                contactList.addItem(controller.FullContactList.getSelectedItem());

            }
               Calendar cal1 = Calendar.getInstance();
            System.out.println("  " + cal1.getTime());
        } else {

            for (int i = 0; i < controller.FullContactList.size(); i++) {
                String contactName = controller.FullNameList.elementAt(i).toString();
                name = name.toLowerCase();
                if (contactName.toLowerCase().indexOf(name) != -1) {
                    phoneList.addElement(controller.FullPhoneList.elementAt(i).toString());
                    nameList.addElement(contactName);
                    //Command item = new Command(contactName, loadImage("/images/contactAva.png"));
                    //contactList.addItem(item);
                    controller.FullContactList.setSelectedIndex(i);
                    contactList.addItem(controller.FullContactList.getSelectedItem());
                }

            }
        }

    }
}
