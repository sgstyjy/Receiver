package receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	public static void main(String[] args) throws IOException {
	       //server socket
		   //build a manage socket to receive the layer information, and generate thread to receive data
	       ServerSocket manager = new ServerSocket(Constant.MPORT);
	       ServerSocket os_socket = new ServerSocket(Constant.OSPORT);
	       ServerSocket we_socket = new ServerSocket(Constant.WEPORT);
	       ServerSocket ud_socket = new ServerSocket(Constant.UDPORT);
	       
	       System.out.println("The outputs are from the Server: ");  
	       int i = 0;
	       while(true)
	       {
	    	   //accept the client connect and the layer information, build thread to receive data
	    	   Socket m_socket = manager.accept();
	    	   System.out.println("The server manager socket port is:"+m_socket.getLocalPort());
		       DataInputStream  min = new DataInputStream(m_socket.getInputStream());
		       int layer = min.readInt();
		       System.out.println("This is receiving data of the layer: " + layer);
		       //build a new thread to received data
		       switch(layer)
		       {
		       case 0: 
		    	   {
		    		   System.out.println("The case 0."); 
		    		   new ReceiverThread(os_socket,layer);
		    		   break;
		    	   }
		       case 1: 
		    	   {
		    		   System.out.println("The case 1."); 
		    		   new ReceiverThread(we_socket,layer);
		    		   break;
		    	   }
		       case 2: 
		    	   {
		    		   System.out.println("The case 2."); 
		    		   new ReceiverThread(ud_socket,layer);
		    		   break;
		    	   }
		       }   	  
	    	   i++;
	    	   System.out.println("The " + i +"th thread is built.");
	       }
	}
}
