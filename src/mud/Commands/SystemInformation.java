/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mud.Commands;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import mud.Tools.Crypto;

/**
 *
 * @author SaturnsVoid
 */
public class SystemInformation {
 //---------------------------------------------------------------------------------------------------\\
    public String CreateID(){
        Crypto Crypt = new Crypto();
        String a, b, c, d, e, ID; 
                a = System.getProperty("user.name");
                b = System.getenv("PROCESSOR_IDENTIFIER");
                c = System.getenv("COMPUTERNAME");
                d = System.getenv("os.arch");
                e = System.getenv("os.name");
                ID = a+b+c+d+e;
                ID = Crypt.MD5(ID);
        return ID;
    }
 //---------------------------------------------------------------------------------------------------\\
    public String GetOS(){
        return System.getProperty("os.name");
    }
    public String GetUsername(){
        return System.getProperty("user.name");
    }
    public String GetHome(){
        return System.getProperty("user.home");
    }
    public String GetExternalIP(){
                BufferedReader in = null;
		String ip = "";
                try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			ip = in.readLine();
		} catch (IOException e) {
		} 
                return ip;
    }
}
