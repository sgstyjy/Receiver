package receiver;

public class Constant {
	public static String FILE_IN = "ubuntu12server.qcow2";
	public static String FILE_OUT = "receivedimage.qcow2";
	public static int CID = 0;
	public static String BLOCKNUM_OUT = "blocknum.txt";
	public static String COMPARE_U12_U14WEB = "com_u12_u14web_4k.xls";
	public static String COMPARE_U14DEV_U14WEB = "com_u14dev_u14web_4k.xls";
	
	public static int TOTALBLOCKS = 524287;
    public static int TRANSFER_BUFFER = 4*1024;
	public static int COLUMNS = 10000;
    public static int CLIENT_ID = 0;
    public static long STARTTIME  = 0;
    public static long ENDTIME  = 0;
    public static String DSTIP = "141.5.103.56";
    public static int PORT=6214;
    public static int PORT2=6443;
    public static int PORT3=6446;
    public static int PORT4=6448;
}