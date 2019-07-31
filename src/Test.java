import java.awt.*;

public class Test{
    public static void main (String [] args) throws Exception{

        Rectangle rect = new Rectangle( Toolkit.getDefaultToolkit().getScreenSize() );
        rect.height=rect.height/2;
        rect.width=rect.width/2;
        Robot robot = new Robot();
        int count = 1;
        long beforeTime = System.currentTimeMillis();
        while ( count < 32 ) {
            robot.createScreenCapture( rect );
            //Jna.getScreenshot(rect);
            count++;
        }
        double time = System.currentTimeMillis() - beforeTime;

        System.out.println( "ROBOT:Seconds it took for 32 screen captures: " + time / 1000 );


         count = 1;
         beforeTime = System.currentTimeMillis();
        while ( count < 32 ) {
            //robot.createScreenCapture( rect );
            Jna.getScreenshot(rect);
            count++;
        }
         time = System.currentTimeMillis() - beforeTime;

        System.out.println( "JNA:Seconds it took for 32 screen captures: " + time / 1000 );


    }
}