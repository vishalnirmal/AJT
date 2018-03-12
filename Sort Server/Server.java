import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
public class Server 
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        System.out.println("Data Recieved.");
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        Thread chandler = new ClientHandler(s,os,is);
        chandler.start();
    }
}
class ClientHandler extends Thread
{
    DataOutputStream dout;
    DataInputStream din;
    ClientHandler(Socket s,OutputStream os,InputStream is)
    {
        dout = new DataOutputStream(os);
        din = new DataInputStream(is);
    }
    @Override
    public void run()
    {
        String msg="";
        while(!msg.equals("end"))
        {
            try {
                msg = din.readUTF();
                if(msg.equals("sort"))
                {
                    msg = din.readUTF();
                    sort(msg);
                    dout.writeUTF(sort(msg));
                    dout.flush();
                }
            } catch (IOException ex) {
            }
        }
        s.close();
    }
    String sort(String msg)
    {
        StringTokenizer st = new StringTokenizer(msg);
        int l = st.countTokens();
        int[] no = new int[l];
        for (int i=0;i<l;i++) 
        {
            no[i] = Integer.parseInt(st.nextToken());    
        }
        no = Arrays.sort(no);
        String ns = "";
        for (int i=0;i<l ;i++ ) 
            ns = ns+no[i]+" ";
        return ns;
    }
}