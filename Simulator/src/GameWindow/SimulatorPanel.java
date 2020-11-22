/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWindow;

import Data.RaceTrack;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class SimulatorPanel extends JPanel {

    private RaceTrack raceTrack;
    private Rectangle gameRect;

    public SimulatorPanel() {
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Graphics2D g2d = (Graphics2D) gc;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //Size Screen
        Rectangle screenRect = this.getBounds();

        if ((raceTrack = SimulatorFrame.getInstance().getRaceTrackToPlay()) == null) {          
            return;
        }
        //Size Game
        gameRect = new Rectangle(screenRect.width / 2 - raceTrack.getWidth() / 2, 0, raceTrack.getWidth(), raceTrack.getHeigh());
        //Background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(gameRect.x, gameRect.y, gameRect.width, gameRect.height);
        

        //Line Outer
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.0f));

        ArrayList<Point> pointsOuter = raceTrack.getCoordOuter();
        GeneralPath pathFormOuter = new GeneralPath(0);
        pathFormOuter.moveTo(gameRect.x + pointsOuter.get(0).x, gameRect.y + pointsOuter.get(0).y);
        for (int i = 1; i < raceTrack.getPointsOutter() - 1; i += 2) {
            pathFormOuter.quadTo(gameRect.x + pointsOuter.get(i).x, gameRect.y + pointsOuter.get(i).y,
                    gameRect.x + pointsOuter.get(i + 1).x, gameRect.y + pointsOuter.get(i + 1).y);
        }
        pathFormOuter.quadTo(gameRect.x + pointsOuter.get(raceTrack.getPointsOutter() - 1).x, gameRect.y + pointsOuter.get(raceTrack.getPointsOutter() - 1).y,
                gameRect.x + pointsOuter.get(0).x, gameRect.y + pointsOuter.get(0).y);
        pathFormOuter.closePath();
       

        //Line Inner
        ArrayList<Point> pointsInner = raceTrack.getCoordInner();
        GeneralPath pathFormInner = new GeneralPath(0);
        pathFormInner.moveTo(gameRect.x + pointsInner.get(0).x, gameRect.y + pointsInner.get(0).y);
        for (int j = 1; j < raceTrack.getPointsInner() - 1; j += 2) {
            pathFormInner.quadTo(gameRect.x + pointsInner.get(j).x, gameRect.y + pointsInner.get(j).y,
                    gameRect.x + pointsInner.get(j + 1).x, gameRect.y + pointsInner.get(j + 1).y);
        }
        pathFormInner.quadTo(gameRect.x + pointsInner.get(raceTrack.getPointsInner() - 1).x, gameRect.y + pointsInner.get(raceTrack.getPointsInner() - 1).y,
                gameRect.x + pointsInner.get(0).x, gameRect.y + pointsInner.get(0).y);
        pathFormInner.closePath();
        

        //Map rectangles
        g2d.setColor(Color.BLUE);
        for (int x = gameRect.x + raceTrack.getGapSize(); x <= gameRect.x + gameRect.width - raceTrack.getGridSize(); x += raceTrack.getGridSize() + raceTrack.getGapSize()) {
            for (int y = gameRect.y + raceTrack.getGapSize(); y <= gameRect.y + gameRect.height - raceTrack.getGridSize(); y += raceTrack.getGridSize() + raceTrack.getGapSize()) {
                if (!pathFormOuter.contains(x + raceTrack.getGridSize() / 2, y + raceTrack.getGridSize() / 2) || pathFormInner.contains(x + raceTrack.getGridSize() / 2, y + raceTrack.getGridSize() / 2)) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(x, y, raceTrack.getGridSize(), raceTrack.getGridSize());
                    continue;
                }
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x, y, raceTrack.getGridSize(), raceTrack.getGridSize());
            }
        }
        g2d.setColor(Color.BLACK);
        g2d.draw(pathFormInner);
         g2d.draw(pathFormOuter);
        //paint start
        
        ArrayList<Point> pointsStart = raceTrack.getCoordStart();
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(5.0f));
        g2d.drawLine(gameRect.x + pointsStart.get(0).x, gameRect.y + pointsStart.get(0).y,
                gameRect.x + pointsStart.get(1).x, gameRect.y + pointsStart.get(1).y);
       
        
       
       
    }

  

}
  
