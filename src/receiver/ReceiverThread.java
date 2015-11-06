package receiver;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ReceiverThread extends Thread{
	 //File IO
    private DataInputStream sin;
    private RandomAccessFile sout;
    private  Long starttime = (long) 0;
    private int t_cid =0;
    private int start_position = 0;
    private long send_times = 0;
    private long last_bytes = 0;
    public ReceiverThread(Socket clientSocket, int cid,int start_block,  int send_time, long last_byte) throws IOException 
    {      
    	   // long tid =   this.getId();
            //System.out.println("The thread id is: "+ tid);
    	    t_cid = cid;
    	    start_position = start_block;
    	    send_times = send_time;
   		    last_bytes = last_byte;
    	    //construct the file name of output
		      StringBuilder strbuilder = new StringBuilder();
			  String fileintype = Constant.FILE_IN.split("\\.")[1];    //cannot use point directly, must use "\\" before it
			  strbuilder.append("received");
			  strbuilder.append("."+fileintype);
			  Constant.FILE_OUT = strbuilder.toString();
			  System.out.println("The saved file  is: "+Constant.FILE_OUT);
			 
              sin = new DataInputStream(clientSocket.getInputStream());
              sout = new RandomAccessFile(Constant.FILE_OUT,"rw");
           
             //start thread
             starttime = System.currentTimeMillis();
             start(); 
    }
    //the thread main method
    public void run() 
    {
    	byte[] rb = new byte[15*Constant.TRANSFER_BUFFER];
		int receive_length = 0;
        try 
        {
        	int i = 0;
        	sout.seek(start_position* Constant.TRANSFER_BUFFER);
        	receive_length = sin.read(rb);
            while(receive_length!=-1 && i< send_times )
            {
            	  sout.write(rb, 0, receive_length);
            	  i++;
            	  receive_length = sin.read(rb);
           }   
            //write the last part of data in the buffer
            sout.write(rb, 0, (int)last_bytes);
           // System.out.println("The send times in thread is: "+ send_times);    
		   //  System.out.println("The last bytes in thread are: "+(int)last_bytes);  
            System.out.println("The received times are: "+i);  
           Long endtime = System.currentTimeMillis();
           Long duration = endtime - starttime;
           System.out.println("The Client " + t_cid + "'s transfer time is: " + duration);
           sin.close();
           
           //System.out.println("The blocks received are: "+i);
          // System.out.println("The last block are: "+last_bytes);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
