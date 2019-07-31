import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver implements Runnable{
    InputStream input;
    ServerSocket ssocket;
    public boolean stop=false;
    public Receiver() throws IOException {
        int port=22222;
         ssocket=new ServerSocket(port);



    }

    @Override
    public void run() {
        Socket socket= null;
        try {
            socket = ssocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input=socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!stop){
            byte[] b=new byte[32*1024];
            try {
                input.read(b);
                System.out.println("rec: "+new String(b));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
