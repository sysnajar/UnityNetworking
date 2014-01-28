/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver.osc;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPort;
import com.illposed.osc.OSCPortOut;
import tcpserver.tcp.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sysnajar
 */
public class OSCServer implements Runnable{

    static int outPort = 57110; 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{ 
        final OSCServer server = new OSCServer();
        new Thread(server).start();
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { /*new GuiCmd(server).setVisible(true);*/
            }
        });
        
        boolean auto = false;
        if(auto)
          {
             while(true)
               {
                  Thread.sleep(2000);
                  server.send("L", "localhost", outPort);
               }
          }
        else
         {  
            //System input reader
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String cmd = in.readLine();
            while(cmd != null)
            {
                server.send(cmd, "localhost" , outPort);
                cmd = in.readLine();
            }
            //---------------------
         }
         
        
    } 
     
    public void run() 
    {
        try 
        {
          startServer();
        } catch (IOException ex) 
        {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void startServer() throws IOException
    {
         
    }
    
    
    public void send(String cmd, String ip, int port) throws IOException
    {
        OSCPortOut sender = new OSCPortOut(InetAddress.getByName(ip),port);
	Object args[] = new Object[2];
	args[0] = cmd;
        args[1] = 0;
        
	OSCMessage msg = new OSCMessage("/rotate", args);
	 try {
		sender.send(msg);
                
	 } catch (Exception e) {e.printStackTrace();
	 } 
    }
    
}
