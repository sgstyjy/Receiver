package receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	public static void main(String[] args) throws IOException {
	       //server socket
		  //System.setProperty("java.net.preferIPv4Stack", "true");
	       ServerSocket s = new ServerSocket(Constant.PORT);
	       System.out.println("The outputs are from the Server: ");               
	       Socket socket = s.accept();
	       while(true)
		   {
	    	      //receive the basic information: client id, the start block, the total block.
			      DataInputStream  sin = new DataInputStream(socket.getInputStream());
			     // int cid = sin.readInt();
			      int total_block = sin.readInt();
			      int start_block = sin.readInt();
			      int send_block = sin.readInt();
			      Constant.FILE_IN = sin.readUTF();
			      System.out.println("The client ID is : "+ Constant.CID);    
			      System.out.println("The total block is: "+total_block);    
			      System.out.println("The start block is: "+ start_block);    
			      System.out.println("The send block is: "+send_block);    
			      System.out.println("The original file name is: "+Constant.FILE_IN);  
		         new ReceiverThread(socket, Constant.CID, start_block, send_block);	
		         socket = s.accept();
		         Constant.CID++;
		    }
	}
}
