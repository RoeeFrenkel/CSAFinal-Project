import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Screen extends JPanel implements KeyListener, ActionListener, MouseListener {

    private JButton start;
    private JButton HowToPlay;
    private JButton back;
    private ArrayList<Item> groundItems;
    private ArrayList<Item> inventoryItems;

    private ArrayList<Bullet> playerMagazine;
    private ArrayList<Bullet> playerFiredBullets;

    private Enemy enemy;


    private BufferedImage bGimage;

    private int questSteps;
    private Player p1;
    private StartMenu startMenu;
    private PrintInstructions instructions;
    private Inventory inventory;

    private boolean startPressed, instructionsPressed;
    private boolean quest1Print, quest2Print, quest3Print, quest4Print, quest5Print, quest6Print, quest7Print;
    private int xDifference, yDifference;
    private int previousXDifference, previousYDifference;
    private boolean fireBullet;

    private int mouseX, mouseY;

    private boolean rationWarning;
    private int leftCount, rightCount, upCount, downCount;

    private int[] xp;
    private int yp;
    private int warningTimer;
    private long startTime, endTime;
    private double timer;
    private boolean kPressedFirsttime;
    private boolean touchingPlane;
    public Screen() {


        try{
            bGimage = ImageIO.read(new File("desertTexture.jpeg"));
        }
        catch (IOException e){
            System.out.println(e);
        }
        p1 = new Player(380, 260);
        instructions = new PrintInstructions(p1);
        startMenu = new StartMenu();
        groundItems = new ArrayList<Item>();
        inventoryItems = new ArrayList<Item>();

        playerMagazine = new ArrayList<Bullet>();
        playerFiredBullets = new ArrayList<Bullet>();

        xp = new int[10];
   
        for (int i =0; i<10; i++){
            playerMagazine.add(new Bullet());
            xp[i] = 380;
        }


        enemy = new Enemy(1700, 500, p1);

     

        inventory = new Inventory();

        groundItems.add(new Wrench(480, -1200));
        
        groundItems.add(new Speaker(600, 300, p1));

        groundItems.add(new Plane(-250, -100));

        groundItems.add(new Propeller(1900, 500));

        groundItems.add(new Ration(200, 100));
        groundItems.add(new Ration(1200, 800));
        groundItems.add(new Ration(500, -100));

        groundItems.add(new Crate(200, 400));
        groundItems.add(new Crate(-100, 0));
        groundItems.add(new Crate(1500, 400));
        groundItems.add(new Crate(700, -440));





        addMouseListener(this);  

        setLayout(null);
		start = new JButton("Start");
		start.setBounds(300, 150, 200, 130);
		add(start);
		start.addActionListener(this);

        HowToPlay = new JButton("How to Play");
		HowToPlay.setBounds(300, 300, 200, 130);
		add(HowToPlay);
		HowToPlay.addActionListener(this);

        back = new JButton("Back");
		back.setBounds(700, 500, 80, 30);
		add(back);
		back.addActionListener(this);
        back.setVisible(false);
        //sets up the instance variables	

        previousXDifference = 0;
        previousYDifference = 0;
        questSteps = 0;
        quest1Print = false;
        quest2Print = false;
        quest3Print = false; quest4Print = false; quest5Print = false; quest6Print = false; quest7Print = false;
        
        rationWarning = false;
        
        kPressedFirsttime = false;

        xDifference = 0;
        yDifference = 0;
        startPressed = false;
        instructionsPressed = false;

        warningTimer = 0;
       
        yp = 260;
       
        fireBullet  = false;
        //sets keylistener
        setFocusable(true);
        addKeyListener(this);

        leftCount = 0;
        rightCount = 0;
        upCount = 0;
        downCount = 0;
        
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800, 600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        

        
        //draws background
        int bgTileX = -1000;
        int bgTileY = -800;
        for (int r = 0; r< 10; r++){
            bgTileX = -1000;
            for (int c =0; c<10; c++){
                g.drawImage(bGimage, bgTileX+xDifference*2, bgTileY+yDifference*2, null);
                bgTileX+=400;
            }
            bgTileY+=300;
        }
        

        //draw the airfield 
        g.setColor(Color.gray);
        g.fillRect(-100+xDifference*2,0+yDifference*2, 1000, 600);
       


        //Draw the ground items


        for(int i=0; i<groundItems.size(); i++){
            groundItems.get(i).drawMe(g, xDifference*2, yDifference*2);
        }

        //draws player
        p1.drawMe(g);

        //in case of player death, reteleport.
        if (p1.getHealth()<=0){
            

            //change xydifference based on how much the player has moved to reset position

            //left movements
            setXVelocity(leftCount*-30);
            setXVelocity(rightCount*30);
            setYVelocity(upCount*-30);
            setYVelocity(downCount*30);
            leftCount = 0;
            rightCount = 0;
            upCount = 0;
            downCount = 0;

            p1.setHealth(70);
        }

        

        //draws enemy
        enemy.drawMe(g, xDifference*2, yDifference*2);

        
        for (int i = 0; i<playerFiredBullets.size(); i++){
            playerFiredBullets.get(i).drawMe(g);
        }
       
                
            
            
        
        
       
        


        //draws the inventory items
        int itemX = 150;
        
        for (int i =0; i<inventoryItems.size(); i++){
            inventoryItems.get(i).setX(itemX);
            inventoryItems.get(i).setY(540);


            inventoryItems.get(i).drawMe(g, 0, 0);
            itemX+=90;

        }


                    
            //draw the bullet counter
            Font font = new Font("Arial", Font.PLAIN, 50);
            setFont(font);
            g.setColor(Color.black);
            g.drawString(""+playerMagazine.size(), 700, 50);



            

    


        
        //draws warning for health full when player tries to consume ration

        if (rationWarning && warningTimer<3000){
            instructions.rationsHealthFull(g);
            warningTimer++;
        }
        if (warningTimer == 3000){
            rationWarning = false;
            warningTimer = 0;
        }

        //draws the instruction that are printed out
        if (quest1Print){
            instructions.quest1Instr(g);
            
            if (inventoryItems.size() == 1 && questSteps == 0){
                quest2Print = true;
                quest1Print = false;
                questSteps = 1;
            }

            

        }
            

        if (quest2Print){
            instructions.quest2Instr(g);
            
            questSteps = 2;
        }
       

        if (quest3Print){
            quest2Print = false;
            instructions.quest3Instr(g);


            if (inventoryItems.size() == 2){
                quest3Print = false;
                quest4Print = true;
                questSteps = 3;
            }


        }

        if (quest4Print){
            instructions.quest4Instr(g);
            questSteps = 4;
            
        }

        if (quest5Print){
            quest4Print = false;
            instructions.quest5Instr(g);

            questSteps = 5;
        }

        if (quest6Print == true){
            quest5Print = false;
            for (int i =0; i<inventoryItems.size();i++){
                inventoryItems.remove(i);
                repaint();

            }



        }



        //draws inventory frame
       inventory.drawMe(g);
        
        if (startPressed == false){
            startMenu.drawMe(g);
        }

        if (instructionsPressed == true){
            startMenu.drawInstructiong(g);
        }

        

    }

    public void updateCollisions(){

        for (int i = 0; i<playerFiredBullets.size(); i++){

                if (enemy.getRect().intersects(playerFiredBullets.get(i).getRect())){
                
                    enemy.decreaseHealth(5);
                    playerFiredBullets.get(i).setXY(20000, 0);
                }
            
            


            
        }



            for (int i =0; i<enemy.enemyFiredBullets.size(); i++){
                if (p1.getRect().intersects(enemy.enemyFiredBullets.get(i).getRect())){
                    p1.decreaseHealth(5);;
                    System.out.println(""+p1.getHealth());
                    enemy.enemyFiredBullets.get(i).setXY(-20000, 0);
                }
            }
        
        
        for (int i = 0; i<groundItems.size(); i++){


            if (p1.getRect().intersects(groundItems.get(i).getRect())){
                

                //enables plane touching but no collision
                if (groundItems.get(i).canPickUP == false){
                   
                    if (groundItems.get(i).getName().equals("Crate")){
                        xDifference = previousXDifference;
                        yDifference = previousYDifference;

                        
                    }

                    if (groundItems.get(i).getName().equals("Ration")){

                        if (p1.getHealth() == 70){
                            rationWarning = true;
                        }
                        if (p1.getHealth()<70){
                            groundItems.get(i).setX(20000);
                            rationWarning = true;
                        }
                        


                        if (p1.getHealth()<=55)
                        p1.decreaseHealth(-15);

                        if (p1.getHealth()>55 && p1.getHealth()<70)
                        p1.setHealth(70);
                        
                    }

                    if (groundItems.get(i).getName().equals("Plane")){
                        touchingPlane = true;

                        
                    }
                    
                    if (groundItems.get(i).getName().equals("Enemy")){
                        xDifference = previousXDifference;
                        yDifference = previousYDifference;
                    }
                    //enable speaker collisions, with print outs 
                    if (groundItems.get(i).getName().equals("Speaker")){
                        xDifference = previousXDifference;
                        yDifference = previousYDifference;
                        
                        if( questSteps == 0){
                            quest1Print = true;
            
                        }

                        if (questSteps == 2){
                            quest3Print = true;
                        }

                        if (questSteps == 4){
                            quest5Print = true;
                        }

                        
                    }

                    

                    
                }
                

                //adds items to inventory and removes from ground
                if (groundItems.get(i).canPickUP == true){
                    inventoryItems.add(groundItems.get(i));
                    groundItems.remove(i);
                    repaint();
                }
                
            }
        }
    }

    public void animate(){
        int count = 0;
        while (true){
            for (int i = 0; i<playerFiredBullets.size(); i++){
                //if (mouseX-p1.getX()>0){
                   // xp[i] += 10;

               // }

               /*  if (mouseX-p1.getX()<=0){
                    xp[i] -= 10;

                }*/
                playerFiredBullets.get(i).moveRight(10);
                if (playerFiredBullets.get(i).getX()>800){
                    playerFiredBullets.remove(i);
                    i-=1;
                }

            }

            

            if (count%50 == 0 && enemy.getX()<800){
                enemy.createBullet();
                count = 0;

            }

            
            enemy.fireBullet();
            updateCollisions();
            count++;
            repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            
            
            }
        }
        
    

        public void setXVelocity(int xVel){
            xDifference+=xVel;
        }

        public void setYVelocity(int yVel){
            yDifference+=yVel;
        }

    //implement methods of the KeyListener
    public void keyPressed(KeyEvent e) {

        previousXDifference = xDifference;
        previousYDifference = yDifference;

        
        //left arrow
        if (e.getKeyCode() == 37){
            setXVelocity(15);
            leftCount++;
        }

        //right arrow
        else if (e.getKeyCode() == 39){
            setXVelocity(-15);
            rightCount++;

        }

        //up arrow
        else if (e.getKeyCode() == 38){
            setYVelocity(15);
            upCount++;

            

        }

        //down arrow
        else if (e.getKeyCode() == 40){
            setYVelocity(-15);
            downCount++;

        }


        //cheat key
        else if (e.getKeyCode() == 'O'){
            switch(questSteps){

                case(0):
                quest1Print = true;
                questSteps = 1;
                break;

                case(1):
                quest1Print = false;
                quest2Print = true;
                questSteps = 2;
                break;


                case(2):
                quest2Print = false;
                quest3Print = true;
                questSteps = 3;
                break;


                case(3):
                quest3Print = false;
                quest4Print = true;
                questSteps = 4;
                break;


                case(4):
                quest4Print = false;
                quest5Print = true;
                questSteps = 5;
                break;


                case(5):
                quest5Print = false;
                quest6Print = true;
                questSteps = 6;
                break;

            }
        }
            
        

        //space
        else if (e.getKeyCode() == 32){
            //fireBullet = true;
            if (playerMagazine.size()>0){

                Bullet b = new Bullet();
                b.setXY(p1.getX(), p1.getY());
                playerFiredBullets.add(b);
                playerMagazine.remove(0);
                //xp[playerFiredBullets.size()-1] = 380;
    
               
                fireBullet = false;
    
            }

        }
        //test
        else if (e.getKeyCode() == 'F'){

        }

        //reload
        else if (e.getKeyCode() == 'R'){
            fireBullet = false;
            for (int i =playerMagazine.size(); i<10; i++){
                Bullet bull = new Bullet();
                playerMagazine.add(bull);
                
                
                

                
                
            }
        }


        //repair function press
        else if (e.getKeyCode() == 'K' && questSteps==5 && touchingPlane == true){
            
            if (kPressedFirsttime == false){
                System.out.println("K pressed");
                startTime = System.currentTimeMillis();
                kPressedFirsttime = true;
            }
            
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {


        //repair function release
        if (e.getKeyCode() == 'K' && questSteps == 5){
            System.out.println("K released");
            kPressedFirsttime = false;
            endTime = System.currentTimeMillis();

            timer = 2*((endTime-startTime)/1000.0);
            System.out.println("YOU HELD K FOR "+timer + "SECONDS");

            if (timer>5){
                System.out.println("Success");
                questSteps = 6;
                System.out.println(""+questSteps);
                quest6Print = true;


            }
        }


        if (e.getKeyCode() == 37){
            setXVelocity(0);
            repaint();
        }

        //right arrow
        else if (e.getKeyCode() == 39){
            setXVelocity(0);
            repaint();

        }

        //up arrow
        else if (e.getKeyCode() == 38){
            setYVelocity(0);
            repaint();

        }

        //down arrow
        else if (e.getKeyCode() == 40){
            setYVelocity(0);
            repaint();

        }



        
    }
    public void keyTyped(KeyEvent e) {}


    //buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == start){
            startPressed = true;
            start.setVisible(!startPressed);
            HowToPlay.setVisible(!startPressed);
            repaint();

        }
        if (e.getSource() == HowToPlay){
            start.setVisible(false);
            HowToPlay.setVisible(false);
            instructionsPressed =true;
            back.setVisible(true);
            repaint();
            

        }

        if (e.getSource() == back){
            start.setVisible(true);
            HowToPlay.setVisible(true);
            startPressed = false;
            instructionsPressed = false;
            back.setVisible(false);
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println(e.getX()+ ":"+e.getY());
        mouseX = e.getX();
        mouseY = e.getY();
        fireBullet = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}