package lightingfight;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class EnemyBullet extends Bullet
  {
    private static Image eb01 = Unit.gete_bullet01();
    public EnemyBullet(Emeny enemy,Plane player,AnchorPane pane,ArrayList<Bullet> bullets){
        speed = 4;
        this.setImage(eb01);
        this.setFitHeight(30);
        this.setFitWidth(30);
        this.setLayoutY(enemy.getLayoutY()+enemy.getFitHeight());
        this.setLayoutX(enemy.getLayoutX() + enemy.getFitWidth() / 2-10);
        identify ="enemy";
        this.enemybulletanmination01(pane, player, bullets);
        pane.getChildren().add(this);
        bullets.add(this);
        this.timeline.play();
      }
     
public EnemyBullet(Emeny enemy,Plane player,double length,AnchorPane pane,ArrayList<Bullet> bullets,char type)
{
    speed=4;
    this.setImage(eb01);
    this.setFitHeight(30);
    this.setFitWidth(30);
    this.setLayoutY(enemy.getLayoutY()+enemy.getFitHeight());
    this.setLayoutX(enemy.getLayoutX() + enemy.getFitWidth() / 2-10);
    identify ="enemy";
    this.enemybulletanmination02(pane, player, bullets, length,type);
    pane.getChildren().add(this);
    bullets.add(this);
    this.timeline.play();
    bullets.add(this);
}
  
public EnemyBullet(Bullet mother,Plane player,double length,AnchorPane pane,ArrayList<Bullet> bullets,char type)
{
    speed=4;
    this.setImage(eb01);
    this.setFitHeight(30);
    this.setFitWidth(30);
    this.setLayoutY(mother.getLayoutX());
    this.setLayoutX(mother.getLayoutY());
    identify ="enemy";
    this.enemybulletanmination02(pane, player, bullets, length,type);
    pane.getChildren().add(this);
    bullets.add(this);
    this.timeline.play();
    bullets.add(this);
}
  



private void enemybulletanmination01(AnchorPane pane, Plane player,ArrayList<Bullet> bullets)
 {     
  timeline =new Timeline(new KeyFrame(Duration.millis(16),evenbullet->
  { 
    crushDetect(player, bullets, pane);
    this.setLayoutY(this.getLayoutY()+speed);
    if(this.getLayoutY()<-pane.getHeight())
    {
        bullets.remove(this);
        pane.getChildren().remove(this);
        timeline.stop();
    }
    
  }));
  timeline.setCycleCount(Timeline.INDEFINITE);
 } 
    
 private void enemybulletanmination02(AnchorPane pane, Plane player,ArrayList<Bullet> bullets,double length,char type)
 {     
   timeline =new Timeline(new KeyFrame(Duration.millis(16),evenbullet->
  {
    crushDetect(player, bullets, pane);
    if(type=='-')
    {
      this.setLayoutY(this.getLayoutY()-speed);
    }
    if(type=='+')
    {
      this.setLayoutY(this.getLayoutY()+speed);
    }
    this.setLayoutX(this.getLayoutX()+speed/length);
    
    if(this.getLayoutY()>pane.getHeight()||this.getLayoutX()>pane.getHeight()||this.getLayoutX()<0)
    {
        pane.getChildren().remove(this);
        timeline.stop();
        bullets.remove(this);
    }
    
  }));
  
  timeline.setCycleCount(Timeline.INDEFINITE);
}
 
 
 private void crushDetect(Plane player, ArrayList<Bullet> bullets, AnchorPane pane)
  {
    double deltaX = (player.getLayoutX()+player.getFitWidth()/2)- (this.getLayoutX()+this.getFitWidth()/2);
    double deltaY = (this.getLayoutY()+this.getFitHeight()/2)-(player.getLayoutY()+player.getFitHeight()/2);
    double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

     if(length<=30)
     {
      this.timeline.stop();
      pane.getChildren().remove(this);
      bullets.remove(this);
      player.gethurt();
     }
    }

}
