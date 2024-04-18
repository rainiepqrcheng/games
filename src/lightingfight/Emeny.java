package lightingfight;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public  class Emeny extends ImageView{
    protected IntegerProperty hp ;
    protected double px,py,sizex,sizey,speed;
    protected AnchorPane pane;
    protected Timeline timeline,shooting =new Timeline(),paused;
    protected int point,type,playcount;
    protected boolean canrepeat = true,inArray =false,hurtable=true;
    protected Image[] deadImage;
    
    //set the random postion of enemy
    protected void setupPosition(double sizex,double sizey,AnchorPane pane){
        px = sizex+ Math.random()*(pane.getWidth()-sizex*2);
        py = -sizey;
    }
    
public void sethp(int damge)
{   
    int hpchange = hp.get();
    hpchange = hpchange-damge;
    hp.set(hpchange);
}



public void handleDeath(IntegerProperty score, ArrayList<Emeny> enemies) {
    if(this.hp.get() <= 0) {
    if(canrepeat ==false)
    {
     return;
    }else{ 
        this.setImage(null);
        enemies.remove(this);
        this.timeline.stop();
        this.canrepeat =false;
        
        this.shooting.stop();
        Timeline pause = new Timeline(new KeyFrame(Duration.millis(16),event->
        {
            this.setImage(deadImage[playcount]);
            playcount++;
          
        }));
        pause.setCycleCount(36);
        pause.play();
        pause.setOnFinished(event->{
            this.setImage(null);
            this.timeline.stop();
            pane.getChildren().remove(this);
            score.set(score.get() + this.point);
            });
     
       }
      
    }
}




public AnchorPane getPane()
{
    return pane;
}

protected void crushDecte (Plane player,AnchorPane pane,ArrayList<Emeny> enemies) {

    
    double deltaX = (player.getLayoutX()+player.getFitWidth()/2)- (this.getLayoutX()+this.getFitWidth()/2);
    double deltaY = (this.getLayoutY()+this.getFitHeight()/2)-(player.getLayoutY()+player.getFitHeight()/2);
    double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    if(length<=50){ 
        if(canrepeat ==false)
    {
     return;
    }else{
    if(type!=3)
    {

        this.canrepeat =false;
        this.shooting.stop();
        playcount =0;
        enemies.remove(this);
        Timeline pause = new Timeline(new KeyFrame(Duration.millis(16),event->
        {
         this.setImage(deadImage[playcount]);
         playcount++;
        }));
        pause.setCycleCount(36);
        pause.play();
        pause.setOnFinished(event->{
        this.setImage(null);
        this.timeline.stop();
        pane.getChildren().remove(this);
        });
    }
      player.gethurt();
    }}
   }
}

