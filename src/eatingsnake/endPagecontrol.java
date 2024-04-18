package eatingsnake;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class endPagecontrol {
    @FXML
    private AnchorPane endpane;
    @FXML
    private Label Score_showing;
    @FXML
    private Button back;
    private int finalscore;
    @FXML
    private void initialize()
    { 
      

    	
        back.setLayoutX(App.getprimeStage().getWidth()/2-back.getPrefWidth()/2-50);
        back.setLayoutY(App.getprimeStage().getHeight()/2-back.getPrefHeight());       
        Score_showing.setLayoutX(App.getprimeStage().getWidth()/2-Score_showing.getPrefWidth()/2-50);
        Score_showing.setLayoutY(back.getLayoutY()-100);
        finalscore = scoretransfer.getunit().getscore();
        Score_showing.setText("Final Score: " + finalscore);


     
        
    }
    
    
    @FXML
    private  void click()throws IOException
    {
      try {

        App.setRoot("eatinggame");
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }

}
