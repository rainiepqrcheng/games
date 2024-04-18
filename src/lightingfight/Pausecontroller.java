package lightingfight;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Pausecontroller
{
    @FXML
    private ImageView pause; 
    @FXML
    private AnchorPane pane02;
    @FXML
    private void initialize() 
    {
      pause.setImage(Unit.getrestart());  
    
    }
    
    @FXML
    private void click() {
       
        messagePass.passMessage().changestate(true);

        
        Stage stage = (Stage) pause.getScene().getWindow();
        stage.close();
    }
}
