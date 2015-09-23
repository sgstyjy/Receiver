package receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Receiver {

	public static void main(String[] args) throws IOException {
	       //server socket
	       ServerSocket s = new ServerSocket(Constant.PORT1);
	       System.out.println("The outputs are from the Server: ");    
	       //the start time
	       Date date = new Date();
	       Long starttime = date.getTime();
	       // for(;;)                          
		   //{
		      //waitting for the connection from client
		      Socket socket = s.accept();
		      //start the thread
		      new ReceiverThread(socket);
		      
		      s.close();
		      //the end time
		      Date date1 = new Date();
		      Long endtime = date1.getTime();
		      Long duration = endtime-starttime;
		      System.out.println("The receive time of is:"+duration);
		 //  }
	}

}
