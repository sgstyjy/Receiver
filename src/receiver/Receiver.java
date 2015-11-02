package receiver;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.SocketHandler;

public class Receiver {

	public static void main(String[] args) throws IOException {
	       //server socket
		  //System.setProperty("java.net.preferIPv4Stack", "true");
	       ServerSocket s = new ServerSocket(Constant.PORT);
	       System.out.println("The outputs are from the Server: ");    
	       File received_file = new File("newVM.qcow2");
	       
	       while(true)
		   {
		      Socket socket = s.accept();
		      //receive the basic information: client id, the start block, the total block.
		      DataInputStream  sin = new DataInputStream(socket.getInputStream());
		      int cid = sin.readInt();
		      int startblock = sin.readInt();
		      int totalblock = sin.readInt();
		      System.out.println("The client ID is : "+ cid);    
		      System.out.println("The start block is: "+ startblock);    
		      System.out.println("The total block is: "+totalblock);    
		     new ReceiverThread(socket, received_file, startblock, totalblock);		      
		      s.close();
		    }
	}
}
