/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class LoadGameAction extends AbstractAction{
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Load CSV-File", "csv"));
        int showOpenDialog = fileChooser.showOpenDialog(SimulatorFrame.getInstance());
        
        if ( showOpenDialog == JFileChooser.APPROVE_OPTION){
            File input = fileChooser.getSelectedFile();
            SimulatorFrame.getInstance().chatModel.addElement("Loaded File: " + input.getName());
            SimulatorFrame.getInstance().setInputFile(input);
            SimulatorFrame.getInstance().setCsvData(input);
        } else {
            JOptionPane.showMessageDialog(SimulatorFrame.getInstance(), "No Selection");
        }
        
        
    }
    
}
