import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Plane extends Item{

    private BufferedImage image;

    private int x, y;
    private int newX, newY;
    
    public Plane(int x, int y){

        canPickUP = false;
        this.x = x;
        this.y = y;
        try{
            image = ImageIO.read(new File("plane.png"));
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
        return newX+180;
    }

    @Override
    public int getY() {
        return newY+200;
       
    }
    @Override
    public int getWidth() {
        return 300;
       
    }

    @Override
    public int getHeight() {
        return 250;
       
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getX(), getY(), getWidth(),getHeight() );
       
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
        return "Plane";
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    

    
    
}
