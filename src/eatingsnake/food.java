package eatingsnake;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class food extends ImageView
{
  private int size;
  private AnchorPane rootPane;
  private Random random =new Random();
  private boolean eatten;
  private Timeline timeline;
  

  public food(Snake head,ArrayList<Snake> snakes, AnchorPane pane)
  {
    rootPane =pane;
    size =20;
    this.setImage(new Image(App.class.getResourceAsStream("/config/pear.png")));
    this.setFitHeight(size);
    this.setFitWidth(size);
    this.setLayoutX(random.nextInt(831));
    this.setLayoutY(random.nextInt(401));
    eatten =false;
    setDetect(head, snakes);
    rootPane.getChildren().add(this);
    timeline.play();
  }

  private void setDetect(Snake head,ArrayList<Snake> snakes)
  {
    timeline =new Timeline(new KeyFrame(Duration.millis(16),event->
    {
      crushDeteced(head, snakes);
    }));
    timeline.setCycleCount(timeline.INDEFINITE);
  } 

  private void crushDeteced(Snake head,ArrayList<Snake> snakes)
  {
    if(head.getBoundsInParent().intersects(this.getBoundsInParent()))
    {
      eatten=true;
      snakebody body =new snakebody( rootPane);
      body.setpreviousSnake(snakes.get(snakes.size()-1),head);
      rootPane.getChildren().add(body);
      snakes.add(body);
    }
  }


  public void resetPosition() 
{
     
    double x = Math.random() * (rootPane.getWidth() - this.getFitWidth());
    double y = Math.random() * (rootPane.getHeight() - this.getFitHeight());
    setLayoutX(x);
    setLayoutY(y);
    eatten =false;
}


  public boolean getstate()
  {
    return eatten;
  }
}
 

