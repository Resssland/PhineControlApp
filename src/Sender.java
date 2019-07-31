import java.awt.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Sender<T> implements Runnable{
    boolean stop=false;
    int port=22222;
    String host="localhost";
    DatagramSocket dsocket;
    DatagramPacket pac;
    InetAddress addr;

   MessageQueue<Screen> Mq;
    public Sender(MessageQueue<Screen> Mq){
        try {
            this.Mq=Mq;
            /*
            Socket socket=new Socket(host,port);
            out=socket.getOutputStream();
            obOut=new ObjectOutputStream(out);
*/


            dsocket =new DatagramSocket();
            addr =InetAddress.getByName("192.168.0.101");





        } catch (IOException e) {
            e.printStackTrace();
        }
    }



  public byte[] ByteConverter(Object s){

       ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] byteScreen=null;
      try {
                ObjectOutputStream oos=new ObjectOutputStream(baos);
                oos.writeObject(s);
         baos.flush();
         oos.flush();
           byteScreen = baos.toByteArray();
           oos.close();
           baos.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       return  byteScreen;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                Screen message;
                message=Mq.pop();
                if(message!=null){

                    byte[] d=ByteConverter(message);
                    if(d.length==0)continue;
                    if(d.length>30000){
                        continue;
                    }
                    //System.out.println(d.length+"||"+Mq.size());

                    pac=new DatagramPacket(d,d.length,addr,port);
                    dsocket.send(pac);
                    //Thread.sleep(20);

                    }


            } catch (IOException e) {
                e.printStackTrace();


        }}

    }
}
