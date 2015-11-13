package receiver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverThread extends Thread{
	//connect with the client, and read data into different files according to the layer information
    private DataInputStream sin;
    private RandomAccessFile sout;
    private  Long starttime = (long) 0;
    private int t_cid =0;
    private int start_position = 0;
    private int t_layer = 0;
    InetAddress r_address=null;

    public ReceiverThread(ServerSocket serverSocket, int layer) throws IOException 
    {      
    	    Socket socket = serverSocket.accept();
    	    r_address = socket.getInetAddress();
    	    //System.out.println("This in thread."); 
    	    //System.out.println("The server port is:"+socket.getLocalPort());
			//receive the basic information: the start block, the total send block, the file name
    	    sin = new DataInputStream(socket.getInputStream());
			int start_block = sin.readInt();
			int send_block = sin.readInt();
			Constant.FILE_IN = sin.readUTF();
			System.out.println("The original file name is:" + Constant.FILE_IN); 
    
    	    t_layer = layer;
    	    //construct the file name of output
    	    StringBuilder strbuilder = new StringBuilder();
			String fileintype = Constant.FILE_IN.split("\\.")[1];    //cannot use point directly, must use "\\" before it
			switch(layer)
			{
			case 0: 
			{
				strbuilder.append("OS");
				t_cid = Constant.OS_CID++;
				break;
			}
			case 1: 
			{
				strbuilder.append("WE");
				t_cid = Constant.WE_CID++;
				break;
			}
			case 2: 
			{
				strbuilder.append("UD");
				t_cid = Constant.UD_CID++;
				break;
			}
			}
			strbuilder.append("."+fileintype);
			Constant.FILE_OUT = strbuilder.toString();
			start_position = start_block;
			
			System.out.println("The received data is from the client "+ r_address + " of the layer "+layer);    
			System.out.println("The start block is: "+ start_block);    
			System.out.println("The send block is: "+send_block);  
			System.out.println("The saved file  is: "+Constant.FILE_OUT);
			
            sout = new RandomAccessFile(Constant.FILE_OUT,"rw");
           
             //start thread to receive data
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
        	System.out.println("Write file at the position: "+sout.getFilePointer()/Constant.TRANSFER_BUFFER);  
        	receive_length = sin.read(rb);
            while(receive_length!=-1 && !rb.equals("end") )
            {
            	  sout.write(rb, 0, receive_length);
            	  i++;
            	  receive_length = sin.read(rb);
           }    
           System.out.println("The actual received times are: "+i);  
            
           //calculate the received duration
           Long endtime = System.currentTimeMillis();
           Long duration = endtime - starttime;
           System.out.println("**********************************************************************************");
           System.out.println("The Client " + r_address + " of the layer "+ t_layer+ "'s transfer time is: " + duration);
           System.out.println("**********************************************************************************");
           
           sin.close();
           sout.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
