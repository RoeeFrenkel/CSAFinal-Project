import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Player {

    
    private int x;
    private int y;
   
    private int health;
    private int width;
    private int height;


    public Player(int x, int y) {


        health = 70;
        this.x = x;
        this.y = y;
        

        
        this.width = 40; //total width of the player
        this.height = 80; //total height of the player




    }


    public void setHealth(int amount){
        health = amount;
    }
    public void drawMe(Graphics g) {


        
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, width);
        g.fillRect(x + 15, y, 10, height);


        //draw health bar
        g.drawRect(getX()-15, getY()-20, 70, 10);
        if (health>40){
            g.setColor(Color.green);
        }

        else if (health>20){
            g.setColor(Color.yellow);
        }

        else {
            g.setColor(Color.red);
        }
        g.fillRect(getX()-15, getY()-20, health, 10);
    }


    public int getX(){
        return x;
    }

    public Rectangle getRect(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void decreaseHealth(int amount){
        this.health -= amount;
    }
    
    public int getHealth(){
        return this.health;
    }

}

  


    




