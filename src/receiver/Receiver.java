package receiver;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
	       
	         Socket socket = s.accept();
	         //receive the basic information: client id, the start block, the total block.
		      DataInputStream  sin = new DataInputStream(socket.getInputStream());
		      int cid = sin.readInt();
		      int total_block = sin.readInt();
		      int startblock = sin.readInt();
		      int send_block = sin.readInt();
		      Constant.FILE_IN = sin.readUTF();
		      System.out.println("The client ID is : "+ cid);    
		      System.out.println("The total block is: "+total_block);    
		      System.out.println("The start block is: "+ startblock);    
		      System.out.println("The send block is: "+send_block);    
	          RandomAccessFile init = new RandomAccessFile(Constant.FILE_IN,"rw");
	         byte[] bb = new byte[4*1024];
		      for( int i = 0; i<total_block; i++)
		    	   init.write(bb);
            init.close();
	       while(true)
		   {
		         new ReceiverThread(socket, cid, startblock, send_block);	
		         socket = s.accept();
		    }
	}
}
