/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import com.nokia.lwuit.GestureHandler;
import com.nokia.lwuit.components.ContextMenu;
import com.nokia.lwuit.components.HeaderBar;
import com.nokia.mid.ui.PopupList;
import com.nokia.mid.ui.PopupListItem;
import com.nokia.mid.ui.PopupListListener;
import com.nokia.mid.ui.gestures.GestureEvent;
import com.nokia.mid.ui.gestures.GestureInteractiveZone;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.geom.Rectangle;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import java.io.IOException;
import models.Friend;
import models.Util;
import models.Var;

/**
 *
 * @author TRITUEVIET
 */
public class ViewHome extends BaseScreen implements ActionListener {

    private Button[] user = new Button[14];
    private Container contain[] = new Container[14];
    private Friend friend[] = new Friend[14];
    private Button call;
    private Button sms;
    Container south,north, west, east;
    private boolean kt = false;
    private int gt = -1;
    Form form = new Form();
    Util imgadd;
    int vi;
    int contactx;
    public com.sun.lwuit.Command about;
    public com.sun.lwuit.Command help;
    public ViewHome() {
        imgadd = new Util();
    }

    public void showForm() {

        form.refreshTheme();// chinh form o day
        form.repaint();
        //Container parent = form.getParent();
        
        Display.getInstance().setForceFullScreen(true);
        Display.setObjectTrait(Display.getInstance().getImplementation(), "nokia.ui.canvas.status_zone", Boolean.TRUE);
        

        try {
            Image icall = Image.createImage("/images/make-call123.png");
            call = new Button(icall);
            call.getStyle().setPadding(0, 0, 0, 0);
            call.getPressedStyle().setPadding(0, 0, 0, 0);
            call.getUnselectedStyle().setPadding(0, 0, 0, 0);
            call.getSelectedStyle().setPadding(0, 0, 0, 0);
            call.getSelectedStyle().setMargin(0, 0, 0, 0);
            call.getStyle().setMargin(0, 0, 0, 0);
            call.getPressedStyle().setMargin(0, 0, 0, 0);
            call.getUnselectedStyle().setMargin(0, 0, 0, 0);
            call.getSelectedStyle().setMargin(0, 0, 0, 0);

            sms = new Button(Image.createImage("/images/message123.png"));
            sms.getStyle().setPadding(0, 0, 0, 0);
            sms.getPressedStyle().setPadding(0, 0, 0, 0);
            sms.getUnselectedStyle().setPadding(0, 0, 0, 0);
            sms.getSelectedStyle().setPadding(0, 0, 0, 0);
            sms.getSelectedStyle().setMargin(0, 0, 0, 0);
            sms.getStyle().setMargin(0, 0, 0, 0);
            sms.getPressedStyle().setMargin(0, 0, 0, 0);
            sms.getUnselectedStyle().setMargin(0, 0, 0, 0);
            sms.getSelectedStyle().setMargin(0, 0, 0, 0);

        } catch (Exception ea) {
        }
        
        form.setLayout(new BorderLayout());



        north = new Container(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            user[i] = new Button(imgadd.returnImage("", "", "", 0));
            //user[Integer.parseInt(Var.itemsID.elementAt(i).toString())].setIcon(imgadd.returnImage(Var.itemsName.elementAt(i).toString(), Var.itemsPhone.elementAt(i).toString(), "", 1));
            //user[i].addActionListener(this);
            contain[i] = new Container(new BorderLayout());
            contain[i].addComponent(BorderLayout.CENTER,user[i]);
            contain[i].getStyle().setMargin(0, 0, 5, 0);
            contain[i].getPressedStyle().setMargin(0, 0, 5, 0);
            contain[i].getUnselectedStyle().setMargin(0, 0, 5, 0);
            contain[i].getSelectedStyle().setMargin(0, 0, 5, 0);
            contain[i].getStyle().setBgColor(0x4F3376);
            north.addComponent(contain[i]);
        }
        north.getStyle().setMargin(0, 1, 0, 4);
        north.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        form.addComponent(BorderLayout.NORTH, north);

        east = new Container(new GridLayout(3, 1));
        for (int i = 4; i <= 6; i++) {
            user[i] = new Button(imgadd.returnImage("", "", "/images/contact234-1.png", 0));
            
//            user[i].getStyle().setMargin(5, 0, 0, 0);
//            user[i].getPressedStyle().setMargin(5, 0, 0, 0);
//            user[i].getUnselectedStyle().setMargin(5, 0, 0, 0);
//            user[i].getSelectedStyle().setMargin(5, 0, 0, 0);
            

            contain[i] = new Container(new BorderLayout());
            contain[i].addComponent(BorderLayout.CENTER,user[i]);
            contain[i].getStyle().setBgColor(0x4F3376);
            //contain[i].getStyle().setMargin(1, 1, 0, 0);
            //contain[i].getPressedStyle().setMargin(1, 1, 0, 0);
            //contain[i].getUnselectedStyle().setMargin(1, 1, 0, 0);
            //contain[i].getSelectedStyle().setMargin(1, 1, 0, 0);
            east.addComponent(contain[i]);
        }
        //east.getStyle().setMargin(2, 0, 0, 0);
        east.getStyle().setMargin(0, 4, 0, 4);
        east.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        form.addComponent(BorderLayout.EAST, east);

        south = new Container(new GridLayout(1, 4));
        for (int i = 10; i >= 7; i--) {
            user[i] = new Button(imgadd.returnImage("", "", "", 0));
            //user[i].addActionListener(this);

            contain[i] = new Container(new BorderLayout());
            contain[i].addComponent(BorderLayout.CENTER,user[i]);
            contain[i].getStyle().setMargin(0, 0, 5, 0);
            contain[i].getPressedStyle().setMargin(0, 0, 5, 0);
            contain[i].getUnselectedStyle().setMargin(0, 0, 5, 0);
            contain[i].getSelectedStyle().setMargin(0, 0, 5, 0);
            contain[i].getStyle().setBgColor(0x4F3376);
            south.addComponent(contain[i]);
        }
       
        south.getStyle().setMargin(0, 1, 0, 4);
        south.getStyle().setBgPainter(new Painter() {
 
            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        form.addComponent(BorderLayout.SOUTH, south);
        ////////
        
        ///////
        west = new Container(new GridLayout(3, 1));
        for (int i = 13; i >= 11; i--) {
           // user[i] = new Button(imgadd.returnImage("", "", "", 0));
            user[i] = new Button(imgadd.returnImage("", "", "/images/contact234-1.png", 0));
            //user[i].addActionListener(this);
            
            contain[i] = new Container(new BorderLayout());
            contain[i].addComponent(BorderLayout.CENTER,user[i]);
            contain[i].getStyle().setBgColor(0x4F3376);
//            contain[i].getStyle().setMargin(0, 5, 0, 0);
//            contain[i].getPressedStyle().setMargin(0, 5, 0, 0);
//            contain[i].getUnselectedStyle().setMargin(0, 5, 0, 0);
//            contain[i].getSelectedStyle().setMargin(0, 5, 0, 0);
//            contain[i].getStyle().setPadding(0, 0, 0, 0);
//            contain[i].getPressedStyle().setPadding(0, 0, 0, 0);
//            contain[i].getUnselectedStyle().setPadding(0, 0, 0, 0);
//            contain[i].getSelectedStyle().setPadding(0, 0, 0, 0);
              
            west.addComponent(contain[i]);
        }
        west.getStyle().setMargin(0, 5, 5, 0);
        west.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        //west.getStyle().setMargin(2, 0, 0, 0);
        form.addComponent(BorderLayout.WEST, west);
        
        for (int i = 0; i < 14; i++) {
            //user[i].setUIID("DemoButton");
            //user[i] = new Button(imgadd.returnImage("", "", "", 0));
            user[i].getStyle().setMargin(0, 0, 0, 0);
            user[i].getPressedStyle().setMargin(0, 0, 0, 0);
            user[i].getUnselectedStyle().setMargin(0, 0, 0, 0);
            user[i].getSelectedStyle().setMargin(0, 0, 0, 0);
            user[i].getStyle().setPadding(0, 0, 0, 0);
            user[i].getPressedStyle().setPadding(0, 0, 0, 0);
            user[i].getUnselectedStyle().setPadding(0, 0, 0, 0);
            user[i].getSelectedStyle().setPadding(0, 0, 0, 0);
            user[i].getStyle().setBgColor(0x4F3376);
            user[i].setPreferredSize(new Dimension(54, 46));
            try {
                user[i].setPressedIcon(Image.createImage("/images/contact234-Press.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            user[i].setAlignment(Component.CENTER);
            contain[i].setDraggable(true);
            contain[i].setDropTarget(true);
            user[i].setHideInPortrait(true);
            user[i].addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    //updateUser();
                }
            });

            for (int iter = 0; iter < contain[i].getComponentCount(); iter++) {
                contain[i].getComponentAt(iter).setDraggable(true);
            }
        }
        for (int i = 4; i <= 6; i++) {
           
            user[i].getStyle().setMargin(6, 0, 0, 0);
            user[i].getPressedStyle().setMargin(6, 0, 0, 0);
            user[i].getUnselectedStyle().setMargin(6, 0, 0, 0);
            user[i].getSelectedStyle().setMargin(6, 0, 0, 0);
            user[i].getStyle().setBgColor(0x4F3376);
           

        }
        for (int i = 13; i >= 11; i--) {
          
            user[i].getStyle().setMargin(6, 0, 0, 0);
            user[i].getPressedStyle().setMargin(6, 0, 0, 0);
            user[i].getUnselectedStyle().setMargin(6, 0, 0, 0);
            user[i].getSelectedStyle().setMargin(6, 0, 0, 0);
            user[i].getStyle().setBgColor(0x4F3376);
        }

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container center1 = new Container();
        Container center2 = new Container();

        call.setPreferredSize(new Dimension(113, 72));//120 75
        center1.addComponent(call);
        
        sms.setPreferredSize(new Dimension(113, 71));
        center2.addComponent(sms);
        center2.getStyle().setMargin(5, 5, 0, 0);
        
        center.addComponent(center1);
        center.addComponent(center2);
        center.getStyle().setMargin(5, 1, 5, 0);
        center.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        form.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        form.addComponent(BorderLayout.CENTER, center);

        form.setAlwaysTensile(true);
        form.setDraggable(true);
        form.setScrollableY(false);
        form.setScrollableX(false);
        form.addPointerReleasedListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                int x = ae.getX();
                int y = ae.getY();
                System.out.println("" + gt);

                if (call.contains(x, y) && gt != -1) {
                    System.out.println("call");
                    int vitri = -1;
                    for (int i = 0; i < Var.itemsID.size(); i++) {
                        if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == gt) {
                            vitri = i;
                            break;
                        }
                    }
                    if (vitri != -1) {
                        Display.getInstance().execute("tel://" + Var.itemsPhone.elementAt(vitri).toString());
                    }

                } else if (sms.contains(x, y) && gt != -1) {
                    int vitri = -1;
                    for (int i = 0; i < Var.itemsID.size(); i++) {
                        if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == gt) {
                            vitri = i;
                            break;
                        }
                    }
                    if (vitri != -1) {
                        Display.getInstance().execute("sms:" + Var.itemsPhone.elementAt(vitri).toString() + "?body=");
                    }
                } else {
                    gt = -1;
                }
                kt = false;
                gt = -1;

            }
        });
        ImageGridGestureHandler gestureHandler = new ImageGridGestureHandler();
        setupGestureHandler(gestureHandler);
        updateUser();
        about = new com.sun.lwuit.Command("About");
        help =  new com.sun.lwuit.Command("Help");
        form.addCommand(about);
        form.addCommand(help);
        form.addCommandListener(new ActionListener() {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getCommand().equals(about)) {
                //Do Exit command code
                
            controller.showAbout(0);
        
       
            } else if (ae.getCommand().equals(help)) {
                //Do Start command code
                controller.showHelp(0);
            }
        }
    });
        form.show();
    }

    public void updateUser() {
//        for (int i = 0; i < Var.itemsID.size(); i++) {
//            user[Integer.parseInt(Var.itemsID.elementAt(i).toString())].setIcon(imgadd.returnImage(Var.itemsName.elementAt(i).toString(), Var.itemsPhone.elementAt(i).toString(), "", 1));
//        }
        for (int i = 0; i < Var.itemsID.size(); i++) {
            contactx = Integer.parseInt(Var.itemsID.elementAt(i).toString()) ;
            user[contactx].setPressedIcon(imgadd.returnImage(Var.itemsName.elementAt(i).toString(), Var.itemsPhone.elementAt(i).toString(), "/images/contactName-Press.png", 1));
            if((contactx>=0 && contactx<=3)||(contactx>=7 && contactx<=10))
                user[Integer.parseInt(Var.itemsID.elementAt(i).toString())].setIcon(imgadd.returnImage(Var.itemsName.elementAt(i).toString(), Var.itemsPhone.elementAt(i).toString(), "", 1));
            else
                user[Integer.parseInt(Var.itemsID.elementAt(i).toString())].setIcon(imgadd.returnImage(Var.itemsName.elementAt(i).toString(), Var.itemsPhone.elementAt(i).toString(), "/images/contactName11.png", 1));
       
        }
    }

    public void contextMenu(final int vitri) {
        
        
      /*  try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        * */
        /* try {
        user[vitri].setIcon(Image.createImage("/images/contact2.png"));
        } catch (IOException ex) {
        ex.printStackTrace();
        }
         * */
        try {
           controller.categoryBar.setVisibility(false);
            
           //form.show();
           south.getStyle().setMargin(0, 50, 0, 0);
           
           Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
           }

        Display.getInstance().callSerially(new Runnable() {

           public void run() {
                Command[] cmds = null;
                System.out.println("" + Var.v[0].size());
                cmds = new Command[Var.v[0].size()];
                for (int i = 0; i < cmds.length; i++) {
                    cmds[i] = new Command(Var.v[Var.language].elementAt(i).toString());
                }
                //hanh
               Command selectedContextCmd = ContextMenu.show(cmds, form);
                
                
                //Command selectedContextCmd = ContextMenu.show(cmds,);
                //tim kiem chi so
                vi = -1;
                for (int i = 0; i < Var.itemsID.size(); i++) {
                    if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == vitri) {
                        vi = i;
                        break;
                    }
                }
                System.out.println("" + vi);
                //  xử lý sự kiện ở đây
                
                Controller.getInstance().showHome();
                if (vi == -1 ) {
                   
                 System.out.println("chay thu thu 1");
                 //try {
                      controller.categoryBar.setVisibility(true);
                      south.getStyle().setMargin(0, 0, 0, 0);
                      // Thread.sleep(100);
//                    } catch (InterruptedException ex) {
//                        ex.printStackTrace();
//                   }
                }

                if (selectedContextCmd == cmds[0]) {

                    if (vi != -1) {
                        Controller.getInstance().showSave(
                                Var.itemsName.elementAt(vi).toString(),
                                Var.itemsPhone.elementAt(vi).toString(),
                                vitri);

                    } else {
                        Controller.getInstance().showSave("", "", vitri);
                    }
                } else if (selectedContextCmd == cmds[1]) {

                    for (int i = 0; i < Var.itemsID.size(); i++) {
                        if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == vitri) {
                            Var.itemsID.removeElementAt(i);
                            Var.itemsName.removeElementAt(i);
                            Var.itemsPhone.removeElementAt(i);
                            break;
                        }

                    }
                    form.refreshTheme();
                    form.repaint();
                    Controller.getInstance().showHome();

                } else if (selectedContextCmd == cmds[2]) {

                    controller.waitScreen();
                    new Thread(new Runnable() {

                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            if (vi != -1) {
                                Display.getInstance().execute("sms:?body="
                                        + Var.itemsName.elementAt(vi).toString() + " "
                                        + Var.itemsPhone.elementAt(vi).toString());
                            } else {
                                Display.getInstance().execute("sms:?body=");
                            }
                        }
                    }).start();
                    System.out.println("dsff");
                    form.refreshTheme();
                    form.repaint();
                    Controller.getInstance().showHome();


                } else if (selectedContextCmd == cmds[3]) {


                    controller.waitScreen();
                    new Thread(new Runnable() {

                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            if (vi != -1) {
                                Display.getInstance().execute("mailto:?subject=" + " " + "&body=" + Var.itemsName.elementAt(vi).toString() + " "
                                        + Var.itemsPhone.elementAt(vi).toString());

                            } else {
                                Display.getInstance().execute("mailto:&body=" + " ");
                            }
                        }
                    }).start();
                    System.out.println("dsff");
                    form.refreshTheme();
                    form.repaint();
                    Controller.getInstance().showHome();

                }

            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
//        for (int i = 0; i < user.length; i++) {
//            if (user[i] == ae.getSource()) {
//                System.out.println("nhap" + i);// su kien nhap tt 
//
//            }
//        }
        if(ae.getSource()== about){
            controller.showAbout(0);
        }
        else if(ae.getSource() == help){
            controller.showHelp(0);
        }
        
    }

    private class ImageGridGestureHandler extends GestureHandler {

        public void gestureAction(GestureEvent ge) {
            int x = ge.getStartX();
            int y = ge.getStartY();

            switch (ge.getType()) {

                case GestureInteractiveZone.GESTURE_DRAG: {
                    //int x = ge.getStartX(), y = ge.getStartY();
                    // if(x<240&&y<)

                    if (kt == false) {
                        kt = true;
                        gt = -1;
                        for (int i = 0; i < 14; i++) {
                            //System.out.println("user: "+ i+"  : "+ user[i].+"  "+user[i].isDraggable());
                            if (user[i].contains(x, y)) {
                                System.out.println("++" + i);
                                gt = i;
                            }
                            // lay vi tri cua user                   
                        }
                        // System.out.println("" + ge.getStartX() + ".." + ge.getStartY());
                    }
                    break;
                }
                case GestureInteractiveZone.GESTURE_TAP: {
                   
                   
                    break;
                }
                case GestureInteractiveZone.GESTURE_LONG_PRESS: {
                     for (int i = 0; i < 14; i++) {
                        if (user[i].contains(x, y)) {
                            updateUser();
                            //contextMenu(i);
                            //ContextMenuDisplay(i);
                            showContextMenu(i);
                            break;
                        }
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }

    private void setupGestureHandler(GestureHandler handler) {
        GestureHandler.setFormGestureHandler(form, handler);
    }
    //hanh
     public void showContextMenu(final int vitri) {
            PopupList popupList = new PopupList();
            final PopupListItem editItem = new PopupListItem(
                    "Edit");
            final PopupListItem clearItem = new PopupListItem(
                    "Clear");
            final PopupListItem shareSMSItem = new PopupListItem(
                    "Share by SMS");
            final PopupListItem shareEmailItem = new PopupListItem(
                    "Share by Email");
                    
            popupList.setListener(new PopupListListener() {
                
                public void itemSelected(PopupList list, PopupListItem item) {
                     vi = -1;
                for (int i = 0; i < Var.itemsID.size(); i++) {
                    if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == vitri) {
                        vi = i;
                        break;
                    }
                }
                    
                   if (item == editItem) {
                       
                       //Controller.getInstance().showHome();                  
                        if (vi != -1) {      
                            Controller.getInstance().showHome();  
                            Controller.getInstance().showSave(
                                Var.itemsName.elementAt(vi).toString(),
                                Var.itemsPhone.elementAt(vi).toString(),
                                vitri);

                        }else {
                            Controller.getInstance().showHome();  
                            Controller.getInstance().showSave("", "", vitri);
                        }
                         
                }
                else if (item == clearItem) {

                    for (int i = 0; i < Var.itemsID.size(); i++) {
                        if (Integer.parseInt(Var.itemsID.elementAt(i).toString()) == vitri) {
                            Var.itemsID.removeElementAt(i);
                            Var.itemsName.removeElementAt(i);
                            Var.itemsPhone.removeElementAt(i);
                            break;
                        }

                    }
                    //form.refreshTheme();
                    //form.repaint();
                    Controller.getInstance().showHome();

                } 
                else if (item == shareSMSItem) {

                    controller.waitScreen();
                    new Thread(new Runnable() {

                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            if (vi != -1) {
                                Display.getInstance().execute("sms:?body="
                                        + Var.itemsName.elementAt(vi).toString() + " "
                                        + Var.itemsPhone.elementAt(vi).toString());
                            } else {
                                Display.getInstance().execute("sms:?body=");
                            }
                        }
                    }).start();
                    System.out.println("dsff");
                    //form.refreshTheme();
                   // form.repaint();
                    Controller.getInstance().showHome();


                } 
                else if (item == shareEmailItem) {


                    controller.waitScreen();
                    new Thread(new Runnable() {

                        public void run() {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            if (vi != -1) {
                                Display.getInstance().execute("mailto:?subject=" + " " + "&body=" + Var.itemsName.elementAt(vi).toString() + " "
                                        + Var.itemsPhone.elementAt(vi).toString());

                            } else {
                                Display.getInstance().execute("mailto:&body=" + " ");
                            }
                        }
                    }).start();
                    System.out.println("dsff");
                    //form.refreshTheme();
                    //form.repaint();
                    Controller.getInstance().showHome();

                }

            
            }

                public void listDismissed(PopupList list) {
                    
                   Controller.getInstance().showHome();
                }
            
            });                    
            popupList.appendItem(editItem);
            popupList.appendItem(clearItem);
            popupList.appendItem(shareSMSItem);
            popupList.appendItem(shareEmailItem);
            
            
            popupList.setVisible(true);
        }
     

  
}
