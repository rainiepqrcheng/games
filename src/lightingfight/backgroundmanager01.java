package lightingfight;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Random;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class backgroundmanager01 {
   @FXML
   private ImageView background1;
   @FXML
   private ImageView background2;
   @FXML
   private AnchorPane pane;
   private Timeline backgroundmvoing,Emenymoving01,Emenymoving02;

   private int scorechanging,gamepassing,number,scorechanging2,gamepassing2;
   private IntegerProperty progressChanging;
   private Random random =new Random();
   private boolean bosscome=false,stopped=false;
   private ChangeListener<Number> gamepass,bosshp;
   private DoubleProperty difficulty;

public backgroundmanager01( AnchorPane pane,Plane plane, ArrayList<Emeny> emenies,StackPane pane01) 
{
    this.pane = pane;

    background1 =new ImageView(Unit.getbackground01());
    background2 =new ImageView(Unit.getbackground01());
    background1.fitHeightProperty().bind(pane01.heightProperty());
    background2.fitHeightProperty().bind(pane01.heightProperty());
    background1.setFitWidth(860);
    background2.setFitWidth(860);
    background1.setLayoutX(0);
    background1.setLayoutY(0);
    background2.setLayoutX(0);
    background2.setLayoutY(-background1.getFitHeight());
    initializeBackgroundmoving();
    difficulty = new SimpleDoubleProperty(1);
    pane.getChildren().add(background1);
    pane.getChildren().add(background2);
    progressChanging =new SimpleIntegerProperty(0);
    
}

public void pauseall()
{
  backgroundmvoing.pause();
  Emenymoving01.pause();
  Emenymoving02.pause();
  
}

public void resumeAll()
{

  backgroundmvoing.play();
  if(bosscome==false){
  Emenymoving01.play();
  Emenymoving02.play();
  }
}


public void stopAll(ArrayList<Emeny> emenies,ArrayList<Upgradeitem> items, IntegerProperty score, ArrayList<Bullet> bullets)
{   
   
    backgroundmvoing.stop();
    Emenymoving01.stop();
    for(Emeny Emeny:emenies)
    {
     Emeny.timeline.stop();
     Emeny.shooting.stop();
     Emeny.canrepeat =false;
  
     pane.getChildren().remove(Emeny);
    }
    emenies.clear();
    for(Upgradeitem up:items)
    {
     up.timeline.stop();
     pane.getChildren().remove(up);
    }
     items.clear();
     for(Bullet bullet:bullets)
     {
      bullet.timeline.stop();
      pane.getChildren().remove(bullet);
     }
     bullets.clear();
     Emenymoving02.stop();
     gamepass =null;
}

public void start(ArrayList<Emeny> emenies,ArrayList<Upgradeitem> items, IntegerProperty score,ArrayList<Bullet> bullets,ScoreCounting counter,Plane player)
{  
    
    initializeEmeny01(emenies,player,bullets);
    initializeEmeny02(emenies, bullets,player);
    gamepass = ((obs,odv,nwv)->
    { 
      
      gamepassing2 += nwv.intValue()-odv.intValue();
      if(gamepassing2<=1000){
        gamepassing += nwv.intValue()-odv.intValue();
     }else if(gamepassing2>2000)
      {
        if(stopped==false)
        {
          Emenymoving01.stop();
          Emenymoving02.stop();
          stopped =true;
         
        }else if(emenies.isEmpty()&&bosscome==false){
          bossCreate(player, emenies, bullets);
        }
      }
    });
    score.addListener((obs,odv,nwv)->
    {
     scorechanging += nwv.intValue()-odv.intValue();
     scorechanging2 += nwv.intValue()-odv.intValue();
     counter.setText("Score :"+" "+nwv.intValue());
    if(scorechanging2>=100)
    {
      Upgradeitem pack =new Upgradeitem(player, pane, items, 2);
      pack.timeline.play();
      items.add(pack);
      pane.getChildren().add(pack);
      scorechanging2 =0;
    }
    
     if(scorechanging>=300) 
     {
      Upgradeitem pack =new Upgradeitem(player, pane, items, 1);
      pack.timeline.play();
      items.add(pack);
      pane.getChildren().add(pack);
      scorechanging =0;
     }
    });

    progressChanging.addListener(gamepass);

}



//Timeline used for background anmie
private void initializeBackgroundmoving()
{
    backgroundmvoing= new Timeline(new KeyFrame(Duration.millis(16), event -> 
    {   
        progressChanging.set(progressChanging.get()+1);
        double move1 = background1.getLayoutY()+10;
        double move2 = background2.getLayoutY()+10;
//when one pictur totally get out from the anchorpane,it will be redeploy the otherone will immidiately full up  
       if (move1 > pane.getHeight()) {
          move1 = -background1.getFitHeight();
          move2 = 0;
         }

       if (move2 > pane.getHeight()) {
        move2 = -background2.getFitHeight();
        move1 = 0;
}

        background1.setLayoutY(move1);
        background2.setLayoutY(move2);
    }));
    backgroundmvoing.setCycleCount(Timeline.INDEFINITE);
   backgroundmvoing.play();
}


private void initializeEmeny01 (ArrayList<Emeny> emenies,Plane player,ArrayList<Bullet> bullets)
{
    Emenymoving01 =new Timeline(new KeyFrame(Duration.seconds((random.nextDouble()+0.5)/difficulty.get()),eventEmeny->
     {
   
   
        Emeny main = new Emeny01(pane,player,emenies,difficulty.get());
        Emeny side1 =new Emeny01(main, 1,player,emenies,difficulty.get());
        Emeny side2 =new Emeny01(main,-1,player,emenies,difficulty.get());
        emenies.add(main);
        emenies.add(side1);
        emenies.add(side2);
        pane.getChildren().add(main);
        pane.getChildren().add(side1);
        pane.getChildren().add(side2);
        main.timeline.play();
        side1.timeline.play();
        side2.timeline.play();
        if(difficulty.get()>1.5)
        {
          Emeny side3 =new Emeny01(side1, 1,player,emenies,difficulty.get());
          Emeny side4 =new Emeny01(side2, -1,player,emenies,difficulty.get());
          emenies.add(side3);
          emenies.add(side4);
          pane.getChildren().add(side3);
          pane.getChildren().add(side4);
          side3.timeline.play();
          side4.timeline.play();
        }
      }));
   Emenymoving01.setCycleCount(Timeline.INDEFINITE);
   Emenymoving01.play(); 
   }


   private void initializeEmeny02(ArrayList<Emeny> emenies,ArrayList<Bullet> bullets,Plane player)
   {
    
    Emenymoving02=new Timeline( new KeyFrame(Duration.seconds(random.nextInt(1)+8),event->
    {
        
        for(Emeny emeny:emenies)
        {
          if(emeny.type==2)
          {
            number+=1;
          }
        }
        if(number<(4*difficulty.get())){
         Emeny emeny =new Emeny02(pane, emenies, player,bullets,difficulty.get());
         emenies.add(emeny);
         pane.getChildren().add(emeny);
         emeny.timeline.play();
         emeny.shooting.play();
        System.out.println("created");
       
        }
        
        
    }));
     Emenymoving02.setCycleCount(Timeline.INDEFINITE);
     Emenymoving02.play(); 
   }

   private void bossCreate(Plane player, ArrayList<Emeny>emenies,ArrayList<Bullet> bullets)
   {
    Emeny boss =new boss01(pane, player, emenies, bullets,difficulty.get());
    pane.getChildren().add(boss);
    bosscome=true;
    bosshp=((obs1,odv1,nwv1)->
    {
     if(nwv1.intValue()<0)
     {
      double changed =difficulty.get()+0.5;
      difficulty.set(changed);
      bosscome =false;
      stopped=false;
      Emenymoving01.play();
      gamepassing2=0;
      Emenymoving02.play();
      boss.hp.removeListener(bosshp);
     }
    });
    boss.hp.addListener(bosshp);
   }
  





  }
