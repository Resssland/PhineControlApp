import java.awt.*;
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        MessageQueue<Screen> Mq=new MessageQueue<>();
        //ArrayDeque<Screen> sc =new ArrayDeque<>();
     ScreenWatchdog sw=new ScreenWatchdog(Mq);
        new Thread(sw).start();
        ScreenWatchdog sw2=new ScreenWatchdog(Mq);
        new Thread(sw2).start();

    Form f=new Form(Mq);
        //Sender s=new Sender(Mq);
        //new Thread(s).start();



        /*
        Receiver r=new Receiver();
        new Thread(r).start();

        MessageQueue<String> Mq=new MessageQueue<>();


        MessageGenerator m=new MessageGenerator(Mq);
        new Thread(m).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.stop=true;
        r.stop=true;
*/

    }
}
