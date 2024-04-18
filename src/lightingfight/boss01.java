package lightingfight;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class boss01 extends Emeny
{
 private int shootingtype;
 private static Image boss =Unit.getBoss1(),fire01=Unit.getFire01(),fire02 =Unit.getFire02();
 public boss01(AnchorPane pane,Plane plane,ArrayList<Emeny> enemies,ArrayList<Bullet> bullets,double difficulty)
 {
  deadImage =Unit.getdead03();
  this.hp= new SimpleIntegerProperty((int)(100*(1+difficulty)));
  this.speed =4;
  this.pane =pane;
  this.point =100;
  this.type=3;
  this.setImage(boss);
  hurtable =false;
  this.setFitWidth(300);
  this.setFitHeight(300);
  this.setLayoutX(pane.getWidth()/2);
  this.setLayoutY(-this.getFitHeight());
  paused =new Timeline(new KeyFrame(Duration.seconds(3)));
  paused.setCycleCount(1);
  movingLine(pane, plane, enemies,bullets);
  enemies.add(this);
  timeline.play();
  if(difficulty<2)
  {
    bossShooting(plane, bullets);
  }else
  {
    bossShooting02(plane, bullets);
  }
  shooting.play();
 

 }

 private void movingLine(AnchorPane pane,Plane plane,ArrayList<Emeny> enemies,ArrayList<Bullet> bullets)
 {
  timeline =new Timeline();
   KeyFrame moveframe01 =new KeyFrame(Duration.millis(32),event->
   {
      this.setLayoutY(this.getLayoutY()+speed);
   });
   
 
   KeyFrame moveFrame02 = (new KeyFrame(Duration.millis(32),event->
   {
    
    this.setLayoutX(this.getLayoutX()+speed);
    if (this.getLayoutX() <= 0 || this.getLayoutX() >= pane.getWidth() - this.getFitWidth()) {
        speed = -speed; 
    }
      crushDecte(plane, pane,enemies);
   }));
   timeline.getKeyFrames().add(moveframe01);
   timeline.setCycleCount(80);   
   timeline.setOnFinished(event->
   {
     
     hurtable=true;
     timeline.getKeyFrames().clear();
    if(this.canrepeat==true)
    {
    this.setImage(fire02);
     timeline.getKeyFrames().add(moveFrame02);
     timeline.play();
    }
   });
 }
 


 private void bossShooting(Plane player,ArrayList<Bullet> bullets)
 { 
  Random random =new Random();

   shooting =new Timeline();
   KeyFrame key1 =new KeyFrame(Duration.seconds(0.3),event->
   {
    for(int i=0;i<3;i++){
    new EnemyBullet2(this, type, player, bullets, pane);
    new EnemyBullet2(this, -type, player, bullets, pane);
   }
 });

 KeyFrame key2 =new KeyFrame(Duration.seconds(0.3),event->{
  double[] lengths = {-2, -1, 1,2,3,-3,4,-4}; 
  for (double length : lengths) {
      new EnemyBullet(this, player, length, pane, bullets,'+');

 
 } });
   shooting.getKeyFrames().add(key1);
   shooting.setCycleCount(6);

   shooting.setOnFinished(event->
   {
     shooting.stop();
     shooting.getKeyFrames().clear();
      paused.play();
   });
   paused.setOnFinished(event->
   
   {
    if(canrepeat==true)
    {
      shootingtype = random.nextInt(2)+1;
      
      if(shootingtype==1)
      {
        this.setImage(fire02);
       shooting.getKeyFrames().add(key1);
      }else if(shootingtype==2)
      {
        this.setImage(fire01);
        shooting.getKeyFrames().add(key2);
      }
      shooting.setCycleCount(6);
      shooting.play();
 }});
   
 }
 


 private void bossShooting02(Plane player,ArrayList<Bullet> bullets)
 { 
  Random random =new Random();

   shooting =new Timeline();
   KeyFrame key1 =new KeyFrame(Duration.seconds(0.3),event->
   {
   
    for(int i=0;i<3;i++){
    new EnemyBullet2(this, type, player, bullets, pane);
    new EnemyBullet2(this, -type, player, bullets, pane);
   }
 });

 KeyFrame key2 =new KeyFrame(Duration.seconds(0.3),event->{
  double[] lengths = {-2,-1.5,1.5,2.5,-2.5,-1, 1,2,3,-3,4,-4}; 
  for (double length : lengths) {
      new EnemyBullet(this, player, length, pane, bullets,'+');

 
 } });

 KeyFrame key3 =new KeyFrame(Duration.seconds(0.6),event->{
  double[] lengths = {-2, -1, 1,2,3,-3,4,-4}; 
  for (double length : lengths) {
      new EnemyBullet2(this, player,(int)length, pane, bullets);

 
 } });
   shooting.getKeyFrames().add(key1);
   shooting.setCycleCount(6);
   shooting.setOnFinished(event->
   {
     
      paused.play();
     
    
   });
   paused.setOnFinished(event->
   {
    if(canrepeat==true)
    {
      shootingtype = random.nextInt(3)+1;
      shooting.getKeyFrames().clear();
      if(shootingtype==1)
      {
        this.setImage(fire02);
       shooting.getKeyFrames().add(key1);
       shooting.setCycleCount(6);
      }else if(shootingtype==2)
      {
        this.setImage(fire01);
        shooting.getKeyFrames().add(key2);
        shooting.setCycleCount(6);
      }else if(shootingtype==3)
      {
        this.setImage(fire01);
        shooting.getKeyFrames().add(key3);
        shooting.setCycleCount(3);
      }
    
      shooting.play();
 }});
   
 }


}
