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
    BufferedImage icon;
    private atask a;
    MessageQueue<Screen> sc;

    public  Form(MessageQueue<Screen> sc) throws IOException  {
        this.sc=sc;

        jf=new JFrame("Receiver");
        // File f=new File("C:\\Users\\Руслан\\Desktop\\im\\screen53.png");
        //Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        //Rectangle rec=new Rectangle(0,0,screenSize.width,screenSize.height);
        //im=Jna.getScreenshot(rec);
        ii=new ImageIcon();
        jl=new JLabel(ii);
        jf.add(jl);
        jf.getContentPane().add(jl, BorderLayout.CENTER);
        //jf.setSize(im.getWidth()/2,im.getHeight()/2);
        //jf.setVisible(true);
        a=new atask();
        a.execute();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }




    public class atask extends SwingWorker<Void,Screen>
    {


        @Override
        protected Void doInBackground() throws Exception {

            while (true) {
                //Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
                //Rectangle rec=new Rectangle(0,0,screenSize.width,screenSize.height);
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


                    publish(ss);

                }
            }
        }


        @Override
        protected void process(List<Screen> b){
            Screen ss=b.get(b.size()-1);
            //icon.getGraphics().drawImage(ss.getIm(),ss.getW()*ss.getX(),ss.geth()*ss.getY(),null);
                icon.getGraphics().drawImage(ss.getBufIm(),ss.getW()*ss.getX(),ss.geth()*ss.getY(),null);
            //jl.setIcon(new ImageIcon(ScreenWatchdog.Resize(ss,2)));
            jl.setIcon(new ImageIcon(icon));




    }
        }
}