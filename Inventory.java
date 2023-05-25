import java.awt.Graphics;
import java.awt.Color;

public class Inventory {
    

    public void drawMe(Graphics g){
        g.setColor(Color.black);
                //draw vertical lines
        g.fillRect(120, 520, 15, 100);

        g.fillRect(220, 520, 15, 100);
        g.fillRect(320, 520, 15, 100);
        g.fillRect(420, 520, 15, 100);
        g.fillRect(520, 520, 15, 100);
        g.fillRect(620, 520, 15, 100);


        


        //horizontal line
        g.fillRect(120, 520, 500, 15);
    }
}
