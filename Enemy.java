import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Enemy{

    private BufferedImage image;

    private int health;
    private int x, y;
    private int newX, newY;
    public ArrayList<Bullet> enemyMagazine;
    public ArrayList<Bullet> enemyFiredBullets;
   
    private int[] slopes;
    private boolean canPickUP;
    public boolean fireBullet;
    private int bulletsOut;
    private int shotTimer;
    private int reloadTimer;
    private Player p;
    private int reloadcounter;
    private boolean left, right;
    public Enemy(int x, int y, Player p){

        
        this.p = p;
        health = 100;
        shotTimer = 0;
        reloadTimer = 0;
        bulletsOut = 0;
        canPickUP = false;
        fireBullet = false;
        this.x = x;
        this.y = y;
        enemyMagazine = new ArrayList<Bullet>();
        enemyFiredBullets = new ArrayList<Bullet>();

        left = false;
        right = false;
        reloadcounter = 0;

        slopes = new int[15];
   
        for (int i =0; i<15; i++){
            enemyMagazine.add(new Bullet());
            enemyMagazine.get(i).setXY(getX(), getY());
           
        }


        
        try{
            image = ImageIO.read(new File("enemy.png"));
        }
        catch (IOException e){
            System.out.println(e);
        }

        enemyFiredBullets.clear();
    }
    
    
    

    public int getX() {
        return newX;
    }

    public int getY() {
        return newY;
       
    }
    public int getWidth() {
        return 100;
       
    }

    public int getHeight() {
        return 140;
       
    }

    public Rectangle getRect() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
       
    }

    public void setX(int x) {
        this.x = x;
       
    }

    public void setY(int y) {
        this.y = y;
       
    }

    public String getName(){
        return "Enemy";
    }
    


    public void createBullet(){
        if (enemyMagazine.size()>0 ){
            Bullet b = enemyMagazine.get(0);
            enemyFiredBullets.add(b);
            enemyMagazine.remove(0);
            b.setXY(getX(), getY()+60);
            //enemyFiredBullets.get(enemyFiredBullets.size()-1).setXY(this.x, this.y);

            
            //fireBullet = false;
            bulletsOut++;
            
        }

        else if (enemyMagazine.size()<=0){
            reloadcounter++;

            System.out.println("Mag empty - "+reloadcounter);
            if (reloadcounter == 6){
                enemyFiredBullets.clear();
                enemyMagazine.clear();
                for (int i = 0; i< 15; i++){
                    enemyMagazine.add(new Bullet());
                    reloadcounter = 0;

                }
            }
        }
    }

    
    public void fireBullet(){
        
            if (health>0){
            //if (getX()<700){
               /*  shotTimer++;
                if (shotTimer == 30){
                    if (bulletsOut<15){

                        bulletsOut++;
                    }
                    shotTimer = 0;

                }*/

                if (bulletsOut == 15){
                    reloadTimer++;
                    if (reloadTimer == 50){
                        shotTimer = 0;
                        reloadTimer = 0;
                        bulletsOut = 0;
                        

                    }
                }
                
                


                fireBullet = true;
            }
            for (int i =0; i<enemyFiredBullets.size(); i++){
                //calculate slope to shoot bullets

                    //left side
                if (getX()>p.getX()){
                    enemyFiredBullets.get(i).moveLeft(10);
                    
                }
               
            }

        //}
    }

    
            
            
    

    public void drawMe(Graphics g, int xDifference, int yDifference) {

        newX = this.x+xDifference;
        newY = this.y+yDifference;
        if (health<=0){
            enemyFiredBullets.clear();
            enemyMagazine.clear();
            setX(20000);

        }
        //if (health>0){
            //draw enemy
        g.setColor(Color.black);
        g.fillRect(getX(), getY(), getWidth(), getHeight());


        //draw health bar
        g.drawRect(getX(), getY()-40, 100, 10);
        if (health>40){
            g.setColor(Color.green);
        }

        else if (health>20){
            g.setColor(Color.yellow);
        }

        else {
            g.setColor(Color.red);
        }
        g.fillRect(getX(), getY()-40, health, 10);
        

        for (int i = 0; i<enemyFiredBullets.size(); i++){
            enemyFiredBullets.get(i).drawMe(g);
        }

        
        /*if (fireBullet && enemyMagazine.size()>0 ){
            Bullet b = enemyMagazine.get(0);
            enemyFiredBullets.add(b);
            enemyMagazine.remove(0);
            b.setXY(getX(), getY());
            //enemyFiredBullets.get(enemyFiredBullets.size()-1).setXY(this.x, this.y);

            
            fireBullet = false;

        }
        */
    }

    public int getHealth(){
        return this.health;
    }

    public void decreaseHealth(int decreaseAmount){
        
            if (health>0){
                this.health-=decreaseAmount;
            }
        
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    
    
}
