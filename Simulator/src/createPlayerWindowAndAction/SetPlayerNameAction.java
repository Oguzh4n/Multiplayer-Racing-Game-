/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPlayerWindowAndAction;

import GameWindow.SimulatorFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


public class SetPlayerNameAction extends AbstractAction{

    private SimulatorFrame frame = SimulatorFrame.getInstance();
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = frame.createPlayerWindow.getjTextField_Username().getText();
        
        if(username.equalsIgnoreCase("") || username == null ){
            JOptionPane.showMessageDialog(frame.createPlayerWindow, "username cannot be empty!");
            return;
        }
        frame.player.setUsername(username);
        
        frame.createPlayerWindow.dispose();
    }
    
}
