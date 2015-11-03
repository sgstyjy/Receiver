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
    private int send_blocks =0;
    public ReceiverThread(Socket clientSocket, int cid, int startblock, int send_block) throws IOException 
    {      
    	   // long tid =   this.getId();
            //System.out.println("The thread id is: "+ tid);
    	    t_cid = cid;
    	    send_blocks = send_block;
    	    //construct the file name of output
    	    /*
		      StringBuilder strbuilder = new StringBuilder();
			  String fileintype = Constant.FILE_IN.split("\\.")[1];    //cannot use point directly, must use "\\" before it
			  strbuilder.append(cid);
			  strbuilder.append("."+fileintype);
			  Constant.FILE_OUT = strbuilder.toString();
			  System.out.println("The saved file  is: "+Constant.FILE_OUT);
			  */
			  start_position = startblock;
            sin = new DataInputStream(clientSocket.getInputStream());
            sout = new RandomAccessFile(Constant.FILE_OUT,"rw");
           
           //start thread
          starttime = System.currentTimeMillis();
           start(); 
    }
    //the thread main method
    public void run() 
    {
    	
    	byte[] bb = new byte[Constant.TRANSFER_BUFFER];
		int receive_length = 0;
		int receive_blocks =0;
        try 
        {
        	sout.seek(start_position* Constant.TRANSFER_BUFFER);
            receive_length = sin.read(bb);
            while(receive_length!=-1 && receive_blocks < send_blocks){
        	   receive_blocks++;
        	   sout.write(bb, 0, receive_length);
        	   receive_length = sin.read(bb);
           }   
           Long endtime = System.currentTimeMillis();
           Long duration = endtime - starttime;
           System.out.println("The Client" + t_cid + "'s transfer time is: " + duration);
           sin.close();
          // t_sout.close();
           System.out.println("The blocks received are: "+receive_blocks);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
