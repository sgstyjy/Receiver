package receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {

	public static void main(String[] args) throws IOException, InterruptedException {
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
	    	   //System.out.println("The server manager socket port is:"+m_socket.getLocalPort());
		       DataInputStream  min = new DataInputStream(m_socket.getInputStream());
		       int total_layer = min.readInt();

		       //build a new thread to received data
		       if(total_layer == 1)
		       {
		    	   System.out.println("The total layer is: 1." );
		    	   Thread os_thread = new ReceiverThread(os_socket,0);
		       }
		       
		       if(total_layer == 2)
		       {
		    	   System.out.println("The total layer is: 2." );
		    	   Thread we_thread = new ReceiverThread(we_socket,1);
		    	   Thread ud_thread = new ReceiverThread(ud_socket,2);
		       }
		       if(total_layer ==3)
		       {    	   
		    	   System.out.println("The total layer is: 3." );
		    	   Thread os_thread = new ReceiverThread(os_socket,0);
		    	   Thread we_thread = new ReceiverThread(we_socket,1);
		    	   Thread ud_thread = new ReceiverThread(ud_socket,2);
		       }
		       	  
	    	   i++;
	    	   System.out.println("The " + i +"th client is connected.");
	       }
	}
}
