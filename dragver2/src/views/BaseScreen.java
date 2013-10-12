/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Control.Controller;
import com.sun.lwuit.Form;


/**
 *
 * @author TRITUEVIET
 */
public class BaseScreen extends Form{
    protected Controller controller;
    public BaseScreen() {
        controller= Controller.getInstance();
    }
    
}
