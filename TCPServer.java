///////////////////////////////////////////////////////////////////
// File name: TCPServer.java
// Purpose: Implements a TCP server for the Telephone Game.
//          The server receives a message, randomly swaps
//          two words, displays the received and sent messages,
//          sends the altered message to the next player,
//          and then terminates.
///////////////////////////////////////////////////////////////////

import java.io.*;
import java.net.*;
import java.util.Random;

class TCPServer {

   // ------------------------------------------------------
   public static void main(String[] args) throws Exception {

      int portNumber;
      String receivedMessage;
      String alteredMessage;

      if (args.length != 1) {
         System.out.println("Usage: java TCPServer <4000>");
         System.exit(1);
      }

      portNumber = Integer.parseInt(args[0]);

      //Added so it can only run onces
      ServerSocket welcomeSocket = new ServerSocket(portNumber);

      // Accept ONE connection only
      Socket connectionSocket = welcomeSocket.accept();

      BufferedReader inFromClient =
         new BufferedReader(
            new InputStreamReader(connectionSocket.getInputStream()));

      DataOutputStream outToClient =
         new DataOutputStream(connectionSocket.getOutputStream());

      receivedMessage = inFromClient.readLine();

      System.out.println("Message Received: " + receivedMessage);

      //added this so it can remove the uppercase conversion from the client
      alteredMessage = swapTwoWords(receivedMessage);

      System.out.println("Message Sent: " + alteredMessage);

      outToClient.writeBytes(alteredMessage + "\n");
      outToClient.flush();

      connectionSocket.close();
      welcomeSocket.close();
   }

   // ------------------------------------------------------

   //Added the swap logic 
   static String swapTwoWords(String message) {

      String[] words = message.split("\\s+");
      Random rand = new Random();

      int i = rand.nextInt(words.length);
      int j = rand.nextInt(words.length);

      String temp = words[i];
      words[i] = words[j];
      words[j] = temp;

      return String.join(" ", words);
   }
}