/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Data.PlayerList;
import Interfaces.Client;

/**
 *
 * @author Peter Heusch
 */
public class ClientImpl implements Client { 

    public ClientImpl() {
    }
    @Override
    public void receiveString(String msg) {
        EventQueue.invokeLater(() -> {
            try {
                Thread.sleep(50);
                SimulatorFrame.getInstance().chatModel.addElement(msg);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    @Override
    public void receivePlayerDatabase(PlayerList player) throws RemoteException {
        EventQueue.invokeLater(() -> {
            try {
                Thread.sleep(50);

            } catch (InterruptedException ex) {
                Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        SimulatorFrame.getInstance().playerDatabase = player;
    }

   
    
   
}
