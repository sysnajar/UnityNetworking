/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author sysnajar
 */
public class Client 
{  
   Socket sock; 
   BufferedReader r;
   PrintWriter w;
           
    
   public Client(Socket _sock) throws IOException
   {
      sock = _sock;
      
       InputStream in   = sock.getInputStream();
       OutputStream out = sock.getOutputStream();
         
       r = new BufferedReader(new InputStreamReader(in));
       w = new PrintWriter(out,true); 
   }
   
   public void send(String str) throws IOException
   {
     w.println(str);
   }
   
}
