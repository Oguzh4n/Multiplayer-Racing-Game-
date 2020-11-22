/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createGameLobbyWindowAndAction;

import GameWindow.ShowMapAction;
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


public class CreateGameAction extends AbstractAction{

    private SimulatorFrame frame = SimulatorFrame.getInstance();
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String gameName = frame.createGameLobbyWindow.getjTextField_GameName().getText();
        String gameCode = frame.createGameLobbyWindow.getjTextField_GameCode().getText();
        int playerCount = frame.createGameLobbyWindow.calcPlayerCount();
        
      
        frame.playerCount = playerCount;
        frame.gameName = gameName;
        frame.gameCode = gameCode;
        //create game

            try {
                frame.registry = LocateRegistry.getRegistry(29871);
                frame.connection = (Connection) frame.registry.lookup(Connection.class.getName());
                
                frame.connection.createGame(frame.gameName, frame.playerCount, frame.gameCode);

            } catch (RemoteException ex) {
                Logger.getLogger(ShowMapAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(ShowMapAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        frame.createGameLobbyWindow.dispose();
    }
    
}
