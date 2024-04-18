package lightingfight;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;


public class level01controller {
    @FXML
    private StackPane root;
    @FXML
    private AnchorPane pane01,uiLayer;
    @FXML
    private ImageView pause;
    
    private Plane selfPlane;
    private backgroundmanager01 control01;
    private ArrayList<Emeny> enemies = new ArrayList<Emeny>();
    private ArrayList<Bullet> bullets =new ArrayList<Bullet>();
    private ArrayList<Upgradeitem> items = new ArrayList<Upgradeitem>();
    private IntegerProperty score=new SimpleIntegerProperty(0);; 
    private ScoreCounting counter;
    private ChangeListener<Number> endGame;
    private Image toLeft, toright,planeself;
    
  @FXML
private void initialize()
{  
    
    Platform.runLater(()->
  { 
     setInitial();
     pause.setImage(Unit.getpause());
     toLeft =Unit.gettoleft();
     toright =Unit.gettoright();
     planeself =Unit.getPlane();
     counter =new ScoreCounting(uiLayer);
     selfPlane= new Plane(pane01,uiLayer,root);
     control01 =new backgroundmanager01(pane01,selfPlane,enemies,root);
     control01.start(enemies,items,score,bullets,counter,selfPlane);
     selfPlane.start(enemies,score,bullets);  
     selfPlane.toFront(); 
     root.requestFocus();

    messagePass.passMessage().getstate().addListener((obs,odv,nwv)->
  {
    if(nwv.booleanValue()==true)
    {
      resumeGame();
      root.requestFocus();
    }
  });
    endGame = ((obs,odv,nwv)->
    {
      if(nwv.intValue()<=0)
      {
      control01.stopAll(enemies, items, score,bullets);
      root.getChildren().clear();
        try
      {
        scoretransfer.getunit().setscore(score.get());
        App.setRoot("endpage");
      }catch(IOException io)
      {
      }
      selfPlane.gethp().removeListener(endGame);
      }
    });
    selfPlane.gethp().addListener(endGame);
    });    
      
    
}
    
    @FXML

    private void startmoving(KeyEvent event){
        
        switch (event.getCode()) {
                case W:selfPlane.gotop =true;               
                    break;
                case A:selfPlane.goleft =true;
                     selfPlane.setImage(toLeft);  
                    break;
                case S:selfPlane.gobot =true;
                    break;
                case D:selfPlane.goright =true;
                     selfPlane.setImage(toright);
                       break;
                case SPACE:selfPlane.isFire =true;
                         break;
                default:
                    break;
            }
      }
    @FXML 
    private void stopmoving(KeyEvent event){
        switch (event.getCode()) {
            case W:selfPlane.gotop =false; 
                        
                break;
            case A:selfPlane.goleft =false;
                   selfPlane.setImage(planeself);
                break;
            case S:selfPlane.gobot =false;
                break;
            case D:selfPlane.goright =false;
                   selfPlane.setImage(planeself);
                   break;
            case SPACE :selfPlane.isFire =false;
                        break;
            default:
                break;
        }
  }
    @FXML
   private void click01() throws IOException
   {if(messagePass.passMessage().getstate().get()==true){
     pasuegame();
     selfPlane.isFire=false;
     Stage stage =new Stage();
     stage.initOwner(App.getprimeStage());
     Scene scene =new Scene(FXMLLoader.load(level01controller.class.getResource("/config/fight/PauseMenu.fxml")));
     scene.setFill(Color.TRANSPARENT);
     stage.setScene(scene);
     messagePass.passMessage().changestate(false);
     stage.initStyle(StageStyle.TRANSPARENT);
     scene.getRoot().requestFocus();
     stage.show();}
   }

     private void pasuegame()
     {
      for(Emeny emeny:enemies)
      { 
        emeny.timeline.pause();
        if(emeny.type!=1){
        if(emeny.paused.getStatus()==Animation.Status.RUNNING)
        {
          emeny.paused.pause();
          
        }else if(emeny.shooting.getStatus()==Animation.Status.RUNNING)
        {
          emeny.shooting.pause();
          
        }}
   
      }
      for(Bullet bullet:bullets)
      {
        bullet.timeline.pause();  
      }
      for(Upgradeitem up: items)
      {
        up.timeline.pause();  
      }
       selfPlane.flying.pause();
       selfPlane.shooting.pause();
       control01.pauseall();
     }




     private void resumeGame() {
       
        for (Emeny enemy: enemies) {
           
            enemy.timeline.play();
           if(enemy.type!=1)
          {
            if(enemy.paused.getStatus()==Animation.Status.PAUSED)
           {
            enemy.paused.play();
            enemy.canrepeat =true;
           }else if(enemy.shooting.getStatus()==Animation.Status.PAUSED)
           {
            enemy.shooting.play();
           }
          }
         
        }
        for (Bullet bullet : bullets) {
            bullet.timeline.play();
        }
        for (Upgradeitem item : items) {
            item.timeline.play();
        }
        selfPlane.flying.play();
        selfPlane.shooting.play();
        control01.resumeAll();
      }

    private void setInitial()
    {
      root.prefHeightProperty().bind(root.getScene().heightProperty());
      root.prefWidthProperty().bind(root.getScene().widthProperty());
      pane01.prefHeightProperty().bind(root.heightProperty());
      pane01.prefWidthProperty().bind(root.widthProperty());
      uiLayer.prefHeightProperty().bind(root.heightProperty());
      uiLayer.prefWidthProperty().bind(root.widthProperty());
   

    }





    }