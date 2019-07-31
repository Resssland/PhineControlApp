import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.List;


public class Form {
    BufferedImage im;
    JPanel jp;
    JLabel jl;
    JFrame jf;
    ImageIcon ii;
    JLabel jl2;
    ImageIcon ii2;
    BufferedImage icon;
    private atask a;
    MessageQueue<Screen> sc;
    Rectangle rec = null;
    Robot robot;
    public Form(MessageQueue<Screen> sc) throws IOException {
        this.sc = sc;

        jf = new JFrame("Receiver");
        // File f=new File("C:\\Users\\Руслан\\Desktop\\im\\screen53.png");
        rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        rec.width=rec.width;
        rec.height=rec.height;

        //im = Jna.getScreenshot(rec);
        ii = new ImageIcon();
        jl = new JLabel(ii);
        //ii2 = new ImageIcon();
        //jl2 = new JLabel(ii);
        jf.add(jl);
        jf.getContentPane().add(jl, BorderLayout.CENTER);
        //jf.getContentPane().add(jl2, BorderLayout.EAST);
        jf.setSize( rec.width,rec.height);
        jf.setVisible(true);
        a = new atask();
        a.execute();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }


    public class atask extends SwingWorker<Void, Integer> {


        @Override
        protected Void doInBackground() throws Exception {

            while (true) {
                //Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
                //Rectangle rec=new Rectangle(0,0,screenSize.width,screenSize.height);
                /*
                Screen ss = (Screen) sc.pop();
                if (ss == null) {
                    Thread.sleep(100);
                    continue;
                } else {
                    if (!jf.isVisible()) {
                        icon = new BufferedImage(ss.getW() * ss.getXmax(), ss.geth() * ss.getYmax(), BufferedImage.TYPE_INT_ARGB);
                        //icon.getGraphics().drawImage(ss.getIm(), ss.getW() * ss.getX(), ss.geth() * ss.getY(), null);
                        icon.getGraphics().drawImage(ss.getBufIm(), ss.getW() * ss.getX(), ss.geth() * ss.getY(), null);
                        jl.setIcon(new ImageIcon(icon));
                        jf.setSize(icon.getWidth(), icon.getHeight());
                        jf.setVisible(true);
                    }
*/

                publish(1);
            }

        }


        @Override
        protected void process(List<Integer> b) {
            //Screen ss=b.get(b.size()-1);
            //icon.getGraphics().drawImage(ss.getIm(),ss.getW()*ss.getX(),ss.geth()*ss.getY(),null);
            // icon.getGraphics().drawImage(ss.getBufIm(),ss.getW()*ss.getX(),ss.geth()*ss.getY(),null);
            //jl.setIcon(new ImageIcon(ScreenWatchdog.Resize(ss,2)));
            //jl.setIcon(new ImageIcon(icon));
            //ii.setImage(Jna.getScreenshot(rec));
            //jl.setIcon(new ImageIcon(Jna.getScreenshot(rec)));
            //jl2.setIcon(new ImageIcon(Jna.getScreenshot(rec)));
            jl.setIcon(new ImageIcon(robot.createScreenCapture( new Rectangle( Toolkit.getDefaultToolkit().getScreenSize() ) )));

        }

    }
}