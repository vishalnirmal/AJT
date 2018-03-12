import java.net.*;
import java.io.*;
public class Server 
{
    String clients[][] = new String[500][2];
    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(3000);
        Socket s = ss.accept();
        System.out.println("Client 1 connected.");
        Socket s2 = ss.accept();
        System.out.println("Client 2 connected.");
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        InputStream is2 = s2.getInputStream();
        OutputStream os2 = s2.getOutputStream();
        Thread client2 = new ClientSendHandler(s,os,is2);
        client2.start();
        Thread client1 = new ClientReceiveHandler(s,os2,is);
        client1.start();
    }
}
class ClientSendHandler extends Thread
{
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    ClientSendHandler(Socket s,OutputStream os,InputStream is2)
    {
        this.s = s;
        dout = new DataOutputStream(os);
        din = new DataInputStream(is2);
    }
    @Override
    public void run()
    {
        String msg="";
        while(!msg.equals("end"))
        {
            try {
                msg = din.readUTF();
                dout.writeUTF(msg);
                dout.flush();
            } catch (IOException ex) {
                
            }
        }
    }
}
class ClientReceiveHandler extends Thread
{
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    ClientReceiveHandler(Socket s,OutputStream os2,InputStream is)
    {
        this.s = s;
        this.din = new DataInputStream(is);
        dout = new DataOutputStream(os2);
    }
    @Override
    public void run()
    {
        String msg;
        while(true)
        {
            try {
                msg = din.readUTF();
                dout.writeUTF(msg);
                dout.flush();
            } catch (IOException ex) {
                
            }
        }
    }
}
