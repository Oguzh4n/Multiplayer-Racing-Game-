/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createPlayerWindowAndAction;


import connectToGameWindowAndAction.ConnectToGameAction;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class CreatePlayerWindow extends javax.swing.JDialog {

    /**
     * Creates new form PlayerSettings
     */
    public CreatePlayerWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_TitleConnect = new javax.swing.JLabel();
        jLabel_Username = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jButton_Continue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel_TitleConnect.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel_TitleConnect.setText("Create a Player");

        jLabel_Username.setText("Username:");

        jTextField_Username.setText("testUser");

        jButton_Continue.setAction(new createPlayerWindowAndAction.SetPlayerNameAction());
        jButton_Continue.setText("Create");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel_Username)
                .addGap(43, 43, 43)
                .addComponent(jTextField_Username, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(182, 182, 182))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_TitleConnect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jButton_Continue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_TitleConnect)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Continue)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Continue;
    private javax.swing.JLabel jLabel_TitleConnect;
    private javax.swing.JLabel jLabel_Username;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration//GEN-END:variables

   

    public JTextField getjTextField_Username() {
        return jTextField_Username;
    }

    public void setjTextField_Username(JTextField jTextField_Username) {
        this.jTextField_Username = jTextField_Username;
    }

    
}