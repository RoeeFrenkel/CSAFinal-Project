import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class StartMenu {
    
    private int windowWidth;
    private int windowHeight;

    private int textWidth;
    private int textHeight;

    private BufferedImage instructionImage;



    public StartMenu() {
    
        windowHeight = 600;
        windowWidth = 800;

        textHeight = 400;
        textWidth = 500;
        try{
            instructionImage = ImageIO.read(new File("instructionImage.png"));
        }
        catch (IOException e){
            System.out.println(e);
        }
       
    }


    public void drawMe(Graphics g) {
        //draw background
        g.setColor(Color.gray);
        g.fillRect(0, 0, windowWidth, windowHeight);

        

        
        //draw textbox
        g.setColor(Color.darkGray);
        g.fillRect(150, 100, textWidth, textHeight);


    }

    public void drawInstructiong(Graphics g){
        //draw instructions
        g.drawImage(instructionImage, 0, 0, null);
    }


   


}