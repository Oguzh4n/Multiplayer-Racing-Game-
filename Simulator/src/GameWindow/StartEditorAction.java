/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import Editor.Editor;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class StartEditorAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        String [] args = {""};
        Thread t = new Thread(() -> {
            Editor.main(args);
        });
        t.start();
    }

}
