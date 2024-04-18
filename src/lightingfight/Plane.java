package lightingfight;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.util.Duration;

public class Plane extends ImageView{   
    private IntegerProperty hp = new SimpleIntegerProperty(3);
    private double sizex,sizey,speed;
    private Unit unit =new Unit();
    private IntegerProperty bullettype =new SimpleIntegerProperty(1);
    public  boolean gotop=false, gobot=false,goleft=false, goright=false,isFire=false;
    protected Timeline shooting,flying,flashing;
    private AnchorPane pane;
    protected boolean hurtable;
    private int maxHP,maxType;
    private HpView sethp;


   
   
public Plane(AnchorPane pane, AnchorPane uilayer,StackPane Root)
{   
    
    sethp =new HpView(uilayer, hp.get(),maxHP,Root);
    this.setImage(unit.getPlane());
    this.setLayoutX(300);
    this.setLayoutY(600);
    this.toFront();
    this.setFitHeight(60);
    this.setFitWidth(60);
    sizex=30;
    sizey=30;
    this.speed = 8;
    maxHP =5;
    maxType=3;
    this.pane=pane;
    hurtable =true;
    hurtflashing();
    pane.getChildren().add(this);
}
  

  public IntegerProperty gethp()
  {
   return hp;
  }
  
  
  //change the bullet type
  public void setBulletType(){
    if(bullettype.get()<maxType)
    {
    int changed =bullettype.get()+1;
     bullettype.set(changed);
    }
   
  }

  
  //use to import the plane elemnet
  public void start(ArrayList<Emeny> emenis,IntegerProperty score,ArrayList<Bullet> bullets)
  {
    initializeshooting(emenis,score,bullets);
    initializeflying();
    addListener(emenis,score,bullets);
  }

//set up plane control
  private void initializeflying(){
    flying =new Timeline(planecontrol());
    flying.setCycleCount(Timeline.INDEFINITE);
    flying.play();
}

 // set up shooting control
 private void initializeshooting(ArrayList<Emeny> emenis,IntegerProperty  score,ArrayList<Bullet> bullets)
 {
    shooting = new Timeline();
    shooting.setCycleCount(Timeline.INDEFINITE);
    updateShootingMode(emenis,score,bullets); 

 }
   
 
 private void addListener(ArrayList<Emeny> emenis,IntegerProperty  score,ArrayList<Bullet> bullets)
 {
  bullettype.addListener((obs,odv,nev)->{   
    updateShootingMode(emenis,score,bullets);
 });
 }

 //change plane hp
  public void gethurt()
  {
   if(hurtable==true){
    sethp.decreaseHp();
    int changing =hp.get();
   changing -=1;
   hp.set(changing);
   if(bullettype.get()>1)
   {
    bullettype = new SimpleIntegerProperty(bullettype.get()-1);
   }
   if(hp.get()>0)
   {
    hurtable =false;
    flashing.play();
    flashing.setOnFinished(event->
    {
     this.setVisible(true);
     hurtable =true;
    });
   }else
   {
    pane.getChildren().remove(this);
    this.shooting.stop();
    this.flying.stop();
    this.flashing.stop();
    
   }}
  }

   

  //use to update shooting moulde
   private void updateShootingMode(ArrayList<Emeny> emenis,IntegerProperty score,ArrayList<Bullet> bullets) 
  {
    if (shooting != null) {
        shooting.stop();
        shooting.getKeyFrames().clear();

        switch (bullettype.get()) 
        {
            case 1:
                shooting.getKeyFrames().add(shootingfire(emenis,score,bullets));
                break;
            case 2:
                shooting.getKeyFrames().add(shootingfire2(emenis,score,bullets));
                break;
            case 3:
                shooting.getKeyFrames().add(shootingfire3(emenis,score,bullets));
                break; 
                          
        }
            shooting.play();
    }
}

 
 //control logic
 private KeyFrame planecontrol(){
      return new KeyFrame(Duration.millis(16), event2->{
    if(gotop==true){
        this.setLayoutY(Math.max(0, this.getLayoutY()-speed));
    }
    if(gobot==true){
        this.setLayoutY(Math.min(this.getLayoutY()+speed,pane.getHeight()-sizey));
    }
     if(goleft==true){
        this.setLayoutX(Math.max(0, this.getLayoutX()-speed));
    }
    if(goright==true){
        this.setLayoutX(Math.min(this.getLayoutX() + speed, pane.getWidth() - sizex));
        
    }
});

   }

  // shooting type 1
   private KeyFrame shootingfire(ArrayList<Emeny> emenis,IntegerProperty score ,ArrayList<Bullet> bullets){
  
     return new KeyFrame(Duration.millis(200), event->{
        if(isFire==true){
   
            Bullet bullet =new Selfbullet(this,pane,emenis,score,bullets);
            pane.getChildren().add(bullet);
            bullets.add(bullet);
            bullet.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet.timeline.play();
    }});}

 //shooting type 2
    private KeyFrame shootingfire2(ArrayList<Emeny> emenis,IntegerProperty  score,ArrayList<Bullet> bullets){
    return new KeyFrame(Duration.millis(200), event->{
        if(isFire==true){
   
            Bullet bullet3 =new Selfbullet(this,10,pane,emenis,score,bullets);
            Bullet bullet2 =new Selfbullet(this,-10,pane,emenis,score,bullets);
            Bullet bullet1 =new Selfbullet(this,pane,emenis,score,bullets);
            pane.getChildren().add(bullet1);
            pane.getChildren().add(bullet2);
            pane.getChildren().add(bullet3);
            bullets.add(bullet1);
            bullets.add(bullet2);
            bullets.add(bullet3);
            bullet1.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet2.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet3.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet1.timeline.play();
            bullet2.timeline.play();
            bullet3.timeline.play();
    }});
   }

   private KeyFrame shootingfire3(ArrayList<Emeny> emenis,IntegerProperty  score,ArrayList<Bullet> bullets)
   {
    return new KeyFrame(Duration.millis(200), event->{
        if(isFire==true){
            Bullet bullet3 =new Selfbullet(this,10,pane,emenis,score,bullets);
            Bullet bullet2 =new Selfbullet(this,-10,pane,emenis,score,bullets);
            Bullet bullet1 =new Selfbullet(this,pane,emenis,score,bullets);
            Bullet bullet4=new Selfbullet(this,-20,pane,emenis,score,bullets);
            Bullet bullet5=new Selfbullet(this,-20,pane,emenis,score,bullets);
            pane.getChildren().add(bullet1);
            pane.getChildren().add(bullet2);
            pane.getChildren().add(bullet3);
            pane.getChildren().add(bullet4);
            pane.getChildren().add(bullet5);
            bullets.add(bullet1);
            bullets.add(bullet2);
            bullets.add(bullet3);
            bullets.add(bullet4);
            bullets.add(bullet5);
            bullet1.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet2.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet3.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet4.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet5.timeline.setCycleCount(Timeline.INDEFINITE);
            bullet1.timeline.play();
            bullet2.timeline.play();
            bullet3.timeline.play();
            bullet4.timeline.play();
            bullet5.timeline.play();
    }});

   }

   
   private void hurtflashing(){
    {
     flashing =new Timeline(new KeyFrame(Duration.seconds(0.2),event->
     {
        this.setVisible(!this.isVisible());
     }));
     flashing.setCycleCount(5*2);
    }
}


 public void healing()
 {
    if(hp.get()<maxHP){
    sethp.increaseHp();
    int hpchange = hp.get();
    hpchange =hpchange+1;
    hp.set(hpchange);
 }}
}


