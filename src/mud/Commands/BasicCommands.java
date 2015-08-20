/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mud.Commands;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.net.URI;
/**
 *
 * @author SaturnsVoid
 */
public class BasicCommands {
    
    public void ShowMSGBOX(String st){
        JOptionPane.showMessageDialog(null,st);
    }   
    
    public void OpenWebsite(String st){
        SystemInformation Info = new SystemInformation();
        try {
             if (Info.GetOS().toLowerCase().contains("win")){
                 Desktop.getDesktop().browse(new URI(st));
             } else if (Info.GetOS().toLowerCase().contains("mac")){
                 Runtime runtime = Runtime.getRuntime();
                 String[] args = { "osascript", "-e", "open location \"" + st + "\"" };
                 try{
                     Process process = runtime.exec(args);
                    }
                 catch (Exception e){
                    }
             } else if (Info.GetOS().toLowerCase().contains("nix")){
                 //Runtime runtime = Runtime.getRuntime();
                 //runtime.exec("/usr/bin/firefox -new-window " + st);
             } else if (Info.GetOS().toLowerCase().contains("nux")){
                 Runtime runtime = Runtime.getRuntime();
                 runtime.exec("/usr/bin/firefox -new-window " + st);
             } else if (Info.GetOS().toLowerCase().contains("sunos")){
                 //Runtime runtime = Runtime.getRuntime();
                 //runtime.exec("/usr/bin/firefox -new-window " + st);
             }  
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }
}
