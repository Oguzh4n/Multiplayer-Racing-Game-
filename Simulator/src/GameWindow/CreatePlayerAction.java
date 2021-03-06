/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import createPlayerWindowAndAction.CreatePlayerWindow;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class CreatePlayerAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {

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
                java.util.logging.Logger.getLogger(CreatePlayerAction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(CreatePlayerAction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(CreatePlayerAction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(CreatePlayerAction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    SimulatorFrame.getInstance().createPlayerWindow = new CreatePlayerWindow(new javax.swing.JFrame(), true);
                    SimulatorFrame.getInstance().createPlayerWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            SimulatorFrame.getInstance().createPlayerWindow.dispose();
                        }
                    });
                    SimulatorFrame.getInstance().createPlayerWindow.setVisible(true);
                }
            });
        });
        t.start();
    }
}
