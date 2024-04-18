package lightingfight;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Bounds;

public class Selfbullet extends Bullet{
  private static Image sbullet =Unit.getbullet1();
  private static Image blast =Unit.getself_bullet_blast();
  private int damge =1;

//initial bullet for player
public Selfbullet(Plane player,AnchorPane pane,ArrayList<Emeny> enemies,IntegerProperty score,ArrayList<Bullet> bullets)
{
    speed = 5;
    this.setImage(sbullet);
    this.setFitHeight(50);
    this.setFitWidth(50);
    this.setLayoutY(player.getLayoutY()-this.getFitHeight());
    this.setLayoutX(player.getLayoutX() + player.getFitWidth() / 2 - this.getFitWidth() / 2);
    identify ="player";
    this.selfbulletanmination(pane, enemies,score,bullets);
}
//extra bullet for upgrade
public Selfbullet(Plane player,double length,AnchorPane pane,ArrayList<Emeny> enemies,IntegerProperty score,ArrayList<Bullet> bullets)
{
    speed=5;
    this.setImage(sbullet);
    this.setFitHeight(50);
    this.setFitWidth(50);
    this.setLayoutY(player.getLayoutY()-this.getFitHeight());
    this.setLayoutX(player.getLayoutX() + player.getFitWidth() / 2 - this.getFitWidth() / 2+length);
    identify ="player";
    this.selfbulletanmination(pane, enemies,score,bullets);
}

// selfplane's bullet animetion
  
private void selfbulletanmination(AnchorPane pane, ArrayList<Emeny> enemies,IntegerProperty score,ArrayList<Bullet> bullets)
{     
   timeline =new Timeline();
  KeyFrame key = new KeyFrame(Duration.millis(16),evenbullet->
  { 
  
      this.crushDetcet(enemies, pane,score,bullets);   
    this.setLayoutY(this.getLayoutY()-speed);
    if(this.getLayoutY()<-this.getFitHeight())
    {
        pane.getChildren().remove(this);
        timeline.stop();
    }
    
  });
    timeline.getKeyFrames().add(key);
}

// this this the function achieve kill enemy
private void crushDetcet(ArrayList<Emeny> enemies,AnchorPane pane,IntegerProperty score, ArrayList<Bullet> bullets)
{
  Bounds boundbullet = this.getBoundsInParent();
  for(Emeny enemy:enemies)
  { 
    
    if(boundbullet.intersects(enemy.getBoundsInParent()))
    {
    if(enemy.hurtable)
    {
      this.timeline.stop();
      bullets.remove(this);
      this.setImage(blast);
      PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
      pause.play();
      pause.setOnFinished(event->
      {
       enemy.sethp(damge);
       pane.getChildren().remove(this);
      });
      enemy.handleDeath(score, enemies);
      break;
    }
  }
 }
}
 }

