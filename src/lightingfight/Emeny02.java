package lightingfight;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Emeny02 extends Emeny
{   private static Image e02 = Unit.getemeny02();
  private static Image[] dead02 =Unit.getdead02();
    public Emeny02 (AnchorPane pane,ArrayList<Emeny> enemies,Plane plane, ArrayList<Bullet> bullets,double difficulty)
    {   deadImage =dead02;
        setImage(e02);
        hp = new SimpleIntegerProperty(10);
        sizex =100;
        sizey =100;
        setupPosition(sizex, sizey, pane);
        setFitHeight(sizey);
        setFitWidth(sizex);
        setLayoutX(px);
        setLayoutY(py);
        this.pane = pane;
        speed =1;
        point =50;
        type =2;
        enemymove(plane,enemies);
        paused =new Timeline(new KeyFrame(Duration.seconds(3)));
        paused.setCycleCount(1);
        if(difficulty>1)
        {
          makeshooting2(bullets, plane, pane);
        }else
        {
          makeShooting(bullets, plane,pane);
        }
    }
    private void enemymove(Plane player,ArrayList<Emeny> enemies)
    {  
        timeline =new Timeline();
        KeyFrame key = new KeyFrame(Duration.millis(32),eventEnemy->
        {   
            crushDecte(player, pane, enemies);
            boolean canmove =true;
            for(Emeny enemy:enemies)
            {
             if(enemy!=this && enemy.type==2 && enemy.getBoundsInParent().intersects(this.getBoundsInParent())&&enemy.getLayoutY()>this.getLayoutY())
             {
              canmove =false;
             }
            }
            if(this.getLayoutY()<pane.getHeight()/3&& canmove ==true)
           {
            this.setLayoutY(this.getLayoutY()+0.5*speed);
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
    
    private void makeShooting(ArrayList<Bullet> bullets,Plane player,AnchorPane pane)
    {
     shooting =new Timeline(new KeyFrame(Duration.seconds(0.5),event->
     {
        Bullet bullet1 = new EnemyBullet(this, player, pane, bullets);
        Bullet bullet2 = new EnemyBullet(this, player, 2, pane, bullets,'+');
        Bullet bullet3 = new EnemyBullet(this, player, -2, pane, bullets,'+');
     }));
      shooting.setCycleCount(3);
      shooting.setOnFinished(event2->
      {
       paused.play();    
      });
      paused.setOnFinished(event3->
      {if(canrepeat==true){
       shooting.play();
       }
      });
    }

    private void makeshooting2(ArrayList<Bullet> bullets,Plane player,AnchorPane pane)
    {
        shooting =new Timeline(new KeyFrame(Duration.seconds(0.5),event->
     {
        Bullet bullet1 = new EnemyBullet(this, player, pane, bullets);
        Bullet bullet2 = new EnemyBullet(this, player, 2, pane, bullets,'+');
        Bullet bullet3 = new EnemyBullet(this, player, -2, pane, bullets,'+');
        Bullet bullet4 = new EnemyBullet(this, player, -1, pane, bullets,'+');
        Bullet bullet5 = new EnemyBullet(this, player, 1, pane, bullets,'+');
     }));
      shooting.setCycleCount(3);
      shooting.setOnFinished(event2->
      {
       paused.play();    
      });
      paused.setOnFinished(event3->
      {if(canrepeat==true){
       shooting.play();
       }
      });
    }
}
