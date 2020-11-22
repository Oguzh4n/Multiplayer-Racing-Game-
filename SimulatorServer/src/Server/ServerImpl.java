/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Data.RaceTrack;

import Interfaces.Server;
import Interfaces.Client;


public class ServerImpl implements Server { 
    
    
    Client client;
    public ServerImpl(Client client) {
        this.client = client;
    }
    
   
    @Override
    public void sendString(String data) throws RemoteException {
        try {
            
            String mesg = String.format("String sent by %s, data: %s",
                    RemoteServer.getClientHost(), data);
            System.out.println(mesg);
                
           ServerMethods.sendString(this, data);  
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(ServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   
}
