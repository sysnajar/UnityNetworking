/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sysnajar
 */
public class TcpServer implements Runnable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{ 
        final TcpServer server = new TcpServer();
        new Thread(server).start();
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new GuiCmd(server).setVisible(true);
            }
        });
        
        
        //System input reader
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String cmd = in.readLine();
        while(cmd != null)
        {
            server.sendToAll(cmd);
            cmd = in.readLine();
        }
        //---------------------
         
        
    }
    
    int port;
    
    volatile List<Client> clients = new ArrayList();
    
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
         ServerSocket ss = new ServerSocket(port==0?5555:port);
         
         while(true)
         { 
            clients.add(new Client(ss.accept()));
         }
    }
    
    
    public void sendToAll(String cmd) throws IOException
    {
      for(Client c : clients) 
          c.send(cmd);  
    }
    
}
