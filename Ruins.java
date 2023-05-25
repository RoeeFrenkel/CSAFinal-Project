import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Ruins extends Item{

    private BufferedImage image;

    private int x, y;
    private int newX, newY;
    
    public Ruins(int x, int y){

        canPickUP = false;
        this.x = x;
        this.y = y;
        try{
            image = ImageIO.read(new File("crate.png"));
        }
        catch (IOException e){
            System.out.println(e);
        }

   

    }
    
    
    @Override
    public void drawMe(Graphics g, int xDifference, int yDifference) {

       
        g.drawImage(image, x+xDifference, y+yDifference, null);
        newX = x+xDifference;
        newY = y+yDifference;
    }


    @Override
    public int getX() {
        return newX;
    }

    @Override
    public int getY() {
        return newY;
       
    }
    @Override
    public int getWidth() {
        return 45;
       
    }

    @Override
    public int getHeight() {
        return 50;
       
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
       
    }

    @Override
    public void setX(int x) {
        this.x = x;
       
    }

    @Override
    public void setY(int y) {
        this.y = y;
       
    }

    @Override
    public String getName(){
        return "Ruins";
    }

    
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
