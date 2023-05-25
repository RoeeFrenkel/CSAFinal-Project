import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PrintInstructions{

    private Player p;

    public PrintInstructions(Player p){

        this.p = p;
    }
    public void quest1Instr(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("Hey there. My name is Mike. The first thing that you", 50, 50);
        g.drawString("need to do is grab a wrench. I think I left one somewhere", 50, 70);
        g.drawString("in the ancient ruins North of the airfield.", 50, 90);


    }


    public void quest2Instr(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("Nice Work! Head back to the airfield to talk to Mike", 50, 50);
        
    }
    public void quest3Instr(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("Nice Work. Now, the next step to repair the plane is ", 50, 50);
        g.drawString("to find a propeller from the scrap pile. Head East", 50, 70);


    }

    public void quest4Instr(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("You got it, now head back to Mike, so that we can finally", 50, 50);
        g.drawString("get this plane up and running.", 50, 70);

    }

    public void quest5Instr(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("Great job, son. Now we've got everything we need to get this baby working", 50, 50);
        g.drawString("again. I'm going to need you to walk over to the plane and hold down", 50, 70);
        g.drawString("the K key for a few seconds. That should do it.", 50, 90);

    }

    public void rationsHealthFull(Graphics g){
        Font normalFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(normalFont);
        g.setColor(Color.white);
        g.drawString("Health is full, cannot consume rations", p.getX()-125, p.getY()-70);



    }
}