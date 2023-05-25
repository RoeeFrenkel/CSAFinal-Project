import java.awt.Color;
import java.awt.Graphics;

public class Tree{
	private int x;
	private int y;
	private Color brown;
	private Color darkGreen;
	
	public Tree(int x, int y){	
		this.x = x;
		this.y = y;

		brown = new Color(205,133,63);
		darkGreen = new Color(0,100,0);
	}
	

	public void drawMe(Graphics g, int xDifference, int yDifference){
	
		g.setColor(brown);
		g.fillRect(x+30+xDifference,y+50+yDifference,40,100); 
		
		g.setColor(darkGreen);
		g.fillOval(x+xDifference,y+yDifference,100,100);
	}



}
