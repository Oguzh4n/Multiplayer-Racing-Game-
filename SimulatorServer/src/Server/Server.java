/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import Interfaces.Connection;


public class Server {
    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException, 
            AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(29871);    
        Connection connection = new ConnectionImpl();                
        UnicastRemoteObject.exportObject(connection, 0);            
        registry.bind(Connection.class.getName(), connection);      
        ServerMethods.getInstanceEngine();                           
        System.out.println("Server is running.");
        
       
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {               
                System.exit(0);
            }
        }, 3_600_000);
    }    
    
    
}
