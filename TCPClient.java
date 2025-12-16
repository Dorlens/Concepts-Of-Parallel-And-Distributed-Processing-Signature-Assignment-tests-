///////////////////////////////////////////////////////////////////
// File name: TCPClient.java (adapted from Kurose, p. 165)
// Purpose: Implements a simple client that establishes a TCP 
//          connection with a server, reads a text phrase from 
//          standard in, sends the text phrase to the server, 
//          prints the response received from the server,
//          and then terminates the connection
/////////////////////////////////////////////////////////////////// 

import java.io.*;
import java.net.*;

class TCPClient 
{

   // ------------------------------------------------------
   public static void main(String[] args) throws Exception 
   {
   String ipAddress;
   int portNumber;
   String message;   
   Socket clientSocket;
   DataOutputStream outToServer;
   BufferedReader inFromServer;

   if (args.length != 2) 
      {
      System.out.println("java <127.0.0.1> <IP address of server> <4000>");
      System.exit(1);
      } // End if

   ipAddress = args[0];
   portNumber = Integer.parseInt(args[1]);   

   // inFromUser = new BufferedReader( new InputStreamReader(System.in) );

   clientSocket = new Socket(ipAddress, portNumber);

   outToServer = new DataOutputStream(clientSocket.getOutputStream());

   
   inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
   message = inFromUser.readLine();
   outToServer.writeBytes(message + "\n");
   outToServer.flush();
   
   //Added so the client can read the message from the server
   String response =inFromServer.readLine();
   System.out.println("FROM SERVER: " + response);   
   
   clientSocket.close();
   } // End main
	

} // End class

