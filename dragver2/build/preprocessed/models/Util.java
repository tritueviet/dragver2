/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import javax.microedition.pim.PIMItem;

/**
 *
 * @author TRITUEVIET
 */
public class Util extends Canvas{
    public Image img = null;
    
    public com.sun.lwuit.Image returnImage(String name, String phone, String image,int kt){
        if(kt==0){
        img = Image.createImage(54,46 );// 60 ,51
        if(image.length()==0)
            image = "/images/contact234.png";
        
        Graphics g= img.getGraphics();
        try {
            g.drawImage(Image.createImage(image), 0, 0, Graphics.LEFT| Graphics.TOP);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //vẽ string tại đây
        String a = name.trim();
        g.setColor(0xffffff);
        Font f = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
        if(a.length()<6){
            g.drawString(a, 2, 0,Graphics.LEFT| Graphics.TOP );
        }
        else {
            int min = Math.min(a.length(),12);
            String x1 = a.substring(0, 5);
            String x2 = a.substring(5,min);
            g.drawString(x1, 2, 0,Graphics.LEFT| Graphics.TOP );
            g.drawString(x2, 2, 18,Graphics.LEFT| Graphics.TOP );
        }
        com.sun.lwuit.Image imgLwuit=com.sun.lwuit.Image.createImage(img);
        return imgLwuit;
        }
        else {
        img = Image.createImage(54,46 );// 60 ,51
        if(image.length()==0)
            image = "/images/contactName1.png";
        
        Graphics g= img.getGraphics();
        try {
            g.drawImage(Image.createImage(image), 0, 0, Graphics.LEFT| Graphics.TOP);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //vẽ string tại đây
        String a = name.trim();
        g.setColor(0xffffff);
        Font f = Font.getFont(Font.FACE_SYSTEM, 0, Font.SIZE_SMALL);
        if(a.length()<7){
            g.drawString(a, 2, 0,Graphics.LEFT| Graphics.TOP );
        }
        else {
            int min = Math.min(a.length(),12);
            String x1 = a.substring(0, 6);
            String x2 = a.substring(6,min);
            g.drawString(x1, 2, 0,Graphics.LEFT| Graphics.TOP );
            g.drawString(x2, 2, 18,Graphics.LEFT| Graphics.TOP );
        }
        com.sun.lwuit.Image imgLwuit=com.sun.lwuit.Image.createImage(img);
        return imgLwuit;

        }
    }

    protected void paint(Graphics g) {
        
    }
    
    
    //   tra ve 0 la okie 1 là k thanh cong 2 la trung ten
    public int SaveContact(String phoneNo, String nameContact, String txtFax, String email, String TestCorporation) {
        int ok = 0;
        ContactList clist = null;
        try {
            clist = (ContactList) PIM.getInstance().openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE);
        } catch (PIMException e) {
            e.printStackTrace();
            System.out.println("loi doc");
            ok = 1;
        }
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
                //contact đã tồn tại
                wasFound = true;
                result = (Contact) itemsEnum.nextElement();
                ok = 2;
            }
            if (!wasFound) {
                // contact chưa tồn tại tạo mới nó
                String[] name = new String[clist.stringArraySize(Contact.NAME)];
                if (clist.isSupportedArrayElement(Contact.NAME, Contact.NAME_GIVEN)) {
                    name[Contact.NAME_GIVEN] = nameContact;
                }
                result.addStringArray(Contact.NAME, PIMItem.ATTR_NONE, name);

                if (clist.isSupportedField(Contact.TEL)) {
                    result.addString(Contact.TEL, Contact.ATTR_MOBILE, phoneNo);
                    result.addString(Contact.TEL, Contact.ATTR_FAX, txtFax);

                }

                if (clist.isSupportedField(Contact.EMAIL)) {
                    result.addString(Contact.EMAIL, Contact.ATTR_NONE, email);
                }

                if (clist.isSupportedField(Contact.ORG)) {
                    result.addString(Contact.ORG, Contact.ATTR_NONE, TestCorporation);
                }

                if (clist.isSupportedField(Contact.BIRTHDAY)) {
                    //result.addDate(Contact.BIRTHDAY, Contact.ATTR_NONE, Long.parseLong(date));
                }
            }
            try {
                result.commit();
            } catch (PIMException f) {
                System.out.println(" lỗi save");
                ok = 1;
            }
        } catch (Exception e) {
            System.out.println("loi..........");
            e.printStackTrace();
        } finally {
            searchPattern = null;
            result = null;
            itemsEnum = null;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    System.out.println("loi dong");
                    ok = 1;
                }
                is = null;
            }
            if (clist != null) {
                try {
                    clist.close();
                } catch (PIMException e) {
                    System.out.println("loi dong");
                    ok = 1;
                }
                clist = null;
            }
        }
        return ok;
    }
}
