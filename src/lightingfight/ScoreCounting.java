package lightingfight;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ScoreCounting extends Label
{
   private Timeline timeline;
   
   public ScoreCounting(AnchorPane pane)
   {
     this.setLayoutX(10);
     this.setLayoutY(10);
     this.setText("Score :"+" "+0);
     pane.getChildren().add(this);
     this.setFont(new Font("Segoe UI",40));
   }

}