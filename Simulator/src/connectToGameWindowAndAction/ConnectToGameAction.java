/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectToGameWindowAndAction;

import GameWindow.SimulatorFrame;
import GameWindow.SimulatorFrame;
import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import Interfaces.Client;
import Interfaces.Connection;


public class ConnectToGameAction extends AbstractAction {

    private SimulatorFrame frame = SimulatorFrame.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (frame.connected == true) {
            JOptionPane.showMessageDialog(null, "Already Connected");
            return;
        }
        
        String gameName = frame.connectToGameWindow.getjTextField_GameName().getText();
        String gameCode = frame.connectToGameWindow.getjTextField_GameCode().getText();
        
        if(gameName.equalsIgnoreCase("") || gameName == null){
            JOptionPane.showMessageDialog(frame.connectToGameWindow, "Input cannot be empty");
            return;
        }
        
        frame.gameName = gameName;
        frame.gameCode = gameCode;
        
        
        try {
            frame.registry = LocateRegistry.getRegistry(29871);
            frame.connection = (Connection) frame.registry.lookup(Connection.class.getName());
            
            frame.connect(gameName, gameCode);
            
            frame.connectToGameWindow.dispose();
   
            
        } catch (RemoteException ex) {
            Logger.getLogger(GameWindow.SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(GameWindow.SimulatorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
