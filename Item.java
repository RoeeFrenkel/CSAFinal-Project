import java.awt.Graphics;
import java.awt.Rectangle;
public abstract class Item {
    
   
    public abstract void drawMe(Graphics g, int xDifference, int yDifference);

    
    public boolean canPickUP;
    public abstract int getX();

    public abstract int getY();

    public abstract void setX(int x);

    public abstract void setY(int y);

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract String getName();
    public abstract Rectangle getRect();

    public void unDraw() {
    }
    



}
