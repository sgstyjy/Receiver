package receiver;

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
		     new ReceiverThread(socket, received_file);		      
		      s.close();
		    }
	}
}
