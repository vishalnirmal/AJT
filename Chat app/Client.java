import java.net.*;
import java.io.*;
public class Client 
{
    
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",3000);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Connection established with the server.");
        System.out.println("Getting the i/o streams of server: ");
        InputStream is = s.getInputStream();
        System.out.println("Input stream recieved.");
        OutputStream os = s.getOutputStream();
        System.out.println("Output stream recieved.");
        System.out.println("Creating threads to handle sending and receiving.");
        Thread send = new ClientSendHandler(s,os,br);
        send.start();
        Thread receive = new ClientReceiveHandler(s,is);
        receive.start();
        System.out.println("Threads created.");
        System.out.println("Start Chatting.");
    }
}
class ClientSendHandler extends Thread
{
    Socket s;
    DataOutputStream dout;
    BufferedReader br;
    ClientSendHandler(Socket s,OutputStream os,BufferedReader br)
    {
        this.s = s;
        dout = new DataOutputStream(os);
        this.br = br;
    }
    @Override
    public void run()
    {
        String msg="";
        while(!msg.equals("end"))
        {
            try {
                msg = br.readLine();
                dout.writeUTF(msg);
                dout.flush();
            } catch (IOException ex) {
                
            }
        }
        try {
            s.close();
        } catch (IOException ex) {
            
        }
    }
}
class ClientReceiveHandler extends Thread
{
    Socket s;
    DataInputStream din;
    ClientReceiveHandler(Socket s,InputStream is)
    {
        this.s = s;
        this.din = new DataInputStream(is);
    }
    @Override
    public void run()
    {
        String msg;
        while(true)
        {
            try {
                msg = din.readUTF();
                System.out.println("Server: "+msg);
            } catch (IOException ex) {
                
            }
        }
    }
}