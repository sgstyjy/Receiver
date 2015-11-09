package receiver;

public class Constant {
	public static String FILE_IN = "default_filein.qcow2";
	//public static String FILE_OUT = "receivedimage.qcow2";
	public static String FILE_OUT = "default_fileout.qcow2";
	public static int OS_CID = 0;
	public static int WE_CID = 0;
	public static int UD_CID = 0;
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
    public static int MPORT = 6212;
    public static int OSPORT=6444;
    public static int WEPORT=6445;
    public static int UDPORT=6446;
}