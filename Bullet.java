import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Bullet {
    

    private int x, y;
  

    public void drawMe(Graphics g){
        //draw bullet at x+xdiff+xvelocity, y+ ydiff+ytrajectory
       
        g.setColor(Color.black);
        g.fillRect(x, y, 20, 8);
        

       
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, 20, 8);
    }

  
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void moveRight(int amount){
        this.x+=amount;
    }
    public void moveLeft(int amount){
        this.x-=amount;
    }
    public void moveUp(int amount){
        this.y-=amount;
    }

    public void moveDown(int amount){
        this.y+=amount;
    }
    

    public int getX(){
        return this.x;
    }

    
}
