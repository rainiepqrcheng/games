package lightingfight;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class EnemyBullet2 extends Bullet
{   private boolean horizontalMoveCompleted = false;
    private int numbers,startpoint;//poisition of each follow bullet
    private static Image eb02 = Unit.getB_Bullet01();
    private static Image eb03 =Unit.getB_Bullet02();
    private static Image blast =Unit.getself_bullet_blast();
    public EnemyBullet2(Emeny enemy,int numbers,Plane player,ArrayList<Bullet> bullets,AnchorPane pane)
    {

     
     speed =8;
    
     this.setImage(eb02);
     this.setFitHeight(45);
     this.setFitWidth(45);
     this.setLayoutY(enemy.getLayoutY()+enemy.getFitHeight()/2);
     this.numbers =numbers;
     if(numbers<0)
     {
      this.setLayoutX(enemy.getLayoutX()-this.getFitWidth());
     }else if(numbers>0)
     {
      this.setLayoutX(enemy.getLayoutX()+enemy.getFitWidth());
     }
     identify ="enemy";
     bullets.add(this);
     b_bullet01(player, bullets, pane);
     pane.getChildren().add(this);
     this.timeline.play();
    }
 

    public EnemyBullet2(Emeny enemy,Plane player,int length,AnchorPane pane,ArrayList<Bullet> bullets)
{
    speed=4;
    this.setImage(eb03);
    this.setFitHeight(30);
    this.setFitWidth(30);
    this.setLayoutY(enemy.getLayoutY()+enemy.getFitHeight());
    this.setLayoutX(enemy.getLayoutX() + enemy.getFitWidth() / 2-10);
    identify ="enemy";
    b_bullet02(enemy, player, bullets, pane, length);
    pane.getChildren().add(this);
    bullets.add(this);
    this.timeline.play();
    bullets.add(this);
    timeline.play();
}


private void b_bullet01(Plane player ,ArrayList<Bullet> bullets,AnchorPane pane)
{
 
  double horizontalMoveDistance = this.getFitWidth() * Math.abs(numbers) - this.getFitWidth();
  double horizontalTarget = this.getLayoutX() + (numbers > 0 ? horizontalMoveDistance : -horizontalMoveDistance);


  this.timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
      if (!horizontalMoveCompleted) {
          if ((numbers > 0 && this.getLayoutX() < horizontalTarget) || (numbers < 0 && this.getLayoutX() > horizontalTarget)) {
              this.setLayoutX(this.getLayoutX() + (numbers > 0 ? speed / 8 : -speed / 8));
          } else {

              horizontalMoveCompleted = true;
          }
      } else {
        
        double deltaX = (player.getLayoutX()+player.getFitWidth()/2)- (this.getLayoutX()+this.getFitWidth()/2);
        double deltaY = (this.getLayoutY()+this.getFitHeight()/2)-(player.getLayoutY()+player.getFitHeight()/2);
        double length = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));     
        double actualx= deltaX / length;
          this.setLayoutY(this.getLayoutY() + speed);
          this.setLayoutX(this.getLayoutX() + (actualx * speed/2));
          this.crushDetect(player, bullets, pane);
      
    }
      if (this.getLayoutY() > pane.getHeight()) {
          pane.getChildren().remove(this);
          timeline.stop();
          bullets.remove(this);
      }

  }));
  timeline.setCycleCount(Timeline.INDEFINITE);

} 


private void b_bullet02(Emeny enemy,Plane player, ArrayList<Bullet> bullets, AnchorPane pane,int length)
{   
    timeline =new Timeline(new KeyFrame(Duration.millis(16),event->
    {  
      startpoint+= speed;
      if(startpoint<200)
      {
       
        this.setLayoutY(this.getLayoutY()+speed);
        this.setLayoutX(this.getLayoutX()+speed/length);
        crushDetect(player, bullets, pane);
      }else
      {
        this.setImage(blast);
        pane.getChildren().remove(this);
        bullets.remove(this);
        double[] lengths = {-2, -1, 1,2,4,-4}; 
        for (double length01 : lengths) 
          {
             new EnemyBullet(this, player, length01, pane, bullets,'+');
          }  
          for (double length01 : lengths) 
          {
             new EnemyBullet(this, player, length01, pane, bullets,'-');
          }  
          new EnemyBullet(enemy, player, 1, pane, bullets,'0');
          new EnemyBullet(enemy, player, -1, pane, bullets,'0');
          this.timeline.stop();
      
     
      }
    }));
    timeline.setCycleCount(Timeline.INDEFINITE);

}






private void crushDetect(Plane player, ArrayList<Bullet> bullets, AnchorPane pane)
  {  double deltaX = (player.getLayoutX()+player.getFitWidth()/2)- (this.getLayoutX()+this.getFitWidth()/2);
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
