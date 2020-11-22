/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.ArrayList;


public class PlayerList implements Serializable {
    
    public ArrayList <Player> playerlist = new ArrayList <Player> ();
    
    
    public PlayerList() {
        playerlist = new ArrayList <Player> ();
    }
    
}
