/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import com.sun.lwuit.Display;
import java.io.IOException;
import javax.microedition.lcdui.*;

/**
 *
 * @author TRITUEVIET
 */
public class ViewNormalCall extends Canvas implements Runnable {

    private int[] dd = new int[16];
    private Image img1, img2, back, back2;
    private String sdt = "";
    private boolean nhanNut = false;
    private Font font;
    private Command about;
    private Command help;

    public ViewNormalCall() {
        
        for (int i = 0; i < dd.length; i++) {
            dd[i] = 0;
        }
        try {

            back = Image.createImage("/images/back.png");
            back2 = Image.createImage("/images/back-hl.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sdt = "";
        
        about = new Command("About", Command.OK, 1);
        help = new Command("Help", Command.OK, 1);
        addCommand(help); 
        addCommand(about); 
        setCommandListener(new CommandListener() {

            public void commandAction(Command c, Displayable d) {
               if(c == about){
                   Controller.getInstance().showAbout(1);
               }
               if(c == help){
                   Controller.getInstance().showHelp(1);
               }
            }
        });
             
        repaint();
       

    }

    protected void paint(Graphics g) {
        if (nhanNut == false) {
            veNen(g);
        } else {
            stop(g);
        }
    }

    public void veNen(Graphics g) {
        // g.setColor(0x0000);
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        // System.out.println(""+ getWidth() + ".." + getHeight());
        if (dd[0] == 1) {
            g.drawImage(back2, 195, 1, Graphics.LEFT | Graphics.TOP);
        } else {
            g.drawImage(back, 195, 1, Graphics.LEFT | Graphics.TOP);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int tinh = i * 3 + 1 + j;
                if (dd[tinh] == 0) {
                    try {
                        g.drawImage(Image.createImage("/images/" + tinh + "-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        g.drawImage(Image.createImage("/images/" + tinh + ".png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        for (int i = 3; i <= 4; i++) {
            for (int j = 0; j < 3; j++) {
                int tinh = i * 3 + 1 + j;
                try {
                    switch (tinh) {
                        case 10:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/_-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/_.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 11:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/0-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/0.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 12:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/#-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/#.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 13:
                            g.drawImage(Image.createImage("/images/make-call1.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;
                        case 15:
                            g.drawImage(Image.createImage("/images/message1.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;
                        case 14:
                            g.drawImage(Image.createImage("/images/add-contact.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;

                    }
                } catch (Exception e) {
                }

            }
        }

        // g.setColor(0xFFFFFF);
        g.setColor(0x0000);
        System.out.println("" + sdt);
        if (sdt.length() > 17) {
            if (sdt.length() > 25) {
                font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
                g.setFont(font);
                g.drawSubstring(sdt, sdt.length() - 25, 25, 3, 10, Graphics.LEFT | Graphics.TOP);
            } else {
                font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
                g.setFont(font);
                g.drawString(sdt, 3, 10, Graphics.LEFT | Graphics.TOP);
            }
        } else {
            font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_LARGE);
            g.setFont(font);
            g.drawString(sdt, 3, 10, Graphics.LEFT | Graphics.TOP);
        }

    }

    public void run() {
    }

    public void stop(Graphics g) {
        // g.setColor(0x0000);
        g.setColor(0xFFFFFF);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (dd[0] == 1) {
            g.drawImage(back2, 195, 1, Graphics.LEFT | Graphics.TOP);
        } else {
            g.drawImage(back, 195, 1, Graphics.LEFT | Graphics.TOP);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int tinh = i * 3 + 1 + j;
                if (dd[tinh] == 0) {
                    try {
                        g.drawImage(Image.createImage("/images/" + tinh + "-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        g.drawImage(Image.createImage("/images/" + tinh + ".png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        for (int i = 3; i <= 4; i++) {
            for (int j = 0; j < 3; j++) {
                int tinh = i * 3 + 1 + j;
                try {
                    switch (tinh) {
                        case 10:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/_-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/_.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 11:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/0-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/0.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 12:
                            if (dd[tinh] == 0) {
                                g.drawImage(Image.createImage("/images/#-hl.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                                break;
                            } else {
                                g.drawImage(Image.createImage("/images/#.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            }
                            break;
                        case 13:
                            g.drawImage(Image.createImage("/images/make-call1.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;
                        case 15:
                            g.drawImage(Image.createImage("/images/message1.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;
                        case 14:
                            g.drawImage(Image.createImage("/images/add-contact.png"), j * 78 + j * 2 + 2, 40 + i * 2 + i * 40, Graphics.LEFT | Graphics.TOP);
                            break;
                    }
                } catch (Exception e) {
                }

            }

        }
        // g.setColor(0xFFFFFF);
        g.setColor(0x000000);
        System.out.println("" + sdt);
        if (sdt.length() > 17) {
            if (sdt.length() > 25) {
                font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
                g.setFont(font);
                g.drawSubstring(sdt, sdt.length() - 25, 25, 3, 10, Graphics.LEFT | Graphics.TOP);
            } else {
                font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
                g.setFont(font);
                g.drawString(sdt, 3, 10, Graphics.LEFT | Graphics.TOP);
            }
        } else {
            font = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_LARGE);
            g.setFont(font);
            g.drawString(sdt, 3, 10, Graphics.LEFT | Graphics.TOP);
        }
        try {
            Thread.sleep(100);
            reset();
            repaint();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public void reset() {
        for (int i = 0; i < dd.length; i++) {
            dd[i] = 0;
        }
    }

    protected void pointerPressed(int x, int y) {
        if (x > 194 && x <= 240 && y >= 1 && y <= 38) {
            dd[0] = 1;
            if (sdt.length() > 0) {
                sdt = sdt.substring(0, sdt.length() - 1);
            }

        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (x > j * 78 + j * 2 + 2 && x <= j * 78 + j * 2 + 2 + 78
                        && y > 40 + i * 2 + i * 40 && y <= 40 + i * 2 + i * 40 + 40) {
                    int tinh = i * 3 + 1 + j;
                    dd[tinh] = 1;
                    nhanNut = true;
                    if (tinh <= 9) {
                        sdt += tinh;
                    } else if (tinh == 10) {
                        sdt += "*";
                    } else if (tinh == 11) {
                        sdt += "0";
                    } else if (tinh == 12) {
                        sdt += "#";
                    } else if (tinh == 13) {
                        Display.getInstance().execute("tel://" + sdt);
                        //sdt = "";
                    } else if (tinh == 14) {
                        Controller.getInstance().categoryBar.setVisibility(false);
                        this.setFullScreenMode(true);
                        //this.removeCommand(about);
                        ViewSave view = new ViewSave(sdt);
                        //sdt = "";
                        view.show();
                       // this.addCommand(help);
                        //this.addCommand(about);

                    } else if (tinh == 15) {
                        Display.getInstance().execute("sms:" + sdt + "?body=" + "");
                        //sdt = "";
                        // platformRequest("sms:123?body=abcv");
                    }
                    repaint();
                    return;
                }
            }
        }


    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
