/*
Created by: Shanjay Kailayanathan
Created on: November 05, 2021
 */

package message.message;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DispatchServer implements Message{

    public DispatchServer() {}

    public static void main(String argv[]) throws Exception {
        try {
            Server obj = new Server();
            Message stub = (Message) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind(run(id), stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
	
	public List<List<String>> getData() throws IOException {
        String fileName = "Data.csv";
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    public void run(int id){
       String jobID, output;
        String name = "";
        String time = "";
        List<List<String>> data;

        try {
            try {
                data = getData();
                int total = 0;
                for (List<String> sublist : data) {
                    // TODO: Null checking
                    total += sublist.size();
                }
                int rows = total / 5;
                int columns = 5;
                for (int i = 0; i < rows; i++){
                    for (int j = 0; j < columns; j++){
                        if (data.get(i).get(0).equalsIgnoreCase(jobID)){
                            name = data.get(i).get(2);
                            time = data.get(i).get(5);
                        }
                    }
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
			output = "Hello " + name + "! Your technician will arrive today at " + time + ".";

            return output;
        } catch (Exception e) {System.out.println("Exception");}
    }
}