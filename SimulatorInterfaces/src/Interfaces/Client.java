/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import Data.PlayerList;
import Data.RaceTrack;



public interface Client extends Remote{
    public void receivePlayerDatabase(PlayerList data) throws RemoteException;
    public void receiveString(String data) throws RemoteException; 
}
