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
    public ReceiverThread(Socket clientSocket, File file_out, int startblock, int totalblock) throws IOException 
    {      
    	    long tid =   this.getId();
            System.out.println("The thread id is: "+ tid);
            sin = new DataInputStream(clientSocket.getInputStream());
            sout = new RandomAccessFile(Constant.FILE_IN,"w");
            sout.seek(startblock);
           //start thread
           Long starttime = System.currentTimeMillis();
           start(); 
           Long endtime = System.currentTimeMillis();
           Long duration = endtime - starttime;
           System.out.println("The Client" + tid + "'s transfer time is: " + duration);
    }
    //the thread main method
    public void run() 
    {
    	
    	byte[] bb = new byte[Constant.TRANSFER_BUFFER];
		int receive_length = 0;
		int receive_blocks =0;
        try 
        {
           receive_length = sin.read(bb);
           while(receive_length!=-1){
        	   sout.write(bb, 0, receive_length);
        	   //sout2.write(b);
        	   receive_blocks++;
        	   receive_length = sin.read(bb);
           }   
           sin.close();
           sout.close();
           System.out.println("The blocks received are: "+receive_blocks);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
