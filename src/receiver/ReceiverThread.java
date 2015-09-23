package receiver;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiverThread extends Thread{
	 //File IO
    private DataInputStream sin;
    private FileOutputStream sout;    
    //private FileOutputStream sout2;    
    public ReceiverThread(Socket clientSocket) throws IOException 
    {          
           sin = new DataInputStream(clientSocket.getInputStream());
           sout = new FileOutputStream(Constant.FILE_OUT); 
          // sout2 = new FileOutputStream(Constant.BLOCKNUM_OUT); 
           //start thread
           start(); 
    }
    //the thread main method
    public void run() 
    {
    	byte[] bb = new byte[Constant.TRANSFER_BUFFER];
		int receive_length = 0;
		int receive_blocks =0;
		int receivesum = 0;
        try 
        {
           receive_length = sin.read(bb, 0, Constant.TRANSFER_BUFFER);
           while(receive_length>0){
        	   sout.write(bb, 0, receive_length);
        	   sout.flush();
        	   //sout2.write(b);
        	   receive_blocks++;
        	   receivesum += receive_length;
        	   receive_length = sin.read(bb, 0, Constant.TRANSFER_BUFFER);
           }
           sin.close();
           sout.close();
           System.out.println("The blocks received are: "+receive_blocks);
           System.out.println("The bytes received are: "+receivesum);
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
