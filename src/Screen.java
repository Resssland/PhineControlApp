import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Screen implements Serializable {
    private int x=0;
    private int y=0;
    private int xmax=1;
    private int ymax=1;
    private int h=0;
    private int w=0;
    private byte[] byteImage=null;
    BufferedImage bufIm=null;

    public Screen(int x, int y, int xmax, int ymax, BufferedImage im){
        this.x=x;
        this.y=y;
        this.xmax=xmax;
        this.ymax=ymax;
        h=im.getHeight();
        w=im.getWidth();
        this.bufIm=im;
        //setIm(im);


    }
    public Screen(){}
    public BufferedImage getBufIm(){return this.bufIm;}
    public void setBufIm(BufferedImage im){this.bufIm=im;}
    public int geth(){return this.h;}
    public int getW(){return this.w;}
    public void setH(int h){this.h=h;}
    public void setW(int w){this.w=w;}
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getXmax(){
        return xmax;
    }
    public int getYmax(){
        return ymax;
    }
    public BufferedImage getIm(){
        InputStream in = new ByteArrayInputStream(byteImage);
        BufferedImage bImageFromConvert = null;
        try {
            bImageFromConvert = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bImageFromConvert;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public void setYmax(int ymax){
        this.ymax=ymax;
    }

    public void setXmax(int xmax){
        this.xmax=xmax;
    }

    public void setIm(BufferedImage im){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(im, "png", baos);
            baos.flush();
            this.byteImage = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
