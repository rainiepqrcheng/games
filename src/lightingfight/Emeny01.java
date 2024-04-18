package lightingfight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Emeny01 extends Emeny{
        private boolean faster =false;
        private static Image e01= Unit.getenemy01();
        private static Image[] d01 =Unit.getdead01();
    public Emeny01 (AnchorPane pane,Plane plane,ArrayList<Emeny> enemies,double difficulty)
    {   
        
        setImage(e01);
        hp = new SimpleIntegerProperty(3);
        deadImage =d01;
        sizex =50;
        sizey =50;
        setupPosition(sizex, sizey, pane);
        setFitHeight(sizey);
        setFitWidth(sizex);
        setLayoutX(px);
        setLayoutY(py);
        this.pane = pane;
        speed =8;
        point =10;
        enemymove(plane,enemies,difficulty);
        type =1;
    }
    
    //change the signal to set the number of sub_plane, when  creat new emeny 
    public Emeny01(Emeny enemy,int signal,Plane plane,ArrayList<Emeny> enemies,double difficulty)
     {
        this.setImage(e01);
        deadImage =d01;
        pane = enemy.getPane();
        hp = new SimpleIntegerProperty(3);
        sizex =50;
        sizey =50;
        speed = 8;
        point = 10;
        this.setFitHeight(sizey);
        this.setFitWidth(sizex);
        px =enemy.getLayoutX()+sizex*signal;
        py =enemy.getLayoutY()-sizey/2;
        this.setLayoutX(px);
        this.setLayoutY(py);
        enemymove(plane,enemies,difficulty);
 
        type=1;
     }
    
        


     public void enemymove(Plane player,ArrayList<Emeny> enemies,double difficulty)
    {  
        timeline =new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(32),eventEnemy->
        {   
            
            boolean canmove =true;
            crushDecte(player, pane, enemies);  
            for(Emeny enemy:enemies)
            {
             if(enemy!=this && enemy.type==1 && enemy.getBoundsInParent().intersects(this.getBoundsInParent())&& enemy.getLayoutY()>this.getLayoutY())
             {
              canmove =false;
             }
            }
            
           
            if(difficulty>1){
            double deltaX = (player.getLayoutX()+player.getFitWidth()/2)- (this.getLayoutX()+this.getFitWidth()/2);
            double deltaY = (this.getLayoutY()+this.getFitHeight()/2)-(player.getLayoutY()+player.getFitHeight()/2);
            double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));     
            double actualx= deltaX / length;
            double actualy = deltaY / length;
            if(length<200)
            {             
             if(canmove==true)
             {         
               this.setLayoutX(this.getLayoutX() + (actualx*speed)); 
               this.setLayoutY(this.getLayoutY()-(actualy*speed));
             }      
            }else
            {
              this.setLayoutY(this.getLayoutY()+speed);
            }}else
            {
              this.setLayoutY(this.getLayoutY()+speed);
            }
            if(this.getLayoutY()>pane.getHeight())
            {
                pane.getChildren().remove(this);
                timeline.stop();
                enemies.remove(this);
            }
           
        });
        timeline.getKeyFrames().add(key);
        timeline.setCycleCount(Timeline.INDEFINITE);
    
    }
     
 
    


    }
