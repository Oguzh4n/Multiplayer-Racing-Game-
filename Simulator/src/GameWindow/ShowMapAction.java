/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import createGameLobbyWindowAndAction.CreateGameLobby;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import Interfaces.Client;
import Interfaces.Connection;


public class ShowMapAction extends AbstractAction implements PropertyChangeListener {

    private SimulatorFrame frame = SimulatorFrame.getInstance();

    public ShowMapAction() {
        setEnabled(false);
        frame.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //<editor-fold defaultstate="collapsed" desc=" Useless Method, just here for watching PropertyChange things ">
        //get settings
        
        Thread t = new Thread(() -> {
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(CreateGameLobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(CreateGameLobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(CreateGameLobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(CreateGameLobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>


            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    CreateGameLobby lobby = new CreateGameLobby(new javax.swing.JFrame(), true);
                    lobby.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            lobby.dispose();
                        }
                    });
                    lobby.setVisible(true);
                }
            });
        });
        t.start();
         
        //</editor-fold>
    }

    public boolean isEnabled() {
        return frame.getInputFile() != null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setEnabled(frame.getInputFile() != null);
    }
}
