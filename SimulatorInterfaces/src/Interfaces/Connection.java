/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import Data.Player;



public interface Connection extends Remote {
    public Server joinGame(Client client, Player player, String gameName, String code) throws RemoteException;
    public void createGame (String name, int count, String code) throws RemoteException;
}
