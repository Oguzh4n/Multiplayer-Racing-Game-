/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RaceTrack implements Serializable {

    transient private File input;
    private String name;
    private int width;
    private int height;
    private int pointsOutter;
    private ArrayList<Point> coordOuter;
    private int pointsInner;
    private ArrayList<Point> coordInner;
    private int grid;
    private ArrayList<Point> coordStart;  
    private int gridSize;
    private int gapSize;
    private ArrayList<Point> validPoints;
    private ArrayList<Point> startPoints;

    public RaceTrack(File input) {
        this.input = input;
        this.name = input.getName();
        this.validPoints = new ArrayList<Point>();
        this.startPoints = new ArrayList<Point>();
        decode();

    }

    public RaceTrack() {
        this.coordInner = new ArrayList<Point>();
        this.coordOuter = new ArrayList<Point>();
        this.coordStart = new ArrayList<Point>();
        this.validPoints = new ArrayList<Point>();
        this.startPoints = new ArrayList<Point>();
    }

    public void exportFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(name + ".csv", true));
            writer.print(csvContent());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RaceTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String csvContent() {
        this.pointsOutter = coordOuter.size();
        this.pointsInner = coordInner.size();    
        StringBuilder csvString = new StringBuilder();
        csvString.append(width + "," + height + "\n");
        csvString.append(pointsOutter + "\n");
        csvString.append(arrayToString(coordOuter) + "\n");
        csvString.append(pointsInner + "\n");
        csvString.append(arrayToString(coordInner) + "\n");
        csvString.append(grid + "\n");
        csvString.append(arrayToString(coordStart) + "\n");     
        csvString.append(arrayToString(startPoints));
        return csvString.toString();
    }
   

   private String arrayToString(ArrayList<Point> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).x + "," + list.get(i).y);
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private void decode() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            String[] splitLine = lines.get(0).split(",");
            width = Integer.valueOf(splitLine[0]);
            height = Integer.valueOf(splitLine[1]);
            pointsOutter = Integer.valueOf(lines.get(1));
            coordOuter = createPointArray(lines.get(2), pointsOutter);
            pointsInner = Integer.valueOf(lines.get(3));
            coordInner = createPointArray(lines.get(4), pointsInner);
            grid = Integer.valueOf(lines.get(5));
            calcGridStuff();
            coordStart = createPointArray(lines.get(6), 2);
           
            if (lines.size() > 8) {
                startPoints = createPointArray(lines.get(8), 5);
            } else {
                startPoints = new ArrayList<Point>();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RaceTrack.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RaceTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    private ArrayList<Point> createPointArray(String line, int length) {
        ArrayList<Point> points = new ArrayList<Point>();
        String[] split = line.split(",");
        for (int i = 0; i < (length * 2); i += 2) {
            points.add(createPoint(split[i], split[i + 1]));
        }
        return points;
    }

    private Point createPoint(String x, String y) {
        Point point = new Point();
        point.x = Integer.valueOf(x);
        point.y = Integer.valueOf(y);
        return point;
    }

    public void calcGridStuff() {
        gridSize = (grid / 3) * 2;
        gapSize = (grid / 3);
    }

    public int getWidth() {
        return width;
    }

    public int getHeigh() {
        return height;
    }

    public int getPointsOutter() {
        return pointsOutter;
    }

    public ArrayList<Point> getCoordOuter() {
        return coordOuter;
    }

    public int getPointsInner() {
        return pointsInner;
    }

    public ArrayList<Point> getCoordInner() {
        return coordInner;
    }

    public int getGridSize() {
        return gridSize;
    }

    public ArrayList<Point> getCoordStart() {
        return coordStart;
    }

    public int getGapSize() {
        return gapSize;
    }

    public File getInput() {
        return input;
    }

    public void setInput(File input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeigh(int heigh) {
        this.height = heigh;
    }

    public void setPointsOutter(int pointsOutter) {
        this.pointsOutter = pointsOutter;
    }

    public void setCoordOuter(ArrayList<Point> coordOuter) {
        this.coordOuter = coordOuter;
    }

    public void setPointsInner(int pointsInner) {
        this.pointsInner = pointsInner;
    }

    public void setCoordInner(ArrayList<Point> coordInner) {
        this.coordInner = coordInner;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public void setCoordStart(ArrayList<Point> coordStart) {
        this.coordStart = coordStart;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public ArrayList<Point> getValidPoints() {
        return validPoints;
    }

    public void setValidPoints(ArrayList<Point> validPoints) {
        this.validPoints = validPoints;
    }

    public ArrayList<Point> getStartPoints() {
        return startPoints;
    }

    public void setStartPoints(ArrayList<Point> startPoints) {
        this.startPoints = startPoints;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    
}
