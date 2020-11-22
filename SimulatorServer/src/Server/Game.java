/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Data.PlayerList;



public class Game {

    private String name;                
    private int gameSize;               
    private String code;                        
    private boolean gameStarted = false;
    private int gameState = 1;
    public ArrayList turnCollection;
    public PlayerList playerData;   
    public ExecutorService executorService;

    
    public Game(String name, int gameSize, String code) {
        this.name = name;
        this.gameSize = gameSize;
        this.code = code;
        this.turnCollection = new ArrayList();
        this.playerData = new PlayerList();
        this.executorService = Executors.newFixedThreadPool(gameSize);
    }

   
          


    public PlayerList getPlayerData() {
        return playerData;
    }

    public void setPlayerData(PlayerList playerData) {
        this.playerData = playerData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGameSize() {
        return gameSize;
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }


    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

}
