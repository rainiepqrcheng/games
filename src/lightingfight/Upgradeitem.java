package lightingfight;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.geometry.Bounds;


public class Upgradeitem extends ImageView{
private  double speedx ,speedy;
private int type;
private Unit unit =new Unit();
protected Timeline timeline;
public Upgradeitem(Plane player,AnchorPane pane,ArrayList<Upgradeitem> items,int type){
    this.setFitHeight(40);
    this.setFitWidth(40);
    this.setImage(unit.getupgrade1());
    double setx =Math.random()*620;
    this.setLayoutX(setx);
    this.setLayoutY(-this.getFitHeight());
    speedx = Math.random()*2+1;
    speedy =Math.random()*2+1;
    this.type =type;
    if(type==1)
   {
    this.setImage(unit.getupgrade1());
   }else if(type==2)
   {
    this.setImage(unit.gethealing());
   }
    this.packanime(pane, player,items);
}




public void packanime(AnchorPane pane,Plane plane, ArrayList<Upgradeitem> items){
    timeline =new Timeline();
    KeyFrame key =new KeyFrame(Duration.millis(16),event->{
        this.setLayoutX(this.getLayoutX() + speedx);
        this.setLayoutY(this.getLayoutY() + speedy);
        this.crushDetect(plane,pane,items);      
            if (this.getLayoutX() <= 0 || this.getLayoutX() >= pane.getWidth() - this.getFitWidth()) {
                speedx = -speedx; 
            }   
            
            if(this.getLayoutY()>pane.getHeight()){
                    pane.getChildren().remove(this);
                    timeline.stop();
                    items.remove(this);
                }
    });
    timeline.getKeyFrames().add(key);
    timeline.setCycleCount(Timeline.INDEFINITE);
}




private void crushDetect(Plane plane,AnchorPane pane,  ArrayList<Upgradeitem> items)
{ 
    Bounds planebounds = plane.getBoundsInParent();
    Bounds upgradebounds = this.getBoundsInParent();
    if(planebounds.intersects(upgradebounds)){ 
       if(type==1){
            plane.setBulletType();
            
       }else if (type==2)
       {
        plane.healing();
       }
       items.remove(this);
       pane.getChildren().remove(this);
        timeline.stop();
        items.remove(this);
    }

}



}
