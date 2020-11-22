/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Data.Player;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaces.Server;
import Interfaces.Client;
import Interfaces.Connection;


public class ConnectionImpl implements Connection {

    public ConnectionImpl() {
    }
    
    
    @Override
    public Server joinGame(Client client, Player player, String gameName, String code) throws RemoteException {
        try {

            String mesg = String.format("%s, user: %s joined the game",
                    RemoteServer.getClientHost(), player.username, gameName, code);
            System.out.println(mesg);
            Server serverFromClient = new ServerImpl(client);
            player.setConnectedServer(serverFromClient);
            Server serverExport = (Server) UnicastRemoteObject.exportObject(serverFromClient, 0);         
            ServerMethods.joinGame(serverFromClient, client, player, gameName, code);
            return serverExport;
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(ConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

    @Override
    public void createGame(String gameName, int count, String code) throws RemoteException {
        try {       
            String mesg = String.format("Game created from %s",
            RemoteServer.getClientHost(), gameName, count, code);
            System.out.println(mesg);
            
          
            ServerMethods.createGame(gameName, count, code);
            
           
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(ConnectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
