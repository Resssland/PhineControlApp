import java.awt.*;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Calendar;


public class Main {
    public static void main(String[] args) throws IOException {
        MessageQueue<Screen> Mq=new MessageQueue<>();
        //ArrayDeque<Screen> sc =new ArrayDeque<>();
     //ScreenWatchdog sw=new ScreenWatchdog(Mq);
        //new Thread(sw).start();
        //Thread th=new Thread(sw);
       // th.start();
        Robot r=null;
        try {
            r=new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }


        //th.interrupt();

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
