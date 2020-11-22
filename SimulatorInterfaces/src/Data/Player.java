/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.io.Serializable;
import Interfaces.Client;
import Interfaces.Server;



public class Player implements Serializable {
    
    public String username;
    private Client connectedClient;
    private Server connectedServer;
   

    public Player() {
        this.username = null;
    }              
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }  
    public Client getConnectedClient() {
        return connectedClient;
    }

    public void setConnectedClient(Client connectedClient) {
        this.connectedClient = connectedClient;
    }
    public Server getConnectedServer() {
        return connectedServer;
    }

    public void setConnectedServer(Server connectedServer) {
        this.connectedServer = connectedServer;
    }   
    
}