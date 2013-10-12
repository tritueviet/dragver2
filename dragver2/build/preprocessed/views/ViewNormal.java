/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import com.sun.lwuit.*;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.geom.Dimension;
import com.sun.lwuit.geom.Rectangle;
import com.sun.lwuit.layouts.BorderLayout;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import java.io.IOException;

/**
 *
 * @author FOX
 */
public class ViewNormal extends BaseScreen implements ActionListener {

    Label lb = new Label("                       ");
    Button bt[] = new Button[15];
    Button back;
    String text = "";
    public Command about;
    public Command help;

    public ViewNormal() {
        setLayout(new BorderLayout());

        Container center = new Container(new GridLayout(5, 3));
        Container north = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c = new Container(new BorderLayout());
        Container c1 = new Container(new BorderLayout());// chua tfname
        Container c2 = new Container(new BorderLayout());// browser
        lb.setSize(new Dimension(250, 40));
        lb.setWidth(250);
        lb.setTickerEnabled(false);
        lb.setEndsWith3Points(false);

            back = new Button();
            back.setPreferredSize(new Dimension(37, 37));
            back.addActionListener(this);
            back.setUIID("DemoButton");
            back.getStyle().setMargin(0, 0, 0, 8);
            back.getPressedStyle().setMargin(0, 0, 0, 8);
            back.getUnselectedStyle().setMargin(0, 0, 0, 8);
            back.getSelectedStyle().setMargin(0, 0, 0, 8);
            back.getStyle().setPadding(0, 0, 0, 0);
            back.getPressedStyle().setPadding(0, 0, 0, 0);
            back.getUnselectedStyle().setPadding(0, 0, 0, 0);
            back.getSelectedStyle().setPadding(0, 0, 0, 0);
       

        c1.addComponent(BorderLayout.WEST, lb);
        c1.setPreferredW(200);

        c2.addComponent(BorderLayout.EAST, back);
        c.addComponent(BorderLayout.CENTER, c1);
        c.addComponent(BorderLayout.EAST, c2);
        c.getStyle().setMargin(0, 0, 0, 0);
        c.getStyle().setPadding(0, 0, 0, 0);
        north.addComponent(c);
        for (int i = 0; i < 9; i++) {
                bt[i] = new Button();
                center.addComponent(bt[i]);
        }
        
        try {//Image.createImage("/images/_-hl.png")
            bt[9] = new Button();
            bt[10] = new Button();
            bt[11] = new Button();
            bt[12] = new Button(Image.createImage("/images/make-call1.png"));
            bt[14] = new Button(Image.createImage("/images/message1.png"));
            bt[13] = new Button(Image.createImage("/images/add-contact.png"));

            center.addComponent(bt[9]);
            center.addComponent(bt[10]);
            center.addComponent(bt[11]);
            center.addComponent(bt[12]);
            center.addComponent(bt[13]);
            center.addComponent(bt[14]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < 15; i++) {
            bt[i].addActionListener(this);
            bt[i].setUIID("DemoButton");
            bt[i].setPreferredSize(new Dimension(78, 40));
            bt[i].getStyle().setMargin(0, 0, 0, 0);
            bt[i].getPressedStyle().setMargin(0, 0, 0, 0);
            bt[i].getUnselectedStyle().setMargin(0, 0, 0, 0);
            bt[i].getSelectedStyle().setMargin(0, 0, 0, 0);

           
            bt[i].getStyle().setPadding(0, 0, 0, 0);
            bt[i].getPressedStyle().setPadding(0, 0, 0, 0);
            bt[i].getUnselectedStyle().setPadding(0, 0, 0, 0);
            bt[i].getSelectedStyle().setPadding(0, 0, 0, 0);
            bt[i].setAlignment(Component.CENTER);

        }
        init();
//            center.getStyle().setMargin(0, 0, 5, 5);
//            center.getPressedStyle().setMargin(0, 0, 5, 5);
//            center.getUnselectedStyle().setMargin(0, 0, 5, 5);
//            center.getSelectedStyle().setMargin(0, 0, 5, 5);
//            center.getStyle().setPadding(0, 0, 0, 0);
//            center.getPressedStyle().setPadding(0, 0, 0, 0);
//            center.getUnselectedStyle().setPadding(0, 0, 0, 0);
//            center.getSelectedStyle().setPadding(0, 0, 0, 0);
        center.getStyle().setBgPainter(new Painter() {

            public void paint(Graphics grphcs, Rectangle rctngl) {
                grphcs.setColor(0x4F3376);
                grphcs.fillRect(rctngl.getX(),rctngl.getY(), rctngl.getSize().getWidth(), rctngl.getSize().getHeight());
            }
        });
        addComponent(BorderLayout.NORTH, north);
        addComponent(BorderLayout.CENTER, center);
        about = new com.sun.lwuit.Command("About");
        help = new com.sun.lwuit.Command("Help");
        addCommand(about);
        addCommand(help);
        addCommandListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (ae.getCommand().equals(about)) {
                    //Do Exit command code

                    controller.showAbout(1);
                } else if (ae.getCommand().equals(help)) {
                    //Do Start command code
                    controller.showHelp(1);
                }
            }
        });
        show();

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            if (text.length() > 0) {
                text = text.substring(0, text.length()-1);
                add();
                return;
            }
        }
        for (int i = 0; i < 15; i++) {
            if (i < 9) {
                if (ae.getSource() == bt[i]) {
                    text += (i + 1);
                    add();
                }
            } else if (i == 9 && ae.getSource() == bt[i]) {
                text = text + "*";
                add();
                return;
            } else if (i == 10 && ae.getSource() == bt[i]) {
                text += "0";
                add();
                return;
            } else if (i == 11 && ae.getSource() == bt[i]) {
                text += "#";
                add();
                return;
            } else if (i == 12 && ae.getSource() == bt[i]) {
                Display.getInstance().execute("tel://" + text);
            } else if (i == 13 && ae.getSource() == bt[i]) {
                Controller.getInstance().categoryBar.setVisibility(false);
                ViewSave view = new ViewSave(text);
                view.show();
            } else if (i == 14 && ae.getSource() == bt[i]) {
                Display.getInstance().execute("sms:" + text + "?body=" + "");

            }
        }
    }

    public void add() {
        String s = "";
        Font font;
        if (text.length() > 16) {
            if (text.length() >= 22) {
                font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
                s = text.substring(text.length() - 22, text.length());

            } else {
                font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
                s = text;
            }
        } else {
            font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
            s = text;
        }
        lb.getStyle().setFont(font);
        lb.setText(s);
        this.show();
        
    }
    public void init(){//Image.createImage("/images/" + (i + 1) + "-hl.png")
        bt[0].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/1.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[0].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/1-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[1].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/2.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[1].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/2-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[2].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/3.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[2].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/3-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[3].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/4.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[3].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/4-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[4].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/5.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[4].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/5-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[5].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/6.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[5].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/6-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[6].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/7.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[6].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/7-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[7].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/8.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[7].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/8-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[8].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/9.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[8].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/9-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[9].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/_.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[9].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/_-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[10].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/0.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[10].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/0-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[11].getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/#.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        bt[11].getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/#-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
       back.getPressedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/back.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
       back.getUnselectedStyle().setBgPainter(new Painter() {

                public void paint(Graphics grphcs, Rectangle rctngl) {
                    try {
                        grphcs.drawImage(Image.createImage("/images/back-hl.png"), rctngl.getX(), rctngl.getY());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        
    }
}
