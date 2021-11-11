/*
Created by: Shanjay Kailayanathan
Created on: November 05, 2021
 */

package message.message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Message extends Remote {
    String getMessage() throws RemoteException;
}