/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import GameWindow.SimulatorFrame;
import Data.RaceTrack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Editor extends javax.swing.JFrame {

    public RaceTrack raceTrack = new RaceTrack();
    private int state = 1;
    private boolean drawStartFinish = false; //Check if Start/Finish is drawn
    
    private boolean drawOuterTrack = false; //Check if outer track is drawn
    private boolean drawInnerTrack = false; //Check if inner track is drawn
    private boolean setLastPoint = false; //Check if Track is Connected to Start/Finish
    private boolean drawGrid = false;  //Check if Grid is drawn
    
  
    private Rectangle gridRect;
    private Rectangle screenRect;

    /**
     * Creates new form CreationFrame
     */
    
    public Editor() {
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
        setValues();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private void setValues() {
        raceTrack.setName(jTextField_Name.getText());
        raceTrack.setWidth(1200);
        raceTrack.setHeigh(800);
        raceTrack.setGrid(15);
        raceTrack.calcGridStuff();
    }

    private void checkStartFinish() {
        if (raceTrack.getCoordStart().size() == 2) {
            drawStartFinish = true;
        }
    }

    private void checkForGrid() {
      
            drawGrid = true;
            
        
    }

    private void checkOutterTrack() {
        if (setLastPoint) {
            if (raceTrack.getCoordOuter().size() % 2 == 0) {
                drawOuterTrack = true;
                setLastPoint = false;
            }
        }
    }

    private void checkInnerTrack() {
        if (setLastPoint) {
            if (raceTrack.getCoordInner().size() % 2 == 0) {
                drawInnerTrack = true;
                setLastPoint = false;
            }
        }
    }

//    private void checkState5() {
//        if (raceTrack.getStartPoints().size() == 5) {
//            state5finished = true;
//        }
//    }

    private void useUNDO() {
        setLastPoint = false;
        switch (state) {
            case 1:
                raceTrack.getCoordStart().clear();
                drawStartFinish = false;
                break;
            case 2:
               
                drawGrid = false;
                break;
            case 3:
                if (raceTrack.getCoordOuter().size() < 2) {
                    JOptionPane.showMessageDialog(this, "No Points to delete.");
                    return;
                }
                raceTrack.getCoordOuter().remove(raceTrack.getCoordOuter().size() - 1);
                drawOuterTrack = false;
                break;
            case 4:
                if (raceTrack.getCoordInner().size() < 2) {
                    JOptionPane.showMessageDialog(this, "No Points to delete.");
                    return;
                }
                raceTrack.getCoordInner().remove(raceTrack.getCoordInner().size() - 1);
                drawInnerTrack = false;
                break;
            case 5:
                raceTrack.getStartPoints().remove(raceTrack.getStartPoints().size() - 1);
               
                break;
            default:
                JOptionPane.showMessageDialog(this, "Something went wrong!");
        }
    }

    private void addPoint(Point point) {
        switch (state) {
            case 1:
                checkStartFinish();
                if (drawStartFinish) {
                    JOptionPane.showMessageDialog(this, "State already done.");
                    return;
                }
                raceTrack.getCoordStart().add(point);
                if (raceTrack.getCoordStart().size() == 1) {
                    raceTrack.getCoordOuter().add(0, point);
                } else {
                    raceTrack.getCoordInner().add(0, point);
                }
                break;
            case 2:
                if (!drawOuterTrack || !drawInnerTrack || !drawStartFinish) {
                    JOptionPane.showMessageDialog(this, "Finish In- and Outline first.");
                    return;
                }
                checkForGrid();
                if (drawGrid) {
                    JOptionPane.showMessageDialog(this, "State already done.");
                    return;
                }
               
               
                break;
            case 3:
                if (!drawStartFinish) {
                    JOptionPane.showMessageDialog(this, "Finish START LINE first.");
                }
                checkOutterTrack();
                if (drawOuterTrack) {
                    JOptionPane.showMessageDialog(this, "State already done.");
                    return;
                }
                raceTrack.getCoordOuter().add(point);
                checkOutterTrack();
                break;
            case 4:
                if (!drawStartFinish) {
                    JOptionPane.showMessageDialog(this, "Finish START LINE first.");
                }
                checkInnerTrack();
                if (drawInnerTrack) {
                    JOptionPane.showMessageDialog(this, "State already done.");
                    return;
                }
                raceTrack.getCoordInner().add(point);
                checkInnerTrack();
                break;
            case 5:
                if (!drawOuterTrack || !drawInnerTrack || !drawStartFinish || !drawGrid) {
                    JOptionPane.showMessageDialog(this, "Finish all other things first.");
                    return;
                }
               
                Point startPoint = calcClosePoint(raceTrack.getValidPoints(), point);
                if (!raceTrack.getStartPoints().contains(startPoint)) {
                    raceTrack.getStartPoints().add(startPoint);
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Something went wrong!");
        }
    }
    
    private Point calcClosePoint(ArrayList<Point> list, Point point) {
        double range = Integer.MAX_VALUE;
        double tempRange;
        Point tempPoint = new Point();

        for (Point listPoint : list) {
            if ((tempRange = Math.sqrt((Math.pow(point.x - listPoint.x, 2) + Math.pow(point.y - listPoint.y, 2)))) < range) {
                range = tempRange;
                tempPoint = listPoint;
            }
        }
        System.out.println(tempPoint.toString());
        return tempPoint;
    }

    /*<editor-fold defaultstate="collapsed" desc="Useless Method">   
    private ArrayList<Point> calcStartPoints(ArrayList<Point> validPoints, Point midPoint) {

        ArrayList<Point> startPoints = new ArrayList<Point>();
        int range = Integer.MAX_VALUE;
        int tempRange;
        Point tempPoint = new Point();

        for (int i = 0; i < 5; i++) {
            for (Point listPoint : validPoints) {
                if (!startPoints.contains(listPoint)) {
                    if (listPoint.y > midPoint.y) {
                        if ((tempRange = Math.abs((midPoint.x + midPoint.y) - (listPoint.x + listPoint.y))) < range) {
                            range = tempRange;
                            tempPoint = listPoint;
                        }
                    }
                }
                startPoints.add(tempPoint);
            }
        }
        System.out.println(startPoints.toString());
        return startPoints;
    }
     */
    //</editor-fold>
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_Work = new javax.swing.ButtonGroup();
        jPanel1 = new CreationPanel();
        jButton_Undo = new javax.swing.JButton();
        jButton_LastPoint = new javax.swing.JButton();
        jButton_SaveMap = new javax.swing.JButton();
        jRadioButton_Start = new javax.swing.JRadioButton();
        jRadioButton_Out = new javax.swing.JRadioButton();
        jRadioButton_In = new javax.swing.JRadioButton();
        jTextField_Name = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calcMousePoint(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1333, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        jButton_Undo.setText("Delete");
        jButton_Undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UndoActionPerformed(evt);
            }
        });

        jButton_LastPoint.setText("Connect to Start/Finish");
        jButton_LastPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastPointActionPerformed(evt);
            }
        });

        jButton_SaveMap.setText("Save\n");
        jButton_SaveMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SaveMapActionPerformed(evt);
            }
        });

        buttonGroup_Work.add(jRadioButton_Start);
        jRadioButton_Start.setSelected(true);
        jRadioButton_Start.setText("Start Line");
        jRadioButton_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGroup_ActionPerformed_State(evt);
            }
        });

        buttonGroup_Work.add(jRadioButton_Out);
        jRadioButton_Out.setText("Outter Line");
        jRadioButton_Out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGroup_ActionPerformed_State(evt);
            }
        });

        buttonGroup_Work.add(jRadioButton_In);
        jRadioButton_In.setText("Inner Line");
        jRadioButton_In.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGroup_ActionPerformed_State(evt);
            }
        });

        jTextField_Name.setBackground(new java.awt.Color(204, 204, 204));
        jTextField_Name.setText("RaceTrack");
        jTextField_Name.setBorder(javax.swing.BorderFactory.createTitledBorder("Name"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(jRadioButton_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Out, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_In, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(385, 385, 385)
                        .addComponent(jButton_LastPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Undo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_SaveMap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(123, 123, 123))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_SaveMap, jButton_Undo, jRadioButton_In, jRadioButton_Out, jRadioButton_Start, jTextField_Name});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton_In)
                        .addComponent(jRadioButton_Out)
                        .addComponent(jRadioButton_Start)
                        .addComponent(jButton_LastPoint)
                        .addComponent(jButton_Undo)
                        .addComponent(jButton_SaveMap)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_LastPoint, jButton_SaveMap, jButton_Undo, jRadioButton_In, jRadioButton_Out, jRadioButton_Start, jTextField_Name});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Actions">
    private void calcMousePoint(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcMousePoint

 
        int x = evt.getX();
        int y = evt.getY();
        if (!gridRect.contains(x, y)) {
            return;
        }
        addPoint(new Point(x - gridRect.x, y - gridRect.y));
        checkStartFinish();
        checkForGrid();


        repaint();

    }//GEN-LAST:event_calcMousePoint

    private void jButton_LastPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastPointActionPerformed
        // TODO add your handling code here:
        setLastPoint = true;
        checkStartFinish();
        checkForGrid();
        switch (state) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                checkOutterTrack();
                break;
            case 4:
                checkInnerTrack();
                break;
            case 5:
                break;
            default:
                JOptionPane.showMessageDialog(this, "ERROR!");
        }
        repaint();
    }//GEN-LAST:event_jButton_LastPointActionPerformed

    private void jButton_UndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UndoActionPerformed
        // TODO add your handling code here:
        useUNDO();
        this.repaint();
    }//GEN-LAST:event_jButton_UndoActionPerformed

    private void jButton_SaveMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SaveMapActionPerformed
        // TODO add your handling code here:        
            setValues();
            JOptionPane.showMessageDialog(this, "Track has been Saved!");
            raceTrack.exportFile();
            this.dispose();
          
    }//GEN-LAST:event_jButton_SaveMapActionPerformed

    private void jRadioButtonGroup_ActionPerformed_State(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonGroup_ActionPerformed_State
        if (jRadioButton_Start.isSelected()) {
            state = 1;
        }  else if (jRadioButton_Out.isSelected()) {
            state = 3;
        } else if (jRadioButton_In.isSelected()) {
            state = 4;
        } else {
            JOptionPane.showMessageDialog(this, "ERROR!");
        }
 
    }//GEN-LAST:event_jRadioButtonGroup_ActionPerformed_State

    // </editor-fold> 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor();

            }
        }
        );
    }

    public void windowClosing(java.awt.event.WindowEvent e) {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_Work;
    private javax.swing.JButton jButton_LastPoint;
    private javax.swing.JButton jButton_SaveMap;
    private javax.swing.JButton jButton_Undo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_In;
    private javax.swing.JRadioButton jRadioButton_Out;
    private javax.swing.JRadioButton jRadioButton_Start;
    private javax.swing.JTextField jTextField_Name;
    // End of variables declaration//GEN-END:variables

    private class CreationPanel extends JPanel {

        public CreationPanel() {
        }

        public void paintComponent(Graphics gc) {
            super.paintComponent(gc);
            Graphics2D g2d = (Graphics2D) gc;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //GameSize
            screenRect = this.getBounds();
            gridRect = new Rectangle(screenRect.width / 2 - raceTrack.getWidth() / 2, 0, raceTrack.getWidth(), raceTrack.getHeigh());
            //Background
            g2d.setColor(Color.WHITE);
            g2d.fillRect(screenRect.width / 2 - raceTrack.getWidth() / 2, 0, raceTrack.getWidth(), raceTrack.getHeigh());

            //Draw Start / Finish line
            if (drawStartFinish) {
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(5.0f));
                g2d.drawLine(gridRect.x + raceTrack.getCoordStart().get(0).x, gridRect.y + raceTrack.getCoordStart().get(0).y,
                        gridRect.x + raceTrack.getCoordStart().get(1).x, gridRect.y + raceTrack.getCoordStart().get(1).y);
            }

            // Draw Grid
            if (drawGrid) {
                g2d.setColor(Color.DARK_GRAY);
                g2d.setStroke(new BasicStroke(5.0f));
               

            }

            //Draw ouTer track
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(1.0f));
            GeneralPath pathFormOuter = new GeneralPath(0);
            if (!raceTrack.getCoordOuter().isEmpty()) {
                //start outer track
                pathFormOuter.moveTo(gridRect.x + raceTrack.getCoordOuter().get(0).x, gridRect.y + raceTrack.getCoordOuter().get(0).y);

                //draw outer track
                for (int i = 1; i < raceTrack.getCoordOuter().size() - 1; i += 2) {
                    pathFormOuter.quadTo(gridRect.x + raceTrack.getCoordOuter().get(i).x, gridRect.y + raceTrack.getCoordOuter().get(i).y,
                            gridRect.x + raceTrack.getCoordOuter().get(i + 1).x, gridRect.y + raceTrack.getCoordOuter().get(i + 1).y);
                }

                if (drawOuterTrack) {
                    //close outer path
                    pathFormOuter.quadTo(gridRect.x + raceTrack.getCoordOuter().get(raceTrack.getCoordOuter().size() - 1).x, gridRect.y + raceTrack.getCoordOuter().get(raceTrack.getCoordOuter().size() - 1).y,
                            gridRect.x + raceTrack.getCoordOuter().get(0).x, gridRect.y + raceTrack.getCoordOuter().get(0).y);
                    pathFormOuter.closePath();
                    g2d.draw(pathFormOuter);
                } else {
                    g2d.draw(pathFormOuter);
                }

            }

            //draw inner track
            GeneralPath pathFormInner = new GeneralPath(0);
            if (!raceTrack.getCoordInner().isEmpty()) {
                //start inner track
                pathFormInner.moveTo(gridRect.x + raceTrack.getCoordInner().get(0).x, gridRect.y + raceTrack.getCoordInner().get(0).y);

                //draw inner track
                for (int i = 1; i < raceTrack.getCoordInner().size() - 1; i += 2) {
                    pathFormInner.quadTo(gridRect.x + raceTrack.getCoordInner().get(i).x, gridRect.y + raceTrack.getCoordInner().get(i).y,
                            gridRect.x + raceTrack.getCoordInner().get(i + 1).x, gridRect.y + raceTrack.getCoordInner().get(i + 1).y);
                }

                if (drawInnerTrack) {
                    //close inner track
                    pathFormInner.quadTo(gridRect.x + raceTrack.getCoordInner().get(raceTrack.getCoordInner().size() - 1).x, gridRect.y + raceTrack.getCoordInner().get(raceTrack.getCoordInner().size() - 1).y,
                            gridRect.x + raceTrack.getCoordInner().get(0).x, gridRect.y + raceTrack.getCoordInner().get(0).y);
                    pathFormInner.closePath();
                    g2d.draw(pathFormInner);
                } else {
                    g2d.draw(pathFormInner);
                }

            }
            //Check if finished 
            if (drawStartFinish && drawGrid && drawOuterTrack && drawInnerTrack) {
                g2d.setColor(Color.blue);
                raceTrack.setValidPoints(new ArrayList<Point>());
                for (int x = gridRect.x + raceTrack.getGapSize(); x <= gridRect.x + gridRect.width - raceTrack.getGridSize(); x += raceTrack.getGridSize() + raceTrack.getGapSize()) {
                    for (int y = gridRect.y + raceTrack.getGapSize(); y <= gridRect.y + gridRect.height - raceTrack.getGridSize(); y += raceTrack.getGridSize() + raceTrack.getGapSize()) {
                        if (!pathFormOuter.contains(x + raceTrack.getGridSize() / 2, y + raceTrack.getGridSize() / 2) || pathFormInner.contains(x + raceTrack.getGridSize() / 2, y + raceTrack.getGridSize() / 2)) {
                            continue;
                        }
                        raceTrack.getValidPoints().add(new Point(x - gridRect.x, y - gridRect.y));
                        g2d.fillRect(x, y, raceTrack.getGridSize(), raceTrack.getGridSize());
                    }
                }
            }
        }

    }
}
