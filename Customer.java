/*
Created by: Shanjay Kailayanathan
Created on: November 05, 2021
 */

package message.message;

import java.io.*;
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.print("Please enter the provided job ID: ");
        String id = scanner.nextLine();

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Message stub = (Message) registry.lookup("id");
            String response = stub.getMessage();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}