/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.sun.lwuit.List;
import java.util.Vector;
import javax.microedition.lcdui.Gauge;

/**
 *
 * @author FOX
 */
public class Var {
    
    // dặt hết tên string ở đây để publib static 
    
    public static int hearder= 0x29A0C3;
    public static int colorBlack= 0x000000;
    public static int colorWhite= 0xffffff;
    
    public static  String about= "About";
    public static  String help= "Help";
    public static  String lang= "languages";
    public static  String setting= "setting";
    public static  String back= "back";
    
    
    public static int language =0;
    
    public static Vector v[]= new Vector[2];
    public static Vector itemsID= new Vector();
    public static Vector itemsPhone= new Vector();
    public static Vector itemsName= new Vector();
    public static String title[] = {"Save","Đã lưu"};
    public static String error[] = {"Error","Lỗi"};
    public static String NULL[] = {"Null","Đã trùng"};
    public static String loitext[]={"name or phone is null","Bạn chưa nhập thông tin"};
    public static String wait[] = {"Waitting","Xin chờ trong giây lát"};
   
    public Var() {
        taoList();
    }
    
    
    public void taoList(){
        
        v[0] = new Vector();
        v[0].addElement("edit");
        v[0].addElement("clear");
        v[0].addElement("share by SMS");
        v[0].addElement("share by Email");
        
        
        v[1] = new Vector();
        v[1].addElement("chỉnh sửa");
        v[1].addElement("xóa");
        v[1].addElement("chia sẻ bằng tin nhắn");
        v[1].addElement("chia sẻ bằng Email");
        
    }
    
    public static void doiNgonNgu(int k){
        if (k==0){
            //tieng anh
            about="About";
            help="Help";
            lang="languages";
            language = 0;
            setting="setting";
            back="back";
        }else {
            
            about="thông tin";
            help="giúp đỡ";
            lang="ngôn ngữ";
            setting="cài đặt";
            back="trở về";
            
            language = 1;
            
            // tiếng việt
        }
    }
    
}
