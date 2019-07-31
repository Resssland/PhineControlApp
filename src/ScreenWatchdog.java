import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;

public class ScreenWatchdog implements Runnable{
    BufferedImage im1=null;
    BufferedImage im2=null;
    Dimension screenSize;
    MessageQueue<Screen> Mq;

    public ScreenWatchdog(MessageQueue<Screen>  Mq){
        screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        this.Mq=Mq;




    }
    public ScreenWatchdog(MessageQueue<Screen>  Mq,int x,int y,int xmax,int ymax) {
        screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        this.Mq=Mq;
    }
        public void DividerComperator(BufferedImage im ,int xmax,int ymax,MessageQueue<Screen> Mq)
    {
        ArrayDeque<Screen> sc= new ArrayDeque<>();
        for (int x=0;x<xmax;x++){
            for(int y=0;y<ymax;y++)
            {
                BufferedImage im_new=im.getSubimage(im.getWidth()*x/xmax,im.getHeight()*y/ymax,im.getWidth()/xmax,im.getHeight()/ymax);
                BufferedImage im_old=im1.getSubimage(im1.getWidth()*x/xmax,im1.getHeight()*y/ymax,im1.getWidth()/xmax,im1.getHeight()/ymax);
                if(!ImageComparator(im_new,im_old,50)){
                    Mq.offer(new Screen(x,y,xmax,ymax,Resize(im_old,2)));
                }

            }
        }

        im1=grabImage(0,0,1,1,screenSize);

    }


    @Override
    public void run() {



        int xmax=1;
        int ymax=1;
        //im1=grabImage(0,0,1,1,screenSize);
        while(true){

            im1=grabImage(0,0,1,1,screenSize);
            Mq.offer(new Screen(0,0,1,1,Resize(im1,2)));
            //DividerComperator(grabImage(0,0,1,1,screenSize),xmax,ymax,Mq);
            //Mq.offer(new Screen(0,0,1,1,im1));



            /*
        for(int x=0;x<xmax;x++)
        for(int y=0;y<ymax;y++)

        {
            //Mq.offer(new Screen(x,y,xmax,ymax,Resize(grabImage(y,x,ymax,xmax,screenSize),3)));


            if(im1==null){im1=grabImage(y,x,ymax,xmax,screenSize); Mq.offer(new Screen(x,y,xmax,ymax,Resize(im1,3)));}
            else{



                if(ImageComparator(im1,grabImage(y,x,ymax,xmax,screenSize),100)
                        )
                        {
                    try {
                        Thread.sleep(20);continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    im1=grabImage(y,x,ymax,xmax,screenSize);
                    Mq.offer(new Screen(x,y,xmax,ymax,Resize(im1,3)));
                }
            }

        }*/

    }}

    private BufferedImage  grabImage(int h,int w,int hmax,int wmax,Dimension screenSize)  {
        return  Jna.getScreenshot
                (
                    new Rectangle(screenSize.width*w/wmax,screenSize.height*h/hmax,screenSize.width/wmax,screenSize.height/hmax)
                );

    }
        private boolean ImageComparator(BufferedImage im1,BufferedImage im2,int k){
        double width=im1.getWidth();
        double heigth=im1.getHeight();
        int comp=(int)(width/k);
        for(int x=0;x<width;x+=comp){
            int y=(int)(-(heigth-1)/(width-1)*x+(heigth-1));
            if(im1.getRGB(x,y)!=im2.getRGB(x,y))return false;
        }
            for(int x=0;x<width;x+=comp){
                int y=(int)(heigth/width*x);
                if(im1.getRGB(x,y)!=im2.getRGB(x,y))return false;
            }
        return true;
        }


        public static BufferedImage Resize(BufferedImage before,int k){
            BufferedImage after=new BufferedImage(before.getWidth()/k,before.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
            BufferedImage afterAll=new BufferedImage(after.getWidth(),before.getHeight()/k,BufferedImage.TYPE_4BYTE_ABGR);

            int x=0;
            int y=0;
            for(int i=0;i<before.getWidth();i+=k){

                after.getGraphics().drawImage(before.getSubimage(i,0,1,before.getHeight()),x,0,null);
                x++;
            }
            for(int i=0;i<before.getHeight();i+=k){

                afterAll.getGraphics().drawImage(after.getSubimage(0,i,after.getWidth(),1),0,y,null);
                y++;
            }
            return afterAll;




        }




}
