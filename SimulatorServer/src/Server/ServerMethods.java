/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Interfaces.Server;
import Interfaces.Client;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Data.Player;
import Data.RaceTrack;



public class ServerMethods {

    private static HashMap<String, Game> games = new HashMap<>();
    private static ArrayList<RaceTrack> raceTracks;
    private static ExecutorService executorService= Executors.newSingleThreadExecutor();
    private static ServerMethods instanceEngine;
    

    
    public synchronized static ServerMethods getInstance() {                           //Singleton
        if (instanceEngine == null) {
            instanceEngine = new ServerMethods();
        }
        return instanceEngine;
    }

    private ServerMethods() {
    }
    
    public static void joinGame(Server source, Client client, Player player, String gameName, String code) {
        games.get(gameName).executorService.submit(() -> {         
            Game game = games.get(gameName);
            //Check if GameCode is Equal
            if (game.getCode().equalsIgnoreCase(code)) {
                    game.playerData.playerlist.add(player);                      
                    }         
             else {
                
                System.out.println("Code does not fit");             
            }
        });
    }
    
    public static void createGame(String gameName, int count, String code) {  
        
        executorService.submit(() -> {          
            HashMap<String, Game> gamesCopy = new HashMap<>(games);
            for (Game game : gamesCopy.values()) {
                if (game.playerData.playerlist.size() == 0) {
                    System.out.println("Removed Game: " + game.getName());
                    games.remove(game.getName()).executorService.shutdown();
                    continue;
                }                         
            }
            //Add new Game
            games.put(gameName, new Game(gameName, count, code));
            System.out.println("Game created: " + gameName);
        });
    }

    
    public static void sendString(Server source, String message) {  
        for (Game game : games.values()) {
            game.executorService.submit(() -> {
                for (Player player : game.playerData.playerlist) {
                    if (player.getConnectedServer() == source) {
                        System.out.println("Found Game for sendStringRequest: " + game.getName());                     
                        for (Player playerVar : game.playerData.playerlist) {
                            if (playerVar.getConnectedServer() != source) {
                                try {
                                    System.out.println("SendString to: " + playerVar.username);
                                    playerVar.getConnectedClient().receiveString(message);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return;
                    }
                }
            });
        }
    }
     
    static void sendRaceTrack(Server source, RaceTrack data) {      
        executorService.submit(() -> {          
            raceTracks.add(data);            
            saveRaceTracks();
        });
    }        
     
    private static void saveRaceTracks() {
        try {
            ObjectOutputStream raceTracksOutput = new ObjectOutputStream(new FileOutputStream("RaceTracks.ser"));
            for (RaceTrack raceTrack : raceTracks) {
                raceTracksOutput.writeObject(raceTrack);
            }
            raceTracksOutput.close();
            System.out.println("RaceTracks are saved!");
        } catch (IOException ex) {
            Logger.getLogger(ServerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static ServerMethods getInstanceEngine() {
        return instanceEngine;
    }

}
